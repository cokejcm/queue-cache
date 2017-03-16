package com.demo.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.app.configuration.Cacheable;

@Cacheable
@Document(collection = "message")
public class Message extends Entity<String> {

	private static final long serialVersionUID = -7155764620974043870L;

	@Id
	private String id;
	private String author;
	private String contents;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
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
