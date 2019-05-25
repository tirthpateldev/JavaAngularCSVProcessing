package complete;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleReader {

	@Autowired
	Config config;
	
	@Scheduled(fixedDelay = 20000, initialDelay = 0)
	public void runFilePeriodically() {
		try (Stream<Path> walk = Files.walk(Paths.get(config.getProcessedFrom()))) {
			walk.filter(Files::isRegularFile).forEach( filePath -> {
				FileProcessingThread fileProcessingThread = new FileProcessingThread(filePath);
				fileProcessingThread.run();
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
