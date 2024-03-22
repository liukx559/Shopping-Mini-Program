package com.atkexin.ssyx.search.mapper;

import com.atkexin.ssyx.model.search.SkuEs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//用Spring-data操作es
@Repository
@Mapper
public interface SkuRepository extends ElasticsearchRepository<SkuEs, Long> {

}


