package com.stackroute.giphermanager.test.service;

import static org.mockito.ArgumentMatchers.any;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.giphermanager.model.FavouriteGif;
import com.stackroute.giphermanager.model.UserFavouriteGif;
import com.stackroute.giphermanager.repository.FavouriteGifRepository;
import com.stackroute.giphermanager.service.FavouriteGifServiceImpl;
import com.stackroute.giphermanager.util.exception.FavouriteGifNotExistsException;
import com.stackroute.giphermanager.util.exception.GifAlreadyBookmarkedException;
import com.stackroute.giphermanager.util.exception.UserDoesNotExistException;

import org.junit.jupiter.api.Test;

public class FavouriteGifServiceTest {
	private FavouriteGif favouriteGif;
	private UserFavouriteGif userfavouriteGif;
	
	List<FavouriteGif> fvrtGifList = new ArrayList();
	
    @Mock
    private FavouriteGifRepository favouriteRepository;
    @InjectMocks
    private FavouriteGifServiceImpl fvrtServiceImpl;
    Optional<UserFavouriteGif> options;
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        favouriteGif = new FavouriteGif();
        userfavouriteGif = new UserFavouriteGif();
        favouriteGif = new  FavouriteGif();
        userfavouriteGif = new UserFavouriteGif();
	    favouriteGif = new  FavouriteGif();
	    favouriteGif.setGifId("8JZxZgr39TLczSJQoS");
	    favouriteGif.setBookmarkedBy("u102");
	    favouriteGif.setTitle("45");
	    favouriteGif.setUrl("https://media0.giphy.com…p37hg22&rid=200.gif&ct=g");
	    fvrtGifList.add(favouriteGif);
        userfavouriteGif.setUserId("u102");
        userfavouriteGif.setUserFavGiflist(fvrtGifList);
        options = Optional.of(userfavouriteGif);
    }
    @Test
    public void addGifToBookmarkSuccess() throws GifAlreadyBookmarkedException {
        when(favouriteRepository.save((UserFavouriteGif) any())).thenReturn(userfavouriteGif);
        boolean status = fvrtServiceImpl.addFavouriteGif(favouriteGif);
        assertEquals(true, status);
    }
    @Test
    public void addGifToBookmarkFailure() throws GifAlreadyBookmarkedException {
        when(favouriteRepository.save((UserFavouriteGif) any())).thenReturn(null);
        boolean status = fvrtServiceImpl.addFavouriteGif(favouriteGif);
        assertEquals(false, false);
     }
    @Test
    public void deleteFormBookmarkSuccess() throws FavouriteGifNotExistsException, UserDoesNotExistException {
        when(favouriteRepository.findById(userfavouriteGif.getUserId())).thenReturn(options);
        when(favouriteRepository.save(userfavouriteGif)).thenReturn(userfavouriteGif);
        boolean flag = fvrtServiceImpl.deleteFavouriteGif("u102", favouriteGif.getGifId());
        assertEquals(true, flag);
    }
    @Test
    public void deleteFromBookmarkFailure() {
        when(favouriteRepository.findById(userfavouriteGif.getUserId())).thenReturn(null);
        when(favouriteRepository.save(userfavouriteGif)).thenReturn(userfavouriteGif);
        assertThrows(
        		NullPointerException.class,
                    () -> { fvrtServiceImpl.deleteFavouriteGif("u102", favouriteGif.getGifId()); });
    }
    @Test
    public void getAllRecipeByUserId() throws UserDoesNotExistException {
        when(favouriteRepository.findById("u102")).thenReturn(options);
        List<FavouriteGif> fvrtGifList1 = fvrtServiceImpl.getAllGifsByUserId("u102");
        assertEquals(fvrtGifList, fvrtGifList1);
    }
}