package com.atkexin.ssyx.acl.mapper;

import com.atkexin.ssyx.model.acl.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository//@Repository是Spring框架中的注解，用于标注数据访问层（DAO）的类。它的作用是将数据访问层的类标识为Spring容器中的Bean对象，从而可以在其他地方方便地使用。
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

}
