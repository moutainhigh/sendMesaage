package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.FuncMenu;
import java.util.List;

public interface FuncMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(FuncMenu record);

    FuncMenu selectByPrimaryKey(Integer menuId);

    List<FuncMenu> selectAll();

    int updateByPrimaryKey(FuncMenu record);

    List<FuncMenu> selectByParMenuId(Integer parMenuId);
}