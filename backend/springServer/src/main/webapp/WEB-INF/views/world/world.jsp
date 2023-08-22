<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--Phaer3 Cdn-->
    <script src="//cdn.jsdelivr.net/npm/phaser@3.60.0/dist/phaser.js"></script>

    <title>Document</title>
</head>
<body>

<div class="game-container" style="width: 1120px; height: 0px; position: relative; top: 0;">
    <!-- 게임 내용 및 캔버스 추가 -->
</div>

<div>1234512345</div>
<script>
    const GameConfig = {
        type: Phaser.AUTO,
        parent: 'game-container',
        backgroundColor: '#93cbee',
        pixelArt: true,
        scale: {
            // mode: Phaser.Scale.ScaleModes.RESIZE,
            width: 1120,
            height: 630,
        },
        physics: {
            default: 'arcade',
            arcade: {
                gravity: { y: 0 },
                debug: false,
            },
        },
        autoFocus: true,
        scene: {
            preload:preload,
            create:create
        }
    };

    var game = new Phaser.Game(GameConfig);

    function preload() {
        this.load.setBaseURL('http://localhost:8080')

        this.load.tilemapTiledJSON('map', 'assets/maps/fianl.json')
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

    function create() {

        // Load Map
        this.map = this.make.tilemap({ key: 'map' });
        const tileset = this.map.addTilesetImage('FloorAndGround', 'floorAndGround')
        // Tile Layer
        const groundLayer = this.map.createLayer('ground', tileset);
        groundLayer.setCollisionByProperty({ collides: true });

        createCharacterAnims(this.anims)
        this.registerKeys()

        // , this.network.mySessionId
        this.myPlayer = new MyPlayer(this, 800, 500, 'adam' , this.cursors)
        this.controller = new PlayerController(this, 0, 0, 16, 16 , this.cursors , this.myPlayer)

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

    }

</script>
</body>
</html>