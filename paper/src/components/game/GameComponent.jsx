import React, { useContext, useEffect, useRef } from 'react';
import Phaser from 'phaser';
import GameConfig from '../../games/config';
import "../../assets/css/Game.css"
import { MemberContext } from 'contexts/MembetContextProvider';
import Game from 'games/scene/Game';


const GameComponent = () => {
    const {
        states:{
            isLogin,
            accessToken,
            userProfile
        },
        actions:{
          setUserProfile,
            setIsLogin,
            setAccessToken,
            LogOut,
            signin
        },
    } = useContext(MemberContext);
    const gameContainerRef = useRef(null);
    let game = null;

    useEffect(() => {
        if(isLogin){
        window.userProfile = userProfile;
        if(accessToken)
        window.token = accessToken;
        game = new Phaser.Game({
            ...GameConfig,
            parent: gameContainerRef.current,
            scene: [Game],
        });

        
        const handleKeyDown = (event) => {
            if (event.keyCode === 38 || event.keyCode === 40)
              event.preventDefault();
          }
      
          window.addEventListener('keydown', handleKeyDown);
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
            game.destroy(true);
        };
    }
    }, [isLogin]);

    useEffect(() => {
        if (game && gameContainerRef.current) {
            game.canvas.parentElement.removeChild(game.canvas);
            gameContainerRef.current.appendChild(game.canvas);
            game.scale.refresh();
        }
    }, []);

    return (
        <div className="game-container" ref={gameContainerRef}>
            
        </div>
    );
};

export default GameComponent;
