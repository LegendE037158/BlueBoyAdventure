package Object;

import java.awt.Rectangle;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;

public class OBJ_Pickaxe extends Entity {
    GamePanel gp;
    public OBJ_Pickaxe(GamePanel gp){
        super(gp);

        this.gp = gp;
        name = "Pickaxe";
        type = type_axe;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        try {
            image = ImageIO.read(new FileInputStream("res/Objects/pickaxe.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        attackValue = 3;
        attackArea = new Rectangle();
        attackArea.width = 36;
        attackArea.height = 36;
        despiction = "["+name+"]/nVery old and rusty/n but still can/n cut somethings...";
    }
}
