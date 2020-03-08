package com.dmj.adminweb.config.dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: admin-web
 * @description: 将dozer的转换器对象加载到Spring容器中
 * @author: Mr.Zhang
 * @create: 2019-09-16 09:22
 **/
@Configuration
public class DozerBeanMapperConfigure {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
