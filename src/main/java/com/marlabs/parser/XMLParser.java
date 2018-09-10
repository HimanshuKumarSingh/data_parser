package com.marlabs.parser;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.marlabs.beans.ListOfUsers;
import com.marlabs.beans.User;
import com.marlabs.log.LogTrace;

public class XMLParser {
//	--------------WRITE XML FILE FROM JAVA BEANS---------------------------------------
	public static void writeXML(List<User> user,String path) {
		ListOfUsers listOfUsers = new ListOfUsers();
		listOfUsers.setUsers(new ArrayList<User>());
		for (User u : user) {
			listOfUsers.getUsers().add(u);
		}
		try {
			JAXBContext context = JAXBContext.newInstance(ListOfUsers.class);
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.marshal(listOfUsers,new FileWriter(path,false));
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
//      ----------write on console---------------------------
//			marshaller.marshal(listOfUsers, System.out);
			
//			------to save on soecified disk-----------------
			
		   }  catch (JAXBException e) {
			     System.err.println(e.toString());
		}     catch (Exception e) {
			     System.err.println(e.toString());
		}
	}
//	------------READING XML FILE ----------------------------------------------------------	
	public static List<User> readXML(String xmlFile)
	{
		List<User> listOfUsers=new ArrayList<User>();
		try {
			JAXBContext jaxbContext=JAXBContext.newInstance(ListOfUsers.class);
			Unmarshaller jaxbUnmarshaler=jaxbContext.createUnmarshaller();
			
			ListOfUsers usersList=(ListOfUsers)jaxbUnmarshaler.unmarshal(new File(xmlFile));
			LogTrace.log.debug("Unmarshalling of XML into java object");
			LogTrace.log.debug("                                                           ");
			for(User user:usersList.getUsers())
			{
				LogTrace.log.debug(user);
				listOfUsers.add(user);
			}
			LogTrace.log.debug("                                                            ");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfUsers;
	}
}
