
package net.sirma.impactoOCR.tesseract.iconfig;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class PropLoader {
	public static Properties env = new Properties();
	private static Logger logger = Logger.getLogger(PropLoader.class);
	
	public PropLoader()
	{   
		if (env.size()<1) {
		String filename = "application.properties";
		InputStream  PropFile = PropLoader.class.getClassLoader().getResourceAsStream(filename);
		try {
			env.load(PropFile);
		} catch (IOException e) {
			logger.error("Failed to Load Property File"+e.getMessage().toString());
			e.printStackTrace();
		}
		}
    };
	

    
    
}
