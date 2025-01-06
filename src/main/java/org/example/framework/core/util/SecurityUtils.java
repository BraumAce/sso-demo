package org.example.framework.core.util;

import org.example.framework.core.LoginUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * 安全工具服务类
 *
 * @author liuguang
 */
public class SecurityUtils {

	public static final String AUTHORIZATION_BEARER = "Bearer";

	private SecurityUtils() {}

	/**
	 * 从请求中，获得认证 Token
	 *
	 * @param request 请求
	 * @param header  认证 Token 对应的 Header 名字
	 * @return 认证 Token
	 */
	public static String obtainAuthorization(HttpServletRequest request, String header) {
		String authorization = request.getHeader(header);
		if (!StringUtils.hasText(authorization)) {
			return null;
		}
		int index = authorization.indexOf(AUTHORIZATION_BEARER + " ");
		// 未找到返回 null
		if (index == -1) {
			return null;
		}
		return authorization.substring(index + 7).trim();
	}

	/**
	 * 获取当前认证信息
	 *
	 * @return 认证信息
	 */
	public static Authentication getAuthentication() {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return null;
		}
		return context.getAuthentication();
	}

	/**
	 * 获取当前用户
	 *
	 * @return 当前用户
	 */
	public static LoginUser getLoginUser() {
		Authentication authentication = getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;
	}

	/**
	 * 获取当前登录用户ID
	 *
	 * @return 当前登录用户ID
	 */
	public static Long getLoginUserId() {
		LoginUser loginUser = getLoginUser();
		return loginUser != null ? loginUser.getId() : null;
	}

	/**
	 * 设置当前用户
	 * @param loginUser 当前用户
	 * @param request 请求
	 */
	public static void setLoginUser(LoginUser loginUser, HttpServletRequest request) {
		// 创建 Authentication，并设置到上下文
		Authentication authentication = buildAuthentication(loginUser, request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private static Authentication buildAuthentication(LoginUser loginUser, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginUser, null, Collections.emptyList());
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return authenticationToken;
	}

}
