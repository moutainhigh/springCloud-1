package com.chauncy.cloud.sysadmin.organization.service.impl;

import com.chauncy.cloud.data.domain.po.organization.GroupsPo;
import com.chauncy.cloud.data.mapper.organization.GroupsMapper;
import com.chauncy.cloud.sysadmin.organization.service.IGroupsService;
import com.chauncy.cloud.core.config.base.AbstractService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-03-25
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class GroupsServiceImpl extends AbstractService<GroupsMapper,GroupsPo> implements IGroupsService {

 @Autowired
 private GroupsMapper mapper;

}
