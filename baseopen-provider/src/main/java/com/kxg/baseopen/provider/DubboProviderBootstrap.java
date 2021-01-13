package com.kxg.baseopen.provider;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
@EnableScheduling
@MapperScan("com.kxg.baseopen.provider.mapper")
@ImportResource(locations = {"classpath:spring-dubbo.xml"})
@EnableSwagger2
@NacosPropertySource(dataId = "open_base", autoRefreshed = true)
@SpringBootApplication
public class DubboProviderBootstrap  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboProviderBootstrap.class)
                .run(args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DubboProviderBootstrap.class);
    }
}