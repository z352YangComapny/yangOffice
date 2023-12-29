import { createAsyncThunk  } from "@reduxjs/toolkit";
import axios from "axios";

const SERVER_BASE_URL = "http://localhost:8000";

export const signIn = createAsyncThunk("memberSlice/signin", async ({username, password}) => {
    const resp = await axios.post(`${SERVER_BASE_URL}/api/member/signup`,{username, password})
    console.log(resp);
    const status = resp.status
    const data = resp.data
    return {data, status}
});

export const signUp = createAsyncThunk("memberSlice/signup", async ({
    username,
    name,
    password,
    nickname,
    birthday,
    gender,
    phone,
    email}) => {
    const resp = await axios.post(`${SERVER_BASE_URL}/api/member/signin`,{
        username,
        name,
        password,
        nickname,
        birthday,
        gender,
        phone,
        email
    })
    const status = resp.status
    const data = resp.data
    return {data, status}
});

export const getUserProfile = createAsyncThunk("memberSlice/getUserProfile", async ()=>{
    const resp = await axios.post(`${SERVER_BASE_URL}/api/profile`)
    return { data }
});