package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.PrivGrant;
import java.util.List;

public interface PrivGrantMapper {
    int deleteByPrimaryKey(Long privGrantId);

    int insert(PrivGrant record);

    PrivGrant selectByPrimaryKey(Long privGrantId);

    List<PrivGrant> selectAll();

    int updateByPrimaryKey(PrivGrant record);

    List<PrivGrant> selectByPrivId(Long privId);
}