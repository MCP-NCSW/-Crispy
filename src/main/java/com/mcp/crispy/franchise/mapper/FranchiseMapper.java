package com.mcp.crispy.franchise.mapper;

import com.mcp.crispy.franchise.dto.FranchiseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FranchiseMapper {

    void insertFranchise(FranchiseDto franchiseDto);
}
