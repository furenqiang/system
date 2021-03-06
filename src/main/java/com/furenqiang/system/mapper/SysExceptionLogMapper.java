package com.furenqiang.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.furenqiang.system.entity.SysExceptionLog;
import com.furenqiang.system.vo.SysExceptionLogCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysExceptionLogMapper extends BaseMapper<SysExceptionLog> {

    int addExceptLog(SysExceptionLog sysExceptionLog);

    List<SysExceptionLog> getExceptionLogListByParams(@Param("username") String username, @Param("excName") String excName, @Param("method") String method, @Param("ip") String ip);

    List<SysExceptionLogCountVO> countExceptByParams();

    List<SysExceptionLogCountVO> countExceptTop3();
}
