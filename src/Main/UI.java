package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import Entity.Entity;
import Object.OBJ_Heart;
import Object.OBJ_Mana;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font MaruMonica;
    BufferedImage heart_full, heart_half, heart_blank, mana_full, mana_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();
    public boolean gameFinished = false;
    public String currentDialogues = "";
    public int cmdIndex = 0;
    public int titleScreenRate = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;
    int counter = 0;
    public Entity npc;


    public UI(GamePanel gp){
        this.gp = gp;
        File font = new File("res/Font/MaruMonica.ttf");
        try {
            MaruMonica = Font.createFont(Font.TRUETYPE_FONT, font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create And Object
        Entity heart = new OBJ_Heart(gp);
        Entity mana = new OBJ_Mana(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        mana_full = mana.image;
        mana_blank = mana.image2;

        

    }
    public void showMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(MaruMonica);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState){
            drawPlayScreen();
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if (gp.gameState == gp.inventoryState){
            drawInventoryScreen();
        }
        if (gp.gameState == gp.menuState){
            drawMenuScreen();
        }
        if (gp.gameState == gp.transitionState){
            drawTransition();
        }
        if (gp.gameState == gp.tradeState){
            drawTrade();
        }

    }
    public void drawTrade(){
        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select(){
        drawDialogueScreen();

        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);
        drawSubWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        placeCursor(x, y, 0);

        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        placeCursor(x, y, 1);
        
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        placeCursor(x, y, 2);
    }
    public void trade_buy(){}
    public void trade_sell(){}

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter*5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter > 50){
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.eHandler.tempCol*gp.tileSize;
            gp.player.worldY = gp.eHandler.tempRow*gp.tileSize;

            counter = 0;
            gp.gameState = gp.playState;
        }
    }
    public void drawMenuScreen() {

        String text;

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*7;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        text = "Option";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;
        int lineHeight = gp.tileSize;

        g2.drawString(text, x, y);
        
        text = "Sound: ";
        x = (gp.tileSize*7) + (gp.tileSize/2);
        y += lineHeight;

        g2.drawString(text, x, y);
        placeCursor(x, y, 0);

        text = "SE: ";
        y += lineHeight;

        g2.drawString(text, x, y);
        placeCursor(x, y, 1);

        text = "Save";
        y += lineHeight;

        g2.drawString(text, x, y);
        placeCursor(x, y, 2);

        text = "Save And Quit";
        y += lineHeight;

        g2.drawString(text, x, y);
        placeCursor(x, y, 3);

        text = "Back";
        y += lineHeight;

        g2.drawString(text, x, y);
        placeCursor(x, y, 4);

        int scaleX = gp.tileSize*4;
        int scaleY = 20;

        x = (gp.tileSize*9) + (gp.tileSize/2);
        y = (gp.tileSize*4) - scaleY;

        g2.drawRect(x, y, scaleX, scaleY);

        int scale = (int)scaleX/10;
        scaleX = scale*gp.music.volumeIndex;

        g2.fillRect(x, y, scaleX, scaleY);

        scaleX = gp.tileSize*4;
        
        y += lineHeight;

        g2.drawRect(x, y, scaleX, scaleY);

        scaleX = scale*gp.se.volumeIndex;

        g2.fillRect(x, y, scaleX, scaleY);
    }
    public void drawInventoryScreen() {
        final int frameX = gp.tileSize*4;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 36;

        g2.drawString("Level: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Life: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon: ", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield: ", textX, textY);
        textY += lineHeight;

        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        String value = String.valueOf((gp.player.level));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.life));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.mana));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.strenght));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.dexterity));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.attack));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.defense));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.exp));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.nextLevelExp));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf((gp.player.coin));
        textX = getXforAlignedText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.image, tailX - 24, textY - 24, 32, 32, null);
        textY += lineHeight;

        g2.drawImage(gp.player.currentShield.image, tailX - 24, textY - 24, 32, 32, null);
        textY += lineHeight;

        int inventoryX = gp.tileSize*11;
        int inventoryY = gp.tileSize;
        int inventoryWidth = gp.tileSize*6;
        int inventoryHeigth = gp.tileSize*5;
        drawSubWindow(inventoryX, inventoryY, inventoryWidth, inventoryHeigth);

        final int slotXstart = inventoryX + 20;
        final int slotYstart = inventoryY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for (int i = 0; i < gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, gp.tileSize, gp.tileSize, null);
            slotX += gp.tileSize;
            if (i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }

        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        int dframeX = inventoryX;
        int dframeY = inventoryY + inventoryHeigth;
        int dframeWidth = inventoryWidth;
        int dframeHeight = inventoryHeigth;
        drawSubWindow(dframeX, dframeY, dframeWidth, dframeHeight);

        textX = dframeX + 20;
        textY = dframeY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if (itemIndex < gp.player.inventory.size()){
            for(String line : gp.player.inventory.get(itemIndex).despiction.split("/n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawTitleScreen() {

        if (titleScreenRate == 0){

            // Title including shandows
            String text = "BlueBoy Adventure";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96));
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.setColor(new Color(45, 45, 45));
            g2.drawString(text, x+5, y+5);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // Character Image
            x = gp.screenWidth/2 - (gp.tileSize*2/2);
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2 , gp.tileSize*2, null);
        
            // Menu
            text = "NEW GAME";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48));
            x = getXforCenteredText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y); 
            placeCursor(x, y, 0);

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            placeCursor(x, y, 1);

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            placeCursor(x, y, 2);
        }
        else if (titleScreenRate == 1){
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

            String text = "Select your role!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            placeCursor(x, y, 0);

            text = "Theif";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            placeCursor(x, y, 1);

            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            placeCursor(x, y, 2);

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            placeCursor(x, y, 3);
        }
    }

    public void placeCursor(int x, int y, int index) {
        if (cmdIndex == index){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    public void drawPlayScreen() {
        if (gameFinished == true){
            if (gp.player.life <= 0){
                gp.stopMusic();
                gp.playSE(7);

                g2.setColor(new Color(0, 0, 0, 210));
                g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

                g2.setFont(MaruMonica);
                g2.setColor(Color.white);

                String text;
                int textLength;
                int x, y;

                text = "You have no heart";
                g2.setFont(g2.getFont().deriveFont(50F));
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - (textLength/2);
                y = gp.screenHeight/2 - (gp.tileSize/2);
                g2.drawString(text, x, y);

                g2.setFont(g2.getFont().deriveFont(96F));
                g2.setColor(Color.yellow);
                text = "Game Over";
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - (textLength/2);
                y = gp.screenHeight/2 - (gp.tileSize*2);
                g2.drawString(text, x, y);

                g2.setFont(g2.getFont().deriveFont(50F));
                g2.setColor(Color.WHITE);

                text = "Retry";
                x = getXforCenteredText(text);
                y = gp.tileSize*10;
                g2.drawString(text, x, y);
                placeCursor(x, y, 0);

                text = "Exit";
                x = getXforCenteredText(text);
                y += gp.tileSize;
                g2.drawString(text, x, y);
                placeCursor(x, y, 1);

            } else if (gp.goalReached == true){
                g2.setFont(MaruMonica);
                g2.setColor(Color.white);

                String text;
                int textLength;
                int x, y;

                text = "You found the treasure!";
                g2.setFont(g2.getFont().deriveFont(50F));
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - (textLength/2);
                y = gp.screenHeight/2 - (gp.tileSize/2);
                g2.drawString(text, x, y);

                g2.setFont(g2.getFont().deriveFont(96F));
                g2.setColor(Color.yellow);
                text = "Congratulations!";
                textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                x = gp.screenWidth/2 - (textLength/2);
                y = gp.screenHeight/2 - (gp.tileSize*2);
                g2.drawString(text, x, y);

                g2.setFont(g2.getFont().deriveFont(50F));
                g2.setColor(Color.WHITE);

                text = "Retry";
                x = getXforCenteredText(text);
                y = gp.tileSize*10;
                g2.drawString(text, x, y);
                placeCursor(x, y, 0);

                text = "Exit";
                x = getXforCenteredText(text);
                y += gp.tileSize;
                g2.drawString(text, x, y);
                placeCursor(x, y, 1);
            } else {
                gameFinished = false;
            }



        } else {

            int messageX = gp.tileSize;
            int messageY = gp.tileSize*3;

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

            for (int i = 0; i < message.size(); i++){
                if (message.get(i) != null){
                    g2.setColor(Color.white);
                    g2.drawString(message.get(i), messageX, messageY);

                    int counter = messageCounter.get(i) + 1;
                    messageCounter.set(i, counter);
                    messageY += 50;
                    if (messageCounter.get(i) >= 100){
                        message.remove(i);
                        messageCounter.remove(i);
                    }
                }
            }
            drawPlayerLife();
            drawManaCrystal();
        }

    }
    public void drawPlayerLife() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while (i < gp.player.maxlife/2){
            g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        while (i < gp.player.life) {

            g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            if (i < gp.player.life){
                g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, null);
            }
            i++;
            x += gp.tileSize;
            
        }
    }
    public void drawManaCrystal() {
        int x = gp.tileSize/2;
        int y = gp.tileSize+gp.tileSize/2;
        int i = 0;

        while (i < gp.player.maxmana){
            g2.drawImage(mana_blank, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        // Reset
        x = gp.tileSize/2;
        y = gp.tileSize+gp.tileSize/2;
        i = 0;

        while (i < gp.player.mana) {

            g2.drawImage(mana_full, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
            
        }
    }
    public void drawDialogueScreen() {
        drawPlayerLife();
        drawManaCrystal();
        // Window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String lines : currentDialogues.split("\n")){
            g2.drawString(lines, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color black = new Color(0, 0, 0, 210);
        g2.setColor(black);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        Color white = new Color(255, 255, 255);
        g2.setColor(white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

    }
    public void drawPauseScreen() {
        drawPlayerLife();
        drawManaCrystal();

        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

        text = "Press 'Escape' To Retrun To Game";
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x = getXforCenteredText(text);
        y += 40;

        g2.drawString(text, x, y);
        
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 -length/2;
        return x;
    }
    public int getXforAlignedText(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

}
