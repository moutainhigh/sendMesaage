package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.Privilege;
import java.util.List;

public interface PrivilegeMapper {
    int deleteByPrimaryKey(Long privId);

    int insert(Privilege record);

    Privilege selectByPrimaryKey(Long privId);

    List<Privilege> selectAll();

    int updateByPrimaryKey(Privilege record);
}