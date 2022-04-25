package com.stackroute.giphermanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.stereotype.Repository;

import com.stackroute.giphermanager.model.UserFavouriteGif;


/*
* This class is implementing the JpaRepository interface for News.
* Annotate this class with @Repository annotation
* */

@Repository 
public interface FavouriteGifRepository extends MongoRepository<UserFavouriteGif, String> {

}
