package Main;

import Tile_Interactive.IT_DryTree;
import Object.OBJ_Axe;
import Object.OBJ_Blue_Shield;
import Object.OBJ_Boot;
import Object.OBJ_Chest;
import Object.OBJ_Door;
import Object.OBJ_Key;
import Entity.NPC_Merchant;
import Entity.NPC_OldMan;
import Monster.MON_Bat;
import Monster.MON_Orc;
import Monster.MON_RedSlime;
import Monster.MON_Slime;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0][0] = new OBJ_Key(gp);
        gp.obj[0][0].worldX = 37*gp.tileSize;
        gp.obj[0][0].worldY = 10*gp.tileSize;

        gp.obj[0][1] = new OBJ_Key(gp);
        gp.obj[0][1].worldX = 13*gp.tileSize;
        gp.obj[0][1].worldY = 33*gp.tileSize;

        gp.obj[0][2] = new OBJ_Key(gp);
        gp.obj[0][2].worldX = 22*gp.tileSize;
        gp.obj[0][2].worldY = 40*gp.tileSize;

        gp.obj[0][3] = new OBJ_Door(gp);
        gp.obj[0][3].worldX = 12*gp.tileSize;
        gp.obj[0][3].worldY = 12*gp.tileSize;

        gp.obj[0][4] = new OBJ_Door(gp);
        gp.obj[0][4].worldX = 10*gp.tileSize;
        gp.obj[0][4].worldY = 20*gp.tileSize;

        gp.obj[0][5] = new OBJ_Door(gp);
        gp.obj[0][5].worldX = 13*gp.tileSize;
        gp.obj[0][5].worldY = 23*gp.tileSize;

        gp.obj[0][6] = new OBJ_Chest(gp);
        gp.obj[0][6].worldX = 12*gp.tileSize;
        gp.obj[0][6].worldY = 8*gp.tileSize;

        gp.obj[0][7] = new OBJ_Boot(gp);
        gp.obj[0][7].worldX = 37*gp.tileSize;
        gp.obj[0][7].worldY = 42*gp.tileSize;

        gp.obj[0][8] = new OBJ_Blue_Shield(gp);
        gp.obj[0][8].worldX = 10*gp.tileSize;
        gp.obj[0][8].worldY = 32*gp.tileSize;

        gp.obj[0][9] = new OBJ_Axe(gp);
        gp.obj[0][9].worldX = 33*gp.tileSize;
        gp.obj[0][9].worldY = 7*gp.tileSize;

        
    }
    public void setNCP() {
        gp.npc[0][0] = new NPC_OldMan(gp);
        gp.npc[0][0].worldX = 21*gp.tileSize;
        gp.npc[0][0].worldY = 21*gp.tileSize;

        gp.npc[1][0] = new NPC_Merchant(gp);
        gp.npc[1][0].worldX = 12*gp.tileSize;
        gp.npc[1][0].worldY = 7*gp.tileSize;
    }

    public void setMonster() {
        gp.monster[0][0] = new MON_Slime(gp);
        gp.monster[0][0].worldX = 22*gp.tileSize;
        gp.monster[0][0].worldY = 7*gp.tileSize;

        gp.monster[0][1] = new MON_Slime(gp);
        gp.monster[0][1].worldX = 26*gp.tileSize;
        gp.monster[0][1].worldY = 8*gp.tileSize;

        gp.monster[0][2] = new MON_Slime(gp);
        gp.monster[0][2].worldX = 18*gp.tileSize;
        gp.monster[0][2].worldY = 21*gp.tileSize;

        gp.monster[0][3] = new MON_Orc(gp);
        gp.monster[0][3].worldX = 12*gp.tileSize;
        gp.monster[0][3].worldY = 32*gp.tileSize;

        gp.monster[0][4] = new MON_Bat(gp);
        gp.monster[0][4].worldX = 32*gp.tileSize;
        gp.monster[0][4].worldY = 42*gp.tileSize;
        
        gp.monster[0][5] = new MON_Slime(gp);
        gp.monster[0][5].worldX = 35*gp.tileSize;
        gp.monster[0][5].worldY = 37*gp.tileSize;

        gp.monster[0][6] = new MON_Bat(gp);
        gp.monster[0][6].worldX = 36*gp.tileSize;
        gp.monster[0][6].worldY = 11*gp.tileSize;

        gp.monster[0][7] = new MON_Slime(gp);
        gp.monster[0][7].worldX = 35*gp.tileSize;
        gp.monster[0][7].worldY = 20*gp.tileSize;
        
        gp.monster[0][8] = new MON_Slime(gp);
        gp.monster[0][8].worldX = 36*gp.tileSize;
        gp.monster[0][8].worldY = 27*gp.tileSize;

        gp.monster[0][9] = new MON_Slime(gp);
        gp.monster[0][9].worldX = 19*gp.tileSize;
        gp.monster[0][9].worldY = 38*gp.tileSize;

        gp.monster[0][10] = new MON_Bat(gp);
        gp.monster[0][10].worldX = 21*gp.tileSize;
        gp.monster[0][10].worldY = 40*gp.tileSize;

        gp.monster[0][11] = new MON_Slime(gp);
        gp.monster[0][11].worldX = 24*gp.tileSize;
        gp.monster[0][11].worldY = 40*gp.tileSize;

        gp.monster[0][12] = new MON_Bat(gp);
        gp.monster[0][12].worldX = 34*gp.tileSize;
        gp.monster[0][12].worldY = 38*gp.tileSize;

        gp.monster[0][13] = new MON_RedSlime(gp);
        gp.monster[0][13].worldX = 39*gp.tileSize;
        gp.monster[0][13].worldY = 10*gp.tileSize;

        gp.monster[0][14] = new MON_RedSlime(gp);
        gp.monster[0][14].worldX = 39*gp.tileSize;
        gp.monster[0][14].worldY = 11*gp.tileSize;

        gp.monster[0][15] = new MON_RedSlime(gp);
        gp.monster[0][15].worldX = 14*gp.tileSize;
        gp.monster[0][15].worldY = 32*gp.tileSize;
        
        gp.monster[0][16] = new MON_RedSlime(gp);
        gp.monster[0][16].worldX = 11*gp.tileSize;
        gp.monster[0][16].worldY = 29*gp.tileSize;

       
    }
    public void setInteractiveTile() {
        gp.tileInteractiveObject[0][0] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][0].worldX = 27*gp.tileSize;
        gp.tileInteractiveObject[0][0].worldY = 12*gp.tileSize;

        gp.tileInteractiveObject[0][1] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][1].worldX = 28*gp.tileSize;
        gp.tileInteractiveObject[0][1].worldY = 12*gp.tileSize;

        gp.tileInteractiveObject[0][2] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][2].worldX = 29*gp.tileSize;
        gp.tileInteractiveObject[0][2].worldY = 12*gp.tileSize;

        gp.tileInteractiveObject[0][3] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][3].worldX = 30*gp.tileSize;
        gp.tileInteractiveObject[0][3].worldY = 12*gp.tileSize;

        gp.tileInteractiveObject[0][4] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][4].worldX = 31*gp.tileSize;
        gp.tileInteractiveObject[0][4].worldY = 12*gp.tileSize;

        gp.tileInteractiveObject[0][5] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][5].worldX = 32*gp.tileSize;
        gp.tileInteractiveObject[0][5].worldY = 12*gp.tileSize;

        gp.tileInteractiveObject[0][6] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][6].worldX = 33*gp.tileSize;
        gp.tileInteractiveObject[0][6].worldY = 12*gp.tileSize;

        
        gp.tileInteractiveObject[0][7] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][7].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][7].worldY = 30*gp.tileSize;

        gp.tileInteractiveObject[0][8] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][8].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][8].worldY = 29*gp.tileSize;

        gp.tileInteractiveObject[0][9] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][9].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][9].worldY = 28*gp.tileSize;

        gp.tileInteractiveObject[0][10] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][10].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][10].worldY = 27*gp.tileSize;

        gp.tileInteractiveObject[0][11] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][11].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][11].worldY = 26*gp.tileSize;

        gp.tileInteractiveObject[0][12] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][12].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][12].worldY = 25*gp.tileSize;

       
        gp.tileInteractiveObject[0][13] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][13].worldX = 18*gp.tileSize;
        gp.tileInteractiveObject[0][13].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][14] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][14].worldX = 17*gp.tileSize;
        gp.tileInteractiveObject[0][14].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][15] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][15].worldX = 16*gp.tileSize;
        gp.tileInteractiveObject[0][15].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][16] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][16].worldX = 15*gp.tileSize;
        gp.tileInteractiveObject[0][16].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][17] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][17].worldX = 14*gp.tileSize;
        gp.tileInteractiveObject[0][17].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][18] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][18].worldX = 13*gp.tileSize;
        gp.tileInteractiveObject[0][18].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][19] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][19].worldX = 13*gp.tileSize;
        gp.tileInteractiveObject[0][19].worldY = 41*gp.tileSize;

        gp.tileInteractiveObject[0][20] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][20].worldX = 12*gp.tileSize;
        gp.tileInteractiveObject[0][20].worldY = 41*gp.tileSize;

        gp.tileInteractiveObject[0][21] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][21].worldX = 11*gp.tileSize;
        gp.tileInteractiveObject[0][21].worldY = 41*gp.tileSize;

        gp.tileInteractiveObject[0][22] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][22].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][22].worldY = 41*gp.tileSize;

        gp.tileInteractiveObject[0][23] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][23].worldX = 10*gp.tileSize;
        gp.tileInteractiveObject[0][23].worldY = 40*gp.tileSize;

        gp.tileInteractiveObject[0][24] = new IT_DryTree(gp);
        gp.tileInteractiveObject[0][24].worldX = 31*gp.tileSize;
        gp.tileInteractiveObject[0][24].worldY = 21*gp.tileSize;


    }
}
