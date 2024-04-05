package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import Entity.Entity;
import Main.GamePanel;

public class OBJ_Mana extends Entity{
    GamePanel gp;
    public OBJ_Mana(GamePanel gp) {
        super(gp);

        this.gp = gp;
        name = "Mana Crystal";
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        try {
            image = ImageIO.read(new FileInputStream("res/Objects/manacrystal_full.png"));
            image2 = ImageIO.read(new FileInputStream("res/Objects/manacrystal_blank.png")); 
        } catch (IOException e){
            e.printStackTrace();
        }
        collisionOn = true;
        
    }
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
