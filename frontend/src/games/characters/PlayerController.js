
import Phaser from "phaser";

class PlayerController extends Phaser.GameObjects.Zone{
    constructor(scene , x, y , width ,height , cursors ,player){
        super(scene, x, y , width, height)
        this.cursors = cursors;
        this.player = player;

        scene.physics.add.existing(this)
    }
    update(){
        if(!this.cursors){
          console.log("there is no cursors @ controller")
            return
        }
        if (this.player.playerBehavior === "SITTING") {
            return
        }
        const {x, y} = this.player;
 
        if (this.cursors.left.isDown) {
            this.setPosition(x - 32, y);
          } else if (this.cursors.right.isDown) {
            this.setPosition(x + 32, y);
          } else if (this.cursors.up.isDown) {
            this.setPosition(x, y - 32);
          } else if (this.cursors.down.isDown) {
            this.setPosition(x, y + 32);
          }
    }
}

export default PlayerController;