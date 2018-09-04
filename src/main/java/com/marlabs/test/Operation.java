package com.marlabs.test;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.marlabs.beans.User;
import com.marlabs.log.LogTrace;
import com.marlabs.parser.CSVParseToJava;
import com.marlabs.parser.XMLParser;

public class Operation {
	private static Scanner sc;

	public static void selection()
	{
		LogTrace.log.debug("Press 1 to parse from csv to xml");
		LogTrace.log.debug("                                  ");
		LogTrace.log.debug("Press 2 to parse from xml to csv. ");
		LogTrace.log.debug("                                  ");
		LogTrace.log.debug("Enter the number :");
		sc = new Scanner(System.in);
		int input = sc.nextInt();
		if(input==1)
		{
			csvToXml();
		}
		else if(input==2)
		{
			xmlToCsv();
		}
		else
		{
			LogTrace.log.debug("! Invalid entry !");
			LogTrace.log.debug("                                      ");
			selection();
		}
	}
	public static void csvToXml()
	{
		List<User> list=null;
		
		File directory = new File("C:\\Users\\himanshu.kumar\\Desktop\\parser\\csv_files");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
            	LogTrace.log.debug(file.getPath());
            	String targetFile=file.getName();
            	list = CSVParseToJava.readCSV(file.getPath(),targetFile);
            	LogTrace.log.debug("processing the CSV file ....");
        		String path=String.format("%s.%s","C:\\Users\\himanshu.kumar\\Desktop\\parser\\processed_xml\\"+file.getName(),"xml");
        		LogTrace.log.debug("savaing file to specified location.");
        		XMLParser.writeXML(list,path);
        		LogTrace.log.debug("file saved.");
            }
        }
		
		
	}
	
	private static void xmlToCsv()
	{

		Scanner sc1 = new Scanner(System.in);
		LogTrace.log.debug("Enter the CSV file name with its specified path");
		LogTrace.log.debug("e.g. C:\\Users\\himanshu.kumar\\Documents\\filename.xml");
		String csvFileName = sc1.nextLine();
		LogTrace.log.debug("processing the XML file ....");
		List<User> list = XMLParser.readXML(csvFileName);
		LogTrace.log.debug("                                             ");
		LogTrace.log.debug("Enter the file name including it's path in CSV extension.");
		String path=sc1.nextLine();
		LogTrace.log.debug("savaing file to specified location.");
		CSVParseToJava.writeCSVData(list,path);
		LogTrace.log.debug("file saved.");
		sc1.close();
	}
}
