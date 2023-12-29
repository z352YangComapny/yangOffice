import { createAsyncThunk  } from "@reduxjs/toolkit";
import axios from "axios";

const SERVER_BASE_URL = "http://localhost:8000";

export const getGuestbookListInfo = createAsyncThunk("guestbookSlice/getGuestbookListInfo", async () => {
    const resp = await axios.post(`${SERVER_BASE_URL}/api/guestbook`,{})
    const status = resp.status
    const data = resp.data
    return {data, status}
});