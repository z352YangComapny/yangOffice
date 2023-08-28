
import Phaser, { Scene } from "phaser";
import { createCharacterAnims } from "../anims/CharacterAnims";
import PlayerController from "../characters/PlayerController";
import MyPlayer from "../characters/Myplayer";
import Network from "../network/NetWork";
import OtherPlayers from "games/characters/OhterPlayer";


export default class Game extends Scene {
    constructor() {
        super({ key: 'Game' });
        this.collidableObject = [];
        this.userProfile = window.userProfile;
        this.map = null;
        this.network = null;
        this.cursors = null;
        this.keyA = null;
        this.keyS = null;
        this.keyEnter = null;
        this.myPlayer = null;
        this.controller = null;
        this.otherPlayers = null;
        this.otherPlayerMap = new Map();
        this.playerTexture = 'adam'
        this.network = new Network(this.userProfile.username);
        this.dialogueBubble = null;
    }


    preload() {
        this.registry.set('network', this.network)
        
        this.load.tilemapTiledJSON('map', `http://localhost:8080/resources/upload/attachment/fianlreal.json` );
        this.load.spritesheet('basement', 'assets/tilesets/Basement.png', {
            frameWidth: 32,
            frameHeight: 32,
        })
        this.load.spritesheet('classroom', 'assets/tilesets/Classroom_and_library.png', {
            frameWidth: 32,
            frameHeight: 32,
        })
        this.load.spritesheet('floorAndGround', 'assets/tilesets/FloorAndGround.png', {
            frameWidth: 32,
            frameHeight: 32,
        })
        this.load.spritesheet('generic', 'assets/tilesets/Generic.png', {
            frameWidth: 32,
            frameHeight: 32,
        })
        this.load.spritesheet('modern_office_black_shadow', 'assets/tilesets/Modern_Office_Black_Shadow.png', {
            frameWidth: 32,
            frameHeight: 32,
        })
        this.load.spritesheet('adam', 'assets/character/adam.png', {
            frameWidth: 32,
            frameHeight: 48,
        })
        this.load.spritesheet('ash', 'assets/character/ash.png', {
            frameWidth: 32,
            frameHeight: 48,
        })
        this.load.spritesheet('lucy', 'assets/character/lucy.png', {
            frameWidth: 32,
            frameHeight: 48,
        })
        this.load.spritesheet('nancy', 'assets/character/nancy.png', {
            frameWidth: 32,
            frameHeight: 48,
        })

    }

    create() {
        if (!this.network.access) {
            console.log(this.network)
            return
        }
        // Load Map
        this.map = this.make.tilemap({ key: 'map' });
        const tileset = this.map.addTilesetImage('FloorAndGround', 'floorAndGround')
        // Tile Layer
        const groundLayer = this.map.createLayer('ground', tileset);
        groundLayer.setCollisionByProperty({ collides: true });

        createCharacterAnims(this.anims)
        this.registerKeys()

        // , this.network.mySessionId
        this.myPlayer = new MyPlayer(this, 800, 500, 'adam', this.cursors, this.network);

        this.myPlayer.setPlayerName(this.userProfile.username);
        console.log(this.myPlayer)
        this.controller = new PlayerController(this, 0, 0, 16, 16, this.cursors, this.myPlayer);

        this.otherPlayers = this.add.group({
            classType: OtherPlayers
        });

        // 알림 메세지 칸


        // Object Layer
        this.addGroupFromTiled('carpet', 'generic', 'Generic', false);
        this.addGroupFromTiled('wall', 'floorAndGround', 'FloorAndGround', true);
        this.addGroupFromTiled('office', 'modern_office_black_shadow', 'Modern_Office_Black_Shadow', false);
        this.addGroupFromTiled('officeOnCollides', 'modern_office_black_shadow', 'Modern_Office_Black_Shadow', true);
        this.addGroupFromTiled('generic', 'generic', 'Generic', false);
        this.addGroupFromTiled('genericOnCollides', 'generic', 'Generic', true);
        this.addGroupFromTiled('classRoom', 'classroom', 'Classroom_and_library', false);
        this.addGroupFromTiled('classRoomOnCollides', 'classroom', 'Classroom_and_library', true);
        this.addGroupFromTiled('basement', 'basement', 'Basement', false);
        this.addGroupFromTiled('basementOnCollides', 'basement', 'Basement', true);
        // Loader Player

        this.cameras.main.zoom = 1.3
        this.cameras.main.startFollow(this.myPlayer, true);

        // this.keySpace = this.input.keyboard.addKey(Phaser.Input.Keyboard.KeyCodes.SPACE);
    }

