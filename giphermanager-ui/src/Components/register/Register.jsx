import React, { useState, useContext, useEffect } from "react";
import { Form, Col, Row, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";
import axios from "axios";
import "./Register.css";
import favContext from "../../main";

function Register() {
  const history = useHistory();
  const [userId, setuserId] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [cPassword, setCPassword] = useState("");
  const {setLoggedIn} = useContext(favContext);
  const [errors, setErrors] = useState({
    userId: "",
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    cpassword: "",
  });


  let isPswdValid;
  const validEmailRegex = RegExp(
    /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  );
  const validInputRegex = RegExp(
    /[A-Za-z].{4}/
  );

  const validuserIdRegex = RegExp(
    /[A-Za-z0-9].{3}/
  );

  const checkConfirmPassword = (event,password) => {
    return isPswdValid = (event.target.value === password) ? true : false ;
  }

  const validateForm = (errors) => {
    debugger
    let validity = true;
    if(userId.length == 0 || firstName.length == 0 || lastName.length == 0 || password.length == 0 || cPassword.length ==0){
      validity = false;
      return validity;
    }
    Object.values(errors).forEach((val) =>{
     if(val.length > 0 && validity == false){
      validity = false;
     }
    }
      );
      return validity;
  };

  const handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;
    switch (name) {
      case "userId":
        errors.userId = validuserIdRegex.test(value) ? "" : "userId must be atleast 4 characters";
            setuserId(value);
        break;
      case "firstname":
        errors.firstname = validInputRegex.test(value) ? "" : "First Name must be at least 4 characters long and should only be letters";
            setFirstName(value); 
        break;
      case "lastname":
        errors.lastname = validInputRegex.test(value) ? "" : "Last Name must be at least 4 characters long and should only be letters";
        setLastName(value); 
        break;
      case "email":
        errors.email = validEmailRegex.test(value) ? "" : "Email is invalid";
        setEmail(value);
        break;
      case "password":
        errors.password = validInputRegex.test(value) ? "" : "Password must be at least 4 characters";
          setPassword(value);
        break;
      case "cpassword":
        checkConfirmPassword(event,password);
        errors.cpassword = !isPswdValid ? "This Password must be same as your password" : "";  
        setCPassword(value);      
        break;
      default:
        break;
    }    
    setErrors({ ...errors, name: errors[name] });    
  };

  useEffect(function () {   
    sessionStorage.clear();     
    setLoggedIn(false)
  }, [userId, firstName, lastName, email, password, cPassword ]);


  const clearForm = () => {
    debugger
   setuserId(userId => {return ""});
   setFirstName("");
   setLastName("");
   setEmail("");
   setPassword("");
   setCPassword("");
   history.push("/register");
  };


  const goToLoginPage = (e) => {
    e.preventDefault();
    history.push("/");
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    let validForm;
    if (validateForm(errors)) {
      validForm = true;
    } else {
      validForm = false;
      alert("Please validate your entries once again, form is not valid.");
    }
    if (validForm) {    
    axios.post("http://localhost:8082/api/v1/auth/register",
    {userId: userId, firstName:firstName, lastName:lastName, email:email, password:password, cpassword: cPassword},
    {
      headers: {
        'Access-Control-Allow-Origin': 'http://localhost:3000'
        }
    }
    )
    .then((res) => {
      alert("Successfully Registered")
      // history.push("/")
      clearForm();
      console.log(res.data);
    })
    .catch((err) => {
      if(err.resopnse && err.response.status == 409){
        alert("User Already Registered")
      }
      else if(err.request && err.request.status == 409){
        alert("User Already Registered")
      }
      console.log(err)
    });
  }
}
  return (
    <div>
      <h2 className="gif-reg-header">
        {" "}
        Please Register Here{" "}
      </h2>
      <Form className="gif-reg-formholder" onSubmit={handleSubmit} >
        <Row sm={6}>
        <Col xs={12}>
            <Form.Group className="col-sm-3 col-md-6 col-lg-4" as={Col} controlId="formGridEmail">
              <Form.Label>userId</Form.Label>
              <Form.Control
                id = "userId"
                name = "userId"
                type="text" 
                placeholder="User Name" 
                className= "nowrap incwidth"
                noValidate
                onChange={handleChange}/>
                {errors.userId.length > 0 && (
                <span className="error">{errors.userId}</span>
              )}
            </Form.Group>
          </Col>
          <Col xs={12}>
            <Form.Group className="col-sm-3 col-md-6 col-lg-4" as={Col} controlId="formGridEmail">
              <Form.Label  className= "nowrap">First Name</Form.Label>
              <Form.Control
              className = "incwidth"
              id="firstname"
              name = "firstname"
                type="text" 
                noValidate
                placeholder="First Name" 
                onChange={handleChange}/>
                {errors.firstname.length > 0 && (
                <span className="error">{errors.firstname}</span>
              )}
            </Form.Group>
          </Col>
          <Col xs={12}>
            <Form.Group className="col-sm-3 col-md-6 col-lg-4" as={Col} controlId="formGridEmail">
              <Form.Label className= "nowrap">Last Name</Form.Label>
              <Form.Control 
              className = "incwidth"
              id="lastname"
              name="lastname"
             type="text" 
             noValidate
              placeholder="Last Name"
              onChange={handleChange}/>
              {errors.lastname.length > 0 && (
                <span className="error">{errors.lastname}</span>
              )}
            </Form.Group>
          </Col>
        </Row>
        <Row sm={6}>
          <Col xs={12}>
            <Form.Group className="col-sm-3 col-md-6 col-lg-4" as={Col} controlId="formGridEmail">
              <Form.Label>Email</Form.Label>
              <Form.Control 
              className = "incwidth"
              id = "email"
              name="email"
              noValidate
              type="email" placeholder="Enter email"
              onChange={handleChange}/>
               {errors.email.length > 0 && (
                <span className="error">{errors.email}</span>
              )}
            </Form.Group>
          </Col>
          <Col xs={12}>
            <Form.Group className="col-sm-3 col-md-6 col-lg-4" as={Col} controlId="formGridPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control  className = "incwidth" id="password" name="password" type="password" placeholder="Password" noValidate
              onChange={handleChange}/>
               {errors.password.length > 0 && (
                <span className="error">{errors.password}</span>
              )}
            </Form.Group>
          </Col>
          <Col xs={12}>
            <Form.Group className="col-sm-3 col-md-6 col-lg-4" as={Col} controlId="formGridPassword">
              <Form.Label  className= "nowrap">Confirm Password</Form.Label>
              <Form.Control className="w-160" id="cpassword" name="cpassword" type="password" placeholder="Confirm Password" noValidate
              onChange={handleChange} />
               {errors.cpassword.length > 0 && (
                <span className="error">{errors.cpassword}</span>
              )}
            </Form.Group>
          </Col>
        </Row>
        <div className="buttonDiv">
        <Button id="register" className="gif-reg-btn" variant="success" type="submit" >
          Register
        </Button>
        <Button id="submit" variant="primary" type="submit" className ="regSignbutton" onClick={(e) => goToLoginPage(e)}>
        If already registered, Sign In
        </Button><br/>
        </div>
      </Form>
    </div>
  );
}
export default Register;