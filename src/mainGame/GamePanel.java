package mainGame;

import entity.PlayableCharacter;
import tile.TileManager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    final int originTileSize = 16; //The Size of the tiles, or grids
    final int scale = 3;
    public final int tileSize = originTileSize * scale; //Making the tile size
    public final int screenCol = 10;
    public final int screenRow = 12;
    final int screenHeight = screenCol * tileSize; //480 pixels
    final int screenWidth = screenRow * tileSize; //576 pixels
    int FPS = 60; //Frames Per Second of the Game
    public boolean upPressed = false, downPressed = false, rightPressed = false, leftPressed = false;

    Thread gameThread;
    PlayableCharacter player = new PlayableCharacter(this);
    TileManager tileM = new TileManager(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();

        //Changes the frame so the panel comes out like this

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

        PlayableCharacter.update();

        // this is constantly being updated so it stimulates movement as the key is repeatedly being pressed
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);



        g2.dispose();
    }
    //Constantly redraws the character.
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
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
        //key listener listens out for when key is pressed and does accordingly

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        else if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        else if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        else if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        //key listener listens out for when key is released and does accordingly

    }

}


