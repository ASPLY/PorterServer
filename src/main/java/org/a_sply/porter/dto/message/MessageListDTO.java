package org.a_sply.porter.dto.message;


public class MessageListDTO {

	private int messageId;
	private String from;
	private String preview;
	private String sending;

	public MessageListDTO() {
	}

	public MessageListDTO(int messageId, String from, String preview,
			String sending) {
		super();
		this.messageId = messageId;
		this.from = from;
		this.preview = preview;
		this.sending = sending;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSending() {
		return sending;
	}

	public void setSending(String sending) {
		this.sending = sending;
	}

}
