import Phaser from 'phaser';
import Game from './scene/Game';
import UI from './scene/UI';

const GameConfig = {
  type: Phaser.AUTO,
  parent: 'game-container',
  backgroundColor: '#93cbee',
  pixelArt: true,
  scale: {
    // mode: Phaser.Scale.ScaleModes.RESIZE,
    width: 800,
    height: 450,
  },
  physics: {
    default: 'arcade',
    arcade: {
      gravity: { y: 0 },
      debug: false,
    },
  },
  autoFocus: true,
  scene: [Game, UI],
};

export default GameConfig;
