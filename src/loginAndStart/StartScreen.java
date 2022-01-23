package loginAndStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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


    public StartScreen(){
        startScreen = new JFrame();

        panel = new JPanel();
        panel.setVisible(true);

        backgroundIcon = new ImageIcon(this.getClass().getResource("loginAndStart/AstreaNew.jpg"));
        background = new JLabel(backgroundIcon);
        background.setSize(1920,1080);

        JButton start = new JButton("START");
        start.setFont(new Font("Arial",Font.PLAIN, 25));
        start.setBounds(480,405,400,200);


        JButton options = new JButton("OPTIONS");
        options.setFont(new Font("Arial",Font.PLAIN, 25));
        options.setBounds(1040,405,400,200);


        JButton help = new JButton("HELP");
        help.setFont(new Font("Arial",Font.PLAIN, 25));
        help.setBounds(480,675,400,200);
        help.addActionListener(this);


        JButton exit = new JButton("EXIT");
        exit.setFont(new Font("Arial",Font.PLAIN, 25));
        exit.setBounds(1040,675,400,200);
        exit.addActionListener(this);


        panel.setLayout(null);
        panel.add(start);
        panel.add(options);
        panel.add(help);
        panel.add(exit);
        panel.add(background);

        panel2 = new JPanel();
        panel2.setVisible(true);

        backgroundIcon2 = new ImageIcon(this.getClass().getResource("loginAndStart/AstreaRein.jpg"));
        background2 = new JLabel(backgroundIcon2);
        background2.setSize(1920,1080);

        JButton back = new JButton("BACK");
        back.setFont(new Font("Arial",Font.PLAIN, 25));
        back.setBounds(480,675,400,200);
        back.addActionListener(this);

        panel2.add(background2);
        panel2.add(back);
        panel.setLayout(null);


        startScreen.setSize(1920,1080);
        startScreen.setVisible(true);
        startScreen.setResizable(false);
        startScreen.setTitle("Astrea's Reincarnation");
        startScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startScreen.add(panel);
        startScreen.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "EXIT") {
            System.exit(0);
        }
        else if(e.getActionCommand() == "HELP"){
//            panel.setVisible(false);
//            panel2.setVisible(true);
            startScreen.remove(panel);
            startScreen.add(panel2);
            startScreen.pack();
        }
        else if(e.getActionCommand() == "BACK"){
            startScreen.remove(panel2);
            startScreen.add(panel);
            startScreen.pack();
//            startScreen.remove(panel2);
//            startScreen.add(panel);
        }

    }
}
