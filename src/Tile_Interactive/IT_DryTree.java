package Tile_Interactive;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class IT_DryTree extends InteractiveObject{
    GamePanel gp;
    public IT_DryTree(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Dry Tree";
        direction = "down";
        destrutible = true;

        maxlife = 4;
        life = maxlife;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try {
            down1 = ImageIO.read(new FileInputStream("res/Tile_Interactive/drytree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getDestoyedForm(int currentTileIndex){
        InteractiveObject tile = new IT_Trunk(gp);
        tile.worldX = worldX;
        tile.worldY = worldY;

        gp.tileInteractiveObject[gp.currentMap][currentTileIndex] = tile;
    }
    public boolean checkRequiredItem(){
        boolean isReqItem = false;

        if (gp.player.currentWeapon.type == type_axe){
            isReqItem = true;
        }
        return isReqItem;
    }
    public Color getParticleColor(){
        Color color = new Color(65, 50, 30);
        return color;
    }
    public int getParticleSize(){
        int size = 6;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxlife = 20;
        return maxlife;
    }

}
