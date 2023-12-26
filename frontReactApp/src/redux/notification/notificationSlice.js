const { createSlice } = require("@reduxjs/toolkit");

const notificationSlice = createSlice({
    name:"notificationSlice",
    initialState: {},
    reducers:{},
    extraReducers:(builder)=>{
        builder.addCase(.pending,()=>{});
        builder.addCase(.fulfilled,()=>{});
        builder.addCase(.rejected,()=>{});
    }
});

export const {} = notificationSlice.actions;
export default notificationSlice;