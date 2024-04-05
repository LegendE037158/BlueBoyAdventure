package Monster;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;
import Object.OBJ_Rock;

public class MON_RedSlime extends Entity{
    GamePanel gp;
    public MON_RedSlime(GamePanel gp){
        super(gp);

        this.gp = gp;
        name = "Red Slime";
        type = type_monster;
        speed = 2;
        maxlife = 15;
        life = maxlife;
        direction = "down";
        collisionOn = false;
        attack = 5;
        defense = 3;

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        projectile = new OBJ_Rock(gp);

        getImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/Monster/redslime_down_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void setAction() {
        
        actionLockCounter++;

        if (actionLockCounter == 120){
            
            stayInPosition = false;
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if (i <= 20){
                direction = "up";
            } else if (i > 20 && i <= 40){
                direction = "down";
            } else if (i > 40 && i <= 60) {
                direction = "left";
            } else if (i > 60 && i <= 80) {
                direction = "right";
            } else if (i > 80) {
                stayInPosition = true;
            }

            actionLockCounter = 0;
        }


        
        int i = new Random().nextInt(100) + 1;
        if (i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
        stayInPosition = false;
        direction = gp.player.direction;
    }

}
