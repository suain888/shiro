package com.dmj.adminweb.config.dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *将dozer的转换器对象加载到Spring容器中
 *
 **/
@Configuration
public class DozerBeanMapperConfigure {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
