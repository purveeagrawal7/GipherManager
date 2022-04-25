import React from "react";
import { Redirect, Route } from "react-router";

export default function PrivateRoute({ component: Component, ...rest }) {
  console.log("Inside private route")
  console.log(rest)
  return (
    <Route
      {...rest}
      render={(routeprops) => {
        console.log(routeprops)
        return sessionStorage.getItem("isAuthenticated") === "true" ? (
          <Component {...routeprops} />
        ) : (
          <Redirect to="/" />
        );
      }}
    />
  );
}
