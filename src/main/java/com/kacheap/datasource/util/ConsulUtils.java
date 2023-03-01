package com.kacheap.datasource.util;

import java.util.List;
import java.util.Random;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;
import com.ecwid.consul.v1.health.model.HealthService.Service;

public class ConsulUtils {
	private static HealthServicesRequest request = HealthServicesRequest.newBuilder()
																	.setPassing(true)
																	.setQueryParams(QueryParams.DEFAULT)
																.build();
	
	private static ConsulClient client = null;
	
	private static Random random = new Random();

	/**
	 * 根据key获取value
	 */
	public static Service getHealthService(String host, int port, String serviceName) {
		
		List<HealthService> services = null;
		try {
			
			// 初始化
			if (null == client) {
				synchronized (ConsulUtils.class) {
					if (null == client) {
						client = new ConsulClient(host, port);
					}
				}
			}
			
			services = client.getHealthServices(serviceName, request).getValue();

			if (null == services || services.size() == 0) {
				return null;
			}

			int index = random.nextInt(services.size());

			return services.get(index).getService();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != services) {
				services.clear();
				services = null;
			}
		}
		return null;
	}

}