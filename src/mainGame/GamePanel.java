package mainGame;

import battle.BattlePanel;
import entity.PlayableCharacter;
import sounds.Sound;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    //SCREEN SETTINGS
    final int originTileSize = 16; //The Size of the tiles, or grids
    final int scale = 3;

    public final int tileSize = originTileSize * scale; //Making the tile size
    public final int screenCol = 12;
    public final int screenRow = 12;
    public final int screenHeight = screenCol * tileSize; //576 pixels
    public final int screenWidth = screenRow * tileSize; //576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldLength =tileSize*maxWorldRow;

    int FPS = 60; //Frames Per Second of the Game
    public boolean upPressed = false, downPressed = false, rightPressed = false, leftPressed = false, pPressed = false, enterPressed = false;

    TileManager tileM = new TileManager(this);
    //estantiation of Tile Manager
    Thread gameThread;
    public CollisionCheck cCheck = new CollisionCheck(this);
    //estantiation of CollisionCheck
    Sound sound = new Sound();
    //estantiation of Sound

    public PlayableCharacter player = new PlayableCharacter(this);
    //enstatiation of PlayableCharacter class

    public BattlePanel battle = new BattlePanel(this);
    //enstatiation of Battle Panel Class

    public int gameState;
    public final int roamState = 1;
    public final int fightState = 2;
    public final int pauseState = 3;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();

        //Changes the frame so the panel comes out like this

    }

    public void setUpGame(){
        gameState = roamState;
        if(gameState == roamState){
            playMusic(0);
            //if i change the state to roam when your on the map walking, it plays this music
        }
        if(gameState == fightState){
            playMusic(1);
            //if i change the state to fight when your on the battle screen, it plays this music
        }


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        //Puts everything in the Class into the thread
        gameThread.start();
        //automatically calls the run method
    }


    @Override
    public void run() {
        //This is where we will keep a game loop. A thread is where it'll always keep running and so a game loop will always be running in here unless closed.


        double drawInterval = 1000000000/FPS;
        //1billion nanoseconds = 1 second /60 = 0.1666 seconds, so this is the time each loop should hold, for it ot be updated 60 times per second
        double nextDrawTime = System.nanoTime() + drawInterval;
        //System.nanotime is the current time, so if you plus the draw interval to the current time it will tell you when you should redo the loop so that it happens 60 times per second

        while(gameThread != null){
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                //This checks how much time is remaining until the next draw time.
                remainingTime = remainingTime/1000000;
                //converts remaingTime from nano to milliseconds, as the sleep method only takes milliseconds

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                //so if the update and repaint took more time than the interval, this makes sure that no time is left and sleeping is unrequired.

                Thread.sleep((long) remainingTime);
                //Makes the thread sleep(or be inactive) until the next interval in which it should re run, so that it can be updated only every 60 seconds

                nextDrawTime += drawInterval;
                //So after the thread has slept, it is going to set a new draw time so the process can continue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
    private void update() {
        if(gameState == roamState){
            player.update();
            //calls the update function in player, if the player state is set
        }
        else if(gameState == fightState){
            battle.update();
            //call the update function in battle, if the fight state is set
        }



        // this is constantly being updated so it stimulates movement as the key is repeatedly being pressed
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if(gameState == roamState){
            tileM.draw(g2);
            player.draw(g2);
            //draws the tiles and player
        }
        else if(gameState == fightState){
            battle.drawBackground(g2);
            battle.draw(g2);
            //draws the bakground and ohter things associated
        }

        g2.dispose();
    }
    //Constantly redraws the character.
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gameState == fightState){
            if(code == KeyEvent.VK_W){
                battle.commandNum ++;
                if(battle.commandNum == 4){
                    battle.commandNum = 0;
                }
                else if(battle.commandNum2 == 1 && battle.commandNum == 2){
                    battle.commandNum = 0;
                }
            }
            else if(code == KeyEvent.VK_S){
                battle.commandNum --;
                if(battle.commandNum == -1 && battle.commandNum2 == 0){
                    battle.commandNum = 3;
                }
                if(battle.commandNum2 == 1 && battle.commandNum ==-1){
                    battle.commandNum = 1;
                }
            }
            else if(code == KeyEvent.VK_ENTER){
                if(battle.commandNum == 0 && battle.commandNum2 == 0 && battle.commandNum3 == 0){
                    battle.commandNum2 ++;
                }
                else if(battle.commandNum == 2 && battle.commandNum3 == 0){
                    battle.commandNum3 ++;
                }
                else if(battle.commandNum2 == 1 || battle.commandNum3 == 1 || battle.commandNum == 1 || battle.commandNum == 3){
                    battle.selected ++;
                    if(battle.selected == 2){
                        battle.selected =1;
                    }
                }
                else if(battle.win || battle.loose){
                    enterPressed = true;
                }



            }
            else if(code == KeyEvent.VK_BACK_SPACE){
                if(battle.commandNum2 == 1){
                    battle.commandNum2 = 0;
                    battle.commandNum = 0;
                }
                else if(battle.commandNum3 == 1){
                    battle.commandNum3 = 0;
                    battle.commandNum = 2;
                }
            }
        }
        //everyone command num, is a layer in the selection, the normal command num are the primary commands
        //the other command nums are options for every seldction in the normal commands
        //the entered press is selection
        //when the fight state is active the key events work

        if(gameState == roamState){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            else if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            else if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            else if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            else if(code == KeyEvent.VK_P){
                pPressed = true;
                System.out.println("P is pressed");
            }
            //if any of the keys are being pressed it makes the respective boolean true
        }

        //key listener listens out for when key is pressed and does accordingly

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
            PlayableCharacter.spriteNum = 1;
            //resets the sprite animation
        }
        else if(code == KeyEvent.VK_A) {
            leftPressed = false;
            PlayableCharacter.spriteNum = 1;
            //resets the sprite animation
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = false;
            PlayableCharacter.spriteNum = 1;
            //resets the sprite animation
        }
        else if(code == KeyEvent.VK_S) {
            downPressed = false;
            PlayableCharacter.spriteNum = 1;
            //resets the sprite animation
        }
        else if(code == KeyEvent.VK_P){
            pPressed = false;
        }
        //if the key is unpressed it turns off the respective boolean


    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.start();
        sound.loop();
    }
    //this starts and loops the music
    public void stopMusic(){
        sound.stop();
   }
    public void playSE(int i){
        sound.setFile2(i);
        sound.start2();
    }

    //this starts the music and doesn't loop it as it's a sound effect
}


