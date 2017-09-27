package co.crazytech.ggqr.ui;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="AppConfig")
public class AppConfig {
	private List<String> urls;
	
	@XmlElementWrapper (name="urls")
	@XmlElement (name="url")
	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	
}
