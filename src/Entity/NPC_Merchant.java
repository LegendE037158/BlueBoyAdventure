package Entity;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Object.OBJ_Axe;
import Object.OBJ_Blue_Shield;
import Object.OBJ_Lantern;
import Object.OBJ_Pickaxe;
import Object.OBJ_Potion_Red;
import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity{
    GamePanel gp;
    public NPC_Merchant(GamePanel gp){
        super(gp);
        this.gp = gp;

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
        inventory = new ArrayList<>();
        getMerchantImage();
        setDialogue();
        setItem();
    }
    public void getMerchantImage(){
        try {
            up1 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/NPC/merchant_down_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDialogue(){
        dialogue[0] = "He he, so you found me,\nI have some good stuff.\nDo you want to trade?";
    }
    public void setItem(){
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Blue_Shield(gp));
        inventory.add(new OBJ_Pickaxe(gp));
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Lantern(gp));
    }
    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
