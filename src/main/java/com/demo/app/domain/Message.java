package com.demo.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class Message extends Entity<String> {

	private static final long serialVersionUID = -7155764620974043870L;

	@Id
	private String _id;
	private String author;
	private String contents;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	@Override
	public String getId() {
		return this._id;
	}

	@Override
	public void setId(String id) {
		this._id = id;
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
}
