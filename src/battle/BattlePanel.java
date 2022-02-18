package battle;

import entity.Enemy;
import entity.PlayableCharacter;
import mainGame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BattlePanel {
    GamePanel gp;
    public static Enemy[] enemy;
    public static BufferedImage backgroundIcon;
    public static BufferedImage backPlayer;
    public static BufferedImage backSpirit;
    public static int currentPlayerHP = PlayableCharacter.healthPoints;
    public static int currentEnemyHP = Enemy.healthPoints;
    public static int currentPlayerSP = PlayableCharacter.staminaPoints;
    public static int currentEnemySP = Enemy.staminaPoints;
    public int commandNum = 0, commandNum2 = 0, commandNum3 = 0, selected = 0;
    int playerDamage = 0;
    int playerStaminaCon = 0;
    String playerDamageType;
    int enemyDamage = 0;
    String actionText;
    boolean defence;
    boolean turn = true;
    int counter;



    public BattlePanel(GamePanel gp) {
        this.gp = gp;
        enemy = new Enemy[10];
        getBattleImages();
    }

    public void getBattleImages() {
        try {
            backgroundIcon = ImageIO.read(getClass().getResourceAsStream("/battle/battle_screen.png"));

            backPlayer = ImageIO.read(getClass().getResourceAsStream("/battle/PlayerBackSpriteNew.png"));

            enemy[0] = new Enemy();
            enemy[0].image = ImageIO.read(getClass().getResourceAsStream("/battle/MimeMonster.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {
        if (gp.pPressed) {
            currentPlayerHP -= 10;
        }
    }

    public void draw(Graphics2D g2) {
        //PLAYER DRAWING
        g2.drawImage(backPlayer, 40, 277, 181, 176, null);




        //PLAYER HP AND SP

        double healthBar = 160 * (currentPlayerHP / PlayableCharacter.healthPoints);
        double spBar = 160 * (currentEnemySP / Enemy.staminaPoints);

        //HP
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(400, 350, 166, 26);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(403, 353, (int) healthBar, 20);

        //SP
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(400, 380, 166, 26);

        g2.setColor(new Color(30, 0, 255));
        g2.fillRect(403, 383, 160, 20);

        //ENEMY DRAWING
        g2.drawImage(enemy[0].image, 300, 60, 181, 176, null);

        //ENEMY HP AND SP

        double enemyHealthBar = 160 * (currentEnemyHP / Enemy.healthPoints);
        double enemyStamina = 160 * (currentEnemySP / Enemy.staminaPoints);

        //HP
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(10, 10, 166, 26);

        System.out.println("Here is your CEHP"+currentEnemyHP);
        System.out.println("Here is EHP"+Enemy.healthPoints);
        System.out.println("Here is length of health bar,"+enemyHealthBar);
        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(13, 13, (int) enemyHealthBar, 20);

        //SP
        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(10, 40, 166, 26);

        g2.setColor(new Color(30, 0, 255));
        g2.fillRect(13, 43, (int) enemyStamina, 20);

        //Player Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        String text;
        int x;
        int y;

        if(selected == 0){
            if(commandNum2 == 0 && commandNum3 == 0){
                text = "FIGHT";
                x = 40;
                y = 505;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                    g2.drawString(">", x - 25, y);

                }
            }
            if (commandNum2 == 1) {
                text = "KNIFE";
                x = 40;
                y = 505;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                    g2.drawString(">", x - 25, y);
                }
                text = "GUN";
                x = 40;
                y = 565;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                    g2.drawString(">", x - 25, y);

                }

            }

            if(commandNum2 == 0 && commandNum3 == 0){
                text = "DEFEND";
                x = 40;
                y = 565;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                    g2.drawString(">", x - 25, y);
                    if (commandNum == 1 && gp.enterPressed) {

                    }
                }
            }


            if(commandNum2 == 0 && commandNum3 == 0){
                text = "SPIRIT";
                x = 390;
                y = 505;
                g2.drawString(text, x, y);
                if (commandNum == 2) {
                    g2.drawString(">", x - 25, y);
                    if (commandNum == 2 && gp.enterPressed) {

                    }
                }
            }
            if(commandNum3 == 1){
                text = "GIGAPUNCH";
                x = 40;
                y = 505;
                g2.drawString(text, x, y);
                if (commandNum == 0) {
                    g2.drawString(">", x - 25, y);


                }
                text = "DIVEKICK";
                x = 40;
                y = 565;
                g2.drawString(text, x, y);
                if (commandNum == 1) {
                    g2.drawString(">", x - 25, y);

                }
                text = "FLAMEFIST";
                x = 320;
                y = 505;
                g2.drawString(text, x, y);
                if (commandNum == 2) {
                    g2.drawString(">", x - 25, y);


                }
                text = "DARKFIST";
                x = 320;
                y = 565;
                g2.drawString(text, x, y);
                if (commandNum == 3) {
                    g2.drawString(">", x - 25, y);

                }


            }

            if(commandNum2 == 0  && commandNum3 == 0){
                text = "RUN";
                x = 390;
                y = 565;
                g2.drawString(text, x, y);
                if (commandNum == 3) {
                    g2.drawString(">", x - 25, y);
                    if (commandNum == 3 && gp.enterPressed) {

                    }
                }

            }

        }
        if(selected == 1){
            if(commandNum2 == 1 && commandNum == 0){
                playerDamage = 9;
                playerStaminaCon = 5;
                playerDamageType = "normal";
                actionText = "You run over and slash your opponent across the chest";

            }
            else if(commandNum2 == 1 && commandNum == 1){
                playerDamage = 5;
                playerStaminaCon = 5;
                playerDamageType = "anti-sky";
                actionText = "You aim your gun towards your opponent and pull down on the trigger of your pistol";
            }
            else if(commandNum3 == 1 && commandNum == 0){
                playerDamage = 20;
                playerStaminaCon = 5;
                playerDamageType = "normal";
                actionText = "Your spirit manifests and reels back it's fist before unleashing a destructive strike";

            }
            else if(commandNum3 == 1 && commandNum == 1){
                playerDamage = 12;
                playerStaminaCon = 5;
                playerDamageType = "anti-sky";
                actionText = "Your spirit manifests and leaps up before falling down as to deliver and a destructive strike";

            }
            else if(commandNum3 == 1 && commandNum == 2){
                playerDamage = 15;
                playerStaminaCon = 5;
                playerDamageType = "fire";
                actionText = "Your spirit manifests and reels it's arm back before sending forward and enflamed fist";

            }
            else if(commandNum3 == 1 && commandNum == 3){
                playerDamage = 15;
                playerStaminaCon = 5;
                playerDamageType = "cold";
                actionText = "Your spirit manifests and reels it's arm back before sending forward a freezing fist";

            }
            else if(commandNum == 1){
                defence = true;
                actionText = "Your spirit manifests, crossing it's arms to protect you";
            }
            else if(commandNum == 3){
                selected --;
                actionText = "You can not run!";

            }

            Random rand = new Random();
            int enemyAction = rand.nextInt(5);

            if(commandNum2 == 1 || commandNum3 == 1){
                if(playerDamageType == Enemy.weakness){
                    playerDamage *= 1.2;
                }

                if(enemyAction == 4){
                    playerDamage /= 5;
                }

                currentEnemyHP -= playerDamage;
                playerDamage = 0;

                while(counter < 500){
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
                    if(enemyAction == 4){
                        g2.drawString(actionText + "It was defended against well", 40, 505);
                    }
                    else{
                        g2.drawString(actionText, 40, 505);
                    }
                    counter++;
                }

            }
            counter = 0;


            enemyDamage = Enemy.enemyAttacks[enemyAction];
            String enemyActionText = Enemy.enemyActions[enemyAction];
            if(enemyAction > 4){

                if(commandNum == 1){
                    enemyDamage /= 5;
                }
                currentPlayerHP -= enemyDamage;
                while(counter < 500){
                    g2.drawString(enemyActionText, 40, 515);
                    counter++;
                }
                counter =0;
            }

            commandNum =0;
            commandNum2=0;
            commandNum3=0;
            selected = 0;
        }

    }
    public void drawBackground (Graphics2D g2){
        g2.drawImage(backgroundIcon, 0, 0, 576, 576, null);

    }
}

