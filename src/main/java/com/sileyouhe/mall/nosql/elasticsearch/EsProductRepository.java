package com.sileyouhe.mall.nosql.elasticsearch;

import com.sileyouhe.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    /**
     *
     * @param name   name of the product
     * @param subTitle subtitle of the product
     * @param keywords keywords
     * @param page    page info
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
