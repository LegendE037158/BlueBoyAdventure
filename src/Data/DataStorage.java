package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{

    public int level;
    public int maxlife;
    public int life;
    public int maxmana;
    public int mana;
    public int strenght;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int playerRole;
    public String currentWeapon;
    public String currentShield;
    public ArrayList<String> inventory = new ArrayList<>();

}
