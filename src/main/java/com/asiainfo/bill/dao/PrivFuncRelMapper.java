package com.asiainfo.bill.dao;

import com.asiainfo.bill.model.PrivFuncRel;
import java.util.List;

public interface PrivFuncRelMapper {
    int deleteByPrimaryKey(Long privFuncRelId);

    int insert(PrivFuncRel record);

    PrivFuncRel selectByPrimaryKey(Long privFuncRelId);

    List<PrivFuncRel> selectAll();

    int updateByPrimaryKey(PrivFuncRel record);

    List<PrivFuncRel> selectByPrivId(Long privId);
}