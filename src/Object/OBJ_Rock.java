package Object;

import java.awt.Color;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import Entity.Projectile;
import Main.GamePanel;

public class OBJ_Rock extends Projectile{
    GamePanel gp;
    public OBJ_Rock(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 10;
        maxlife = 80;
        life = maxlife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        try {
            up1 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            down1 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            left1 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            right1 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/Projectile/rock_down_1.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public Color getParticleColor(){
        Color color = new Color(40, 50, 0);
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
