import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen implements ActionListener {
    private JFrame loginScreen;
    private JPanel panel;
    private JTextField username;
    private JTextField password;
    private JButton login;
    private JButton register;

    @Override
    public void actionPerformed(ActionEvent e){
            String usernameL = username.getText();
            String passwordL = password.getText();
            System.out.println(usernameL);
            System.out.println(passwordL);

    }

    public LoginScreen(){
        JFrame loginScreen = new JFrame();
        JPanel panel = new JPanel();


        JTextField username = new JTextField("Enter your Username here");
        username.setBounds(390,150,500,50);

        JTextField password = new JTextField("Enter your Password here");
        password.setBounds(390,250,500,50);

        JButton login = new JButton("LogIn");
        login.setFont(new Font("Arial",Font.PLAIN, 25));
        login.setBounds(390,350,200,100);
        login.addActionListener(this);

        JButton register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 25));
        register.setBounds(690,350,200,100);
        register.addActionListener(this);


        JLabel mainText = new JLabel("Login or Register to play Spirit Drive Primo");
        mainText.setFont(new Font("Arial", Font.PLAIN, 15));
        mainText.setBounds(500,50,350,100);


        panel.setLayout(null);
        panel.add(login);
        panel.add(register);
        panel.add(mainText);
        panel.add(username);
        panel.add(password);

        loginScreen.setSize(1280,720);
        loginScreen.setVisible(true);
        loginScreen.setResizable(false);
        loginScreen.setTitle("Welcome to Spirit Drive Primo");
        loginScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginScreen.add(panel);


    }

    public static void main(String[]args){
        new LoginScreen();
    }
}
