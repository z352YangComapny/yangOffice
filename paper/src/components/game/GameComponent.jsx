import React, { useContext, useEffect, useRef } from 'react';
import Phaser from 'phaser';
import GameConfig from '../../games/config';
import "../../assets/css/Game.css"
import { MemberContext } from 'contexts/MembetContextProvider';
import Game from 'games/scene/Game';
import { Button, Input } from 'reactstrap';


const GameComponent = () => {
    const {
        states: {
            isLogin,
            accessToken,
            userProfile
        },
        actions: {
            setUserProfile,
            setIsLogin,
            setAccessToken,
            LogOut,
            signin
        },
    } = useContext(MemberContext);
    const gameContainerRef = useRef(null);
    let game = null;


    const handleOnClickSocket = () => {
        window.userProfile = userProfile;
        if (accessToken)
            window.token = accessToken;
        game = new Phaser.Game({
            ...GameConfig,
            parent: gameContainerRef.current
        });
        if (isLogin && game) {
            window.addEventListener('keydown', handleKeyDown);

        }
    }
    const handleKeyDown = (event) => {
        if (event.keyCode === 38 || event.keyCode === 40 || event.keyCode === 13 || event.keyCode === 32)
            event.preventDefault();
    }
    

    useEffect(() => {
        if (isLogin && game) {

            const handleKeyDown = (event) => {
                if (event.keyCode === 38 || event.keyCode === 40)
                    event.preventDefault();
            }
            window.addEventListener('keydown', handleKeyDown);
        }
        if (game && gameContainerRef.current) {
            game.canvas.parentElement.removeChild(game.canvas);
            gameContainerRef.current.appendChild(game.canvas);
            game.scale.refresh();
        }
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
            if (game)
                game.destroy(true);
        };
    }, [game]);

    return (
        <>
            <div className="game-container" ref={gameContainerRef}>
            
            </div>
            <Input type='text' id='inputElement' placeholder='Enter your message' style={{width:"1120px"}}></Input>
            <Button onClick={handleOnClickSocket} style={{margin:"20px"}}>Online 접속하기</Button>
        </>
    );
};

export default GameComponent;
