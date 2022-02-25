package entity;

import java.awt.image.BufferedImage;

public class Enemy extends Entity{
    public BufferedImage image;
    public BufferedImage death;
    public int action1;
    public int action2;
    public static int[] enemyAttacks = new int[]{15, 10, 12, 6};
    public static String[] enemyActions = new String[]{"The corrupted mime uses shadow shot",
            "The corrupted mime uses bite",
            "The corrupted mime uses shadow fist",
            "The corrupted mime tooted and the stink damaged you" };

    public void setDefaultValues(){

        weakness = "fire";

    }
    public Enemy(){
        setDefaultValues();
    }
}
