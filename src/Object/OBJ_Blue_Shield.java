package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import Entity.Entity;
import Main.GamePanel;

public class OBJ_Blue_Shield extends Entity{
    GamePanel gp;
    public OBJ_Blue_Shield(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Blue Shield";
        type = type_shield;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        try {
            image = ImageIO.read(new FileInputStream("res/Objects/shield_blue.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        defenseValue = 1;
        despiction = "["+name+"]/nA shiny blue shield.";
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
