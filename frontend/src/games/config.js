import Phaser from 'phaser';
import Game from './scene/Game';

const GameConfig = {
  type: Phaser.AUTO,
  parent: 'game-container',
  backgroundColor: '#93cbee',
  pixelArt: true,
  scale: {
    // mode: Phaser.Scale.ScaleModes.RESIZE,
    width: 1120,
    height: 630,
  },
  physics: {
    default: 'arcade',
    arcade: {
      gravity: { y: 0 },
      debug: false,
    },
  },
  autoFocus: true,
  scene: [Game],
};

export default GameConfig;
