package com.swyxl.manager.service.ServiceImpl;

import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.AchievementMapper;
import com.swyxl.manager.service.AchievementService;
import com.swyxl.model.entity.service.exhibit.Achievement;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;

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
}
