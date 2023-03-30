package com.sileyouhe.mall.service;

import com.sileyouhe.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {

    // import all products to ES
    int importAll();

    // delete by id
    void delete(long id);

    // create by id
    EsProduct create(Long id);

    // delete products
    void delete(List<Long> ids);

    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

}
