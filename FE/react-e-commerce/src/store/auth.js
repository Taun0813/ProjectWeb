import { useReducer } from "react";
import { toast } from "react-toastify";
import {
  setExpirationDate,
  getUserFromLocalStorage,
} from "../helpers/checkExpiration";

const initialState = {
  user: getUserFromLocalStorage() || null,
};
const actions = Object.freeze({
  SET_USER: "SET_USER",
  LOGOUT: "LOGOUT",
});

const reducer = (state, action) => {
  if (action.type == actions.SET_USER) {
    return { ...state, user: action.user };
  }
  if (action.type == actions.LOGOUT) {
    return { ...state, user: null };
  }
  return state;
};

const useAuth = () => {
  const [state, dispatch] = useReducer(reducer, initialState);

  const register = async (userInfo) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/user/register`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*"
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(userInfo),
      });

      const user = await response.json();
      console.log(user)

      if (user.error) {

        toast.error(user.error);
      }
      if (user.user) {
        dispatch({ type: actions.SET_USER, user: user.user });
        user.user.expirationDate = setExpirationDate(7);
        localStorage.setItem("user", JSON.stringify(user.user));
        toast.success("Registration successful");
        // login user
      }
    } catch (error) {
      console.log(error)
      toast.error("There was a problem registering, try again");
    }
  };

  const login = async (userInfo) => {
    try {
      const response = await fetch(`${import.meta.env.VITE_API_URL}/user/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        credentials: "include",
        body: JSON.stringify(userInfo),
      });
      const user = await response.json();
      if (user.user.error) {
        console.log(user.user.error)
        toast.error(user.user.error);
      }
      console.log(user)
      if (user) {
        dispatch({ type: actions.SET_USER, user: user.user });
        user.user.expirationDate = setExpirationDate(7);
        localStorage.setItem("user", JSON.stringify(user.user));
        localStorage.setItem("token", user.token.toString());
        toast.success("Login successful");
      }
    } catch (error) {
      console.log(error)
      toast.error("There was a problem logging in, try again");
    }
  };

  const logout = async () => {
    const token = await localStorage.getItem('token')
    await fetch(`${import.meta.env.VITE_API_URL}/user/logout`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      },
      mode: "cors",
      credentials: "include",
    });
    localStorage.removeItem("user");
    toast.success("Logout successful")
    dispatch({ type: actions.LOGOUT });
  };

  return { state, register, login, logout };
};

export default useAuth;
