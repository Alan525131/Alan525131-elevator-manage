package org.lufengxue.oauth.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 作 者: 陆奉学
 * 工 程 名:  elevator
 * 包    名:  org.lufengxue.config
 * 日    期:  2022-03-2022/3/29
 * 时    间:  15:39
 * 描    述:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "jks")
public class JKSProperties {
        /**
         * jks文件的名称
         */
        private String name;
        /**
         * 存储密码
         */
        private String storepassword;

        /**
         * 秘钥密码
         */
        private String keypassword;
        /**
         * 别名
         */
        private String alias;
}
