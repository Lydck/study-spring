package com.lydck.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lydck.domain.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamSample {
	private static XStream xstream;
	static {
		xstream = new XStream(new DomDriver());
		xstream.alias("user", User.class);
		xstream.aliasAttribute(User.class, "id", "id");
		xstream.registerConverter(new DateConvert());
	}
	//Java对象转化为XML
	public static void objToXML(Object obj) throws IOException {
		String simpleName = obj.getClass().getSimpleName() + ".xml";
		FileOutputStream outputStream = new FileOutputStream(simpleName, true);
		xstream.toXML(obj, outputStream);
	}
	//XML转化为Java对象
	public static void XMLToObj(String xmlPath) throws FileNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(xmlPath);
		User user = (User) xstream.fromXML(fileInputStream);
		System.out.println(user);
	}
	//Java对象转化为Jason
	public static void objToJson(Object obj) throws FileNotFoundException {
		String simpleName = obj.getClass().getSimpleName() + ".json";
		FileOutputStream outputStream = new FileOutputStream(simpleName, true);
		xstream = new XStream(new JsonHierarchicalStreamDriver());
		xstream.alias("user", User.class);
		xstream.toXML(obj, outputStream);
	}
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("hehehe");
		user.setScore(99);
		user.setLastLoginTime(new Date());
//		objToXML(user);
//		XMLToObj("User.xml");
		objToJson(user);
	}
}
class DateConvert implements Converter {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		boolean result = Date.class.isAssignableFrom(type);
		return result;
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		writer.setValue(dateFormat.format(source));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		String value = reader.getValue();
		Object date = null;
		try {
			date = dateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
