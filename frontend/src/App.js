import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { useEffect, useState } from 'react';
import GameComponent from './components/game/GameComponent';
import Footer from './components/common/Footer';

function App() {
  const [ping, setPing] = useState('');

  useEffect(() => {
    console.log("build!")
    axios.get('/ping')
      .then((resp) => {
        console.log(resp)
        setPing(resp.data)
      })
      .catch((err) => { console.log(err) })

    const handleKeyDown = (event) => {
      if (event.keyCode === 38 || event.keyCode === 40)
        event.preventDefault();
    }

    window.addEventListener('keydown', handleKeyDown);
    return () => {
      window.removeEventListener('keydown', handleKeyDown);
    }
  }, [])


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
      <GameComponent />
      <Footer/>
    </div>
  );
}

export default App;
