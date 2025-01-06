package org.example.controller;

import cn.hutool.core.util.StrUtil;
import org.example.client.OAuth2Client;
import org.example.client.dto.CommonResult;
import org.example.client.dto.oauth2.OAuth2AccessTokenRespDTO;
import org.example.framework.core.util.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author liuguang
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Resource
	private OAuth2Client oauth2Client;

	/**
	 * 使用 code 访问令牌，获取访问令牌
	 * @param code 授权码
	 * @param redirectUri 重定向URI
	 * @return 访问令牌
	 */
	@PostMapping("/login-by-code")
	public CommonResult<OAuth2AccessTokenRespDTO> loginByCode(@RequestParam("code") String code,
	                                                          @RequestParam("redirectUri") String redirectUri) {
		return oauth2Client.postAccessToken(code, redirectUri);
	}

	/**
	 * 使用刷新令牌，获取（刷新）访问令牌
	 * @param refreshToken 刷新令牌
	 * @return 访问令牌
	 */
	@PostMapping("/refresh-token")
	public CommonResult<OAuth2AccessTokenRespDTO> refreshToken(@RequestParam("refreshToken") String refreshToken) {
		return oauth2Client.refreshToken(refreshToken);
	}

	@PostMapping("/logout")
	public CommonResult<Boolean> logout(HttpServletRequest request) {
		String token = SecurityUtils.obtainAuthorization(request, "Authorization");
		if (StrUtil.isNotBlank(token)) {
			return oauth2Client.revokeToken(token);
		}
		// 返回成功
		return new CommonResult<>();
	}

}
