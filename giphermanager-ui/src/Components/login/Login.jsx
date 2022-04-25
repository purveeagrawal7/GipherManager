import React, { useState,useContext, useEffect } from "react";
import Axios from 'axios';
import { useHistory } from "react-router-dom";
import "./Login.css";
import { Form, Button } from "react-bootstrap";
import favContext from "../../main";


function Login(props) {
  const history = useHistory();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const {setLoggedIn} = useContext(favContext);

  useEffect(function () {   
    sessionStorage.clear();     
    setLoggedIn(false)
  }, []);

  const loginUser = (e) => {
    setLoggedIn(false)
    const credentials = { userId: username, password:password };
    console.log("entering login user fnctn");
    console.log("HI",JSON.stringify(credentials));
    e.preventDefault();
    Axios.post("http://localhost:8082/api/v1/auth/login",JSON.stringify(credentials),
    {
    headers: {
    'Access-Control-Allow-Origin': 'http://localhost:3000',
    "content-Type": "application/json",
    }
    
    })
    .then((res) => {
    setLoggedIn(true)
    sessionStorage.setItem("isAuthenticated", true);
    sessionStorage.setItem("mytoken", res.data);
    sessionStorage.setItem("userId", username);
    sessionStorage.setItem("password", password);
    history.push("dashboard");
    })
    .catch((err) => console.log(err));
    };

  const registerUser = (e) => {
    console.log("entering register user fnctn");
    e.preventDefault();
    history.push("register");
  };


  return (
    <div id="loginParent" className="loginParent">
      <Form>
        <Form.Group className="text-muted" controlId="formBasicUserName">
          <Form.Label>User Name</Form.Label>
          <Form.Control
            type="admin"
            id="login"
            placeholder="Enter User Name"
            onChange={(evt) => setUsername(evt.target.value)}
          />
          <Form.Text className="text-muted">Enter your User Name</Form.Text>
        </Form.Group>
        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            id="password"
            placeholder="Enter Password"
            onChange={(evt) => setPassword(evt.target.value)}
          />
          <Form.Text className="text-muted">Enter your Password</Form.Text>
        </Form.Group>
        <Button id="submit" variant="primary" type="submit" onClick={(e) => loginUser(e)}>
          Sign In
        </Button><br/>
        <Form.Text className="text-muted">If you are new user please click on register </Form.Text>
        <Button
          variant="primary"
          type="submit"
          onClick={(e) => registerUser(e)}
          className= "registerBtn"
        >
          Register
        </Button>
      </Form>
    </div>
  );
}

export default Login;
