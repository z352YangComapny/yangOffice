import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
// styles
import "assets/css/bootstrap.min.css"
import "assets/css/paper-kit.css";
import "assets/demo/demo.css";
import reportWebVitals from './reportWebVitals';
import MembetContextProvider from 'contexts/MembetContextProvider';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <MembetContextProvider>
    <App />
    </MembetContextProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
