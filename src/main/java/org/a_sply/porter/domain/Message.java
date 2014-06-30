package org.a_sply.porter.domain;

import java.util.Date;

import org.a_sply.porter.domain.user.User;

public class Message {

	private static final int MEG_LIST_PREVIEW_LENGTH = 20;			// message preview content length limited.

	private long messageId;													// message id.
	private User to;												// user to accept message.
	private User from;												// user to send message.
	private String content;											// message full content.
	private String preview;											// message preview content.
	private Date sending;											// sending date.

	public Message(User to, User from, String content) {
		super();
		this.to = to;
		this.from = from;
		this.content = content;
		setPreview(content, content.length());
	}

	public Message() {
	}

	public long getId() {
		return messageId;
	}

	public void setId(long id) {
		this.messageId = id;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		setPreview(content, content.length());
	}

	private void setPreview(String content, int length) {
		if (length > MEG_LIST_PREVIEW_LENGTH)
			this.preview = content.substring(0, MEG_LIST_PREVIEW_LENGTH)
					+ "...";
		else
			this.preview = this.content;
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
