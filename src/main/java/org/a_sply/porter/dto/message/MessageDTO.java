package org.a_sply.porter.dto.message;

import org.a_sply.porter.dto.user.UserDTO;

public class MessageDTO {

	private UserDTO to;
	private UserDTO from;
	private String content;
	private String preview;
	private String sending;

	public MessageDTO() {
	}

	public MessageDTO(UserDTO to, UserDTO from, String content, String preview,
			String sending) {
		super();
		this.to = to;
		this.from = from;
		this.content = content;
		this.preview = preview;
		this.sending = sending;
	}

	public UserDTO getTo() {
		return to;
	}

	public void setTo(UserDTO to) {
		this.to = to;
	}

	public UserDTO getFrom() {
		return from;
	}

	public void setFrom(UserDTO from) {
		this.from = from;
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

	public String getSending() {
		return sending;
	}

	public void setSending(String sending) {
		this.sending = sending;
	}

}
