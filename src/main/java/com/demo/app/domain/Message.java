package com.demo.app.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.app.configuration.Cacheable;

@Cacheable
@Document(collection = "message")
public class Message extends Entity {

	private static final long serialVersionUID = -7155764620974043870L;

	private String author;
	private String contents;

	public Message(String id, String author, String contents) {
		super();
		this.setId(id);
		this.author = author;
		this.contents = contents;
	}

	public Message() {
		super();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Message [id= " + this.getId() + ", author=" + author + ", contents=" + contents + "]";
	}

}
