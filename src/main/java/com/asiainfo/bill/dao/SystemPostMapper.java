package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.SystemPost;
import java.util.List;

public interface SystemPostMapper {
    int deleteByPrimaryKey(Long sysPostId);

    int insert(SystemPost record);

    SystemPost selectByPrimaryKey(Long sysPostId);

    List<SystemPost> selectAll();

    int updateByPrimaryKey(SystemPost record);
}