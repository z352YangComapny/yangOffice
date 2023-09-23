import React, { useContext, useEffect, useRef, useState } from 'react';
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
    const [game ,setGame] = useState(null);


    const handleOnClickSocket = () => {
        const game = new Phaser.Game({
            ...GameConfig,
            parent: gameContainerRef.current
        });
        setGame(game);
    }
    const handleKeyDown = (event) => {
        // || event.keyCode === 32
        if (event.keyCode === 38 || event.keyCode === 40 || event.keyCode === 13 || event.keyCode === 37 || event.keyCode === 39 )
            event.preventDefault();
    }
    useEffect(()=>{
        handleOnClickSocket()
        return()=>{
        }
    },[])

    useEffect(() => {
        if (isLogin && game) {
            window.addEventListener('keydown', handleKeyDown);
        }
        if (game && gameContainerRef.current) {
            game.canvas.parentElement.removeChild(game.canvas);
            gameContainerRef.current.appendChild(game.canvas);
            game.scale.refresh();
        }
        return () => {
            window.removeEventListener('keydown', handleKeyDown);
            console.log('언마운트')
            if(game){ 
                const scene = game.scene.getScene('Game');
                if(scene) scene.network.disconnect();
                game.destroy(true); 
                setGame(null);
            }
            
        };
    }, [game]);

    return (
        <>
            <div className="game-container" ref={gameContainerRef}>
            <div className='game-margin'></div>
            <Input type='text' id='inputElement' placeholder='Enter your message' style={{width:"800px"}}></Input>
            
            </div>
            
            {/* <Button onClick={handleOnClickSocket} style={{margin:"20px"}}>Online 접속하기</Button> */}
        </>
    );
};

export default GameComponent;
