package org.a_sply.porter.domain;

public class Description {

	private static final int PART_LIST_DES_LENGTH = 20;

	private String content;
	private String preview;

	public Description() {
	}

	public Description(String content, String preview) {
		super();
		this.content = content;
		this.preview = preview;
	}

	public Description(String description) {
		content = description;
		preview = getPreview(description);
	}

	private String getPreview(String description) {
		int length = description.length();
		if (length > PART_LIST_DES_LENGTH)
			return description.substring(0, PART_LIST_DES_LENGTH) + "...";

		return description + "...";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

}
