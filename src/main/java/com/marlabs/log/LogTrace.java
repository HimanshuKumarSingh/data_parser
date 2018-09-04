package com.marlabs.log;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LogTrace {

	public static Logger log=Logger.getLogger(LogTrace.class.getName());
	
	static
	{
		BasicConfigurator.configure();
	}
}
