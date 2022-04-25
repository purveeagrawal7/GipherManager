import React, {useState} from "react";
import Register from "./Components/register/Register";
import Footer from "./Components/footer/Footer";
import Login from "./Components/login/Login";
import BookMarkedGifContainer from "./Container/BookMarkedGifContainer";
import MainContainer from "./Container/MainContainer";
import Header from "./Components/header/Header";
import PrivateRoute from "./PrivateRoute";
import favContext from "./main";

import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {

  const [gifList, setGifList] = useState([]);    
  const [favouriteGifList, setFavouriteGifList] = useState([]);
  const [loggedIn, setLoggedIn] = useState(false);
  const [showBookMarked, setShowBookMarked] = useState(false);
  const [ifHomePage, setIfHomePage] = useState(false);

  return (
    <Router>
      <favContext.Provider value={{
        favouriteGifList: favouriteGifList,
        setFavouriteGifList: setFavouriteGifList,
        gifList: gifList,
        setGifList: setGifList,
        loggedIn:loggedIn,
        setLoggedIn: setLoggedIn,
        showBookMarked: showBookMarked,
        setShowBookMarked: setShowBookMarked,
        ifHomePage: ifHomePage,
        setIfHomePage: setIfHomePage
    }}>
      <Header />
        <Switch>                   
          <Route exact path="/" component={Login} />
          <Route path="/register" component={Register} />
          <PrivateRoute path="/dashboard" component={MainContainer} />
          <PrivateRoute path="/favourites" component={BookMarkedGifContainer} />   
        </Switch>
        <Footer />
        </favContext.Provider>
      </Router>
  );
}

export default App;
