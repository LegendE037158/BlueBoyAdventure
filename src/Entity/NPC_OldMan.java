package Entity;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        type = type_npc;
        speed = 1;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getOldManImage();
        setDialogue();
    }
    
    public void getOldManImage() {
        try {
            up1 = ImageIO.read(new FileInputStream("res/NPC/oldman_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/NPC/oldman_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/NPC/oldman_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/NPC/oldman_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/NPC/oldman_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/NPC/oldman_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/NPC/oldman_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/NPC/oldman_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDialogue(){
        dialogue[0] = "Hello, lad.";
        dialogue[1] = "So you've come to this island to find the \ntreasure?";
        dialogue[2] = "I used to be a great wizard but now... I'm \na bit old for taking an adventure.";
        dialogue[3] = "Well, good luck on you.";
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
    public void speak() {
        super.speak();        
    }
}
