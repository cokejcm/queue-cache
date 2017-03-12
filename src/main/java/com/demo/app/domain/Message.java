package com.demo.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message") // optional
public class Message extends Entity<String> {

	private static final long serialVersionUID = 2033215599775171440L;
	@Id
	private String _id;
	private String author;
	private String contents;

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
