import React from "react";
import axios from "axios";


class AppService {
   singIn({username, password}){
      const axiosConfig = {
         headers:{
           "Content-Type" : "application/json",
         }
       }
       const axiosBody = {
         username:username,
         password:password
       }
      return axios.post("/login",axiosBody,axiosConfig)
   }
}

export default new AppService();