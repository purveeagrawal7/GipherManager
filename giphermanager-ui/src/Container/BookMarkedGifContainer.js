import React, {useEffect, useContext} from 'react'
import axios from "axios";
import { useHistory } from "react-router-dom";
import GifList from "../Components/gifList/GifList";
import favContext from "../main";


export default function BookMarkedGifContainer() {
  const {favouriteGifList, setFavouriteGifList, setLoggedIn, setShowBookMarked, setIfHomePage} = useContext(favContext)    
  const history = useHistory();

    let userId = sessionStorage.getItem("userId");
    const deleteFromBookMarkList = (favGifId) => { 
        deletFavouritegif(favGifId);
    }
        async function deletFavouritegif(favGifId) { 
            let deleteGifUrl = `http://localhost:8080/api/v1/deletefavouritegifbyid/${userId}/${favGifId}`;            
             axios.delete(deleteGifUrl, {
              headers: {           
                  'Access-Control-Allow-Origin': 'http://localhost:3000',
                  "content-Type": "application/json"
                  }
            })
            .then((result) => {
              alert("Bookmarked");  
              const updatedBookMarkedList = favouriteGifList.filter((item) => item.gifId !== favGifId);
              setFavouriteGifList(updatedBookMarkedList);
              history.push({        
                pathname: "/favourites"
            });
              console.log(result.data);
            })
            .catch((err) => console.log(err));                   
        }
      useEffect(function () {    
        setLoggedIn(true)
        setShowBookMarked(false)
        setIfHomePage(false)    
        axios
          .get(`http://localhost:8080/api/v1/getfavouritegifs/${userId}`, {
            headers: {           
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                "content-Type": "application/json",
                }
          })
          .then((result) => {
            setFavouriteGifList(result.data);
            console.log(result.data);
          })
          .catch((err) => console.log(err));
      }, []);
    return (
        <div  className="container">
          {favouriteGifList.length > 0 ?
          (
          <div className="row"> 
              {favouriteGifList.map(eachGif => {
                    return <GifList 
                                eachGif = {eachGif} 
                                favouriteGifList={favouriteGifList} 
                                setFavouriteGifList = {setFavouriteGifList}
                                showDeleteBookMarkButton = {true}   
                                deleteFromBookMarkList = {deleteFromBookMarkList}                         
                            />;
                })}  
                <div>
                 </div>
              </div>)
          :
          (<h2>No Gifs Bookmarked !!</h2>)
          }
        </div>
    )
}
