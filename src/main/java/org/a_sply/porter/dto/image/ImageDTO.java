package org.a_sply.porter.dto.image;

public class ImageDTO {
	private String original;
	private String thumbnail;

	public ImageDTO() {
	}

	public ImageDTO(String original, String thumbnail) {
		this.original = original;
		this.thumbnail = thumbnail;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
