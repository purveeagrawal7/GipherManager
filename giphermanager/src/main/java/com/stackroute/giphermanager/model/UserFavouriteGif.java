package com.stackroute.giphermanager.model;

import java.util.List;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserFavouriteGif {
	@Id
	public String userId;
	public List userFavGiflist;
	
	public List getUserFavGiflist() {
		return userFavGiflist;
	}


	public void setUserFavGiflist(List userFavGiflist) {
		this.userFavGiflist = userFavGiflist;
	}

	public UserFavouriteGif() {
		super();
	}
	
	public UserFavouriteGif(String userId, List<FavouriteGif> userFavGiflist)
	{
		this.userId = userId;
		this.userFavGiflist = userFavGiflist;
	}
	
	
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
