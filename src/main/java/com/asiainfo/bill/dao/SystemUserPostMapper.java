package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.SystemUserPost;
import java.util.List;

public interface SystemUserPostMapper {
    int deleteByPrimaryKey(Long sysUserPostId);

    int insert(SystemUserPost record);

    SystemUserPost selectByPrimaryKey(Long sysUserPostId);

    List<SystemUserPost> selectAll();

    int updateByPrimaryKey(SystemUserPost record);

    List<SystemUserPost> selectBySysPostId(Long sysPostId);

    List<SystemUserPost> selectBySysUserId(Long sysUserId);
}