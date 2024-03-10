package com.atkexin.ssyx.repository;


import com.atkexin.ssyx.model.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//用Spring-data操作es
public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {

}


