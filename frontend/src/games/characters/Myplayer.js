import Phaser from "phaser";
import Player from "./Player";

export default class MyPlayer extends Player{
  // , frame
    constructor(scene, x, y, texture) {
        super(scene, x, y, texture);
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

      update(controller, cursors , keyA , keyS){
        if(!cursors) return
        if(Phaser.Input.Keyboard.JustDown(keyA)){
          /* 상호작용 모드 */
        }

        switch(this.playerBehavior){
          case 'IDLE':
            const speed = 200;
            let vx=0;
            let vy=0;
            if(cursors.left.isDown){
              vx-=speed
            }
            else if(cursors.right.isDown){
              vx+=speed
            }

            if(cursors.up.isDown){
              vy-=speed
              this.setDepth(this.y)
            } else if(cursors.down.isDown){
              vy+=speed
              this.setDepth(this.y)
            }

            this.setVelocity(vx , vy)
            this.body.velocity.setLength(speed);
            this.playContainerBody.setVelocity(vx , vy)
            this.playContainerBody.setLength(speed)

            if (vx > 0) {
              this.play(`${this.playerTexture}_run_right`, true)
            } else if (vx < 0) {
              this.play(`${this.playerTexture}_run_left`, true)
            } else if (vy > 0) {
              this.play(`${this.playerTexture}_run_down`, true)
            } else if (vy < 0) {
              this.play(`${this.playerTexture}_run_up`, true)
            } else {
              const parts = this.anims.currentAnim.key.split('_')
              parts[1] = 'idle'
              const newAnim = parts.join('_')
              // this prevents idle animation keeps getting called
              if (this.anims.currentAnim.key !== newAnim) {
                this.play(parts.join('_'), true)
                // send new location and anim to server
                // network.updatePlayer(this.x, this.y, this.anims.currentAnim.key)
              }
            }

        }

        

      }
}