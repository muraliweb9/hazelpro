package me.synology.murali.hazelpro.model;

import static com.synology.murali.corelib.utils.DateUtils.localDateFromStr;
import static com.synology.murali.corelib.utils.DateUtils.SHORT_DATE_PATTERN;

import java.io.Serializable;
import java.time.LocalDate;
import com.synology.murali.corelib.utils.DateUtils;


public class PnlProduction implements Serializable {
	
	private static final long serialVersionUID = -5884177779678155703L;
	private Org org;
	private LocalDate cob;
	
	
	public PnlProduction(String orgAlias, String orgDomain, String cobStr) {
		this.org = new Org(orgAlias, orgDomain);
		this.cob = localDateFromStr(SHORT_DATE_PATTERN, cobStr);
	}
	
	
	public PnlProduction(String orgAlias, String orgDomain, LocalDate cob) {
		this.org = new Org(orgAlias, orgDomain);
		this.cob = cob;
	}
	
	
	public PnlProduction(Org org, LocalDate cob) {
		this.org = org;
		this.cob = cob;
	}
	
	
	public Org getOrg() {
		return org;
	}
	
	
	public void setOrg(Org org) {
		this.org = org;
	}
	
	
	public String getAlias() {
		return org.getAlias();
	}
	
	
	public String getDomain() {
		return org.getDomain();
	}
	
	
	public LocalDate getCob() {
		return cob;
	}
	
	
	public void setCob(LocalDate cob) {
		this.cob = cob;
	}
	
	
	public String toString() {
		return org.toString() + DateUtils.localDateToString(SHORT_DATE_PATTERN, cob);
	}
	
	
	public int hashCode() {
		return toString().hashCode();
	}
	
}
