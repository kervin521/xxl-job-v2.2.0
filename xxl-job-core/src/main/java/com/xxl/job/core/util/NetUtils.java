package com.xxl.job.core.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 项目: SF_Common
 * 描述: Docker网络
 * 作者: zhangyi183790
 * 时间: 2019年3月20日 下午1:52:54
 * 版本: v1.0
 * JDK: 1.8
 */
public class NetUtils {
	private static Logger logger = LoggerFactory.getLogger(NetUtils.class);
	/**
	 * 描述: 获取本地IP
	 * 
	 * @author ZhangYi
	 * @date 2019-06-20 14:43:34
	 * @return
	 * @throws SocketException
	 */
	public static String getLocalHost() throws SocketException {
		String local = "127.0.0.1";
		Map<String, NetworkInterface> networks = networks();
		for (Map.Entry<String, NetworkInterface> entry : networks.entrySet()) {
			String host = entry.getKey();
			NetworkInterface network = entry.getValue();
			// 网卡名称
			String netname = System.getProperty("network.name");
			if(StringUtils.isEmpty(netname)) {
			    netname = System.getenv("network.name");
			}
			if(StringUtils.isEmpty(netname)) {
			    netname = System.getProperty("NETWORK_NAME");
			}
			if(StringUtils.isEmpty(netname)) {
			    netname = System.getenv("NETWORK_NAME");
			}
			logger.info("==>>>netname:{},host:{},name:{},displayName:{}",netname,host,network.getName(),network.getDisplayName());
			if(!StringUtils.isEmpty(netname)&&(netname.equalsIgnoreCase(network.getName())||netname.equalsIgnoreCase(network.getDisplayName()))) {
			    local = host;
			    logger.info("++>>>netname:{},host:{},name:{},displayName:{}",netname,host,network.getName(),network.getDisplayName());
			    break;
			}
			if (network.isLoopback() || network.isVirtual()) {
				continue;
			}
			String[] splits = host.split("\\.");
			if (splits.length != 4) {
				continue;
			}
			if (!(host.startsWith("192.168") || host.startsWith("172.") && Integer.valueOf(splits[1]) >= 16 && Integer.valueOf(splits[1]) <= 31 || host.startsWith("10.") && Integer.valueOf(splits[1]) >= 0 && Integer.valueOf(splits[1]) <= 255)) {
				continue;
			}
			if (host.endsWith(".0") || host.endsWith(".1") || host.endsWith(".255") || host.endsWith(".254")) {
				continue;
			}
			if (!StringUtils.isEmpty(network.getDisplayName()) && network.getDisplayName().toLowerCase().contains("loopback")) {
				continue;
			}
			local = host;
		}
		return local;
	}
	/**
	 * 
	 * 描述: 获取主机所有地址对应网卡信息
	 * 
	 * @author ZhangYi
	 * @date 2019-06-20 14:44:07
	 * @return
	 */
	public static Map<String, NetworkInterface> networks() {
		Map<String, NetworkInterface> networks = new HashMap<String, NetworkInterface>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			NetworkInterface network;
			Enumeration<InetAddress> inetAddresses;
			InetAddress inetAddress;
			String host;
			while (networkInterfaces.hasMoreElements()) {
			    network = networkInterfaces.nextElement();
				inetAddresses = network.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					if (inetAddress instanceof Inet4Address) { // IPV4
					    host = inetAddress.getHostAddress();
						networks.put(host, network);
						logger.info("--host:{},name:{},displayName:{},Loopback:{},PointToPoint:{},Up:{},Virtual:{},Index:{},MTU:{}",host,network.getName(),network.getDisplayName(),network.isLoopback(),network.isPointToPoint(),network.isUp(),network.isVirtual(),network.getIndex(),network.getMTU());
					}
				}
			}
		} catch (SocketException e) {
			logger.error("--Get networks Error!",e);
		}
		return networks;
	}

	public static void main(String[] args) throws SocketException {
		String ip = getLocalHost();
		System.out.println(ip);
	}
}
