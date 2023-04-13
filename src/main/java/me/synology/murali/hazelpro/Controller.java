package me.synology.murali.hazelpro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Set;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;

import me.synology.murali.hazelpro.model.PnlProduction;


public class Controller {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
	
	
	public static void main(String[] args) {
		
		Node node = Node.getNode();
		
		IMap<Object, Object> mapCustomers = node.getMap("customers");
		mapCustomers.put(1, "Joe");
		mapCustomers.put(2, "Ali");
		mapCustomers.put(3, "Avi");
		
		LOGGER.info("Map size is " + mapCustomers.size());
		
		Queue<String> queue = node.getQueue("events");
		for (int i = 0; i < 10; i++) {
			queue.offer("PP_done" + (i + 1));
			LOGGER.info("Offered: " + "PP_done" + (i + 1));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		
		IMap<String, PnlProduction> pps = node.getMap("pps");
		pps.put("ipv1", new PnlProduction("ORG1", "DMN-1", "01/12/15"));
		pps.put("ipv2", new PnlProduction("ORG2", "DMN-1", "01/12/15"));
		pps.put("ipv3", new PnlProduction("ORG3", "DMN-1", "01/12/15"));
		pps.put("ipv4", new PnlProduction("ORG4", "DMN-2", "01/12/15"));
		pps.put("ipv5", new PnlProduction("ORG5", "DMN-2", "01/12/15"));
		pps.put("ipv6", new PnlProduction("ORG6", "DMN-2", "01/12/15"));
		pps.put("ipv7", new PnlProduction("ORG1", "DMN-1", "02/12/15"));
		pps.put("ipv8", new PnlProduction("ORG2", "DMN-1", "02/12/15"));
		pps.put("ipv9", new PnlProduction("ORG3", "DMN-1", "03/12/15"));
		pps.put("ipv10", new PnlProduction("ORG4", "DMN-2", "03/12/15"));
		pps.put("ipv", new PnlProduction("ORG5", "DMN-2", "03/12/15"));
		pps.put("ipv", new PnlProduction("ORG6", "DMN-2", "03/12/15"));
		
	}
	
}
