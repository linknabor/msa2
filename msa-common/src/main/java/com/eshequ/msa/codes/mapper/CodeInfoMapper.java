package com.eshequ.msa.codes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshequ.msa.codes.model.CodeInfo;
import com.eshequ.msa.common.CommonMapper;

public interface CodeInfoMapper extends CommonMapper<CodeInfo>{

    List<CodeInfo> selectByClass(@Param("ciSpClass") String ciSpClass);
}