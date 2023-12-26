const { createSlice } = require("@reduxjs/toolkit");

const memberSlice = createSlice({
    name:"memberSlice",
    initialState: {},
    reducers:{},
    extraReducers:(builder)=>{
        builder.addCase(.pending,()=>{});
        builder.addCase(.fulfilled,()=>{});
        builder.addCase(.rejected,()=>{});
    }
});

export const {} = memberSlice.actions;
export default memberSlice;