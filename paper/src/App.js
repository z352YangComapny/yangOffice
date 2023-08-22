import logo from './logo.svg';
import './App.css';
import axios from 'axios';
import { useCallback, useContext, useEffect, useState } from 'react';
import GameComponent from './components/game/GameComponent';
import Footer from './components/common/Footer';
import SignIn from './pages/SignIn';
import GameSocketComponent from './components/route/GameSocketComponent';
import DemoFooter from 'components/Footers/DemoFooter';
import Index from 'views/Index';
import { MemberContext } from 'contexts/MembetContextProvider';

function App() {
  const [ping, setPing] = useState('');
  const {states:{isLogin}, actions:{setIsLogin}} = useContext(MemberContext);


  useEffect(() => {
    axios.get('http://localhost:8080/ping')
      .then((resp) => {
        console.log(resp)
        setPing(resp.data)
      })
      .catch((err) => { console.log(err) })
  }, [])


  return (
    <div className="App">
      <GameComponent />
      <span>{ping}</span>
      <SignIn/>
      <Footer/>
      <Index/>
    </div>
  );
}

export default App;
