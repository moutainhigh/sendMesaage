package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.SystemRoles;
import java.util.List;

public interface SystemRolesMapper {
    int deleteByPrimaryKey(Long sysRoleId);

    int insert(SystemRoles record);

    SystemRoles selectByPrimaryKey(Long sysRoleId);

    List<SystemRoles> selectAll();

    int updateByPrimaryKey(SystemRoles record);
}