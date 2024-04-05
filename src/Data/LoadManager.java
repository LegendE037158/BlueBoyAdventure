package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import Entity.Entity;
import Main.GamePanel;
import Object.OBJ_Axe;
import Object.OBJ_Blue_Shield;
import Object.OBJ_Key;
import Object.OBJ_Lantern;
import Object.OBJ_Pickaxe;
import Object.OBJ_Potion_Red;
import Object.OBJ_Shield_Wood;
import Object.OBJ_Sword_Normal;

public class LoadManager {
    GamePanel gp;
    public LoadManager(GamePanel gp){
        this.gp = gp;
    }

    public void save(){

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();
            ds.level = gp.player.level;
            ds.maxlife = gp.player.maxlife;
            ds.life = gp.player.life;
            ds.maxmana = gp.player.maxmana;
            ds.mana = gp.player.mana;
            ds.strenght = gp.player.strenght;
            ds.dexterity = gp.player.dexterity;
            ds.attack = gp.player.attack;
            ds.defense = gp.player.defense;
            ds.exp = gp.player.exp;
            ds.nextLevelExp = gp.player.nextLevelExp;
            ds.coin = gp.player.coin;
            ds.playerRole = gp.player.playerRole;
            ds.currentWeapon = gp.player.currentWeapon.name;
            ds.currentShield = gp.player.currentShield.name;

            for (int i = 0; i < gp.player.inventory.size(); i++){
                if (gp.player.inventory.get(i) != null){
                    ds.inventory.add(gp.player.inventory.get(i).name);
                }
            }

            oos.writeObject(ds);

            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            DataStorage ds = (DataStorage)ois.readObject();
            gp.player.level = ds.level;
            gp.player.maxlife = ds.maxlife;
            gp.player.life = ds.life;

            if (gp.player.life <= 0){
                gp.player.life = gp.player.maxlife;
            }

            gp.player.maxmana = ds.maxmana;
            gp.player.mana = ds.mana;
            gp.player.strenght = ds.strenght;
            gp.player.dexterity = ds.dexterity;
            gp.player.attack = ds.attack;
            gp.player.defense = ds.defense;
            gp.player.exp = ds.exp;
            gp.player.nextLevelExp = ds.nextLevelExp;
            gp.player.coin = ds.coin;
            gp.player.playerRole = ds.playerRole;
            gp.player.currentWeapon = getObject(ds.currentWeapon);
            gp.player.currentShield = getObject(ds.currentShield);

            gp.player.inventory = new ArrayList<>();

            for (int i = 0; i < ds.inventory.size(); i++){
                if (ds.inventory.get(i) != null){
                    gp.player.inventory.add(getObject(ds.inventory.get(i)));
                }
            }

            gp.player.getAttackImage();

            ois.close();

        } catch (Exception e) {
            File f = new File("save.dat");
            if (!f.exists()){
                save();
            } else {
                e.printStackTrace();
            }
        }
    }

    public Entity getObject(String objname) {
        Entity obj = null;
        switch (objname) {
            case "Normal Sword":
                obj = new OBJ_Sword_Normal(gp);
                break;
            case "Woodern Shield":
                obj = new OBJ_Shield_Wood(gp);
                break;
            case "Woodcutters' Axe":
                obj = new OBJ_Axe(gp);
                break;
            case "Blue Shield":
                obj = new OBJ_Blue_Shield(gp);
                break;
            case "Key":
                obj = new OBJ_Key(gp);
                break;
            case "Red Potion":
                obj = new OBJ_Potion_Red(gp);
                break;
            case "Lantern":
                obj = new OBJ_Lantern(gp);
                break;
            case "Pickaxe":
                obj = new OBJ_Pickaxe(gp);
                break;
        
        }
        return obj;
    }
    public void saveSettings(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("settings.dat")));

            Settings settings = new Settings();

            settings.soundIndex = gp.music.volumeIndex;
            settings.seIndex = gp.se.volumeIndex;

            oos.writeObject(settings);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadSettings(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("settings.dat")));

            Settings settings = (Settings)ois.readObject();

            gp.music.volumeIndex = settings.soundIndex;
            gp.se.volumeIndex = settings.seIndex;

            ois.close();
        } catch (Exception e) {
            File f = new File("settings.dat");
            if (!f.exists()){
                saveSettings();
            } else {
                e.printStackTrace();
            }
        }

        
    }

}
