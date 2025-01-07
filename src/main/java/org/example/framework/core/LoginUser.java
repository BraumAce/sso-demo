package org.example.framework.core;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 登录用户信息
 *
 * @author liuguang
 */
@Data
@Accessors(chain = true)
public class LoginUser {

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 用户类型
	 */
	private Integer userType;

	/**
	 * 租户ID
	 */
	private Long tenantId;

	/**
	 * 授权范围
	 */
	private List<String> scopes;

	/**
	 * 访问令牌
	 */
	private String accessToken;

}
