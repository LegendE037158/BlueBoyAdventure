package Tile_Interactive;

import Entity.Entity;
import Main.GamePanel;
public class InteractiveObject extends Entity {
    GamePanel gp;
    public boolean destrutible = false;
    public InteractiveObject(GamePanel gp){
        super(gp);
        this.gp = gp;

    }
    public void update(){

    }
    public void getDestoyedForm(int currentTileIndex){}{
    }
    public boolean checkRequiredItem(){
        return false;
    }

}
