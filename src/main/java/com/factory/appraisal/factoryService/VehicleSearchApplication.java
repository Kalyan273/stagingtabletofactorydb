package com.factory.appraisal.factoryService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;*/

//@EnableElasticsearchRepositories(basePackages = "com.factory.appraisal.vehiclesearchapp.repository")

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.factory.appraisal.factoryService")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableAsync
public class  VehicleSearchApplication extends SpringBootServletInitializer {


	@Override

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VehicleSearchApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(VehicleSearchApplication.class, args);

	}



}
