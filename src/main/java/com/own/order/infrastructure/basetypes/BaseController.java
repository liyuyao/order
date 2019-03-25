package com.own.order.infrastructure.basetypes;

import com.own.order.infrastructure.util.DefaultDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 所有 Controller 的基类
 */
public class BaseController {
	@InitBinder
	protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		this.RegisterDateEditor(binder); // 注册日期类型解析器
	}

	/**
	 * 获取当前登录用户
	 */
//	 public CurrentUserQo getCurrentUser() {
//	 	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//	 	HttpServletRequest request = attributes.getRequest();
//	 	String authorizationToken = request.getHeader("Authorization");
//
//	 	if (authorizationToken == null || authorizationToken.length() <= 0) {
//	 		authorizationToken = request.getHeader("token");
//	 	}
//
//	 	if (authorizationToken == null || authorizationToken.length() <= 0) {
//	 		return null;
//	 	}
//
//	 	Map<String, Claim> claims = JwtUtil.decodeToken(authorizationToken);
//	 	if (claims == null) {
//	 		return null;
//	 	}
//
//	 	CurrentUserQo currentUser = new CurrentUserQo();
//	 	currentUser.setUserId(claims.get("sub").asString());
//	 	currentUser.setLoginName(claims.get("loginName").asString());
//	 	currentUser.setUserText(claims.get("userText").asString());
//	 	currentUser.setUserDeptId(claims.get("userDeptId").asString());
//	 	currentUser.setUserDeptCode(claims.get("userDeptCode").asString());
//	 	currentUser.setUserDeptText(claims.get("userDeptText").asString());
//
//	 	return currentUser;
//	 }

	/**
	 * 注册日期类型解析器
	 */
	private void RegisterDateEditor(ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DefaultDateEditor());
	}
}