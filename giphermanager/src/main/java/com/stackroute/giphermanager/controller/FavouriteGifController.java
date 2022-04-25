package com.stackroute.giphermanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.giphermanager.model.FavouriteGif;
import com.stackroute.giphermanager.service.FavouriteGifService;
import com.stackroute.giphermanager.util.exception.FavouriteGifNotExistsException;
import com.stackroute.giphermanager.util.exception.GifAlreadyBookmarkedException;
import com.stackroute.giphermanager.util.exception.UserDoesNotExistException;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class FavouriteGifController {
	
	@Autowired
	FavouriteGifService favouriteGifService;
//	
//	@Autowired
//	public FavouriteGifController(FavouriteGifService favouriteGifService) {
//		this.favouriteGifService = favouriteGifService;
//	}
	
	@DeleteMapping("/deletefavouritegifbyid/{userId}/{gifId}")
	public ResponseEntity<?> deleteFavouriteById(@PathVariable("userId") String userId, @PathVariable("gifId") String gifId)
	{
		try {
				boolean response = favouriteGifService.deleteFavouriteGif(userId, gifId);
				if(response)
				{
					return new ResponseEntity<String>("Favourtie Gif is Deleted", HttpStatus.OK);
				}
				else {
					return new ResponseEntity<String>("Error in Deleting", HttpStatus.CONFLICT);
				}

		} catch (FavouriteGifNotExistsException | UserDoesNotExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getfavouritegifs/{userid}")
	public ResponseEntity<?> getallFavourites(@PathVariable("userid") String userId) throws UserDoesNotExistException
	{
		List<FavouriteGif> response = favouriteGifService.getAllGifsByUserId(userId);
		return new ResponseEntity<List<FavouriteGif>>(response,HttpStatus.OK);
	}
	
	@PostMapping("/favouritegifs")
	public ResponseEntity<?> addToFavourite(@RequestBody FavouriteGif data)
	{
		try {
			boolean response = favouriteGifService.addFavouriteGif(data);
			if(response)
			{
				return new ResponseEntity<String>("Bookmarked", HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("This Gif is already Bookmarked", HttpStatus.CONFLICT);

			}
		} catch (GifAlreadyBookmarkedException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
