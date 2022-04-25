import React, { useEffect, useState, useContext } from "react";
import { useHistory } from "react-router-dom";
import { Button, Navbar, Nav } from "react-bootstrap";
import "./Header.css";
import favContext from "../../main";

function Header() {
  const {loggedIn, setLoggedIn, showBookMarked, ifHomePage} = useContext(favContext);
  const [isAuth, setIsAuth] = useState(false);
  const history = useHistory();

  const logout = () => {
    setLoggedIn(false)
    sessionStorage.clear();
    history.push("/");
  };

  useEffect(() => {
    setIsAuth(localStorage.getItem("isAuthenticated"));
  }, [isAuth, loggedIn]);
 

  return (
    <header className="h-80">
      <Navbar variant="dark" className="navClass">
        <Navbar.Brand>Welcome to the GIFs Library
        <div class="Sticker">
        <img src="https://c.tenor.com/AvHPuvcRU4wAAAAi/cute-penguin.gif" width="407" height="407" 
        className = "penguinImg"
        alt="Cute Penguin Sticker - Cute Penguin Hey Stickers" />
        </div>
        </Navbar.Brand>
     {  loggedIn && showBookMarked ? (<Nav className="me-auto top-nav-right">
          <Nav.Link href="/favourites">Bookmarked GIFS</Nav.Link>
        </Nav>) : ''}
       { loggedIn && !ifHomePage ? (<Nav className="me-auto">
          <Nav.Link href="/dashboard">Home</Nav.Link>
        </Nav>) : ''}
       { loggedIn ? (
       <Button className="sign-out" variant="outline-light" onClick={logout}>
          SignOut
        </Button>) : ''}
      </Navbar>
    </header>
  );
}

export default Header;
