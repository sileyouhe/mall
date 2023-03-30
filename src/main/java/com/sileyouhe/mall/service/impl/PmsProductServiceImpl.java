package com.sileyouhe.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.sileyouhe.mall.mbg.mapper.PmsProductMapper;
import com.sileyouhe.mall.mbg.model.PmsProduct;
import com.sileyouhe.mall.mbg.model.PmsProductExample;
import com.sileyouhe.mall.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsProduct> listAllProduct() {
        return productMapper.selectByExample(new PmsProductExample());
    }

    @Override
    public List<PmsProduct> listProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return productMapper.selectByExample(new PmsProductExample());
    }

    @Override
    public List<PmsProduct> listByKeyword(String keyword) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        if (!StrUtil.isEmpty(keyword)){
            criteria.andNameLike("%" + keyword + "%");
            productExample.or().andProductSnLike("%" + keyword + "%");
        }
        return productMapper.selectByExample(productExample);
    }

    @Override
    public int createProduct(PmsProduct product) {
        return productMapper.insertSelective(product);
    }

    @Override
    public int updateProduct(Long id, PmsProduct product) {
        product.setId(id);
        return productMapper.updateByPrimaryKeySelective(product);
    }


    @Override
    public int deleteProduct(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProduct getProduct(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
