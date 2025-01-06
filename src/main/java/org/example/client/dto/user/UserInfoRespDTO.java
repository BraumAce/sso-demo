package org.example.client.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 获取用户基本信息 Response DTO
 *
 * @author liuguang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRespDTO {

	/**
	 * 用户ID
	 */
	private Long id;

	/**
	 * 用户账户
	 */
	private String username;

	/**
	 * 用户昵称
	 */
	private String nickname;

	/**
	 * 用户邮箱
	 */
	private String email;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 用户性别
	 */
	private Integer sex;

	/**
	 * 用户头像
	 */
	private String avatar;

	/**
	 * 所在部门
	 */
	private Dept dept;

	/**
	 * 所属岗位组
	 */
	private List<Post> posts;

	/**
	 * 部门
	 */
	@Data
	public static class Dept {

		/**
		 * 部门ID
		 */
		private Long id;

		/**
		 * 部门名称
		 */
		private String name;
	}

	/**
	 * 岗位
	 */
	@Data
	public static class Post {

		/**
		 * 岗位ID
		 */
		private Long id;

		/**
		 * 岗位名称
		 */
		private String name;
	}

}
