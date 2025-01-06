package org.example.framework.config;

import org.example.framework.core.filter.TokenAuthenticationFilter;
import org.example.framework.core.handler.AccessDeniedHandlerImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

/**
 * @author liuguang
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecurityConfiguration{

	@Resource
	private AccessDeniedHandlerImpl accessDeniedHandler;

	@Resource
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Resource
	private TokenAuthenticationFilter tokenAuthenticationFilter;

	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// 设置 URL 安全权限
		httpSecurity.csrf().disable() // 禁用 CSRF 保护
				.authorizeHttpRequests()
				// 1. 静态资源，可匿名访问
				.antMatchers(HttpMethod.GET, "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
				// 2. 登录相关的接口，可匿名访问
				.antMatchers("/auth/login-by-code").permitAll()
				.antMatchers("/auth/refresh-token").permitAll()
				.antMatchers("/auth/logout").permitAll()
				// last，兜底规则，必须认证
				.and().authorizeRequests()
				.anyRequest().authenticated();

		// 设置处理器
		httpSecurity.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPoint);

		// 添加 Token Filter
//		httpSecurity.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}