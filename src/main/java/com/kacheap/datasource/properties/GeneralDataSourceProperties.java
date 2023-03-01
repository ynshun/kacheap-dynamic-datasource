package com.kacheap.datasource.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;

@Component
@ConditionalOnProperty("spring.datasource.url")
@Import({ DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class })
public class GeneralDataSourceProperties {

}