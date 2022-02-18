package entity;

import java.awt.image.BufferedImage;

public class Enemy extends Entity{
    public BufferedImage image;
    public BufferedImage death;
    public int action1;
    public int action2;
    public static int[] enemyAttacks = new int[]{15, 10, 12, 6};
    public static String[] enemyActions = new String[]{"The devious mime squeels and unleashes a wave of dark energy",
            "The mime aims it's fingers towards you and shoots out `air bullets`",
            "The mime pulls out an imaginary weapon from it's pockets and slams it in your gut",
            "The mime pulls farts and the stink damages you" };

    public void setDefaultValues(){
        healthPoints = 70;
        staminaPoints = 60;
        weakness = "fire";

    }
    public Enemy(){
        setDefaultValues();
    }
}
