package com.lydck.xml;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLTest {
	public static void main(String[] args) throws SAXException, IOException {
		//解析器实例
		XMLReader reader = XMLReaderFactory.createXMLReader();
		//注册内容处理程序
		reader.setContentHandler(new MyHandler());
		//解析
		InputSource inputSource = new InputSource("E:/github/study-spring/src/main/resources/xml/user.xml");
		reader.parse(inputSource);
	}
}
class MyHandler extends DefaultHandler {
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
		System.out.println("开始startElement事件");
		System.out.println("namespaceURI:" + namespaceURI);
		System.out.println("localName:" + localName);
		System.out.println("qName:" + qName);
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		System.out.println("结束endElement事件");
		System.out.println("namespaceURI:" + namespaceURI);
		System.out.println("localName:" + localName);
		System.out.println("qName:" + qName);
	}

	@Override
	public void characters(char[] data, int start, int end) throws SAXException {
		String cont = new String(data, start, end);
		System.out.println("characters");
		System.out.println(cont);
	}
	
}