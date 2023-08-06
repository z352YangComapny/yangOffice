import Phaser from "phaser";
import Player from "./Player";

export default class MyPlayer extends Player{
    constructor(scene, x, y, texture, id, frame) {
        super(scene, x, y, texture, id, frame);
        this.playContainerBody = this.playerContainer.body;
        this.chairOnSit = undefined;
      }

      setPlayerName(name){
        this.playerName.setText(name);
        const phaserEvents = new Phaser.Events.EventEmitter()
        phaserEvents.emit('setMyPlayerName', name)
        
      }
}