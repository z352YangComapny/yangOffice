import Phaser from "phaser";
import Player from "./Player";

class MyPlayer extends Player {
  // , frame
  constructor(scene, x, y, texture, cursors, network) {
    super(scene, x, y, texture);
    // this.playContainerBody = new Phaser.Physics.Arcade.Body() ;
    this.phaserEvents = new Phaser.Events.EventEmitter();
    this.cursors = cursors;
    this.network = network;
    this.name = '';
    this.ContainerBody = this.playerContainer.body;

    scene.add.existing(this);
    scene.physics.add.existing(this);
  }

  setPlayerName(name) {
    this.name = name;
    this.playerName.setText(name);
    this.phaserEvents.emit('setMyPlayerName', name)
  }

  setPlayerTexture(texture) {
    this.playerTexture = texture
    this.anims.play(`${this.playerTexture}_idle_down`, true)
    this.phaserEvents.emit('my-player-texture-change', this.x, this.y, this.anims.crruntAnim.key)
  }

  update() {
    if (!this.cursors) {
      console.log("there are no cursors")
      return
    }
    // if(Phaser.Input.Keyboard.JustDown(keyA)){
    //   /* 상호작용 모드 로직 작성 예정*/
    // }

    const speed = 200;
    let vx = 0;
    let vy = 0;
    if (this.cursors.left.isDown) {
      vx -= speed
    }
    else if (this.cursors.right.isDown) {
      vx += speed
    }

    else if (this.cursors.up.isDown) {
      vy -= speed
      this.setDepth(this.y)
    } else if (this.cursors.down.isDown) {
      vy += speed
      this.setDepth(this.y)
    }


    // console.log(this)
    this.setVelocity(vx, vy)
    this.body.velocity.setLength(speed);
    this.playerContainer.setPosition(this.x, this.y - 30);
    this.ContainerBody.setVelocity(vx,vy);
    this.ContainerBody.velocity.setLength(speed);


    
    if (vx > 0) {
      this.play(`${this.playerTexture}_run_right`, true)
      this.network.updatePlayer(this.x, this.y, this.anims.currentAnim.key, this.name)
    } else if (vx < 0) {
      this.play(`${this.playerTexture}_run_left`, true)
      this.network.updatePlayer(this.x, this.y, this.anims.currentAnim.key, this.name)
    } else if (vy > 0) {
      this.play(`${this.playerTexture}_run_down`, true)
      this.network.updatePlayer(this.x, this.y, this.anims.currentAnim.key, this.name)
    } else if (vy < 0) {
      this.play(`${this.playerTexture}_run_up`, true)
      this.network.updatePlayer(this.x, this.y, this.anims.currentAnim.key, this.name)
    } else {
      if (this.anims.currentAnim) {
        const parts = this.anims.currentAnim.key.split('_')
        parts[1] = 'idle'
        const newAnim = parts.join('_')
        // this prevents idle animation keeps getting called
        if (this.anims.currentAnim.key !== newAnim) {
          this.play(parts.join('_'), true)
          // send new location and anim to server
          this.network.updatePlayer(this.x, this.y, this.anims.currentAnim.key, this.name)
        }
      }
    }

  }
}


export default MyPlayer;