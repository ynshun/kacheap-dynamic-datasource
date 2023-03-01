package com.kacheap.datasource;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.ecwid.consul.v1.health.model.HealthService.Service;
import com.kacheap.datasource.properties.DynamicDataSourceProperties;
import com.kacheap.datasource.util.ConsulUtils;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private static DynamicDataSourceProperties properties;
	private static DruidDataSource instance = null;

	public DynamicDataSource(DynamicDataSourceProperties properties) {
		DynamicDataSource.properties = properties;
	}
	
	
	/**
	 * 获取当前可用的数据源
	 * @return
	 */
	public static DataSource getDataSource() {
		Service service = ConsulUtils.getHealthService(properties.getConsulHost(), properties.getConsulPort(), properties.getConsulService());
		
		String url = "jdbc:mysql://{host}:{port}/{database}?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true"
							.replaceAll("\\{host\\}", service.getAddress())
							.replaceAll("\\{port\\}", String.valueOf(service.getPort()))
							.replaceAll("\\{database\\}", properties.getDatabase());
		
		if (null == instance || !url.equals(instance.getUrl())) {
			synchronized (DynamicDataSource.class) {
				if (null == instance || !url.equals(instance.getUrl())) {
					instance = new DruidDataSource();
					instance.setUrl(url);
					instance.setDbType(properties.getDbType());
					instance.setUsername(properties.getUsername());
					instance.setPassword(properties.getPassword());
					instance.setDriverClassName(properties.getDriverClassName());
				}
			}
		}
		
		return instance;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return null;
	}

	@Override
	protected DataSource determineTargetDataSource() {
		return getDataSource();
	}

}