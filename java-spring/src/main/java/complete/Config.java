package complete;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class Config {

	private String processedFrom;

	public String getProcessedFrom() {
		return processedFrom;
	}

	public void setProcessedFrom(String processedFrom) {
		this.processedFrom = processedFrom;
	}
}
