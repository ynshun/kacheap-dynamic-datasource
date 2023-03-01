package com.kacheap.datasource.properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kacheap.datasource")
@ConditionalOnProperty({ 
		"spring.kacheap.datasource.consulHost", 
		"spring.kacheap.datasource.consulPort",
		"spring.kacheap.datasource.consulService" })
public class DynamicDataSourceProperties {

	// Consul服务端口
	private int consulPort = 8500;

	// Consul服务地址
	private String consulHost = "internal-SLB-consul-1387779336.us-west-1.elb.amazonaws.com";

	// Consul中服务名称
	private String consulService = "mysql-charge-write";

	// 数据库名
	private String database;

	// 数据库账号
	private String username;

	// 数据库密码
	private String password;

	// 数据库类型
	private String dbType = "mysql";

	// 数据库驱动类名
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getConsulService() {
		return consulService;
	}

	public void setConsulService(String consulService) {
		this.consulService = consulService;
	}

	public int getConsulPort() {
		return consulPort;
	}

	public void setConsulPort(int consulPort) {
		this.consulPort = consulPort;
	}

	public String getConsulHost() {
		return consulHost;
	}

	public void setConsulHost(String consulHost) {
		this.consulHost = consulHost;
	}

}