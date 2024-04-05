package Object;

import java.awt.Color;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import Entity.Projectile;
import Main.GamePanel;

public class OBJ_Fireball extends Projectile{
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 5;
        maxlife = 80;
        life = maxlife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        try {
            up1 = ImageIO.read(new FileInputStream("res/Projectile/fireball_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/Projectile/fireball_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/Projectile/fireball_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/Projectile/fireball_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/Projectile/fireball_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/Projectile/fireball_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/Projectile/fireball_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/Projectile/fireball_right_2.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public Color getParticleColor(){
        Color color = new Color(240, 50, 0);
        return color;
    }
    public int getParticleSize(){
        int size = 10;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxlife = 20;
        return maxlife;
    }
}
