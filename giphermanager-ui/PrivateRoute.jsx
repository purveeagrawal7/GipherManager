import React from "react";
import { Redirect, Route } from "react-router";
export default function PrivateRoute({ component: Component, ...rest }) {
  return (
    <Route
      {...rest}
      render={(routeprops) => {
        return sessionStorage.getItem("isAuthenticated") === "true" ? (
          <Component {...routeprops} />
        ) : (
          <Redirect to="/" />
        );
      }}
    />
  );
}