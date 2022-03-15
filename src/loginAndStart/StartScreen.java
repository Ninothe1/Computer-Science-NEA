package loginAndStart;

import mainGame.MainGame;
import mainGame.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.UIManager.*;


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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        gp.playMusic(11);
        startScreen = new JFrame();

        panel = new JPanel();
        panel.setVisible(true);
        //creating the first panel

        backgroundIcon = new ImageIcon(this.getClass().getResource("/loginAndStart/AstreaNew.jpg"));
        background = new JLabel(backgroundIcon);
        background.setSize(1280,720);
        //creating background

        JButton start = new JButton("START");
        start.setFont(new Font("Arial",Font.BOLD, 40));
        start.setBounds(320,270,267,133);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.addActionListener(this);
        //creating start button with action listener


        JButton options = new JButton("OPTIONS");
        options.setFont(new Font("Arial",Font.BOLD, 40));
        options.setBounds(693,270,267,133);
        options.setOpaque(false);
        options.setContentAreaFilled(false);
        options.setBorderPainted(false);
        //creating options button with action listener


        JButton help = new JButton("HELP");
        help.setFont(new Font("Arial",Font.BOLD, 40));
        help.setBounds(320,450,267,133);
        help.setOpaque(false);
        help.setContentAreaFilled(false);
        help.setBorderPainted(false);
        help.addActionListener(this);
        //creating help button with action listener


        JButton exit = new JButton("EXIT");
        exit.setFont(new Font("Arial",Font.BOLD, 40));
        exit.setBounds(693,450,267,133);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.addActionListener(this);
        //creating exit button with action listener


        panel.setLayout(null);
        panel.add(start);
        panel.add(options);
        panel.add(help);
        panel.add(exit);
        panel.add(background);
        //adding all the buttons and icons onto the panel

        panel2 = new JPanel();
        panel2.setVisible(true);
        //creating the 2nd panel

        backgroundIcon2 = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/loginAndStart/AstreaRein.jpg")));
        background2 = new JLabel(backgroundIcon2);
        background2.setSize(1280,720);
        //creating the second background icon

        JButton back = new JButton("BACK");
        back.setFont(new Font("Arial",Font.PLAIN, 25));
        back.setBounds(320,675,400,200);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.addActionListener(this);
        //creating the back button with action listener

        panel2.add(background2);
        panel2.add(back);
        panel.setLayout(null);
        //adding everything onto the second panel, the background and button


        startScreen.setSize(1280,720);
        startScreen.setVisible(true);
        startScreen.setResizable(false);
        startScreen.setTitle("Astrea's Reincarnation");
        startScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startScreen.add(panel);
        startScreen.setLocationRelativeTo(null);
        //setting the frame and adding everything to it
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("EXIT")) {
            System.exit(0);
            //when you press the exit button, the button exits
        }
        else if(e.getActionCommand().equals("HELP")){
//            panel.setVisible(false);
//            panel2.setVisible(true);
            startScreen.remove(panel);
            startScreen.add(panel2);
            startScreen.pack();
            //when you press the help button, the button it takes away the normal panel and puts on the help screen panel
        }
        else if(e.getActionCommand().equals("BACK")){
            startScreen.remove(panel2);
            startScreen.add(panel);
            startScreen.pack();
//            startScreen.remove(panel2);
//            startScreen.add(panel);
            //when you press the back button, it takes away the back button and brings you back
        }
        else if(e.getActionCommand().equals("START")){
            gp.stopMusic();
            startScreen.dispose();
            mainGame = new MainGame();
            //when you press the start button it disposes of the startscreen frame and starts the main game code
        }
    }
}
