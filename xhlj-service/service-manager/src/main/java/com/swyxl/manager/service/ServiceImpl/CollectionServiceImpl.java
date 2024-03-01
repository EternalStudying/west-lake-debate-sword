package com.swyxl.manager.service.ServiceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.swyxl.common.exception.XHLJException;
import com.swyxl.manager.mapper.CollectionMapper;
import com.swyxl.manager.service.CollectionService;

import com.swyxl.model.dto.service.manage.CollectionQueryDto;
import com.swyxl.model.entity.service.active.Collection;
import com.swyxl.model.vo.common.PageResult;
import com.swyxl.model.vo.common.ResultCodeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public void add(Collection collection) {
        Collection collection1 = collectionMapper.getByName(collection.getName());
        if(collection1 != null){
            throw new XHLJException(ResultCodeEnum.COLLECTION_IS_EXIST);
        }else{
            collectionMapper.save(collection);
        }
    }

    @Override
    public Collection findById(Long id) {
        Collection collection =  collectionMapper.findById(id);
        if(collection == null){
            throw new XHLJException(ResultCodeEnum.COLLECTION_IS_NOT_EXIST);
        }else {
            return collection;
        }
    }

    @Override
    public void update(Collection collection) {
        Collection collection1 = collectionMapper.findById(collection.getId());
        if(collection1 == null){
            throw new XHLJException(ResultCodeEnum.COLLECTION_IS_NOT_EXIST);
        }else {
            BeanUtils.copyProperties(collection,collection1);
            collection1.setUpdateTime(new Date());
            collectionMapper.update(collection1);
        }
    }

    @Override
    public void delete(Long id) {
        Collection collection = collectionMapper.findById(id);
        if(collection == null){
            throw new XHLJException(ResultCodeEnum.COLLECTION_IS_NOT_EXIST);
        }else{
            collection.setUpdateTime(new Date());
            collection.setIsDeleted(1);
            collectionMapper.update(collection);
        }
    }

    @Override
    public PageResult page(Integer limit, Integer page, CollectionQueryDto collectionQueryDto) {
        PageHelper.startPage(page,limit);
        Page<Collection> collectionPage = collectionMapper.page(collectionQueryDto);
        PageResult pageResult = new PageResult();
        pageResult.setRecords(collectionPage.getResult());
        pageResult.setTotal(collectionPage.getTotal());
        return pageResult;
    }
}
