package com.kacheap.datasource;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kacheap.datasource.properties.DynamicDataSourceProperties;
import com.kacheap.datasource.properties.GeneralDataSourceProperties;


@Configuration
@ConditionalOnBean(DynamicDataSourceProperties.class)
@ConditionalOnMissingBean(GeneralDataSourceProperties.class)
public class DataSourceConfig {
	@Autowired
	private DynamicDataSourceProperties dynamicDataSourceProperties;

	@Bean
	@ConditionalOnMissingBean
	public DataSource dataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource(dynamicDataSourceProperties);
		dynamicDataSource.setTargetDataSources(new HashMap<Object, Object>());
		return dynamicDataSource;
	}

}