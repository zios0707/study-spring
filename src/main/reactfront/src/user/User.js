import "./User.css";
import { useEffect, useState } from "react";
import React from "react";
import axios from "axios";

const User = () => {
  const [user, setUser] = useState({
      name : "",
      email : "",
  });

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/auth/user")
      .then((response) => setUser(response.data));
  });

  return (
    <>
        <p>Name: {user.name}</p>
        <p>Email : {user.email}</p>
    </>
  );
};
export default User;
