package entity;

import mainGame.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public static int speed;
    public static int worldX, worldY;
    public static BufferedImage up1;
    public static BufferedImage up2;
    public static BufferedImage left1;
    public static BufferedImage left2;
    public static BufferedImage right1;
    public static BufferedImage right2;
    public static BufferedImage down1;
    public static BufferedImage down2;
    public static String direction;
    public static int spriteCounter = 0;
    public static int spriteNum = 1;
    public Rectangle solidArea;
    public static boolean collisionOn = false;
    public static String weakness;



}
