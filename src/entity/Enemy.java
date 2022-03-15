package entity;

import java.awt.image.BufferedImage;

public class Enemy extends Entity{
    public BufferedImage image;
    public BufferedImage death;
    public int action1;
    public int action2;
    public static int[] enemyAttacks;
    public static String weakness;
    public static int EHp;
    //the enemy attack numbers for the first enemy

    public Enemy(){
    }
}
