package me.synology.murali.hazelpro;

import java.util.Arrays;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;


public class Node {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Node.class);
	
	private static Node instance = null;
	private final HazelcastInstance hzInstance;
	
	
	private Node() {
		Config config = getConfig();
		hzInstance = Hazelcast.newHazelcastInstance(config);
	}
	
	
	private Config getConfig() {
		return joinConfig(new Config());
	}
	
	
	private Config joinConfig(Config config) {
		JoinConfig join = config.getNetworkConfig().getJoin();
		
		join.getMulticastConfig().setEnabled(false);
		join.getTcpIpConfig().setEnabled(true);
		join.getTcpIpConfig().setMembers(Arrays.asList(new String[] { "127.0.0.1" }));
		return config;
		
	}
	
	
	public static synchronized Node getNode() {
		if (instance == null) {
			instance = new Node();
		}
		return instance;
	}
	
	
	public <K, V> IMap<K, V> getMap(String nameSpace) {
		return hzInstance.getMap(nameSpace);
	}
	
	
	public <K, V> MultiMap<K, V> getMultiMap(String nameSpace) {
		return hzInstance.getMultiMap(nameSpace);
	}
	
	
	public <E> IList<E> getList(String nameSpace) {
		return hzInstance.getList(nameSpace);
	}
	
	
	public Queue<String> getQueue(String nameSpace) {
		return hzInstance.getQueue(nameSpace);
	}
	
}
