import React, {useEffect, useContext, useState} from "react";
import Axios from "axios";
import GifList from "../Components/gifList/GifList";
import favContext from "../main";

export default function MainContainer(props) {
    const {gifList, setGifList, setFavouriteGifList, setLoggedIn, setShowBookMarked, setIfHomePage} = useContext(favContext);
    const [search, setSearch] = useState("");
    let userId = sessionStorage.getItem("userId");    
    const API_KEY = "Qvbn0cCXRIVX5h3MWIE8arQS4w0HlNHe";
      
    useEffect(function () {        
      setLoggedIn(true)
      setShowBookMarked(true)
      setIfHomePage(true)
      Axios
        .get(`https://api.giphy.com/v1/gifs/trending?api_key=${API_KEY}&limit=50`, {
          headers: {           
              "content-Type": "application/json"
              }
        })
        .then((result) => {
          setGifList(result.data.data);
        })
        .catch((err) => console.log(err));
    }, []);


    const handleSubmit = async event => {
      event.preventDefault();
    
      try {
        const result = await Axios("https://api.giphy.com/v1/gifs/search", {
          params: {
            api_key: API_KEY,
            q: search,
            limit: 50
          }
        });
        setGifList(result.data.data);
      } catch (err) {
        console.log(err)
      }
      };
  
      const handleSearchChange = event => {
        setSearch(event.target.value);
      };
    

    async function addToBookmarkList(props) {
        let gifBookmarked = props.eachGif;
        const favObj = { 
                        gifId: gifBookmarked.id,
                        title: gifBookmarked.title,
                        url: gifBookmarked.images.fixed_height.url,
                        bookmarkedBy: userId
                       };
        let gifURL = `http://localhost:8080/api/v1/favouritegifs`;            
         Axios.post(gifURL, JSON.stringify(favObj), {
          headers: {           
              'Access-Control-Allow-Origin': 'http://localhost:3000',
              "content-Type": "application/json",
              }
        })
        .then((result) => {  
          alert("Bookmarked");  
          setFavouriteGifList(favObj);
        })
        .catch((err) => {
        if(err.resopnse && err.response.status == 409){
          alert("GiF already Bookmarked")
        }
        else if(err.request && err.request.status == 409){
          alert("GiF already Bookmarked")
        }
        console.log(err)
      });                   
      }

    return(
        <div className="container">
           <form className="form-inline justify-content-center m-2">
        <input
          value={search}
          onChange={handleSearchChange}
          type="text"
          placeholder="search"
          className="form-control"
        />
        <button
          onClick={handleSubmit}
          type="submit"
          id="submit"
          className="btn btn-primary mx-2"
        >
          Go
        </button>
      </form>
            <h1>Check the GIFs Available</h1>
            <div className="row">
                {gifList.map(eachGif => {
                    return <GifList 
                                eachGif={eachGif}
                                addToBookmarkList={addToBookmarkList} 
                                showBookMarkButton = {true}
                            />;
                })}
            </div>
        </div>
    )
}