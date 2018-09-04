package com.marlabs.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.marlabs.beans.User;
import com.marlabs.custom_exception.FileNotValidException;
import com.marlabs.log.LogTrace;
import com.marlabs.test.Operation;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVParseToJava {
//	------------------READ CSV FILE AND CONVERT IT INTO JAVA BEANS----------------------------------------------------------------
	public static List<User> readCSV(String csvFileName,String targetFile)  {
		List<User> userList=null;
		CSVReader csvReader=null;
		try {
			csvReader = new CSVReader(new FileReader(csvFileName));

			userList = new ArrayList<User>();
			String[] record = null;
			csvReader.readNext();
			while ((record = csvReader.readNext()) != null) {
				if(record.length!=6)
				{
					try {
						throw new FileNotValidException("file not valid");
						
					} catch (FileNotValidException e) {
						
						csvReader.close();
						Path path=Paths.get("C:\\Users\\himanshu.kumar\\Desktop\\parser\\processed_file\\failed");
						Files.createDirectories(path);
						Files.move(Paths.get(csvFileName),Paths.get("C:\\Users\\himanshu.kumar\\Desktop\\parser\\processed_file\\failed\\"+targetFile),StandardCopyOption.REPLACE_EXISTING);
						LogTrace.log.debug("file moved");
						e.printStackTrace();
					}
					finally
					{
						Operation.csvToXml();
					}
									}
				User user = new User(record[0], record[1], record[2], record[3], record[4], record[5]);
				
				userList.add(user);
		
				
				
			
			}
			csvReader.close();
			LogTrace.log.debug("Converting CSV file to java object....");
			LogTrace.log.debug("                                                                    ");
			LogTrace.log.debug("--------------------------------------------------------------------------------------------------------");
			 for(User user:userList)
			 {
				 LogTrace.log.debug(user);
			 }
			 LogTrace.log.debug("--------------------------------------------------------------------------------------------------------");
			 LogTrace.log.debug("                                                                    ");
			 LogTrace.log.debug("processing java object to XML elements.....");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				csvReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Path path=Paths.get("C:\\Users\\himanshu.kumar\\Desktop\\processed_file\\success");
			try {
				Files.createDirectories(path);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Files.move(Paths.get(csvFileName),Paths.get("C:\\Users\\himanshu.kumar\\Desktop\\parser\\processed_file\\success\\"+targetFile),StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LogTrace.log.debug("file moved");
		}
		return userList;

	}
//	----------------WRITING CSV FILE FROM JAVA BEANS--------------------------------------------------------------------------------
	private static List<String[]> toStringArray(List<User> users)
	{
		List<String[]> records=new ArrayList<String[]>();
		records.add(new String[] {"Id","Username","Firstname","Lastname","Sex","Age"});
		Iterator<User> it=users.iterator();
		while(it.hasNext())
		{
			User user=it.next();
			records.add(new String[] {user.getId(),user.getUserName(),user.getFirstName(),user.getLastName(),user.getSex(),user.getAge()});
		}
		return records;
		
	}
	
	@SuppressWarnings("deprecation")
	public static void writeCSVData(List<User> users,String path)
	{
		try {	
	//		StringWriter stringWriter=new StringWriter();
			CSVWriter csvWriter=new CSVWriter(new FileWriter(path),',');
			List<String[]> data=toStringArray(users);
			csvWriter.writeAll(data);
			csvWriter.close();
		}catch(IOException e)
		{
			System.err.println(e.toString());
		}
		catch(Exception e)
		{
			System.err.println(e.toString());
		}
		
		
		
	}
}
