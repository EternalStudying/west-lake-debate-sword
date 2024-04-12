package com.swyxl.manager.service.ServiceImpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.feign.user.UserFeignClient;
import com.swyxl.manager.mapper.LiveMapper;
import com.swyxl.manager.mapper.LiveUserKickingMapper;
import com.swyxl.manager.properties.LiveProperty;
import com.swyxl.manager.properties.TencentLiveProperty;
import com.swyxl.manager.service.LiveService;
import com.swyxl.model.constant.TypeConstant;
import com.swyxl.model.dto.service.active.KickingDto;
import com.swyxl.model.dto.service.active.LiveDto;
import com.swyxl.model.dto.service.manage.LiveEditDto;
import com.swyxl.model.dto.service.manage.LiveQueryDto;
import com.swyxl.model.entity.service.manager.*;
import com.swyxl.model.entity.service.user.UserInfo;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.vo.service.manager.LiveUserVo;
import com.swyxl.utils.AuthContextUtils;
import com.swyxl.utils.HttpClientUtils;
import com.swyxl.utils.LiveUtils;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.CreateLiveRecordTemplateRequest;
import com.tencentcloudapi.live.v20180801.models.CreateLiveRecordTemplateResponse;
import com.tencentcloudapi.live.v20180801.models.RecordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class LiveServiceImpl implements LiveService {

    @Autowired
    private LiveProperty liveProperty;
    @Autowired
    private TencentLiveProperty tencentLiveProperty;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private LiveMapper liveMapper;
    @Autowired
    private LiveUserKickingMapper liveUserKickingMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;

    // http://api.sd-rtn.com/dev/v1/channel/user/{appid}/{channelName}/{hosts_only}
    @Override
    public LiveUserVo getUser(String channelName) {
        // 创建 authorization header
        String authorizationHeader = getAuthorizationHeader();
        // 获取 appId
        String appId = liveProperty.getAppId();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authorizationHeader);
        headers.put("Content-Type", "application/json");
        String result = HttpClientUtils.sendGet("http://api.sd-rtn.com/dev/v1/channel/user/" + appId + "/" + channelName, headers, "UTF-8");

        LiveUserResponse liveUserResponse = JSON.parseObject(result, LiveUserResponse.class);

        if (!liveUserResponse.getSuccess())
            throw new XHLJException(ResultCodeEnum.LIVE_USER_ERROR);

        LiveUserResponse.Response data = liveUserResponse.getData();

        if(data.getMode() != 2 && !data.getChannel_exist())
            throw new XHLJException(ResultCodeEnum.CHANNEL_ERROR);

        LiveUserVo liveUserVo = new LiveUserVo();

        Integer audienceTotal = data.getAudience_total();
        liveUserVo.setAudienceTotal(audienceTotal);
        Integer integer = AuthContextUtils.getInteger();
        if (integer != null) AuthContextUtils.setInteger((integer + audienceTotal) / 2);
        else AuthContextUtils.setInteger(audienceTotal);

        List<Integer> broadcasterIds = data.getBroadcasters();
        List<LiveUser> broadcasters = userFeignClient.getLiveUserByIds(broadcasterIds);
        liveUserVo.setBroadcasters(broadcasters);

        List<Integer> audienceIds = data.getAudience();
        List<LiveUser> audience = userFeignClient.getLiveUserByIds(audienceIds);
        liveUserVo.setAudiences(audience);

        return liveUserVo;
    }

    @Override
    @Cacheable(value = "service:live", key = "#root.methodName", sync = true)
    public List<Live> getLive() {
        return liveMapper.selectAll();
    }

    //http://api.sd-rtn.com/dev/v1/kicking-rule
    @Override
    @CacheEvict(value = "service:live:kickingRule", allEntries = true)
    public void kickingRuleSet(KickingDto kickingDto) {
        // 创建 authorization header
        String authorizationHeader = getAuthorizationHeader();
        // 获取 appId
        String appId = liveProperty.getAppId();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authorizationHeader);
        headers.put("Content-Type", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("cname", kickingDto.getCname());
        params.put("uid", String.valueOf(kickingDto.getUid()));
        params.put("time_in_seconds", String.valueOf(kickingDto.getTime()));
        params.put("privileges", "join_channel");

        String result = HttpClientUtils.sendPost("http://api.sd-rtn.com/dev/v1/kicking-rule", headers, params);
        KickingSetResponse kickingSetResponse = JSON.parseObject(result, KickingSetResponse.class);

        if (!kickingSetResponse.getStatus().equals("success"))
            throw new XHLJException(ResultCodeEnum.KICKING_ERROR);

        Integer rid = kickingSetResponse.getId();

        LiveUserKicked liveUserKicked = new LiveUserKicked();
        liveUserKicked.setUid(kickingDto.getUid());
        liveUserKicked.setUname(kickingDto.getUname());
        liveUserKicked.setRid(rid);
        liveUserKicked.setTime(kickingDto.getTime());
        liveUserKicked.setCname(kickingDto.getCname());
        liveUserKickingMapper.insert(liveUserKicked);
    }

    @Override
    @Cacheable(value = "service:live:kickingRule", key = "#cname", sync = true)
    public PageResult kickingRuleGet(Integer limit, Integer page, String cname) {
        PageHelper.startPage(page, limit);
        Page<LiveUserKicked> liveUserKickedPage = liveUserKickingMapper.getAll(cname);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(liveUserKickedPage.getTotal());
        pageResult.setRecords(liveUserKickedPage.getResult());
        return pageResult;
    }

    @Override
    @CacheEvict(value = "service:live:kickingRule", allEntries = true)
    public void kickingRuleDelete(Long id) {
        String authorizationHeader = getAuthorizationHeader();
        // 获取 appId
        String appId = liveProperty.getAppId();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", authorizationHeader);
        headers.put("Content-Type", "application/json");

        LiveUserKicked liveUserKicked = liveUserKickingMapper.getById(id);
        Map<String, String> params = new HashMap<>();
        params.put("appid", appId);
        params.put("id", String.valueOf(liveUserKicked.getRid()));

        String result = HttpClientUtils.sendDelete("http://api.sd-rtn.com/dev/v1/kicking-rule", headers, params);
        KickingDeleteResponse kickingDeleteResponse = JSON.parseObject(result, KickingDeleteResponse.class);
        if (!Objects.equals(kickingDeleteResponse.getStatus(), "success"))
            throw new XHLJException(ResultCodeEnum.UNKICKING_ERROR);

        liveUserKicked.setIsDeleted(1);
        liveUserKicked.setUpdateTime(new Date());
        liveUserKickingMapper.update(liveUserKicked);
    }

    @Override
    @CacheEvict(value = "service:live", allEntries = true)
    public void createLive(LiveDto liveDto) {
        Long userId = AuthContextUtils.getUserInfo().getId();
        Live live = new Live();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 6; i++){
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        String identifier = sb.toString();
        UserInfo userInfo = userFeignClient.getById(userId);
        live.setIdentifier(identifier);
        live.setName(liveDto.getName());
        live.setCover(liveDto.getCover());
        live.setParticipants(0);
        live.setStatus(1);
        live.setCreateUid(userId);
        live.setCreateName(userInfo.getName());
        live.setCreateAvatar(userInfo.getAvatar());
        liveMapper.insert(live);
    }

    @Override
    @CacheEvict(value = "service:live", allEntries = true)
    public void startLive(Long id) {
        Live live = new Live();
        live.setId(id);
        live.setStatus(2);
        live.setBeginTime(new Date());
        live.setUpdateTime(new Date());
        liveMapper.update(live);
    }

    @Override
    @CacheEvict(value = "service:live", allEntries = true)
    public void overLive(Long id) {
        Live live = new Live();
        live.setId(id);
        live.setStatus(3);
        live.setOverTime(new Date());
        live.setUpdateTime(new Date());
        liveMapper.update(live);
    }

    @Override
    public void start(LiveDto liveDto) {
        Random random = new Random();
        String streamName = random.nextInt(9999) + 10000 + "";
        long txTime = LiveUtils.getTimeByHours(liveDto.getDuringTime());
        String pushUrl = LiveUtils.getPushUrl(tencentLiveProperty.getPushDomain(), tencentLiveProperty.getAppName(), tencentLiveProperty.getKey(), streamName, txTime);
        String pullUrl = LiveUtils.getPullUrl(tencentLiveProperty.getPullDomain(), tencentLiveProperty.getAppName(), streamName);
        Live live = new Live();
        live.setName(liveDto.getName());
        live.setCover(liveDto.getCover());
        live.setIdentifier(streamName);
        live.setStatus(1);
        live.setPushAdd(pushUrl);
        live.setPullAdd(pullUrl);
        live.setVideo("https://live-playback-1324865185.cos.ap-shanghai.myqcloud.com/push.westlaker.xyz/" + streamName);
        UserInfo userInfo = AuthContextUtils.getUserInfo();
        live.setCreateUid(userInfo.getId());
        live.setCreateName(userInfo.getUsername());
        live.setCreateAvatar(userInfo.getAvatar());
        live.setBeginTime(new Date());
        liveMapper.insert(live);
    }

    @Override
    public PageResult AllRoom(Integer limit, Integer page, LiveQueryDto liveQueryDto) {
        PageHelper.startPage(page, limit);
        Page<Live> roomPage = liveMapper.page(liveQueryDto);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(roomPage.getTotal());
        pageResult.setRecords(roomPage.getResult());
        return pageResult;
    }

    @Override
    public List<Live> myRoom() {
        Long userId = AuthContextUtils.getUserInfo().getId();
        return liveMapper.getByUid(userId);
    }

    @Override
    public void editRoom(LiveEditDto liveEditDto) {
        Live live = new Live();
        live.setId(liveEditDto.getId());
        live.setName(liveEditDto.getName());
        live.setCover(liveEditDto.getCover());
        live.setUpdateTime(new Date());
        liveMapper.update(live);
    }

    @Override
    public void over(Long id) {
        Live live = new Live();
        live.setId(id);
        live.setStatus(3);
        live.setOverTime(new Date());
        live.setUpdateTime(new Date());
        liveMapper.update(live);
    }

    @Override
    public String upload(MultipartFile file) {
        String url = commonFeignClient.fileUpload(file, TypeConstant.LIVE_COVER);
        if (url == null) throw new XHLJException(ResultCodeEnum.SYSTEM_ERROR);
        return url;
    }

    @Override
    public Live room(Long id) {
        return liveMapper.getById(id);
    }

    @Override
    public void deleteRoom(Long id) {
        liveMapper.deleteRoom(id);
    }

    private String getAuthorizationHeader(){
        // 拼接客户 ID 和客户密钥并使用 base64 编码
        String plainCredentials = liveProperty.getKey() + ":" + liveProperty.getSecret();
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        // 创建 authorization header
        String authorizationHeader = "Basic " + base64Credentials;
        return authorizationHeader;
    }
}
