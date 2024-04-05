package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("res/World/worldV3.txt", 0);
        loadMap("res/World/interior01.txt", 1);
    }

    public void getTileImage() {
        getImage(0, "grass00", false);
        getImage(1, "grass00", false);
        getImage(2, "grass00", false);
        getImage(3, "grass00", false);
        getImage(4, "grass00", false);
        getImage(5, "grass00", false);
        getImage(6, "grass00", false);
        getImage(7, "grass00", false);
        getImage(8, "grass00", false);
        getImage(9, "grass00", false);

        getImage(10, "grass00", false);
        getImage(11, "grass01", false);
        getImage(12, "water00", true);
        getImage(13, "water01", true);
        getImage(14, "water02", true);
        getImage(15, "water03", true);
        getImage(16, "water04", true);
        getImage(17, "water05", true);
        getImage(18, "water06", true);
        getImage(19, "water07", true);
        getImage(20, "water08", true);
        getImage(21, "water09", true);
        getImage(22, "water10", true);
        getImage(23, "water11", true);
        getImage(24, "water12", true);
        getImage(25, "water13", true);
        getImage(26, "road00", false);
        getImage(27, "road01", false);
        getImage(28, "road02", false);
        getImage(29, "road03", false);
        getImage(30, "road04", false);
        getImage(31, "road05", false);
        getImage(32, "road06", false);
        getImage(33, "road07", false);
        getImage(34, "road08", false);
        getImage(35, "road09", false);
        getImage(36, "road10", false);
        getImage(37, "road11", false);
        getImage(38, "road12", false);
        getImage(39, "earth", false);
        getImage(40, "wall", true);
        getImage(41, "tree", true);
        getImage(42, "hut", true);
        getImage(43, "floor01", false);
        getImage(44, "table01", true);

                
    }
    public void getImage(int index, String name, boolean collision){
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream("res/Tiles/"+name+".png"));
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String name, int map) {
        try {
            InputStream is = new FileInputStream(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY



            ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }
            worldCol++;
   
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
