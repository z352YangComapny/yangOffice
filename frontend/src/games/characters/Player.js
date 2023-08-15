import Phaser from "phaser";

const sittingShiftData = {
    up: [0, 3, -10],
    down: [0, 3, 1],
    left: [0, -8, 10],
    right: [0, -8, 10],
  };

  class Player extends Phaser.Physics.Arcade.Sprite{
    // , frame
    constructor(scene, x, y, texture) {
        super(scene, x, y, texture);
    
        //plyaer info
        // this.playerId = id;
        this.playerTexture = texture;
        this.playerBehavior = "IDLE";

        //connection Setting
        this.readyToConnect = false;

        //WebCam Setting
        this.videoConnected = false;

        //players character view Setting
        this.playerName = this.scene.add.text(0, 0, '').setFontFamily('Arial').setFontSize(12).setColor('#000000').setOrigin(0.5);
        this.playerContainer = this.scene.add.container(this.x, this.y - 30).setDepth(5000);
        this.playerDialogBubble = this.scene.add.container(0, 0).setDepth(5000);
        this.playerContainer.add(this.playerDialogBubble);
        this.playerContainer.add(this.playerName);
    
        //Sitting Motion Setting
        this.setDepth(this.y);
        this.anims.play(`${this.playerTexture}_idle_down`, true);

        //Collides & add to World
        this.scene.physics.world.enable(this.playerContainer);
        // const temp = new Phaser.Physics.Arcade.Body();
        const playContainerBody = this.playerContainer.body;
        const collisionScale = [0.5, 0.2];
        playContainerBody.setSize(this.width * collisionScale[0], this.height * collisionScale[1]).setOffset(-8, this.height * (1 - collisionScale[1]) + 6);

        console.log("부모생성자 호출됨")
      }

      updateDialogBubble(content) {
        this.clearDialogBubble();

        //70자 제한
        const dialogBubbleText = content.length <= 100 ? content : content.substring(0, 100).concat('...');
    
        const innerText = this.scene.add.text(0, 0, dialogBubbleText, { wordWrap: { width: 165, useAdvancedWrap: true } }).setFontFamily('Arial').setFontSize(12).setColor('#000000').setOrigin(0.5);
    
        const innerTextHeight = innerText.height;
        const innerTextWidth = innerText.width;
    
        innerText.setY(-innerTextHeight / 2 - this.playerName.height / 2);
        const dialogBoxWidth = innerTextWidth + 10;
        const dialogBoxHeight = innerTextHeight + 3;
        const dialogBoxX = innerText.x - innerTextWidth / 2 - 5;
        const dialogBoxY = innerText.y - innerTextHeight / 2 - 2;
    
        this.playerDialogBubble.add(this.scene.add.graphics().fillStyle(0xffffff, 1).fillRoundedRect(dialogBoxX, dialogBoxY, dialogBoxWidth, dialogBoxHeight, 3).lineStyle(1, 0x000000, 1).strokeRoundedRect(dialogBoxX, dialogBoxY, dialogBoxWidth, dialogBoxHeight, 3));
        this.playerDialogBubble.add(innerText);

        //채팅창 지속 6초
        this.timeoutID = window.setTimeout(() => {
          this.clearDialogBubble();
        }, 6000);
    }
  }

  export default Player;