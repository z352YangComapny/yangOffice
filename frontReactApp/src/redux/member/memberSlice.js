import { signIn } from './memberThunk';
import { createSlice } from "@reduxjs/toolkit";

const memberSlice = createSlice({
    name:"memberSlice",
    initialState:{
        isLaoding:false,
        status:null,
        isLogin:false,
        userProfile:null,
    },
    reducers:{},
    extraReducers:(builder)=>{
        builder.addCase(signIn.pending,(state)=>{state.isLoading=true});
        builder.addCase(signIn.fulfilled,(state,action)=>{
            state.isLaoding=false;
            console.log(state,action)
        });
        builder.addCase(signIn.rejected,(state)=>{state.isLaoding=false;});
    }
});

export const {} = memberSlice.actions;
export default memberSlice;