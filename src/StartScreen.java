import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen implements ActionListener {
    private JPanel panel;
    private JFrame frame;
    Image img;


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "exit");
            System.exit(0);
    }



    public StartScreen() {


        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource("download.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,1280,720);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Spirit Drive Primo");
        frame.setVisible(true);
        frame.setSize(1280,720);
        frame.add(panel);

        JLabel mainTitle = new JLabel("Spirit Drive Primo");
        mainTitle.setBounds(590,0, 100, 100);
        mainTitle.setVisible(true);

        JButton start = new JButton("Start");
        start.setBounds(390,200,500,100);
        start.setVisible(true);

        JButton option = new JButton("Option");
        option.setBounds(390,300,500,100);
        option.setVisible(true);

        JButton help = new JButton("Help");
        help.setBounds(390,400,500,100);
        help.setVisible(true);

        JButton exit = new JButton("Exit");
        exit.setBounds(390,500,500,100);
        exit.setVisible(true);
        exit.addActionListener(this);

        panel.add(backgroundLabel);
        panel.add(mainTitle);
        panel.add(start);
        panel.add(option);
        panel.add(help);
        panel.add(exit);
        panel.setLayout(null);

    }

    public static void main(String[]args){
        new StartScreen();
    }
}