    update() {
        if (this.myPlayer) {
            this.myPlayer.update()
            this.handleNotifications();


            this.dialogueBubble = this.registry.get("bubble")
            if (this.dialogueBubble.length > 0)
                this.handleMyDialogueBubble();
        }
    }

    handleNotifications() {
        while (this.network.pendingNotification.length > 0) {
            const notification = this.network.pendingNotification.shift();
            console.log(notification)
            switch (notification.message.msgType) {
                case "joinNotification":
                    // this.showNotification(notification.message, 3500);
                    this.addOtherPlayer(notification.message.name);
                    break;
                case "chat":
                    console.log(notification.message);
                    this.handleOhterPlayerBubble(notification.message)
                    break;
                case "position":
                    console.log(notification.message);
                    this.handleOtherPlayerPostion(notification.message);
                    break;
                case "connectedList":
                    console.log(notification.message.connectedList);

                    this.handleConectedPlayer(notification.message.connectedList);
                    break;
                case "leaveNotification":
                    console.log(notification.message);
                    this.handleLeavePlayer(notification.message);
                    break;
                default:
                    break;
            }
        }
    }

    handleOhterPlayerBubble(message) {
        console.log(message)
        const text = message.text
        const name = text.split(' ')[0];
        if (name === this.myPlayer.name) return;
        this.otherPlayerMap.get(name).updateDialogBubble(text)
    }

    handleMyDialogueBubble() {
        while (this.dialogueBubble.length > 0) {
            const textVal = this.dialogueBubble.shift();

            this.myPlayer.updateDialogBubble(textVal);
            this.network.handleSendChatMsg(textVal);

            this.registry.set('bubble', this.dialogueBubble);
        }
    }

    handleLeavePlayer(leavePlayer) {
        if (this.otherPlayerMap.has(leavePlayer.name)) {
            const leavingPlayer = this.otherPlayerMap.get(leavePlayer.name);
            this.otherPlayerMap.delete(leavePlayer.name);

            // Remove the leaving player from the otherPlayers group
            leavingPlayer.destroy();
            leavingPlayer.playerContainer.destroy();
        }

    }

    handleOtherPlayerPostion(otherPlayerPostion) {
        this.otherPlayers.getChildren().forEach((player) => {
            if (player.name !== otherPlayerPostion.name) {
                return;
            }
            console.log(player)
            player.update(otherPlayerPostion);
        })


    }

    handleConectedPlayer(connectedList) {
        if (connectedList)
            connectedList.map((player) => {
                this.addOtherPlayer(player);
            })
    }

    addOtherPlayer(name) {
        if (this.myPlayer.name === name || name === '') return;
        const otherPlayer = new OtherPlayers(this, 800, 500, 'adam', name, this.otherPlayers);
        this.physics.add.collider([otherPlayer, otherPlayer.playerContainer], this.collidableObject);
        console.log(otherPlayer)
        this.otherPlayers.add(otherPlayer, true);
        this.otherPlayerMap.set(name, otherPlayer);
    }


    registerKeys() {
        this.cursors = {
            ...this.input.keyboard.createCursorKeys()
        }
        // maybe we can have a dedicated method for adding keys if more keys are needed in the future
        this.input.keyboard.disableGlobalCapture()

        // this.input.keyboard.on('keydown-ENTER', (event) => {
        //     store.dispatch(setShowChat(true))
        //     store.dispatch(setFocused(true))
        // })
        // this.input.keyboard.on('keydown-ESC', (event) => {
        //     store.dispatch(setShowChat(false))
        // })

    }

    addGroupFromTiled(objectLayerName, key, tilesetName, collidable) {
        const group = this.physics.add.staticGroup();
        const objectLayer = this.map.getObjectLayer(objectLayerName);
        objectLayer.objects.forEach((object) => {
            const actualX = object.x + object.width * 0.5;
            const actualY = object.y - object.height * 0.5;

            const tileset = this.map.getTileset(tilesetName);
            const tileIndex = object.gid - tileset.firstgid;

            const tile = group
                .get(actualX, actualY, key, tileIndex)
                .setDepth(actualY);

            // Check if flipping horizontal is true and flip the image accordingly
            if (object.flippedHorizontal) {
                tile.flipX = true;
            }
        });

        if (this.myPlayer && collidable) {
            this.physics.add.collider([this.myPlayer, this.myPlayer.playerContainer], group);
            this.collidableObject = [...this.collidableObject, group]
        }
    }

    disableKeys() {
        this.input.keyboard.enabled = false
    }

    enableKeys() {
        this.input.keyboard.enabled = true
    }
    destroy() {
        this.network.disconnect();
        super.destroy();
    }
}

