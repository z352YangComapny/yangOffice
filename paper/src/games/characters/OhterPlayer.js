import Phaser from "phaser";
import Player from "./Player";



class OtherPlayers extends Player {
    constructor(scene , x , y , texture, id, name ,){
        super(scene , x, y , texture , id );
        this.targetPosition = [x , y];
        this.playerName.setText(name);
        
        
    }
}