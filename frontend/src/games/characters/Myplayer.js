import Phaser from "phaser";
import Player from "./Player";

export default class MyPlayer extends Player{
  // , frame
    constructor(scene, x, y, texture, id) {
        super(scene, x, y, texture, id);
        this.playContainerBody = this.playerContainer.body;
        this.chairOnSit = undefined;
        this.phaserEvents = new Phaser.Events.EventEmitter();
      }

      setPlayerName(name){
        this.playerName.setText(name);
        this.phaserEvents.emit('setMyPlayerName', name)
      }

      setPlayerTexture(texture) {
        this.playerTexture = texture
        this.anims.play(`${this.playerTexture}_idle_down`, true)
        this.phaserEvents.emit('my-player-texture-change', this.x , this.y ,this.anims.crruntAnim.key)
      }

      update(){

      }
}