package me.synology.murali.hazelpro.model;

import java.io.Serializable;


public class Org implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5362193353021083630L;
	private String alias;
	private String domain;
	
	
	public Org(String alias, String domain) {
		this.alias = alias;
		this.domain = domain;
	}
	
	
	public String getAlias() {
		return alias;
	}
	
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	public String getDomain() {
		return domain;
	}
	
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	public String toString() {
		return alias + domain;
	}
	
	
	public int hashCode() {
		return toString().hashCode();
	}
	
}
