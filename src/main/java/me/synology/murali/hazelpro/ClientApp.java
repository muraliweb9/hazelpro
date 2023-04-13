package me.synology.murali.hazelpro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import com.hazelcast.core.MultiMap;
import com.hazelcast.query.Predicate;

import me.synology.murali.hazelpro.model.PnlProduction;


public class ClientApp {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Client client = Client.getClient();
		
		IMap<Object, Object> map = client.getMap("customers");
		LOGGER.info("Map size is " + map.size());
		for (Entry<Object, Object> entry : map.entrySet()) {
			LOGGER.info("Key=" + entry.getKey() + ", value=" + entry.getValue());
		}
		
		IQueue<String> queue = client.getQueue("events");
		
		// while (true) {
		// LOGGER.info("Taken: " + queue.take());
		// }
		
		Predicate<String, String> pred = PredicateHelper.predicate("domain", "DMN-1");
		
		IMap<String, PnlProduction> pps = client.getMap("pps");
		
		for (PnlProduction pp : pps.values(pred)) {
			LOGGER.info("PnlProduction=" + pp);
		}
		
	}
	
}
