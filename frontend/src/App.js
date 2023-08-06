import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { useState } from 'react';
import GameComponent from './component/game/GameComponent';

function App() {
  const [ping, setPing] = useState('');

  axios.get('/ping')
  .then((resp)=> {
    console.log(resp)
    setPing(resp.data)
  })
  .catch((err)=>{console.log(err)})

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <span>{ping}</span>
      </header>
      <GameComponent/>
    </div>
  );
}

export default App;
