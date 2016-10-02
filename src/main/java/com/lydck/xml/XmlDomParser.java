package com.lydck.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlDomParser {
	//要读取的xml文件
	private File inputXML;
	//换行符
	private String lineSeparator = "\n";
	public XmlDomParser(File inputXML) {
		super();
		this.inputXML = inputXML;
	}
	
	public void parse(String outputFile) throws FileNotFoundException, SAXException, IOException {
		DOMParser parser = new DOMParser();
		//以Document对象形式获取DOM树
		parser.parse(new InputSource(new FileInputStream(inputXML)));
		Document document = parser.getDocument();
		//序列化
		serialize(document, new FileWriter(outputFile));
	}
	
	public void serialize(Document doc, Writer writer) throws IOException{
		serizlizeNod(doc, writer, "");
		writer.flush();
		
	}
	private void serizlizeNod(Node node, Writer writer, String indentLevel) throws IOException{
		//按节点类型确定操作
		switch (node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			Document doc = (Document)node;
			writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			writer.write(lineSeparator);
			serizlizeNod(doc.getDocumentElement(), writer, "");
			break;
		case Node.ELEMENT_NODE:
			String nodeName = node.getNodeName();
			writer.write(indentLevel + "<" + nodeName);
			NamedNodeMap attributes = node.getAttributes();
			for(int i = 0; i < attributes.getLength(); i++) {
				Node current = attributes.item(i);
				writer.write(" " + current.getNodeName() + "=\"");
				print(writer, current.getNodeValue());
				writer.write("\"");
			}
			writer.write(">");
			//遍历子节点
			NodeList childNodes = node.getChildNodes();
			if(childNodes != null) {
				if(childNodes.item(0) != null && childNodes.item(0).getNodeType() == Node.ELEMENT_NODE) {
					writer.write(lineSeparator);
				}
				for(int i = 0; i < childNodes.getLength(); i++) {
					serizlizeNod(childNodes.item(i), writer, indentLevel + "");
				}
				if(childNodes.item(0) != null && childNodes.item(childNodes.getLength() - 1).getNodeType() == Node.ELEMENT_NODE) {
					writer.write(indentLevel);
				}
			}
			writer.write("</" + nodeName + ">"); 
			writer.write(lineSeparator);
		default:
			break;
		}
	}
	private void print(Writer writer, String s) throws IOException {
		if(s == null)
			return;
		for(int i = 0, len = s.length(); i < len; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				writer.write("&lt;");
				break;
			case '>':
				writer.write("&gt;");
				break;
			case '&':
				writer.write("&amp;");
				break;
			case '\r':
				writer.write("&#xD;");
				break;
			default:
				writer.write(c);
			}
		}
	}
	public static void main(String[] args) throws FileNotFoundException, SAXException, IOException {
		new XmlDomParser(new File("E:/github/study-spring/src/main/resources/xml/user.xml")).parse("a.xml");
	}
}
