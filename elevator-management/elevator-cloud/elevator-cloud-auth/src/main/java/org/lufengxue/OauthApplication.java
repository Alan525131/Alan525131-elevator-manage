package org.lufengxue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue
 * 日    期:  2022-03-2022/3/29
 * 时    间:  8:07
 * 描    述:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "org.lufengxue.user.feign")
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class,args);
    }
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
