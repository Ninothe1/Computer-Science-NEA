package loginAndStart;

import mainGame.MainGame;
import mainGame.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class StartScreen implements ActionListener{
    private JFrame startScreen;
    private JPanel panel;
    private JButton start;
    private JButton options;
    private JButton help;
    private JButton exit;
    private ImageIcon backgroundIcon;
    private JLabel background;
    private JPanel panel2;
    private ImageIcon backgroundIcon2;
    private JLabel background2;
    private JButton back;
    public static MainGame mainGame;
    GamePanel gp = new GamePanel();

    public StartScreen(){

        gp.playMusic(11);
        startScreen = new JFrame();

        panel = new JPanel();
        panel.setVisible(true);

        backgroundIcon = new ImageIcon(this.getClass().getResource("/loginAndStart/AstreaNew.jpg"));
        background = new JLabel(backgroundIcon);
        background.setSize(1280,720);

        JButton start = new JButton("START");
        start.setFont(new Font("Arial",Font.BOLD, 40));
        start.setBounds(320,270,267,133);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.addActionListener(this);


        JButton options = new JButton("OPTIONS");
        options.setFont(new Font("Arial",Font.BOLD, 40));
        options.setBounds(693,270,267,133);
        options.setOpaque(false);
        options.setContentAreaFilled(false);
        options.setBorderPainted(false);


        JButton help = new JButton("HELP");
        help.setFont(new Font("Arial",Font.BOLD, 40));
        help.setBounds(320,450,267,133);
        help.setOpaque(false);
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);
        help.addActionListener(this);


        JButton exit = new JButton("EXIT");
        exit.setFont(new Font("Arial",Font.BOLD, 40));
        exit.setBounds(693,450,267,133);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);


        panel.setLayout(null);
        panel.add(start);
        panel.add(options);
        panel.add(help);
        panel.add(exit);
        panel.add(background);

        panel2 = new JPanel();
        panel2.setVisible(true);

        backgroundIcon2 = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/loginAndStart/AstreaRein.jpg")));
        background2 = new JLabel(backgroundIcon2);
        background2.setSize(1280,720);

        JButton back = new JButton("BACK");
        back.setFont(new Font("Arial",Font.PLAIN, 25));
        back.setBounds(320,675,400,200);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.addActionListener(this);

        panel2.add(background2);
        panel2.add(back);
        panel.setLayout(null);


        startScreen.setSize(1280,720);
        startScreen.setVisible(true);
        startScreen.setResizable(false);
        startScreen.setTitle("Astrea's Reincarnation");
        startScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startScreen.add(panel);
        startScreen.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("EXIT")) {
            System.exit(0);
        }
        else if(e.getActionCommand().equals("HELP")){
//            panel.setVisible(false);
//            panel2.setVisible(true);
            startScreen.remove(panel);
            startScreen.add(panel2);
            startScreen.pack();
        }
        else if(e.getActionCommand().equals("BACK")){
            startScreen.remove(panel2);
            startScreen.add(panel);
            startScreen.pack();
//            startScreen.remove(panel2);
//            startScreen.add(panel);
        }
        else if(e.getActionCommand().equals("START")){
            gp.stopMusic();
            startScreen.dispose();
            mainGame = new MainGame();
        }
    }
}
