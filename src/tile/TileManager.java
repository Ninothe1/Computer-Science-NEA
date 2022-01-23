package tile;

import mainGame.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(){
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();

    }
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("tiles/tile_grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("tiles/tile_water.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("tiles/tile_brick.png"));
        }catch(IOException e){
            e.printStackTrace();
        }


    }
}
