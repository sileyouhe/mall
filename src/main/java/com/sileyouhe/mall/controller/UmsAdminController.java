package com.sileyouhe.mall.controller;

import com.sileyouhe.mall.dto.UmsAdminLoginParam;
import com.sileyouhe.mall.common.api.CommonResult;
import com.sileyouhe.mall.mbg.model.UmsAdmin;
import com.sileyouhe.mall.mbg.model.UmsPermission;
import com.sileyouhe.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "UmsAdminController", description = "user management")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("register")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result){
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null){
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("login and return Token")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result){
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if (token == null){
            return CommonResult.validateFailed("incorrect username or password");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }

    // to do
//    @ApiOperation("get user information")
//    @RequestMapping(value = "/info",method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult getAdminInfo(Principal principal){
//        if (principal == null){
//            return CommonResult.UNAUTHORIZED(null);
//        }
//        String username = principal.getName();
//        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
//        Map<String,String> data = new HashMap<>();
//        data.put("username", umsAdmin.getUsername());
//        // to do
//        data.put("menus",null);
//        data.put("icon",umsAdmin.getIcon());
//        // to do
////        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
////        if(CollUtil.isNotEmpty(roleList)){
////            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
////            data.put("roles",roles);
////        }
//        return CommonResult.success(data);
//    }

    @ApiOperation("get user's all permissions")
    @RequestMapping(value = "/permission/{adminId}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }



}
