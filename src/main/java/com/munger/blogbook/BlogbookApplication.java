package com.munger.blogbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //定时执行爬取任务  todo 学习了解
public class BlogbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogbookApplication.class, args);
	}

}
