package main.java;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		Main main = new Main();
		Properties prop = main.readPropertiesFile();
		// TODO Auto-generated method stub
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newCachedThreadPool();  
        executor.setMaximumPoolSize(1);
		try (Stream<Path> walk = Files.walk(Paths.get(prop.getProperty("processedFrom")))) {
			walk.filter(Files::isRegularFile).forEach( filePath -> {
				FileProcessingThread fileProcessingThread = new FileProcessingThread(filePath);
				executor.submit(fileProcessingThread);
			});
			executor.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Properties readPropertiesFile() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("resources/application.properties")) {
            Properties prop = new Properties();
            if (input != null) {
            	prop.load(input);
            }
            return prop;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return null;

    }
	
}
