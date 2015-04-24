package ua.webchallenge.logic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ua.web.challenge.entity.UrlNew;
import ua.web.challenge.entity.UrlSet;

import java.net.URL;
import java.net.URLConnection;

public class XmlLogicImpl implements XmlLogic {
	private String urls = null;
	private String LinuxProfile = "eugene";
	private final String filePath = "/home/"+LinuxProfile+"/workspace/ua-web-challenge/xml_sitemap/sitemaps.xml";
	private final Logger logger = Logger
			.getLogger(XmlLogicImpl.class.getName());

	public XmlLogicImpl(String url) {
		this.urls = url;
	}

	private List<UrlNew> getInnerUrlsList() {
		List<UrlNew> listUrls = new ArrayList<UrlNew>();
		URL url = null;
		try {
			Document doc = Jsoup.connect(urls).get();
			Elements links = doc.select("a");
			int i = 0;
			for (Element el : links) {
				if (el.attr("href").startsWith(urls)) {
					i++;
					url = new URL(el.attr("href"));
					URLConnection conn = url.openConnection();
					listUrls.add(getUrl(el.attr("href"),
							conn.getHeaderField("Date")));

				} else if (el.attr("href").startsWith("/")) {
					i++;
					url = new URL(urls + el.attr("href"));
					URLConnection conn = url.openConnection();
					listUrls.add(getUrl(urls + el.attr("href"),
							conn.getHeaderField("Date")));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return listUrls;
	}

	private UrlNew getUrl(String urls, String lastMod) {
		UrlNew url = new UrlNew();
		url.setLoc(urls);
		url.setLastmod(lastMod);
		return url;
	}

	private UrlSet getUrlSet() {
		UrlSet us = new UrlSet();
		us.setList(getInnerUrlsList());
		return us;
	}

	private void marshallObj() {
		try {
			File f = new File(filePath);
			/*
			 * Handler h = new FileHandler(".\\logs\\XmlTransform.log");
			 * Logger.getLogger(XmlLogicImpl.class.getName()).addHandler(h);
			 * loger.log(Level.INFO, f.getCanonicalPath());
			 */
			JAXBContext context = JAXBContext.newInstance(UrlSet.class);
			Marshaller marshall = context.createMarshaller();
			marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshall.marshal(getUrlSet(), f);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getLinkNameXml() {
		marshallObj();
		return urls;
	}

}
