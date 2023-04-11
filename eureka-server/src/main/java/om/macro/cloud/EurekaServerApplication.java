package om.macro.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *  eureka注册中心
 *  (1)applicition.yml 一个eureka注册中心例子
 *  (2)applicition-replical1.yml 和(3)applicition-replical2.yml 形成一个注册中心集群
 *
 */
@EnableEurekaServer//注解来启用Euerka注册中心功能
@SpringBootApplication
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
