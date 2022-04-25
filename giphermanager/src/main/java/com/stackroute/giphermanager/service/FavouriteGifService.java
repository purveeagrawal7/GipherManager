package com.stackroute.giphermanager.service;

import java.util.List;



import com.stackroute.giphermanager.model.FavouriteGif;
import com.stackroute.giphermanager.util.exception.GifAlreadyBookmarkedException;
import com.stackroute.giphermanager.util.exception.UserDoesNotExistException;
import com.stackroute.giphermanager.util.exception.FavouriteGifNotExistsException;

public interface FavouriteGifService {

	public boolean deleteFavouriteGif(String userId, String gifId) throws FavouriteGifNotExistsException, UserDoesNotExistException;

	public boolean addFavouriteGif(FavouriteGif favouriteGif) throws GifAlreadyBookmarkedException;

	public List<FavouriteGif> getAllGifsByUserId(String userId) throws UserDoesNotExistException;
	
	}
