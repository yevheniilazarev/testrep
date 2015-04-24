package ua.web.challenge.entity;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"loc","lastmod"})
public class UrlNew {
	private String loc;
	private String lastmod;
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getLastmod() {
		return lastmod;
	}
	public void setLastmod(String lastmod) {
		this.lastmod = lastmod;
	}
}