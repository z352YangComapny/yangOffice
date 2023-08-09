import Phaser from "phaser";

export default class PlayerController extends Phaser.GameObjects.Zone{
    constructor(scene , x, y , width ,height){
        super(scene, x, y , width, height)
        this.selectItem = undefined;
    }
    update(player, cursors){
        if(!cursors){
            return
        }
        if (player.playerBehavior === "SITTING") {
            return
        }
        const {x, y} = player;
 
        if (cursors.left.isDown) {
            this.setPosition(x - 32, y);
          } else if (cursors.right.isDown) {
            this.setPosition(x + 32, y);
          } else if (cursors.up.isDown) {
            this.setPosition(x, y - 32);
          } else if (cursors.down.isDown) {
            this.setPosition(x, y + 32);
          }
        if (this.selectedItem) {
            if (!this.scene.physics.overlap(this, this.selectedItem)) {
                this.selectedItem.clearDialogBox();
                this.selectedItem = undefined;
            }
        }
    }
}