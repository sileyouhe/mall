package com.sileyouhe.mall.service;


import com.sileyouhe.mall.mbg.model.PmsProduct;

import java.util.List;

public interface PmsProductService {
    List<PmsProduct> listAllProduct();

    List<PmsProduct> listProduct(int pageNum, int pageSize);

    /**
     * return if keyword in name or keyword in Product_Sn
     * @param keyword
     * @return
     */
    List<PmsProduct> listByKeyword(String keyword);

    int createProduct(PmsProduct product);

    int updateProduct(Long id, PmsProduct product);


    int deleteProduct(Long id);



    PmsProduct getProduct(Long id);


}
