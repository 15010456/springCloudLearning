package om.macro.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  带登录认证的eureka注册中心
 *需要添加SpringSecurity模块，为带eureka注册中心添加登录认证
 *访问eureka页面http://localhost:8004时需要按配置的用户名和密码才能登录进eureka应用管理页面
 */
@EnableEurekaServer//注解来启用Euerka注册中心功能
@SpringBootApplication
public class EurekaSecurityServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSecurityServerApplication.class, args);
	}

}
