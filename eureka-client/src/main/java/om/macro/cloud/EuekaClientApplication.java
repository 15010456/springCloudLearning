package om.macro.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 一个简单的注册中心客户端，要注册到eureka服务器（集群）才能被其他客户端发现，才能获取其他客户端
 */
@EnableEurekaClient//表明是一个Eureka客户端
@SpringBootApplication
public class EuekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuekaClientApplication.class, args);
	}

}
