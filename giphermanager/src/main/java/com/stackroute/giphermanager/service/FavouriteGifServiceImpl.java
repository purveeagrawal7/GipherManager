package com.stackroute.giphermanager.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stackroute.giphermanager.model.FavouriteGif;
import com.stackroute.giphermanager.model.UserFavouriteGif;
import com.stackroute.giphermanager.repository.FavouriteGifRepository;
import com.stackroute.giphermanager.util.exception.GifAlreadyBookmarkedException;
import com.stackroute.giphermanager.util.exception.UserDoesNotExistException;
import com.stackroute.giphermanager.util.exception.FavouriteGifNotExistsException;

@Service
public class FavouriteGifServiceImpl implements FavouriteGifService {
	FavouriteGifRepository favGifRepo;
	@Autowired
	 public FavouriteGifServiceImpl(FavouriteGifRepository favGifRepo) {
		 this.favGifRepo = favGifRepo;
	 }
	@Override
	public boolean deleteFavouriteGif(String userId, String gifId) throws FavouriteGifNotExistsException, UserDoesNotExistException {
		boolean result = false;
		Optional<UserFavouriteGif> getuser = favGifRepo.findById(userId);
		System.out.println("printing getuser"+ getuser);
		if(getuser.isPresent())
		{
			UserFavouriteGif user = getuser.get();
			System.out.println("printing user"+ user);
			List<FavouriteGif> favouriteGifList = getuser.get().getUserFavGiflist();
			Iterator<FavouriteGif> getFavGifList = favouriteGifList.iterator();
			while(getFavGifList.hasNext())
			{
				FavouriteGif data = getFavGifList.next();
				if(data.getGifId().equals(gifId))
				{
					getFavGifList.remove();
					user.setUserFavGiflist(favouriteGifList);
					favGifRepo.save(user);
					result = true;
				}
			}
			if(!result) {
				throw new FavouriteGifNotExistsException("No favourites Gif found for this user");
			}
		}
		else {
			throw new UserDoesNotExistException("User Not Found");
		}
		return result;
		}
	@Override
	public boolean addFavouriteGif(FavouriteGif favouriteGif) throws GifAlreadyBookmarkedException {
		boolean result=false;
        try 
        {
         Optional<UserFavouriteGif> userexist= favGifRepo.findById(favouriteGif.getBookmarkedBy());
         if(userexist.isPresent()) {
        	 UserFavouriteGif userFavouriteExist = userexist.get();
             List<FavouriteGif> gifExist = userFavouriteExist.getUserFavGiflist();
             if(gifExist.isEmpty()) {
            	 gifExist.add(favouriteGif);
            	 userFavouriteExist.setUserFavGiflist(gifExist);
                 favGifRepo.save(userFavouriteExist);
                 result = true;
             }
             else {            	 
               Iterator<FavouriteGif> iterator = gifExist.iterator();
                while(iterator.hasNext()) 
                 {
                     FavouriteGif data = iterator.next();
                     if(favouriteGif.getGifId().equals(data.getGifId())) {      
                     	throw new GifAlreadyBookmarkedException("This GIF is already Bookmarked");
                     }
                     else 
                     {
                         result=true;
                     }                    
                 } 
                if(result) 
                {
                	gifExist.add(favouriteGif);
                	userFavouriteExist.setUserFavGiflist(gifExist);
                    favGifRepo.save(userFavouriteExist);
                }
               
             }
        }
        else 
        {        
            List<FavouriteGif> favouritelist = new ArrayList<>();
            favouritelist.add(favouriteGif);
            UserFavouriteGif userFavourite = new UserFavouriteGif(favouriteGif.getBookmarkedBy(),favouritelist);
            favGifRepo.save(userFavourite);
            result = true;         
        }
        }
         catch (Exception e) {			
        	 return false;
		}
		return result;
	}

	@Override
	public List<FavouriteGif> getAllGifsByUserId(String userId) throws UserDoesNotExistException{
		List<FavouriteGif> getAllFavouriteGifs = null;
		Optional<UserFavouriteGif> getUser = favGifRepo.findById(userId);
		if(getUser.isPresent())
		{
			getAllFavouriteGifs = getUser.get().getUserFavGiflist();
			return getAllFavouriteGifs;
		}
		else {
			throw new UserDoesNotExistException("User Not Found");
		}
	}	
	
	
	

}
