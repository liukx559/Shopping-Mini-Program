package com.atkexin.ssyx.search.mapper;

import com.atkexin.ssyx.model.search.SkuEs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

//用Spring-data操作es

@Repository
public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {
    Page<SkuEs> findByCategoryIdAndWareId(Long categoryId, Long wareId, Pageable page);

    Page<SkuEs> findByKeywordAndWareId(String keyword, Long wareId, Pageable page);
}


