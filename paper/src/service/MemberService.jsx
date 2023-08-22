import React from "react";
import axios from "axios";


const springURL = "http://localhost:8080"

class AppService {
   async singIn({username, password}){
      const axiosConfig = {
         headers:{
           "Content-Type" : "application/json",
         }
       }
       const axiosBody = {
         username:username,
         password:password
       }
      return await axios.post(springURL+"/login",axiosBody,axiosConfig)
   }
}

export default new AppService();