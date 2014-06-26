package org.a_sply.porter.util.file_uploader;

public class UploadResult {
	
	private String url;
	private String fileName;
	
	public UploadResult(String url, String fileName) {
		super();
		this.url = url;
		this.fileName = fileName;
	}
	
	public String getUrl() {
		return url;
	}

	public String getFileName() {
		return fileName;
	}

}
