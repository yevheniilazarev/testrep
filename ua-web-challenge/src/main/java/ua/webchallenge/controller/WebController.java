package ua.webchallenge.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ua.webchallenge.logic.XmlLogic;
import ua.webchallenge.logic.XmlLogicImpl;

@Controller
public class WebController {
/*
 1 - парсить урлы до 3-го уровня вложенности;
 2 - сделать многопоточную обработку урлов;
 3 - сделать поддержку картинок-ссылок;
 4 - сделать обработку анализа текущей страницы (главная или нет, 
 если главная, то уровень вложенности 1-ый
 и ищем в нем ссылки (2-й ур-нь),а в тех ссылках - ссылки(3-й ур-нь)); 
 / альтернатива - исходную считать за главную
 если не главная, то парсим название до главного и с той страницы выбираем ссылки;
 5 - заюзать log4j когда возникают ексепшены+сделать обработку ошибок;
 6 - реализовать так, что если ссылок больше 50, то генерировать зип-архив 
 с несколькими файлами sitemap1/2/3.xml (по 50 в каждом)
 */
	@RequestMapping(value = "/url", method = RequestMethod.GET)
	public ModelAndView generateLinkForSiteMapXml(Map<String, String> map,
			@RequestParam(value = "urlName") String urlName) {
		XmlLogic xli = new XmlLogicImpl("http://www." + urlName);
		ModelAndView mav = new ModelAndView("home");
		map.put("url", "Save file sitemap.xml for URL " + xli.getLinkNameXml());
		mav.addObject(map);
		return mav;
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView getStartPage() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}

	@RequestMapping(value = "/downloadfile", method = RequestMethod.GET)
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		String filename = "/home/eugene/workspace/ua-web-challenge/xml_sitemap/sitemaps.xml";
		File f = new File(filename);
		response.setContentType("application/xml");
		response.setHeader("Content-Disposition",
				"attachment; filename=sitemaps.xml");
		try {
			FileCopyUtils.copy(new FileInputStream(f),
					response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
