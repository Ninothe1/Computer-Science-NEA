package entity;

import mainGame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayableCharacter extends Entity{
    static GamePanel gp;

    public static int screenX;
    public static int screenY;

    public PlayableCharacter(GamePanel gp){
        this.gp = gp;

        screenX = (gp.screenWidth/2) - (gp.tileSize/2);
        screenY = (gp.screenHeight/2) - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23; //starting x position
        worldY = gp.tileSize * 21; //starting y position
        speed = 4; //how many pixels it moves per interval
        direction = "down"; //default direction its looking
        //set default variable such as players, x and y, speeds and the default direction they look at

    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy2_left_2.png"));
            //gets all the player images

        }catch(IOException e){
            e.printStackTrace();
        }

        }
    public static void update(){

        if(gp.upPressed == true){
            direction = "up";
            worldY -= speed;
        }
        else if(gp.downPressed == true){
            direction = "down";
            worldY += speed;
        }
        else if(gp.rightPressed == true){
            direction = "right";
            worldX += speed;
        }
        else if(gp.leftPressed == true){
            direction = "left";
            worldX -= speed;
        }
        //when the keys are pressed, the player will move or it's position will change accordingly
        if(gp.upPressed||gp.downPressed||gp.leftPressed||gp.rightPressed){
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

        //times when the character should transfer between it's primary and secondary animation.

    }
    public static void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum ==2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum ==2){
                    image = down2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum ==2){
                    image = right2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum ==2){
                    image = left2;
                }
                break;
        }
        g2.drawImage(image, screenX , screenY , gp.tileSize ,gp.tileSize, null);

    }




}
