package org.a_sply.porter.domain;

import java.util.Date;

import org.a_sply.porter.util.DateFormat;

public class MessageList {

	private int messageId; 				// message id.
	private String from;				// user to send message.
	private String preview;				// message preview content.	
	private Date sending;				// sending date.

	public MessageList() {
	}

	public MessageList(int messageId, String from, String preview, Date sending) {
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public Date getSending() {
		return sending;
	}

	public void setSending(Date sending) {
		this.sending = sending;
	}
}
