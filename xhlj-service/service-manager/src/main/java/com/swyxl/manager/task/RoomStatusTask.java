package com.swyxl.manager.task;

import com.alibaba.fastjson.JSON;
import com.swyxl.manager.mapper.LiveMapper;
import com.swyxl.manager.properties.TencentProperty;
import com.swyxl.model.entity.service.manager.*;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.live.v20180801.LiveClient;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveStreamOnlineListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeLiveStreamOnlineListResponse;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListRequest;
import com.tencentcloudapi.live.v20180801.models.DescribeStreamPlayInfoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class RoomStatusTask {

    @Autowired
    private TencentProperty tencentProperty;
    @Autowired
    private LiveMapper liveMapper;

    @Scheduled(cron = "0/30 * * * * ? ")
    public void checkStatus(){
        try {
            Credential cred = new Credential(tencentProperty.getSecretId(), tencentProperty.getSecretKey());
            LiveClient client = new LiveClient(cred, "");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DescribeLiveStreamOnlineListRequest req = new DescribeLiveStreamOnlineListRequest();

            // 返回的resp是一个DescribeLiveStreamOnlineListResponse的实例，与请求对象对应
            DescribeLiveStreamOnlineListResponse resp = client.DescribeLiveStreamOnlineList(req);

            LiveStreamOnlineResponse liveStreamOnlineResponse = JSON.parseObject(AbstractModel.toJsonString(resp), LiveStreamOnlineResponse.class);
            List<StreamOnlineInfo> onlineInfos = liveStreamOnlineResponse.getOnlineInfo();
            if(onlineInfos == null || onlineInfos.isEmpty()) return;
            liveMapper.status22(onlineInfos);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "30 0/1 * * * ? ")
    public void updateOnline(){
            List<Live> lives = liveMapper.selectAll();
            lives.forEach(live -> {
                try {
                    Credential cred = new Credential(tencentProperty.getSecretId(), tencentProperty.getSecretKey());
                    LiveClient client = new LiveClient(cred, "");
                    DescribeStreamPlayInfoListRequest req = getDescribeStreamPlayInfoListRequest(live.getIdentifier());
                    DescribeStreamPlayInfoListResponse resp = client.DescribeStreamPlayInfoList(req);
                    StreamPlayInfoResponse streamPlayInfoResponse = JSON.parseObject(AbstractModel.toJsonString(resp), StreamPlayInfoResponse.class);
                    List<DayStreamPlayInfo> dataInfoList = streamPlayInfoResponse.getDataInfoList();
                    DayStreamPlayInfo dayStreamPlayInfo = dataInfoList.get(0);
                    liveMapper.updateOnline(dayStreamPlayInfo.getOnline(), live.getIdentifier());
                }catch (Exception e){
                    e.printStackTrace();
                }
            });

    }

    private static DescribeStreamPlayInfoListRequest getDescribeStreamPlayInfoListRequest(String identifier) {
        DescribeStreamPlayInfoListRequest req = new DescribeStreamPlayInfoListRequest();
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -1);
        Date lastDate = calendar.getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        req.setStartTime(simpleDateFormat.format(lastDate));
        req.setEndTime(simpleDateFormat.format(date));
        req.setStreamName(identifier);
        return req;
    }

}
