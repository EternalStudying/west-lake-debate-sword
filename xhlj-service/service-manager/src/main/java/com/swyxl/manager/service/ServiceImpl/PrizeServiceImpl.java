package com.swyxl.manager.service.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.manager.mapper.PrizeMapper;
import com.swyxl.manager.service.PrizeService;
import com.swyxl.model.constant.TypeConstant;
import com.swyxl.model.dto.service.prize.PrizeProbabilityDto;
import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.manage.PrizeQueryDto;
import com.swyxl.model.vo.service.prize.PrizeProbabilityVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrizeServiceImpl implements PrizeService {

    @Autowired
    private PrizeMapper prizeMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;

    @Override
    @CacheEvict(value = "service:prize", allEntries = true)
    public void add(Prize prize) {
       Prize prize1 =  prizeMapper.getByName(prize);
       if(prize1 != null){
           throw new XHLJException(ResultCodeEnum.PRIZE_IS_EXIST);
       }else {
           prizeMapper.save(prize);
       }
    }

    @Override
    @Cacheable(value = "service:prize:id", key = "#id", sync = true)
    public Prize getById(Long id) {
        Prize prize = prizeMapper.getById(id);
        if(prize == null){
            throw new XHLJException(ResultCodeEnum.PRIZE_IS_NOT_EXIST);
        }else {
            return prize;
        }
    }

    @Override
    @CacheEvict(value = "service:prize", allEntries = true)
    public void update(Prize prize) {
        Prize prize1 = prizeMapper.getById(prize.getId());
        if(prize1 == null){
            throw new XHLJException(ResultCodeEnum.PRIZE_IS_NOT_EXIST);
        }else {
            BeanUtils.copyProperties(prize,prize1);
            prize1.setUpdateTime(new Date());
            prizeMapper.update(prize1);
        }
    }

    @Override
    @CacheEvict(value = "service:prize", allEntries = true)
    public void deleteById(Long id) {
        Prize prize = prizeMapper.getById(id);
        if(prize == null){
            throw new XHLJException(ResultCodeEnum.PRIZE_IS_NOT_EXIST);
        }else {
            prize.setUpdateTime(new Date());
            prize.setId(id);
            prize.setIsDeleted(1);
            prizeMapper.update(prize);
        }
    }

    @Override
    @Cacheable(value = "service:prize", key = "#root.methodName", sync = true)
    public PageResult page(Integer limit, Integer page, PrizeQueryDto prizeQueryDto) {
        PageHelper.startPage(page,limit);
        Page<Prize> newsPage = prizeMapper.pageLike(prizeQueryDto);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(newsPage.getTotal());
        pageResult.setRecords(newsPage.getResult());
        return pageResult;
    }

    @Override
    public String upload(MultipartFile file) {
        String url = commonFeignClient.fileUpload(file, TypeConstant.PRIZE);
        if (url == null){
            throw new XHLJException(ResultCodeEnum.FILE_ERROR);
        }
        return url;
    }

    @Override
    @Cacheable(value = "service:prize:probability", key = "#root.methodName", sync = true)
    public List<PrizeProbabilityVo> getProbability() {
        List<Prize> prizeList = prizeMapper.selectAll();
        List<PrizeProbabilityVo> prizeProbabilityVoList = new ArrayList<>();
        prizeList.forEach(prize -> prizeProbabilityVoList.add(new PrizeProbabilityVo(prize.getName(), prize.getProbability())));
        return prizeProbabilityVoList;
    }

    @Override
    @CacheEvict(value = "service:prize:probability", allEntries = true)
    public void updateProbability(List<PrizeProbabilityDto> prizeProbabilityDtos) {
        prizeMapper.updateProbability(prizeProbabilityDtos);
    }
}
