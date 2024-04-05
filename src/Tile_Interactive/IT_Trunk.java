package Tile_Interactive;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class IT_Trunk extends InteractiveObject{
    GamePanel gp;
    public IT_Trunk(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Trunk";
        direction = "down";
        destrutible = false;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        try {
            down1 = ImageIO.read(new FileInputStream("res/Tile_Interactive/trunk.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
