package battle;

import javax.swing.*;
import java.awt.*;

public class BattlePanel extends JPanel {
    //SCREEN SETTINGS
    public final int screenHeight = 576;
    public final int screenWidth = 576;


    public BattlePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus();
    }
    public void getBattleImages(){
        ImageIcon backgroundIcon = new ImageIcon(this.getClass().getResource("battle/battle_screen.png"));
    }


}
