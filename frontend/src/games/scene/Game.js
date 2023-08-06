import Phaser, { Scene } from "phaser";


export default class Game extends Scene{
    constructor(){
        super('game')
        this.map = undefined
    }
    

    preload(){
        this.load.tilemapTiledJSON('map','assets/maps/fianl.json')
        this.load.spritesheet('basement','assets/tilesets/Basement.png',{
            frameWidth:32,
            frameHeight:32,
        })
        this.load.spritesheet('classroom','assets/tilesets/Classroom_and_library.png',{
            frameWidth:32,
            frameHeight:32,
        })
        this.load.spritesheet('floorAndGround','assets/tilesets/FloorAndGround.png',{
            frameWidth:32,
            frameHeight:32,
        })
        this.load.spritesheet('generic','assets/tilesets/Generic.png',{
            frameWidth:32,
            frameHeight:32,
        })
        this.load.spritesheet('modern_office_black_shadow','assets/tilesets/Modern_Office_Black_Shadow.png',{
            frameWidth:32,
            frameHeight:32,
        })
    }

    create(){
        this.map = this.make.tilemap({ key: 'map' });
        // 타일셋 이미지 로드
        const tileset = this.map.addTilesetImage('FloorAndGround', 'floorAndGround')

        // 레이어 생성
        const groundLayer = this.map.createLayer('ground',tileset);
        groundLayer.setCollisionByProperty({ collides: true})

        this.addGroupFromTiled('wall', 'floorAndGround', 'FloorAndGround', true)
        this.addGroupFromTiled('carpet', 'generic', 'Generic', false)
        this.addGroupFromTiled('office', 'modern_office_black_shadow', 'Modern_Office_Black_Shadow', false)
        this.addGroupFromTiled('officeOnCollides', 'modern_office_black_shadow', 'Modern_Office_Black_Shadow', true)
        this.addGroupFromTiled('generic', 'generic', 'Generic', false)
        this.addGroupFromTiled('genericOnCollides', 'generic', 'Generic', true)
        this.addGroupFromTiled('classRoom', 'classroom', 'Classroom_and_library', false)
        this.addGroupFromTiled('classRoomOnCollides', 'classroom', 'Classroom_and_library', true)
        this.addGroupFromTiled('basement', 'basement', 'Basement', false)
        this.addGroupFromTiled('basementOnCollides', 'basement', 'Basement', true)
        
    }
    update(){
    
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
        }
    }
    
}