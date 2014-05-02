package org.a_sply.porter.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.a_sply.porter.dto.message.MessageDTO;
import org.a_sply.porter.util.DateFormat;

public class Message {

	private static final int MEG_LIST_PREVIEW_LENGTH = 20;

	private int id;
	private User to;
	private User from;
	private String content;
	private String preview;
	private Date sending;

	public Message(User to, User from, String content) {
		super();
		this.to = to;
		this.from = from;
		this.content = content;
		setPreview(content, content.length());
	}

	public Message() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public MessageDTO messageDTO() {
		return new MessageDTO(to.userDTO(), from.userDTO(), content, preview,
				DateFormat.format(sending));
	}
}
