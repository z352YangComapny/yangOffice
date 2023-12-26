const { configureStore, combineReducers } = require("@reduxjs/toolkit");
const MemberSlice = require("./member/memberSlice").default;
const guestbookSlice = require("./guestbook/guestbookSlice").default;
const notificationSlice = require("./notification/notificationSlice").default;
const photoFeedSlice = require("./photofeed/photofeedSlice").default


const rootReducer = combineReducers({
  memberReducer: postSlice.reducer,
  guestbookReducer: authSlice.reducer,
  notificationReducer: notificationSlice.reducer,
  photoFeedReducer: photoFeedSlice.reducer
});

const store = configureStore({
  reducer: rootReducer
});

export default store;
