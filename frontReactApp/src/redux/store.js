import { configureStore, combineReducers } from "@reduxjs/toolkit";

const memberSlice = require("./member/memberSlice").default;
const guestbookSlice = require("./guestbook/guestbookSlice").default;
const notificationSlice = require("./notification/notificationSlice").default;
const photoFeedSlice = require("./photofeed/photofeedSlice").default


const rootReducer = combineReducers({
  memberReducer: memberSlice.reducer,
  guestbookReducer: guestbookSlice.reducer,
  notificationReducer: notificationSlice.reducer,
  photoFeedReducer: photoFeedSlice.reducer
});

const store = configureStore({
  reducer: rootReducer
});

export default store;
