package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;

public class Entity {
    
    GamePanel gp;

    public int worldX, worldY;
    public int speed;
    public String direction;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
    attack_up1, attack_up2, attack_down1, attack_down2, attack_left1, attack_left2, attack_right1, attack_right2,
    guard_up, guard_down, guard_left, guard_right;
    public BufferedImage image, image2, image3;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public Rectangle attackArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;
    public boolean stayInPosition = false;

    public int standCounter = 0;

    public boolean invincible = false;
    public int invincibleCounter = 0;

    public boolean monsterAttackAnim = false;

    public String dialogue[] = new String[20];
    int dialogueIndex = 0;

    public boolean attacking = false, defending = false;

    public boolean alive = true;
    public boolean dying = false;
    int dyingCounter = 0;
    boolean hpbarOn = false;
    int hpbarCounter = 0;

    public int attackValue;
    public int defenseValue;
    public String despiction = "";
    public int useCost;

    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_cumsumuble = 6;
    public final int type_light = 7;

    public ArrayList<Entity> inventory;
    public final int inventorySize = 20;

    //Charactor Attributes
    public String name;
    public int maxlife;
    public int life;
    public int level;
    public int strenght;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield; 
    public Entity currentLight; 
    public Projectile projectile;

    public int shotAvailableCounter = 0;

    public int maxmana;
    public int mana;

    public int lightRadius;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction() {}
    public void damageReaction() {}
    public boolean comsumeItem() {
        return false;
    }
    public void speak() {

        if (dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
            gp.gameState = gp.playState;
        } else {
            gp.ui.currentDialogues = dialogue[dialogueIndex];
            dialogueIndex++;
        }
        if (this.type == 1){
            switch (gp.player.direction) {
                case "up":
                    direction = "down";
                    break;
                case "down":
                    direction = "up";
                    break;
                case "left":
                    direction = "right";
                    break;
                case "right":
                    direction = "left";
                    break;
            
            }
        }

    }
    public void update() {
        setAction();
        if (!stayInPosition){
            spriteCounter++;
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, false);
            gp.cChecker.checkEntity(this, gp.npc);
            gp.cChecker.checkEntity(this, gp.monster);
            gp.cChecker.checkEntity(this, gp.tileInteractiveObject);
            boolean contactPlayer = gp.cChecker.checkPlayer(this);

            if (this.type == type_monster && contactPlayer == true){
                damagePlayer( attack);
            }

            if (collisionOn == false){
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
            if (attacking == true){
                if (spriteCounter <= 5){
                    spriteNum = 1;
                } else if (spriteCounter > 5 && spriteCounter <= 25){
                    spriteNum = 2;
                } else if (spriteCounter > 25){
                    spriteNum = 1;
                    spriteCounter = 0;
                    attacking = false;
                }
            } else{
                if (spriteCounter == 12){
                    if (spriteNum == 1){
                        spriteNum = 2;
                    }
                    else if (spriteNum == 2){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
        }
        InvincibleTimer();
        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
    public void damagePlayer(int attack){
        if (monsterAttackAnim == true){
            attacking = true;
        }
        boolean isDefending = false;

        if(direction == "up" && gp.player.direction == "down" && gp.player.defending){
            isDefending = true;
        } 
        if (direction == "down" && gp.player.direction == "up" && gp.player.defending){
            isDefending = true;
        }
        if (direction == "left" && gp.player.direction == "right" && gp.player.defending){
            isDefending = true;
        }
        if (direction == "right" && gp.player.direction == "left" && gp.player.defending){
            isDefending = true;
        }

        if (gp.player.invincible == false && isDefending == false){
            gp.player.life -= attack;
            gp.player.invincible = true;
        } else if (gp.player.invincible == false){
            int damage = (attack - gp.player.defense);
            if (damage <= 0){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
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
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
       
        BufferedImage image = null;
        if (attacking == true){
            switch (direction) {
                case "up":
                    if (spriteNum == 1){
                        image = attack_up1;
                    }
                    else if (spriteNum == 2){
                        image = attack_up2;
                    }
                    
                    break;
                case "down":
                    if (spriteNum == 1){
                    image = attack_down1;
                    }
                    else if (spriteNum == 2){
                    image = attack_down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1){
                        image = attack_left1;
                    }
                    else if (spriteNum == 2){
                        image = attack_left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1){
                        image = attack_right1;
                    }
                    else if (spriteNum == 2){
                        image = attack_right2;
                    }
                    break;
                default :
                    break;
            }
        } else {

            switch (direction) {
                case "up":
                    if (spriteNum == 1){
                        image = up1;
                    }
                    else if (spriteNum == 2){
                        image = up2;
                    }
                    
                    break;
                case "down":
                    if (spriteNum == 1){
                    image = down1;
                    }
                    else if (spriteNum == 2){
                    image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1){
                        image = left1;
                    }
                    else if (spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1){
                        image = right1;
                    }
                    else if (spriteNum == 2){
                        image = right2;
                    }
                    break;
                default :
                    break;
            
            }
        }

        if (type == 2 && hpbarOn == true){
            double oneScale = (double)gp.tileSize/maxlife;
            double hpbarValue = oneScale*life;
            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(screenX, screenY - 15, (int)hpbarValue, 10);

            hpbarCounter++;
            if (hpbarCounter > 600){
                hpbarOn = false;
            }
        }

        if (invincible == true){
            hpbarOn = true;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        } else {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dying == true){
            dyingAnimation(g2);
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
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        if(dyingCounter <= 5){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > 5 && dyingCounter <= 10){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > 15 && dyingCounter <= 20){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > 25 && dyingCounter <= 30){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > 30 && dyingCounter <= 35){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > 35 && dyingCounter <= 40){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > 40 && dyingCounter <= 45){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter > 45){
            alive = false;
        }

    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxlife = 0;
        return maxlife;
    }
    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxlife = generator.getParticleMaxLife();

        Particle particle1 = new Particle(gp, generator, color, size, speed, maxlife, -2, -1);
        Particle particle2 = new Particle(gp, generator, color, size, speed, maxlife, -2, 1);
        Particle particle3 = new Particle(gp, generator, color, size, speed, maxlife, 2, 1);
        Particle particle4 = new Particle(gp, generator, color, size, speed, maxlife, 2, -1);
        
        gp.particleList.add(particle1);
        gp.particleList.add(particle2);
        gp.particleList.add(particle3);
        gp.particleList.add(particle4);
    }
    
}
