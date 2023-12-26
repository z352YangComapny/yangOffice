const { createSlice } = require("@reduxjs/toolkit");

const photoFeedSlice = createSlice({
    name:"photoFeedSlice",
    initialState: {},
    reducers:{},
    extraReducers:(builder)=>{
        builder.addCase(.pending,()=>{});
        builder.addCase(.fulfilled,()=>{});
        builder.addCase(.rejected,()=>{});
    }
});

export const {} = photoFeedSlice.actions;
export default photoFeedSlice;