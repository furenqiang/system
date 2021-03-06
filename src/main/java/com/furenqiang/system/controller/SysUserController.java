package com.furenqiang.system.controller;

import com.furenqiang.system.aop.Log;
import com.furenqiang.system.common.ResponseEnum;
import com.furenqiang.system.common.ResponseResult;
import com.furenqiang.system.entity.SysUser;
import com.furenqiang.system.filter.PredicateParams;
import com.furenqiang.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sysUser")
@Api(tags = "系统用户相关功能")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    /**
     * @return
     * @Description 注册（添加）用户
     * @Time 2020年12月4日
     * @Author Eric
     */
    @Log("注册（添加）用户")
    @PreAuthorize("hasAnyAuthority('vip','sysUser')")
    @ApiOperation(value = "注册（添加）用户", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码(不填默认123456)", dataType = "String")})
    @PostMapping("/register")
    public ResponseResult register(HttpServletRequest request, String username, String password) {
        SysUser userInfo = (SysUser) request.getSession().getAttribute("userInfo");
        Integer creatorId = userInfo.getId();
        String creatorName = userInfo.getUsername();
        return sysUserService.registerUser(username, password, creatorId, creatorName);
    }

    /**
     * @return
     * @Description 删除（停用）用户
     * @Time 2020年12月7日
     * @Author Eric
     */
    @Log("删除（停用）用户")
    @PreAuthorize("hasAnyAuthority('vip','sysUser')")
    @ApiOperation(value = "删除（停用）用户", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", example = "1")})
    @PostMapping("/deleteUser")
    public ResponseResult deleteUser(Integer id) {
        if(new PredicateParams().predicateParam(id)){
            return new ResponseResult(ResponseEnum.BADPARAM.getCode(),ResponseEnum.BADPARAM.getMessage());
        }
        return sysUserService.deleteUser(id);
    }

    /**
     * @return
     * @Description 修改用户信息（密码）
     * @Time 2020年12月7日
     * @Author Eric
     */
    @Log("修改用户信息（密码）")
    @PreAuthorize("hasAnyAuthority('vip','sysUser')")
    @ApiOperation(value = "修改用户信息（密码）", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String")})
    @PostMapping("/updateUser")
    public ResponseResult updateUser(Integer id, String password) {
        if(new PredicateParams().predicateParam(id,password)){
            return new ResponseResult(ResponseEnum.BADPARAM.getCode(),ResponseEnum.BADPARAM.getMessage());
        }
        return sysUserService.updateUser(id, password);
    }

    /**
     * @return
     * @Description 查询用户列表
     * @Params
     * @Time 2020年12月4日
     * @Author Eric
     */
    //@Log("查询用户列表")
    @PreAuthorize("hasAnyAuthority('vip','sysUser')")
    @ApiOperation(value = "查询用户列表", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "creatorName", value = "创建人名称", dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", dataType = "int", example = "1"),
            @ApiImplicitParam(name = "size", value = "每页个数", dataType = "int", example = "10")})
    @GetMapping("/getUserListByParams")
    public ResponseResult getUserListByParams(String username, String creatorName, Integer page, Integer size) {
        if(new PredicateParams().predicateParam(page,size)){
            return new ResponseResult(ResponseEnum.BADPARAM.getCode(),ResponseEnum.BADPARAM.getMessage());
        }
        return sysUserService.getUserListByParams(username, creatorName, page, size);
    }
}
