package com.demo.app.domain;

public class Message {

	private String author;
	private String contents;

	public Message(String author, String contents) {
		this.author = author;
		this.contents = contents;
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
