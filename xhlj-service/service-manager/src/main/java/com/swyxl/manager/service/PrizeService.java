package com.swyxl.manager.service;

import com.swyxl.model.dto.service.prize.PrizeProbabilityDto;
import com.swyxl.model.entity.service.exhibit.Prize;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.dto.service.manage.PrizeQueryDto;
import com.swyxl.model.vo.service.prize.PrizeProbabilityVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrizeService {
    void add(Prize prize);

    Prize getById(Long id);

    void update(Prize prize);

    void deleteById(Long id);

    PageResult page(Integer limit, Integer page, PrizeQueryDto prizeQueryDto);

    String upload(MultipartFile file);

    List<PrizeProbabilityVo> getProbability();

    void updateProbability(List<PrizeProbabilityDto> prizeProbabilityDtos);
}
