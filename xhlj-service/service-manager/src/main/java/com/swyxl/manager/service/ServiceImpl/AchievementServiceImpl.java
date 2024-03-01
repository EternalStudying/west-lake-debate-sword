package com.swyxl.manager.service.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.feign.common.CommonFeignClient;
import com.swyxl.manager.mapper.AchievementMapper;
import com.swyxl.manager.service.AchievementService;
import com.swyxl.model.constant.TypeConstant;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.ResultCodeEnum;
import com.swyxl.model.dto.service.manage.AchievementQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;
    @Autowired
    private CommonFeignClient commonFeignClient;

    @Override
    public void add(Achievement achievement) {
       Achievement achievement1 =  achievementMapper.getByName(achievement.getName());
       if(achievement1 != null){
           throw new XHLJException(ResultCodeEnum.ACHIEVEMENT_IS_EXIST);
       }else {
           achievementMapper.save(achievement);
       }
    }

    @Override
    public Achievement findById(Long id) {
        Achievement achievement = achievementMapper.getById(id);
        if(achievement == null){
            throw new XHLJException(ResultCodeEnum.ACHIEVEMENT_IS_NOT_EXIST);
        }else {
            return achievement;
        }
    }

    @Override
    public void update(Achievement achievement) {
        Achievement achievementMapperById = achievementMapper.getById(achievement.getId());
        if(achievementMapperById == null){
            throw new XHLJException(ResultCodeEnum.ACHIEVEMENT_IS_NOT_EXIST);
        }else {
            Achievement achievement1 = new Achievement();
            BeanUtils.copyProperties(achievement,achievement1);
            achievement1.setUpdateTime(new Date());
            achievementMapper.update(achievement1);
        }
    }

    @Override
    public void deleteById(Long id) {
        Achievement achievementMapperById = achievementMapper.getById(id);
        if(achievementMapperById == null){
            throw new XHLJException(ResultCodeEnum.ACHIEVEMENT_IS_NOT_EXIST);
        }else {
            Achievement achievement = new Achievement();
            achievement.setId(id);
            achievement.setUpdateTime(new Date());
            achievement.setIsDeleted(1);
            achievementMapper.update(achievement);
        }
    }

    @Override
    public String imageUpload(MultipartFile file) {
        String fileUrl = commonFeignClient.fileUpload(file, TypeConstant.ACHIEVEMENT_IMAGE);
        if (fileUrl.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_ERROR);
        return fileUrl;
    }

    @Override
    public String resourceUpload(MultipartFile file) {
        String fileUrl = commonFeignClient.fileUpload(file, TypeConstant.ACHIEVEMENT_RESOURCE);
        if (fileUrl.isEmpty())
            throw new XHLJException(ResultCodeEnum.FILE_ERROR);
        return fileUrl.substring(27);
    }

    @Override
    public PageResult page(Integer limit, Integer page, AchievementQueryDto achievementQueryDto) {
        PageHelper.startPage(page,limit);
        Page<Achievement> achievementPage = achievementMapper.pageLike(achievementQueryDto);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(achievementPage.getTotal());
        pageResult.setRecords(achievementPage.getResult());
        return pageResult;
    }
}
