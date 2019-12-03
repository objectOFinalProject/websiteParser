
public class Webpage {
	private String title;
	private String url;
	private String description;
	
	public Webpage() {
		title = "";
		url = "";
		description = "";
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	Webpage (String title, String url, String description){
		setTitle(title);
		setUrl(url);
		setDescription(description);
	}
	
	public String toString(){
		return String.format("%s %s %s", title, url, description);
	}
}
