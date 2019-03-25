package com.own.order.admin.webapi;

import com.own.order.admin.entity.User;
import com.own.order.admin.service.UserService;
import com.own.order.infrastructure.annotation.Anonymous;
import com.own.order.infrastructure.vo.ApiResult;
import com.own.order.infrastructure.vo.PaggingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Dictionary;
import java.util.List;

/**
 * @Author:liyuyao
 * @DAte：2019-03-21 14:11
 */
@RestController
@RequestMapping({"/api/v1/user"})
@Api(value = "/api/v1/user", tags = "用户")
public class UserController {
    @Autowired
    UserService userService;


    @ApiOperation(value = "用户登录")
    @RequestMapping(method = RequestMethod.POST, path = "login")
    @Anonymous
    public ApiResult<Object> login(@ApiParam(value = "登录名", required = true) @RequestParam String loginName,
                                   @ApiParam(value = "密码", required = true) @RequestParam String password
    ) throws Exception {

        User user = this.userService.login(loginName, password);
        if (user == null)
        {
            throw new ValidationException("用户名或密码错误！");
        }

//        Dictionary<String, String> tokenData = this.userService.createToken(user, maxAge);
        return ApiResult.successData(user);
    }

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(method = RequestMethod.GET, path = "getUserList")
    public ApiResult<List<User>> getUserList(
            @ApiParam(value = "页号", required = false) @RequestParam(required = false) Integer pageNum,
            @ApiParam(value = "每页条数", required = false) @RequestParam(required = false) Integer pageSize,
            @ApiParam(value = "是否按名称排序,传入 null|asc|desc", required = false) @RequestParam(required = false) String orderByName,
            @ApiParam(value = "是否按创建日期排序,传入 null|asc|desc", required = false) @RequestParam(required = false) String orderByLoginName,
            @ApiParam(value = "模糊查询", required = false) @RequestParam(required = false) String query
    ) {
        PaggingResult<User> result = userService.getUserList(pageNum, pageSize, orderByName, orderByLoginName, query);
        return ApiResult.successData(result.getData(), result.getTotal());
    }

    @ApiOperation(value = "获取用户实体")
    @RequestMapping(method = RequestMethod.GET, path = "getUserById")
    public ApiResult<User> getUserById(@RequestParam(required = true) String id) {
        return ApiResult.successData(userService.getUserById(id));
    }

    @ApiOperation(value = "新增用户", notes = "")
    @RequestMapping(method = RequestMethod.POST, path = "saveUser")
    public ApiResult<Object> saveUser(@RequestBody User entity) {
        userService.saveUser(entity);
        return ApiResult.success();
    }

    @ApiOperation(value = "修改用户")
    @RequestMapping(method = RequestMethod.POST, path = "updateUser")
    public ApiResult<Object> updateUser(@RequestBody User entity) {
        userService.updateUser(entity);
        return ApiResult.success();
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(method = RequestMethod.POST, path = "deleteUser")
    public ApiResult<Object> deleteUser(
            @ApiParam(value = "主键", required = true) @RequestParam(required = true) String id) {
        userService.deleteUser(id);
        return ApiResult.success();
    }

    @ApiOperation(value = "判断用户编码是否已存在")
    @RequestMapping(method = RequestMethod.GET, path = "isUserCodeExists")
    public ApiResult<Object> isUserCodeExists(
            @ApiParam(value = "主键", required = false) @RequestParam(required = false) String id,
            @ApiParam(value = "编码", required = true) @RequestParam(required = true) String code) {
        return ApiResult.successData(userService.isUserCodeExists(id, code));
    }

    @ApiOperation(value = "判断用户登录名是否已存在")
    @RequestMapping(method = RequestMethod.GET, path = "isUserLoginNameExists")
    public ApiResult<Object> isUserLoginNameExists(
            @ApiParam(value = "主键", required = false) @RequestParam(required = false) String id,
            @ApiParam(value = "登录名", required = false) @RequestParam(required = false) String loginName) {
        return ApiResult.successData(userService.isUserLoginNameExists(id, loginName));
    }

}
