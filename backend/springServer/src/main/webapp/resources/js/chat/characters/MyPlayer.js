import Phaser from "phaser";
import Player from "./Player";

class MyPlayer extends Player {
    // , frame
    constructor(scene, x, y, texture, cursors) {
        super(scene, x, y, texture);
        // this.playContainerBody = new Phaser.Physics.Arcade.Body() ;
        this.phaserEvents = new Phaser.Events.EventEmitter();
        this.cursors = cursors;

        scene.add.existing(this);
        scene.physics.add.existing(this);
    }

    setPlayerName(name) {
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

        if (this.cursors.up.isDown) {
            vy -= speed
            this.setDepth(this.y)
        } else if (this.cursors.down.isDown) {
            vy += speed
            this.setDepth(this.y)
        }


        // console.log(this)
        this.setVelocity(vx, vy)
        this.body.velocity.setLength(speed);
        // this.playContainerBody.setVelocity(vx , vy)
        // this.playContainerBody.setLength(speed)

        let animKey = "idle_down";

        if (vx > 0) {
            animKey = "run_right";
        } else if (vx < 0) {
            animKey = "run_left";
        } else if (vy > 0) {
            animKey = "run_down";
        } else if (vy < 0) {
            animKey = "run_up";
        }

        if (this.anims.currentAnim.key !== `${this.playerTexture}_${animKey}`) {
            this.play(`${this.playerTexture}_${animKey}`, true);
            // send new location and anim to server
            // network.updatePlayer(this.x, this.y, this.anims.currentAnim.key);
        }

    }
}


export default MyPlayer;