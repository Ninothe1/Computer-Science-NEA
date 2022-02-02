package tile;

import mainGame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    static GamePanel gp;
    static Tile[] tile;
    static int[][] mapTileNum;


    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.screenCol][gp.screenRow];

    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("map/Map-01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.screenCol && row < gp.screenRow){
                String line = br.readLine();

                while(col < gp.screenCol){
                    String numbers[] = line.split("  ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.screenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){


        }
    }


    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_water.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile_brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void draw (Graphics2D g2) {
        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;

        while(col < gp.screenCol && row < gp.screenRow){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize,gp.tileSize,null);
            col++;
            x+= gp.tileSize;

            if(col == gp.screenCol){
                col = 0;
                x = 0;
                row++;
                y+= gp.tileSize;
            }
        }
        }


    }





