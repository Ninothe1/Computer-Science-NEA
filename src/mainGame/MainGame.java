package mainGame;

import javax.swing.*;

public class MainGame{
    private static JFrame gameFrame;
    public MainGame(){
        gameFrame = new JFrame();
        gameFrame.setVisible(true);
        gameFrame.setResizable(false);
        gameFrame.setTitle("Astrea's Reincarnation");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);

        GamePanel gamepanel = new GamePanel();
        gameFrame.add(gamepanel);
        gameFrame.pack();
        gamepanel.requestFocusInWindow();


        gamepanel.startGameThread();

    }
}
