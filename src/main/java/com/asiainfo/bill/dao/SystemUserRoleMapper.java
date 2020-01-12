package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.SystemUserRole;
import java.util.List;

public interface SystemUserRoleMapper {
    int deleteByPrimaryKey(Long sysUserRoleId);

    int insert(SystemUserRole record);

    SystemUserRole selectByPrimaryKey(Long sysUserRoleId);

    List<SystemUserRole> selectAll();

    int updateByPrimaryKey(SystemUserRole record);

    List<SystemUserRole> selectBySysRoleId(Long sysRoleId);

    List<SystemUserRole> selectBySysUserId(Long sysUserId);
}