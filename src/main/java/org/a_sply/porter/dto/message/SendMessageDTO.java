package org.a_sply.porter.dto.message;

import javax.validation.constraints.NotNull;

public class SendMessageDTO {

	@NotNull
	private String to;

	@NotNull
	private String content;

	public SendMessageDTO() {
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof SendMessageDTO))
			return false;

		SendMessageDTO other = (SendMessageDTO) obj;
		return isSame(to, other.to)
				&& isSame(content, other.content);

	}

	private boolean isSame(String one, String other) {
		return one.equals(other);
	}
}
