package com.mcp.crispy;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAsyncpp
@EnableScheduling
@MapperScan("com.mcp.crispy.*")
@SpringBootApplication
public class CrispyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrispyApplication.class, args);
	}

}
