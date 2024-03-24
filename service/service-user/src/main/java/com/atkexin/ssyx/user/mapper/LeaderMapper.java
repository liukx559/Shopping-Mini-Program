package com.atkexin.ssyx.user.mapper;

import com.atkexin.ssyx.enums.user.Leader;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderMapper extends BaseMapper<Leader> {
}
