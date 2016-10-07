package com.lydck.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.lydck.domain.User;

public class JAXBSample {
	//Java对象转化为xml对象
	public static void objToXml(Object obj) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(User.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		String simpleName = obj.getClass().getSimpleName() + "_JAXB.xml";
		FileWriter fileWriter = new FileWriter(simpleName);
		marshaller.marshal(obj, fileWriter);
	}
	//xml转换为Java对象
	public static void XMLToObj(String xmlPath) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(User.class);
		FileReader reader = new FileReader(xmlPath);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		User user = (User) unmarshaller.unmarshal(reader);
		System.out.println(user);
		
	}
	public static void main(String[] args) throws JAXBException, IOException {
		/*User user = new User();
		user.setId(1);
		user.setName("hehehe");
		user.setScore(99);
		user.setLastLoginTime(new Date());*/
		
//		objToXml(user);
		XMLToObj("User_JAXB.xml");
	}
}
