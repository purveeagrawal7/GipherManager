package com.stackroute.giphermanager.model;



import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FavouriteGif {

	@Id
	public String gifId;	

	public String title;
	public String url;
	public String bookmarkedBy;

	public FavouriteGif() {
		super();
	}
	
	public FavouriteGif(String gifId, String title, String url, String bookmarkedBy) {
		this.gifId = gifId;
		this.title = title;
		this.url = url;
		this.bookmarkedBy = bookmarkedBy;
		
	}

	public String getGifId() {
		return gifId;
	}


	public void setGifId(String gifId) {
		this.gifId = gifId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public String getBookmarkedBy() {
		return bookmarkedBy;
	}

	public void setBookmarkedBy(String bookmarkedBy) {
		this.bookmarkedBy = bookmarkedBy;
	}
	
	
}
