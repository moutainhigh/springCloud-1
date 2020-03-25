package com.chauncy.cloud.data.domain.po.organization;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.chauncy.cloud.data.domain.po.BasePo;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户和组关系表
 * </p>
 *
 * @author admin
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_group_relation")
@ApiModel(value = "UserGroupRelationPo对象", description = "用户和组关系表")
public class UserGroupRelationPo extends BasePo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户组id")
    private String groupId;


}