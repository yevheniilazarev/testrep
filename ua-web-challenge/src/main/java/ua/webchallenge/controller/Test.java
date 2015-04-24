package ua.webchallenge.controller;

import ua.webchallenge.logic.XmlLogic;
import ua.webchallenge.logic.XmlLogicImpl;

public class Test {

	public static void main(String[] args) {
		XmlLogic xl = new XmlLogicImpl("https://www.google.com.ua");
		xl.getLinkNameXml();
	}

}
