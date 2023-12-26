const { createSlice } = require("@reduxjs/toolkit");

const guestbookSlice = createSlice({
    name:"guestbookSlice",
    initialState: {},
    reducers:{},
    extraReducers:(builder)=>{
        builder.addCase(.pending,()=>{});
        builder.addCase(.fulfilled,()=>{});
        builder.addCase(.rejected,()=>{});
    }
});

export const {} = guestbookSlice.actions;
export default guestbookSlice;