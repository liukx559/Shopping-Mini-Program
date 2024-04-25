package com.atkexin.ssyx.model.user;

import com.atkexin.ssyx.enums.UserType;
import com.atkexin.ssyx.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.EnumTypeHandler;

@Data
@ApiModel(description = "User")
@TableName("user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField(value = "user_type")
	private Integer userType;

	@ApiModelProperty(value = "会员头像")
	@TableField("photo_url")
	private String photoUrl;

	@ApiModelProperty(value = "昵称")
	@TableField("nick_name")
	private String nickName;

	@ApiModelProperty(value = "身份证号码")
	@TableField("id_no")
	private String idNo;

	@ApiModelProperty(value = "性别")
	@TableField("sex")
	private String sex;

	@ApiModelProperty(value = "电话号码")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "备注")
	@TableField("memo")
	private String memo;

	@ApiModelProperty(value = "小程序open id")
	@TableField("open_id")
	private String openId;

	@ApiModelProperty(value = "微信开放平台unionID")
	@TableField("union_id")
	private String unionId;

	@ApiModelProperty(value = "是否新用户")
	@TableField("is_new")
	private Integer isNew;

}