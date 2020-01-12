package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.FuncComp;
import java.util.List;

public interface FuncCompMapper {
    int deleteByPrimaryKey(Integer compId);

    int insert(FuncComp record);

    FuncComp selectByPrimaryKey(Integer compId);

    List<FuncComp> selectAll();

    int updateByPrimaryKey(FuncComp record);

    List<FuncComp> selectByMenuId(Integer menuId);
}