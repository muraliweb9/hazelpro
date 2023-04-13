package me.synology.murali.hazelpro;

import com.hazelcast.query.EntryObject;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.PredicateBuilder;


public class PredicateHelper {
	
	public static <K, V> Predicate<K, V> predicate(String attrib, String val) {
		EntryObject eo = new PredicateBuilder().getEntryObject();
		Predicate<K, V> pred = eo.get(attrib).equal(val);
		return pred;
		
	}
	
}
