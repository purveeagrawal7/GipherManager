package com.stackroute.giphermanager.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.giphermanager.controller.FavouriteGifController;
import com.stackroute.giphermanager.model.FavouriteGif;
import com.stackroute.giphermanager.model.UserFavouriteGif;
import com.stackroute.giphermanager.service.FavouriteGifService;
import com.stackroute.giphermanager.util.exception.GifAlreadyBookmarkedException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FavouriteGifControllerTest {
	
	 private FavouriteGif favouriteGif;
	 private  UserFavouriteGif userfavouriteGif;
	 List<FavouriteGif> fvrtGifList = new ArrayList();
	 
	@Autowired
    private MockMvc mockMvc;
	
	 @Mock
	 private FavouriteGifService favouriteService;
	 
	
	 @InjectMocks
	 private FavouriteGifController favouriteGifController;
	
	  //Optional<UserFavouriteGif> options;

		
	  @BeforeEach
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(favouriteGifController).build();
	        userfavouriteGif = new UserFavouriteGif();
	       // options = Optional.of(userfavouriteGif);
	        favouriteGif = new  FavouriteGif();
	        favouriteGif.setGifId("8JZxZgr39TLczSJQoS");
	        favouriteGif.setBookmarkedBy("u102");
	        favouriteGif.setTitle("45");
	        favouriteGif.setUrl("https://media0.giphy.com…p37hg22&rid=200.gif&ct=g");
	        fvrtGifList.add(favouriteGif);
	    }
	 
	  @Test
	    public void addToBookmarkSuccess() throws Exception {
	    	when(favouriteService.addFavouriteGif(favouriteGif)).thenReturn(true);
	    	System.out.println(favouriteGif);
	    	
			mockMvc.perform(post("/api/v1/favouritegifs").contentType(MediaType.APPLICATION_JSON).content(asJsonString(favouriteGif)))
			.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
	    }
	    
	    @Test
	    public void addToBookmarkFailure() throws Exception {
	        when(favouriteService.addFavouriteGif(any())).thenReturn(false);
	        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favouritegifs").contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(favouriteGif)))
	                .andExpect(MockMvcResultMatchers.status().isConflict())
	                .andDo(MockMvcResultHandlers.print());
	    }
	   
	    @Test
	    public void deleteBookmarkSuccess() throws Exception {
	        when(favouriteService.deleteFavouriteGif("u102", favouriteGif.getGifId())).thenReturn(true);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletefavouritegifbyid/u102/8JZxZgr39TLczSJQoS")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    
	    @Test
	    public void deleteRecipeFailure() throws Exception {
	        when(favouriteService.deleteFavouriteGif("u102", favouriteGif.getGifId())).thenReturn(false);
	        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deletefavouriterecipebyid/u102/8JZxZgr39TLczSJQoS")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
	                .andDo(MockMvcResultHandlers.print());
	    }
	   
	    @Test
	    public void getAllGifsByUserIdSuccess() throws Exception {
	        when(favouriteService.getAllGifsByUserId("u102")).thenReturn(fvrtGifList);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getfavouritegifs/u102")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isOk())
	                .andDo(MockMvcResultHandlers.print());
	    }
	    
	    @Test
	    public void getAllGifsByUserIdFailure() throws Exception {
	        when(favouriteService.getAllGifsByUserId("u102")).thenReturn(null);
	        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/getfavouritegifs/u102")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isNotFound())
	                .andDo(MockMvcResultHandlers.print());
	    }
	   
	    private static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}