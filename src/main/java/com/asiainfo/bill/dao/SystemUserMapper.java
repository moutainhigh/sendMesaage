package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.SystemUser;
import java.util.List;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Long sysUserId);

    int insert(SystemUser record);

    SystemUser selectByPrimaryKey(Long sysUserId);

    List<SystemUser> selectAll();

    int updateByPrimaryKey(SystemUser record);

    List<SystemUser> selectByStaffId(Long staffId);
}