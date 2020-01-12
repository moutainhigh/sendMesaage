package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.Staff;
import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(Long staffId);

    int insert(Staff record);

    Staff selectByPrimaryKey(Long staffId);

    List<Staff> selectAll();

    int updateByPrimaryKey(Staff record);
}