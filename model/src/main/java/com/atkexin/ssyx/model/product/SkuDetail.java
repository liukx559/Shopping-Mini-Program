package com.atkexin.ssyx.model.product;

import com.atkexin.ssyx.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "SkuDetail")
@TableName("sku_detail")
public class SkuDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "d")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "商品id")
    @TableField("sku_id")
    private Long skuId;

    @ApiModelProperty(value = "属性id")
    @TableField("detail_html")
    private String detailHtml;



}