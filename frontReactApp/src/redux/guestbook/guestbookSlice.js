import { getGuestbookListInfo } from "./guestbookThunk";
import { createSlice } from "@reduxjs/toolkit";


const guestbookSlice = createSlice({
    name:"guestbookSlice",
    initialState: {
        isLoading:false,
        guestbookListInfo:{},
    },
    reducers:{},
    extraReducers:(builder)=>{
        builder.addCase(getGuestbookListInfo.pending,()=>{});
        builder.addCase(getGuestbookListInfo.fulfilled,()=>{});
        builder.addCase(getGuestbookListInfo.rejected,()=>{});
    }
});

export const {} = guestbookSlice.actions;
export default guestbookSlice;