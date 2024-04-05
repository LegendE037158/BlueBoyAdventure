package Main;

import javax.swing.JPanel;

import Data.DataStorage;
import Data.LoadManager;
import Entity.Entity;
import Entity.Player;
import Environment.EnvironmentManager;
import Tile.TileManager;
import Tile_Interactive.InteractiveObject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    final public int tileSize = originalTileSize * scale; // 48x48 tile
    final public int maxScreenCol = 20;
    final public int maxScreenRow = 12;
    final public int screenWidth = tileSize * maxScreenCol;
    final public int screenHeight = tileSize * maxScreenRow;

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    
    //Music
    public Music music = new Music();
    public Music se = new Music();
    
    // Frame Per Second
    int fps = 60;

    public int maxMap = 10;
    public int currentMap = 0;

    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public TileManager tileManager = new TileManager(this);
    public LoadManager loadManager = new LoadManager(this);
    public InteractiveObject tileInteractiveObject[][] = new InteractiveObject[maxMap][50];
    public DataStorage ds = new DataStorage();
    public Entity obj[][] = new Entity[maxMap][50];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][20];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> particleList = new ArrayList<>();
    public EventHandler eHandler = new EventHandler(this);
    public EnvironmentManager environmentManager = new EnvironmentManager(this);

    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int inventoryState = 4;
    public final int menuState = 5;
    public final int transitionState = 6;
    public final int tradeState = 7;

    public boolean goalReached = false; 

    public boolean loadGame = false;

    public void ResetGame(){
        player.setDefaultValues();
        goalReached = false;
    }

    public void ResetEveryThing(){
        aSetter.setObject();
        aSetter.setNCP();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        player.setDefaultValues();
        goalReached = false;
        environmentManager.set();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNCP();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
        gameState = titleState;
        environmentManager.set();
    }
    public void StoreDataInRAM(){
        ds.level = player.level;
        ds.maxlife = player.maxlife;
        ds.life = player.life;
        ds.maxmana = player.maxmana;
        ds.mana = player.mana;
        ds.strenght = player.strenght;
        ds.dexterity = player.dexterity;
        ds.attack = player.attack;
        ds.defense = player.defense;
        ds.exp = player.exp;
        ds.nextLevelExp = player.nextLevelExp;
        ds.coin = player.coin;
        ds.playerRole = player.playerRole;
        ds.currentWeapon = player.currentWeapon.name;
        ds.currentShield = player.currentShield.name;

        for (int i = 0; i < player.inventory.size(); i++){
            if (player.inventory.get(i) != null){
                ds.inventory.add(player.inventory.get(i).name);
            }
        }

        
    }
    public void LoadDataFromRAM(){
        player.level = ds.level;
        player.maxlife = ds.maxlife;
        player.life = ds.life;

        if (player.life <= 0){
            player.life = player.maxlife;
        }

        player.maxmana = ds.maxmana;
        player.mana = ds.mana;
        player.strenght = ds.strenght;
        player.dexterity = ds.dexterity;
        player.attack = ds.attack;
        player.defense = ds.defense;
        player.exp = ds.exp;
        player.nextLevelExp = ds.nextLevelExp;
        player.coin = ds.coin;
        player.playerRole = ds.playerRole;
        player.currentWeapon = loadManager.getObject(ds.currentWeapon);
        player.currentShield = loadManager.getObject(ds.currentShield);

        player.inventory = new ArrayList<>();

        for (int i = 0; i < ds.inventory.size(); i++){
            if (ds.inventory.get(i) != null){
                player.inventory.add(loadManager.getObject(ds.inventory.get(i)));
            }
        }

        ClearDataInRAM();
    }
    public void ClearDataInRAM(){
        ds = new DataStorage();
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = 1000000000/fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if (delta >= 1){
                update();
                repaint(); 
                delta--;
                drawCount++;
            }  
            
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
                    
            }
        }

    }

    public void update() {
        if (gameState == playState && ui.gameFinished == false){
            // Player
            player.update();
            environmentManager.update();
            // NPC
            for (int i = 0; i < npc[currentMap].length; i++){
                if (npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < monster[currentMap].length; i++){
                if (monster[currentMap][i] != null){
                    if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if (monster[currentMap][i].alive == false){
                        monster[currentMap][i] = null;
                    }
                }
            }

            for (int i = 0; i < projectileList.size(); i++){
                if (projectileList.get(i) != null){
                    if (projectileList.get(i).alive == true && currentMap == 0){
                        projectileList.get(i).update();
                    }
                    else if (projectileList.get(i).alive == false){
                        projectileList.remove(i);
                    }
                }
            }

            for (int i = 0; i < particleList.size(); i++){
                if (particleList.get(i) != null){
                    if (particleList.get(i).alive == true && currentMap == 0){
                        particleList.get(i).update();
                    }
                    else if (particleList.get(i).alive == false){
                        particleList.remove(i);
                    }
                }
            }

            for(int i = 0; i < tileInteractiveObject[currentMap].length; i++){
                if (tileInteractiveObject[currentMap][i] != null){
                    tileInteractiveObject[currentMap][i].update();
                }
            }
            
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (gameState == titleState){
            ui.draw(g2);
        } else {
            tileManager.draw(g2);
            for (int i = 0; i < tileInteractiveObject[currentMap].length; i++) {
                if (tileInteractiveObject[currentMap][i] != null){
                    tileInteractiveObject[currentMap][i].draw(g2);
                }
            }
            for (int i = 0; i < obj[currentMap].length; i++) {
                if (obj[currentMap][i] != null){
                    obj[currentMap][i].draw(g2);
                }
            }
            for (int i = 0; i < npc[currentMap].length; i++) {
                if (npc[currentMap][i] != null){
                    npc[currentMap][i].draw(g2);
                }
            }
            for (int i = 0; i < monster[currentMap].length; i++) {
                if (monster[currentMap][i] != null){
                    monster[currentMap][i].draw(g2);
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null && currentMap == 0){
                    projectileList.get(i).draw(g2);
                }
            }
            for (int i = 0; i < particleList.size(); i++) {
                if (particleList.get(i) != null && currentMap == 0){
                    particleList.get(i).draw(g2);
                }
            }
            player.draw(g2);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            environmentManager.draw(g2);
            ui.draw(g2);
        }
        g2.dispose();
    }
    public void playMusic(int i) {
        music.SetFile(i);
        music.play();
        music.loop();
        music.setVol();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.SetFile(i);
        se.play();
        se.setVol();
    }
    
}
