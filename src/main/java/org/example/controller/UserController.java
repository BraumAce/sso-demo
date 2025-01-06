package org.example.controller;

import org.example.client.UserClient;
import org.example.client.dto.CommonResult;
import org.example.client.dto.user.UserInfoRespDTO;
import org.example.client.dto.user.UserUpdateReqDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liuguang
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserClient userClient;

	/**
	 * 获取当前登录用户的登录信息
	 *
	 * @return 用户信息
	 */
	@GetMapping("/get")
	public CommonResult<UserInfoRespDTO> getUser() {
		return userClient.getUser();
	}

	/**
	 * 更新当前登录用户的昵称
	 *
	 * @param nickname 昵称
	 * @return 成功
	 */
	@PutMapping("/update")
	public CommonResult<Boolean> updateUser(@RequestParam("nickname") String nickname) {
		UserUpdateReqDTO updateReqDTO = new UserUpdateReqDTO(nickname, null, null, null);
		return userClient.updateUser(updateReqDTO);
	}

}
