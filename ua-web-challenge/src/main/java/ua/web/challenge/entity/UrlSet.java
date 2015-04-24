package ua.web.challenge.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="urlset")
public class UrlSet {
	private final String urlSet = "http://www.sitemaps.org/schemas/sitemap/0.9";
	private List <UrlNew> url = new ArrayList<UrlNew>();
	@XmlElement(name="url")
	public List <UrlNew> getList() {
		return url;
	}
	public void setList(List <UrlNew> url) {
		this.url = url;
	}
	@XmlAttribute(name="xmlns")
	public String getUrlSet() {
		return urlSet;
	}
}
