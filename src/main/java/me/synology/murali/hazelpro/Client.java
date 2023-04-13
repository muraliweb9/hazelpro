package me.synology.murali.hazelpro;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.MultiMap;


public class Client {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
	
	private static Client instance = null;
	private final HazelcastInstance hzClientInstance;
	
	
	private Client() {
		ClientConfig clientConfig = getConfig();
		hzClientInstance = HazelcastClient.newHazelcastClient(clientConfig);
		
	}
	
	
	private ClientConfig getConfig() {
		return networkConfig(new ClientConfig());
	}
	
	
	private ClientConfig networkConfig(ClientConfig clientConfig) {
		ClientNetworkConfig netConfig = clientConfig.getNetworkConfig();
		netConfig.addAddress("127.0.0.1:5701");
		netConfig.addAddress("127.0.0.1:5702");
		netConfig.addAddress("127.0.0.1:5703");
		return clientConfig;
	}
	
	
	public static synchronized Client getClient() {
		if (instance == null) {
			instance = new Client();
		}
		return instance;
	}
	
	
	public <K, V> IMap<K, V> getMap(String nameSpace) {
		return hzClientInstance.getMap(nameSpace);
	}
	
	
	public <K, V> MultiMap<K, V> getMultiMap(String nameSpace) {
		return hzClientInstance.getMultiMap(nameSpace);
	}
	
	
	public IQueue<String> getQueue(String nameSpace) {
		return hzClientInstance.getQueue(nameSpace);
	}
	
	
	public <E> IList<E> getList(String nameSpace) {
		return hzClientInstance.getList(nameSpace);
	}
	
}
