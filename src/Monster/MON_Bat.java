package Monster;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import Entity.Entity;
import Main.GamePanel;

public class MON_Bat extends Entity{
    GamePanel gp;
    public MON_Bat(GamePanel gp){
        super(gp);

        this.gp = gp;
        name = "Bat";
        type = type_monster;
        speed = 5;
        maxlife = 7;
        life = maxlife;
        direction = "down";
        collisionOn = false;
        attack = 2;
        defense = 1;

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        try {
            up1 = ImageIO.read(new FileInputStream("res/Monster/bat_down_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/Monster/bat_down_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/Monster/bat_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/Monster/bat_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/Monster/bat_down_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/Monster/bat_down_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/Monster/bat_down_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/Monster/bat_down_2.png"));
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

            if (i <= 25){
                direction = "up";
            } else if (i > 25 && i <= 50){
                direction = "down";
            } else if (i > 50 && i <= 75){
                direction = "left";
            } else if (i > 75){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
        stayInPosition = false;
        direction = gp.player.direction;
    }
    
}
