import React, { useEffect, useRef } from 'react';
import Phaser from 'phaser';
import GameConfig from '../../games/config';
import "../../assets/css/game.css"

const GameComponent = () => {
    const gameContainerRef = useRef(null);
    let game = null;

    useEffect(() => {
        game = new Phaser.Game(GameConfig);

        return () => {
            game.destroy(true);
        };
    }, []);

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
