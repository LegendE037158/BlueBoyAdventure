package Entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import Main.GamePanel;
import Main.KeyHandler;
import Object.OBJ_Coin_Bronze;
import Object.OBJ_Fireball;
import Object.OBJ_Heart;
import Object.OBJ_Mana;
import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword_Normal;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;
    public boolean lightUpdated = true;

    public int playerRole;
    public final int fighterRole;
    public final int theifRole;
    public final int scorererRole;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH = keyH;
        type = type_player;
        coin = 0;
        screenX = (gp.screenWidth/2) - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        this.fighterRole = 0;
        this.theifRole = 1;
        this.scorererRole = 2;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
        getAttackImage();
        getGuardImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        level = 1;
        strenght = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefence();
        maxlife = 6;
        life = maxlife;
        maxmana = 3;
        mana = maxmana;
        inventory  = new ArrayList<>();
        invincible = false;
        invincibleCounter = 0;
        setItems();
    }

    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }

    public int getAttack() {
        attackArea = currentWeapon.attackArea;
        return strenght * currentWeapon.attackValue;
    }
    public int getDefence() {
        return dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new FileInputStream("res/Players/boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/Players/boy_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/Players/boy_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/Players/boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/Players/boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/Players/boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/Players/boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/Players/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAttackImage(){
        try {
            if (currentWeapon.name == "Normal Sword"){
                attack_up1 = ImageIO.read(new FileInputStream("res/Players/boy_attack_up_1.png"));
                attack_up1 = ImageIO.read(new FileInputStream("res/Players/boy_attack_up_2.png"));
                attack_down1 = ImageIO.read(new FileInputStream("res/Players/boy_attack_down_1.png"));
                attack_down2 = ImageIO.read(new FileInputStream("res/Players/boy_attack_down_2.png"));
                attack_left1 = ImageIO.read(new FileInputStream("res/Players/boy_attack_left_1.png"));
                attack_left2 = ImageIO.read(new FileInputStream("res/Players/boy_attack_left_2.png"));
                attack_right1 = ImageIO.read(new FileInputStream("res/Players/boy_attack_right_1.png"));
                attack_right2 = ImageIO.read(new FileInputStream("res/Players/boy_attack_right_2.png"));
            } else if (currentWeapon.name == "Woodcutters' Axe"){
                attack_up1 = ImageIO.read(new FileInputStream("res/Players/boy_axe_up_1.png"));
                attack_up1 = ImageIO.read(new FileInputStream("res/Players/boy_axe_up_2.png"));
                attack_down1 = ImageIO.read(new FileInputStream("res/Players/boy_axe_down_1.png"));
                attack_down2 = ImageIO.read(new FileInputStream("res/Players/boy_axe_down_2.png"));
                attack_left1 = ImageIO.read(new FileInputStream("res/Players/boy_axe_left_1.png"));
                attack_left2 = ImageIO.read(new FileInputStream("res/Players/boy_axe_left_2.png"));
                attack_right1 = ImageIO.read(new FileInputStream("res/Players/boy_axe_right_1.png"));
                attack_right2 = ImageIO.read(new FileInputStream("res/Players/boy_axe_right_2.png"));
            } else if (currentWeapon.name == "Pickaxe"){
                attack_up1 = ImageIO.read(new FileInputStream("res/Players/boy_pick_up_1.png"));
                attack_up1 = ImageIO.read(new FileInputStream("res/Players/boy_pick_up_2.png"));
                attack_down1 = ImageIO.read(new FileInputStream("res/Players/boy_pick_down_1.png"));
                attack_down2 = ImageIO.read(new FileInputStream("res/Players/boy_pick_down_2.png"));
                attack_left1 = ImageIO.read(new FileInputStream("res/Players/boy_pick_left_1.png"));
                attack_left2 = ImageIO.read(new FileInputStream("res/Players/boy_pick_left_2.png"));
                attack_right1 = ImageIO.read(new FileInputStream("res/Players/boy_pick_right_1.png"));
                attack_right2 = ImageIO.read(new FileInputStream("res/Players/boy_pick_right_2.png"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getGuardImage(){
        try {
            guard_up = ImageIO.read(new FileInputStream("res/Players/boy_guard_up.png"));
            guard_down = ImageIO.read(new FileInputStream("res/Players/boy_guard_down.png"));
            guard_left = ImageIO.read(new FileInputStream("res/Players/boy_guard_left.png"));
            guard_right = ImageIO.read(new FileInputStream("res/Players/boy_guard_right.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
       
        if (life <= 0){
            invincible = false;
            gp.ui.gameFinished = true;
        }
        if (attacking == true){
            attacking();
        } else if (gp.keyH.cPressed == true){
            defending = true;
        } else if (keyH.upPressed  || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed || keyH.cPressed){
            if (keyH.upPressed == true){
                direction = "up";
            }
            if (keyH.downPressed == true) {
                direction = "down";
            }
            if (keyH.leftPressed == true) {
                direction = "left";
            }
            if (keyH.rightPressed == true) {
                direction = "right";
            }
            spriteCounter++;
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            int monIndex = gp.cChecker.checkEntity(this, gp.monster);
            int tileIndex = gp.cChecker.checkEntity(this, gp.tileInteractiveObject);
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            InterectNPC(npcIndex);
            damageInteractiveTile(tileIndex);
            InterectMON(monIndex);
            pickUpObj(objIndex);
            gp.eHandler.checkEvent();

            if (collisionOn == false && keyH.enterPressed == false && keyH.cPressed == false){
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }
            }
            
            if (spriteCounter == 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            } 
        }  else {
            standCounter++;
            if (standCounter > 20){
                spriteNum = 1;
                standCounter = 0;
            }
        }
        if (gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter >= 30 && mana > 0){
            projectile.set(worldX, worldY, direction, true, this);

            gp.projectileList.add(projectile);
            mana -= projectile.useCost;
            if (mana <= 0){
                mana = 0;
            }
            shotAvailableCounter++;
            gp.playSE(9);
        }

        InvincibleTimer();

        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if (keyH.cPressed == false){
            defending = false;
        }

    }

    public void damageInteractiveTile(int tileIndex){
        if (tileIndex != 999 && 
            keyH.enterPressed == true && 
            gp.tileInteractiveObject[gp.currentMap][tileIndex].destrutible == true && 
            gp.tileInteractiveObject[gp.currentMap][tileIndex].checkRequiredItem()
        ){
            if (attacking != true){
                attacking();
            }
            gp.tileInteractiveObject[gp.currentMap][tileIndex].life--;
            generateParticle(gp.tileInteractiveObject[gp.currentMap][tileIndex], gp.tileInteractiveObject[gp.currentMap][tileIndex]);
            if (gp.tileInteractiveObject[gp.currentMap][tileIndex].life <= 0){
                gp.tileInteractiveObject[gp.currentMap][tileIndex].getDestoyedForm(tileIndex);
            }
        }

    }
    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();
        if (itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefence();
            }
            if (selectedItem.type == type_cumsumuble){
                boolean comsumedItem = selectedItem.comsumeItem();
                if (comsumedItem == true){
                    inventory.remove(itemIndex);
                }
            }
            if (selectedItem.type == type_light){
                if (currentLight == selectedItem){
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
        }
    }

    public void InvincibleTimer() {
        if (invincible == true){
            invincibleCounter++;
            if (invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damageMonster(int monsterIndex, int attack) {
        if (monsterIndex != 999 && playerRole != theifRole){
            if (gp.monster[gp.currentMap][monsterIndex].invincible == false){
                int damage = (attack - gp.monster[gp.currentMap][monsterIndex].defense);
                if (damage <= 0){
                    damage = 0;
                }
                gp.monster[gp.currentMap][monsterIndex].life -= damage;
                gp.ui.showMessage(damage + " damage");
                gp.monster[gp.currentMap][monsterIndex].invincible = true;
                gp.monster[gp.currentMap][monsterIndex].damageReaction();
                gp.playSE(5);
                if (gp.monster[gp.currentMap][monsterIndex].life <= 0){
                    gp.monster[gp.currentMap][monsterIndex].dying = true;
                    gp.ui.showMessage("killed the " + gp.monster[gp.currentMap][monsterIndex].name + "!");
                    getReward(monsterIndex);
                }
            }
        }
    }

    public void getReward(int i) {
        int coinEarn = 0;
        int expEarn = 0;
        if (playerRole == fighterRole){
            if (life != maxlife){
                life++;
            }
        } else {
            coinEarn += 20;
        }
        
        switch (gp.monster[gp.currentMap][i].name){
            case "Green Slime":
                expEarn += 2;
                exp += expEarn;
                coinEarn += 5;
                coin += coinEarn;
                break;
            case "Bat":
                expEarn += 3;
                exp += expEarn;
                coinEarn += 10;
                coin += coinEarn;
                break;
            case "Red Slime":
                expEarn += 5;
                exp += expEarn;
                coinEarn += 25;
                coin += coinEarn;
                break;
            case "Orc":
                expEarn += 10;
                exp += expEarn;
                coinEarn += 50;
                coin += coinEarn;
                break;

        }

        if (exp >= nextLevelExp){

            exp -= nextLevelExp;
            level++;
            coinEarn += 500;
            coin += coinEarn;
            nextLevelExp *= 2;

            maxlife += 2;
            maxmana += 1;
            strenght += 2;
            dexterity += 1;

            attack = getAttack();
            defense = getDefence();

            dialogue[0] = "You Leveled up to " + level + "!\n   You feel stronger.";
            gp.gameState = gp.dialogueState;
            speak();

        }

        Random monDropIndex = new Random();
        int droppedIndex = monDropIndex.nextInt(3);

        for (int index = 0; index < gp.obj[gp.currentMap].length; index++){
            if (gp.obj[gp.currentMap][index] == null){
                if (droppedIndex == 0){
                    gp.obj[gp.currentMap][index] = new OBJ_Heart(gp);
                    if (life == maxlife){
                        gp.obj[gp.currentMap][index] = new OBJ_Coin_Bronze(gp);
                    }
                } else if (droppedIndex == 1){
                    gp.obj[gp.currentMap][index] = new OBJ_Mana(gp);
                    if (mana == maxmana){
                        gp.obj[gp.currentMap][index] = new OBJ_Coin_Bronze(gp);
                    }
                } else if (droppedIndex == 2){
                    gp.obj[gp.currentMap][index] =  new OBJ_Coin_Bronze(gp);
                }
                gp.obj[gp.currentMap][index].worldX = gp.monster[gp.currentMap][i].worldX;
                gp.obj[gp.currentMap][index].worldY = gp.monster[gp.currentMap][i].worldY;
                break;
            }
        }

        gp.ui.showMessage("exp +" + expEarn);
        gp.ui.showMessage("coin + " + coinEarn);

    }

    public void InterectMON(int monIndex) {
        if (monIndex != 999 && defending == false && gp.monster[gp.currentMap][monIndex].dying == false){
            if (invincible == false){
                life -= gp.monster[gp.currentMap][monIndex].attack;
                invincible = true;
            } 
        }else {
            if (monIndex != 999){
                gp.playSE(7);
                if (invincible == false){
                    int damage = (gp.monster[gp.currentMap][monIndex].attack - defense);
                    if (damage <= 0){
                        damage = 0;
                    }
                    life -= damage;
                    invincible = true;
                }
            }
        }
    }
    public void InterectNPC(int i) {
        if (gp.keyH.enterPressed == true){
            if (i != 999){
                    gp.gameState = gp.dialogueState;
                    gp.npc[gp.currentMap][i].speak();
            } else {
                attacking = true;
            }
        }
    }

    public void pickUpObj(int i) {
        if (i != 999){
            String text = "";
            if (inventory.size() != inventorySize){
                switch(gp.obj[gp.currentMap][i].name){
                    case "Boot":
                        speed += 2;
                        gp.playSE(2);
                        gp.ui.showMessage("Your speed increased");
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    case "Door":
                        for(int index = 0; index < inventory.size(); index++){
                            if (inventory.get(index).name == "Key"){
                                gp.ui.showMessage("Door Unlocked");
                                inventory.remove(index);
                                gp.playSE(3);
                                gp.obj[gp.currentMap][i] = null;
                                break;
                            }
                        }
                        break;
                    case "Chest":
                        if(playerRole == theifRole){
                            coin += 600;
                        } else {
                            coin += 400;
                        }
                        gp.stopMusic();
                        gp.playSE(4);
                        gp.goalReached = true;
                        gp.ui.gameFinished = true;
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    case "Bronze Coin":
                        int coinEarn = 0;
                        coinEarn += 5;
                        coin += coinEarn;
                        gp.ui.showMessage("Bonus: "+coinEarn+" x Coin");
                        gp.playSE(1);
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    case "Heart":
                        int heartEarn = 0;
                        heartEarn += 1;
                        life += heartEarn;
                        gp.ui.showMessage("Bonus: "+heartEarn+" x Heart");
                        gp.playSE(1);
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    case "Mana Crystal":
                        int manaEarn = 0;
                        manaEarn += 1;
                        mana += manaEarn;
                        gp.ui.showMessage("Bonus: "+manaEarn+" x Mana Crystal");
                        gp.playSE(1);
                        gp.obj[gp.currentMap][i] = null;
                        break;
                    default:
                        inventory.add(gp.obj[gp.currentMap][i]);
                        gp.playSE(1);
                        text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                        gp.obj[gp.currentMap][i] = null;
                        break;
                }
            } else {
                text = "You can't carry any more.";
            }
            gp.ui.showMessage(text);
        }
    }

    public void draw(Graphics2D g2) {
       
        BufferedImage image = getAnimation();
        playAnimation(image, g2);
        
    }
    public void playAnimation(BufferedImage image, Graphics2D g2) {
        if (invincible == true && life > 0){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        } else {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (attacking == false){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        } else if (attacking == true){
            switch (direction) {
                case "up":
                    g2.drawImage(image, screenX, screenY - gp.tileSize, gp.tileSize, gp.tileSize*2, null);
                    break;
                case "down":
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize*2, null);
                    break;
                case "left":
                    g2.drawImage(image, screenX - gp.tileSize, screenY, gp.tileSize*2, gp.tileSize, null);
                    break;
                case "right":
                    g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize, null);
                    break;
            }
        }
    }

    public BufferedImage getAnimation() {
        BufferedImage image = null;
        if (attacking == false && defending == false){
            switch (direction) {
                case "up": if (spriteNum == 1){image = up1;} else if (spriteNum == 2){image = up2;} break;
                case "down": if (spriteNum == 1){image = down1;} else if (spriteNum == 2){image = down2;} break;
                case "left": if (spriteNum == 1){image = left1;} else if (spriteNum == 2){image = left2;} break;
                case "right": if (spriteNum == 1){image = right1;} else if (spriteNum == 2){image = right2;} break;
            }
        }else if (defending == true){
            switch (direction) {
                case "up": image = guard_up; break;
                case "down": image = guard_down; break;
                case "left": image = guard_left; break;
                case "right": image = guard_right; break;
            
            }
        } else if (attacking == true){
            switch (direction) {
                case "up": if (spriteNum == 1){image = attack_up1;} else if (spriteNum == 2){image = attack_up2;} break;
                case "down": if (spriteNum == 1){image = attack_down1;} else if (spriteNum == 2){image = attack_down2;} break;
                case "left": if (spriteNum == 1){image = attack_left1;} else if (spriteNum == 2){image = attack_left2;} break;
                case "right": if (spriteNum == 1){image = attack_right1;} else if (spriteNum == 2){image = attack_right2;} break;
            }
        }
        return image;
    }

}
