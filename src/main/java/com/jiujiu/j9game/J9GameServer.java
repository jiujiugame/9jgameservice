package com.jiujiu.j9game;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.jiujiu.j9game.mapper")
public class J9GameServer {

	public static void main(String[] args) {
        SpringApplication.run(J9GameServer.class, args);
	}

}
