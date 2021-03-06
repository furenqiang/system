package com.furenqiang.system.controller;

import com.furenqiang.system.aop.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/login")
@Api(tags = "spring security登录功能")
public class LoginController {

    /**
     * @return
     * @Description 登录请求接口, 此接口与登录无关，具体业务在SecurityConfig里面
     * @Time 2020年12月2日
     * @Author Eric
     */
    @Log("登录请求")
    @ApiOperation(value = "登录请求", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String")})
    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        return "登录";
    }

    /**
     * @return
     * @Description 登出请求接口
     * @Time 2020年12月2日
     * @Author Eric
     */
    @Log("登出请求")
    @ApiOperation(value = "登出请求", httpMethod = "GET")
    @GetMapping("/logout")
    public String logout() {
        //return "redirect:/security/test";//也可以重定向到登录页面
        return "登出成功";
    }
}
