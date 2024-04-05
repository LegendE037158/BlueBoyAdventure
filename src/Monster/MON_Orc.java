package Monster;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import Entity.Entity;
import Main.GamePanel;

public class MON_Orc extends Entity{
    GamePanel gp;
    public MON_Orc(GamePanel gp){
        super(gp);

        this.gp = gp;
        name = "Orc";
        type = type_monster;
        speed = 2;
        maxlife = 20;
        life = maxlife;
        direction = "down";
        collisionOn = false;
        attack = 5;
        defense = 4;
        monsterAttackAnim = true;


        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        getAttackImage();
    }
    public void getImage() {
        try {
            up1 = ImageIO.read(new FileInputStream("res/Monster/orc_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/Monster/orc_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/Monster/orc_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/Monster/orc_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/Monster/orc_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/Monster/orc_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/Monster/orc_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/Monster/orc_right_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void getAttackImage() {
        try {
            attack_up1 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_up_1.png"));
            attack_up2 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_up_2.png"));
            attack_down1 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_down_1.png"));
            attack_down2 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_down_2.png"));
            attack_left1 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_left_1.png"));
            attack_left2 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_left_2.png"));
            attack_right1 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_right_1.png"));
            attack_right2 = ImageIO.read(new FileInputStream("res/Monster/orc_attack_right_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void damagePlayer(){
        super.damagePlayer(attack);
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
    }
    public void damageReaction() {
        actionLockCounter = 0;
        stayInPosition = false;
        direction = gp.player.direction;
    }
}
