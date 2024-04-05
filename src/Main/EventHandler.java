package Main;

import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;
    int tempMap, tempCol, tempRow;
    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

        tempMap = 0;
        tempCol = 0;
        tempRow = 0;

    }
    public void checkEvent(){
        if (hit(23, 12, 0, "up") || 
            hit(22, 12, 0, "up") || 
            hit(24, 12, 0, "up") ||

            hit(20, 10, 0, "right") ||
            hit(20, 9, 0, "right") ||

            hit(23, 7, 0, "down") ||
            hit(22, 7, 0, "down") ||
            hit(24, 7, 0, "down") ||

            hit(26, 10, 0, "left") ||
            hit(26, 9, 0, "left")
  
        ){
            healingPool(gp.dialogueState);
        }
        if (hit (10, 40, 0, "up")){
            teleport(1, 12, 13, gp.transitionState);
        }
        if (hit (12, 13, 1, "down")){
            teleport(0, 10, 40, gp.transitionState);
        }
        
    }
    public boolean hit(int eventCol, int eventRow, int reqMap, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol*gp.tileSize + eventRect.x;
        eventRect.y = eventRow*gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect) && gp.currentMap == reqMap){
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    public void healingPool(int gameState){
        if (gp.player.life != gp.player.maxlife && gp.keyH.enterPressed == true){
            gp.player.dialogue[0] = "You drink water from the pond\nYou gain a heart";
            gp.player.speak();
            gp.gameState = gameState;
            gp.player.life++;
        }
        gp.keyH.enterPressed = false;

    }
    public void teleport(int map, int col, int row, int gameState){
        tempMap = map;
        tempCol = col;
        tempRow = row;
        gp.player.dialogue[0] = "Teleported!";
        gp.player.speak();
        gp.gameState = gameState;
    }
}
