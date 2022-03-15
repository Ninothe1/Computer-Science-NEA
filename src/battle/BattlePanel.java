package battle;

import com.mysql.cj.log.Log;
import entity.Enemy;
import entity.PlayableCharacter;
import loginAndStart.ConnectionDB;
import loginAndStart.LoginScreen;
import mainGame.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class BattlePanel {
    GamePanel gp;
    PlayableCharacter pc;
    public static Enemy[] enemy;
    public static BufferedImage backgroundIcon;
    public static BufferedImage backPlayer;
    public static BufferedImage backSpirit;
    public static int currentPlayerHP = 100;
    public static int maxEnemyHP;
    public static int currentEnemyHP;
    public static int currentPlayerSP = 100;
    public int commandNum = 0, commandNum2 = 0, commandNum3 = 0, selected = 0;
    int playerDamage = 0;
    int playerStaminaCon = 0;
    String playerDamageType;
    int enemyDamage = 0;
    String actionText;
    boolean defence;
    boolean turn = true;
    int counter;
    int soundAction;
    public static boolean battleActive = true;
    public boolean win = false;
    public boolean loose = false;
    Random rand = new Random();
    public int TheEnemy;
    public static int[] eAttacks;




    public BattlePanel(GamePanel gp) {
        this.gp = gp;
        enemy = new Enemy[10];
        getBattleImages();
        TheEnemy = rand.nextInt(5);
    }

    public void getBattleImages() {
        try {
            backgroundIcon = ImageIO.read(getClass().getResourceAsStream("/battle/battle_screen.png"));

            backPlayer = ImageIO.read(getClass().getResourceAsStream("/battle/PlayerBackSpriteNew.png"));

            enemy[0] = new Enemy();
            enemy[0].image = ImageIO.read(getClass().getResourceAsStream("/battle/MimeMonster.png"));
            enemy[0].enemyAttacks = new int[]{15, 10, 12, 6};
            eAttacks = enemy[0].enemyAttacks;
            enemy[0].weakness = "fire";
            enemy[0].EHp = 80;
            maxEnemyHP = enemy[0].EHp;
            currentEnemyHP = maxEnemyHP;

            enemy[1] = new Enemy();
            enemy[1].image = ImageIO.read(getClass().getResourceAsStream("/battle/SharkMonster.png"));
            enemy[1].enemyAttacks = new int[]{25, 5, 6, 7};
            eAttacks = enemy[1].enemyAttacks;
            enemy[1].weakness = "ice";
            enemy[1].EHp = 65;
            maxEnemyHP = enemy[1].EHp;
            currentEnemyHP = maxEnemyHP;

            enemy[2] = new Enemy();
            enemy[2].image = ImageIO.read(getClass().getResourceAsStream("/battle/TongueMonster.png"));
            enemy[2].enemyAttacks = new int[]{10, 11, 12, 13};
            eAttacks = enemy[2].enemyAttacks;
            enemy[2].weakness = "fire";
            enemy[2].EHp = 90;
            maxEnemyHP = enemy[2].EHp;
            currentEnemyHP = maxEnemyHP;

            enemy[3] = new Enemy();
            enemy[3].image = ImageIO.read(getClass().getResourceAsStream("/battle/ChompMonster.png"));
            enemy[3].enemyAttacks = new int[]{25, 7, 14, 6};
            eAttacks = enemy[3].enemyAttacks;
            enemy[3].weakness = "cold";
            enemy[3].EHp = 110;
            maxEnemyHP = enemy[3].EHp;
            currentEnemyHP = maxEnemyHP;

            enemy[4] = new Enemy();
            enemy[4].image = ImageIO.read(getClass().getResourceAsStream("/battle/AntlerMonster.png"));
            enemy[4].enemyAttacks = new int[]{15, 10, 10, 15};
            eAttacks = enemy[4].enemyAttacks;
            enemy[4].weakness = "normal";
            enemy[4].EHp = 100;
            maxEnemyHP = enemy[4].EHp;
            currentEnemyHP = maxEnemyHP;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {
    }

    public void draw(Graphics2D g2) {

        int level = LoginScreen.exp / 120;
        if(battleActive) {
            //PLAYER DRAWING
            g2.drawImage(backPlayer, 40, 277, 181, 176, null);


            //PLAYER HP AND SP

            double healthBar = 160 * ((double) currentPlayerHP / (double) (pc.playerHp + (level*10)));
            double spBar = 160 * ((double) currentPlayerSP / (double) (pc.playerHp + (level*10)));
            double expBar = 160 * ((double) LoginScreen.exp/(double) 120);


            //HP
            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(400, 350, 166, 26);

            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(403, 353, (int) healthBar, 20);

            //SP
            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(400, 380, 166, 26);

            g2.setColor(new Color(30, 0, 255));
            g2.fillRect(403, 383, (int) spBar, 20);

            //Experience Bar

            g2.setColor((new Color(10,190,225)));
            g2.fillRect(400, 413, (int) expBar,3);

            //ENEMY DRAWING
            g2.drawImage(enemy[TheEnemy].image, 300, 60, 181, 176, null);

            //ENEMY HP AND SP

            double enemyHealthBar = 160 * ((double) currentEnemyHP / (double) (maxEnemyHP));


            //HP
            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(10, 10, 166, 26);

            g2.setColor(new Color(255, 0, 30));
            g2.fillRect(13, 13, (int) enemyHealthBar, 20);

            //Player Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

            String text;
            int x;
            int y;

            if (selected == 0) {
                if (commandNum2 == 0 && commandNum3 == 0) {
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

                if (commandNum2 == 0 && commandNum3 == 0) {
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


                if (commandNum2 == 0 && commandNum3 == 0) {
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
                if (commandNum3 == 1) {
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
                    text = "FROSTFIST";
                    x = 320;
                    y = 565;
                    g2.drawString(text, x, y);
                    if (commandNum == 3) {
                        g2.drawString(">", x - 25, y);

                    }


                }

                if (commandNum2 == 0 && commandNum3 == 0) {
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
            if (selected == 1) {
                if (commandNum2 == 1 && commandNum == 0) {
                    playerDamage = 9;
                    playerStaminaCon = 5;
                    playerDamageType = "normal";
                    actionText = "You slash your opponent across the chest";
                    soundAction = 5;


                } else if (commandNum2 == 1 && commandNum == 1) {
                    playerDamage = 5;
                    playerStaminaCon = 5;
                    playerDamageType = "anti-sky";
                    actionText = "You aim and let it rain";
                    soundAction = 6;
                } else if (commandNum3 == 1 && commandNum == 0) {
                    playerDamage = 20;
                    playerStaminaCon = 5;
                    playerDamageType = "normal";
                    actionText = "Your spirit manifests unleashing a destructive strike";
                    soundAction = 7;

                } else if (commandNum3 == 1 && commandNum == 1) {
                    playerDamage = 12;
                    playerStaminaCon = 5;
                    playerDamageType = "anti-sky";
                    actionText = "Your spirit manifests in the skies coming down for a kick";
                    soundAction = 8;

                } else if (commandNum3 == 1 && commandNum == 2) {
                    playerDamage = 15;
                    playerStaminaCon = 5;
                    playerDamageType = "fire";
                    actionText = "Your spirit manifests and unleashes and enflamed fist";
                    soundAction = 9;

                } else if (commandNum3 == 1 && commandNum == 3) {
                    playerDamage = 15;
                    playerStaminaCon = 5;
                    playerDamageType = "cold";
                    actionText = "Your spirit manifests and unleashes a ice clad fist";
                    soundAction = 10;

                } else if (commandNum == 1) {
                    defence = true;
                    actionText = "Your spirit manifests, crossing it's arms to protect you";
                } else if (commandNum == 3) {
                    selected--;
                    actionText = "You can not run!";

                }

                Random rand = new Random();
                int enemyAction = rand.nextInt(4);

                if (commandNum2 == 1 || commandNum3 == 1) {
                    if (playerDamageType == Enemy.weakness) {
                        playerDamage *= 1.2;
                    }

                    if (enemyAction == 4) {
                        playerDamage /= 5;
                    }

                    System.out.println(playerDamage);
                    playerDamage += pc.levelBonus[level];


                    currentEnemyHP -= playerDamage;
                    gp.playSE(soundAction);
                    playerDamage = 0;

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

                    System.out.println(currentEnemyHP);
                }

                enemyDamage = eAttacks[enemyAction];
                if (enemyAction == 0 || enemyAction == 1 || enemyAction == 2 || enemyAction == 3) {

                    if (commandNum == 1) {
                        enemyDamage /= 5;
                    }
                    System.out.println(enemyDamage);
                    currentPlayerHP -= enemyDamage;
                    counter = 0;
                    System.out.println(currentPlayerHP);
                }

                commandNum = 0;
                commandNum2 = 0;
                commandNum3 = 0;
                selected = 0;

                if(currentEnemyHP < 0 || currentPlayerHP < 0){
                    if(currentPlayerHP > 0){
                        loose = false;
                    }else if(currentEnemyHP > 0){
                        win = true;
                    }
                    battleActive = false;

                }

            }

        }
        else{
            gp.stopMusic();
            gp.playMusic(0);

            Connection con = ConnectionDB.connect();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql;

            while (gp.enterPressed = false){
                if(win){
                    try{
                        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
                        String text;
                        int x;
                        int y;
                        text = "YOU WON, YOU GOT 15 XP";
                        x = 40;
                        y = 505;
                        g2.drawString(text, x, y);
                        commandNum = 0;
                        commandNum2 = 0;
                        commandNum3 = 0;
                        LoginScreen.exp += 15;

                        sql = "INSERT INTO Login(Exp) VALUES(?) ";
                        ps = con.prepareStatement(sql);
                        ps.setInt(1, LoginScreen.exp);
                        ps.execute();

                    }catch (SQLException d) {
                        System.out.println(d.toString());
                    } finally {
                        try {
                            rs.close();
                            ps.close();
                            con.close();


                        } catch (SQLException d) {
                            System.out.print(d.toString());
                        }
                    }
                }



                else if(loose){
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 60F));
                    String text;
                    int x;
                    int y;
                    text = "YOU LOOSE, SORRY";
                    x = 40;
                    y = 505;
                    g2.drawString(text, x, y);
                    commandNum = 0;
                    commandNum2 = 0;
                    commandNum3 = 0;
                    gp.playMusic(0);
                    gp.stopMusic();
                    gp.playMusic(1);

                }


            }
            Random rand = new Random();
            TheEnemy = rand.nextInt(5);
            maxEnemyHP = enemy[TheEnemy].EHp;
            currentEnemyHP = maxEnemyHP;

            currentPlayerHP = 100 + (level*10);
            currentPlayerSP = 100 + (level*10);



            pc.time = 0;
            gp.gameState = 1;
        }

    }
    public void drawBackground (Graphics2D g2){
        g2.drawImage(backgroundIcon, 0, 0, 576, 576, null);

    }
}