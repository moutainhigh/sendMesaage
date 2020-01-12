package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.SystemPostRole;
import java.util.List;

public interface SystemPostRoleMapper {
    int deleteByPrimaryKey(Long sysPostRoleId);

    int insert(SystemPostRole record);

    SystemPostRole selectByPrimaryKey(Long sysPostRoleId);

    List<SystemPostRole> selectAll();

    int updateByPrimaryKey(SystemPostRole record);

    List<SystemPostRole> selectByRoleId(Long sysRoleId);

    List<SystemPostRole> selectBySysPortId(Long sysPortId);
}