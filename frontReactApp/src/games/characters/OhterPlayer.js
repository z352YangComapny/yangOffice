import Phaser from "phaser";
import Player from "./Player";



class OtherPlayers extends Player {
    constructor(scene, x, y, texture, name) {
        super(scene, x, y, texture);
        this.name = name;
        this.targetPosition = [x, y];
        this.playerName.setText(name);
        this.phaserEvents = new Phaser.Events.EventEmitter();
        this.ContainerBody = this.playerContainer.body;

        scene.physics.add.existing(this);
    }

    update(socketSignal) {
        const { animkey, name, x, y } = socketSignal
        if (this.x !== x && this.y !== y) {
            this.playerContainer.setPosition(x, y);
            this.setPosition(x, y);
        }

        const speed = 200;
        let vx = 0;
        let vy = 0;
        const animkeyParts = animkey.split("_");
        const direction = animkeyParts[animkeyParts.length - 1];
        const state = animkeyParts[animkeyParts.length - 2];

        if (direction === "left" && state === "run") {
            vx -= speed;
        } else if (direction === "right" && state === "run") {
            vx += speed;
        } else if (direction === "up" && state === "run") {
            vy -= speed;
            this.setDepth(this.y);
        } else if (direction === "down" && state === "run") {
            vy += speed;
            this.setDepth(this.y);
        }

        this.setVelocity(vx, vy)
        this.body.velocity.setLength(speed);
        this.playerContainer.setPosition(this.x, this.y - 30);
        this.ContainerBody.setVelocity(vx, vy);
        this.ContainerBody.velocity.setLength(speed);


        if (vx > 0) {
            this.play(`${animkey}`, true)
        } else if (vx < 0) {
            this.play(`${animkey}`, true)
        } else if (vy > 0) {
            this.play(`${animkey}`, true)
        } else if (vy < 0) {
            this.play(`${animkey}`, true)
        } else {
            if (this.anims.currentAnim) {
                const parts = this.anims.currentAnim.key.split('_')
                parts[1] = 'idle'
                const newAnim = parts.join('_')
                // this prevents idle animation keeps getting called
                if (this.anims.currentAnim.key !== newAnim) {
                    this.play(parts.join('_'), true);
                }
            }
        }
    }
}

export default OtherPlayers