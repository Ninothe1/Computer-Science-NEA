package loginAndStart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreen implements ActionListener {
    private static JFrame loginScreen;
    private static JPanel panel;
    private static JTextField username;
    private static JTextField password;
    private static JButton login;
    private static JButton register;
    public static boolean LoggedIn = false;
    public static StartScreen startScreen;
    private static JLabel recognition;
    private static JLabel mainText;





    public LoginScreen() {
        loginScreen = new JFrame();
        panel = new JPanel();

        username = new JTextField("Enter your username here");
        username.setBounds(390, 150, 500, 50);
        username.addFocusListener(new FocusListener() {
        //Textfield to enter username

            @Override
            public void focusGained(FocusEvent e) {
                if(username.getText().equals("Enter your username here")){
                    username.setText("");
                    //Changes the text in username when clicked so user can write in it
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(username.getText().equals("")){
                    username.setText("Enter your username here");
                    //Changes the text in username when someone clicks off it so they can see thats where they enter their username
                }

            }
        });



        password = new JTextField("Enter your password here");
        password.setBounds(390, 250, 500, 50);
        password.addFocusListener(new FocusListener() {
            //Textfield to enter password
            @Override
            public void focusGained(FocusEvent e) {
                if(password.getText().equals("Enter your password here")){
                    password.setText("");
                    //Changes the text in password when clicked so user can write in it
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(password.getText().equals("")){
                    password.setText("Enter your password here");
                    //Changes the text in username when someone clicks off it so they can see where to write their password again
                }

            }
        });

        login = new JButton("LogIn");
        login.setFont(new Font("Arial", Font.PLAIN, 25));
        login.setBounds(390, 350, 200, 100);
        login.addActionListener(this);
        login.setActionCommand("LogIn");
        //Login button

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 25));
        register.setBounds(690, 350, 200, 100);
        register.addActionListener(this);
        register.setActionCommand("Register");
        //Register button


        mainText = new JLabel("Login or Register to play Spirit Drive Primo");
        mainText.setFont(new Font("Arial", Font.PLAIN, 15));
        mainText.setBounds(500, 50, 350, 100);
        //Label at the top

        recognition = new JLabel("");
        recognition.setFont(new Font("Arial", Font.PLAIN, 15));
        recognition.setBounds(465, 600, 350, 100);
        //Label to say whether you have an incorrect or used password

        panel.setLayout(null);
        panel.add(login);
        panel.add(register);
        panel.add(mainText);
        panel.add(username);
        panel.add(password);
        panel.add(recognition);
        //adding all my panels

        loginScreen.setSize(1280, 720);
        loginScreen.setVisible(true);
        loginScreen.setResizable(false);
        loginScreen.setTitle("Welcome to Spirit Drive Primo");
        loginScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginScreen.add(panel);
        //setting window size
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Connection con = ConnectionDB.connect();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        //connecting to database and getting variable ready to prepare statement and to store result set
        if(e.getActionCommand().equals("LogIn")){
            String usernameL = username.getText();
            String passwordL = password.getText();
            //Getting whatever is inside of the username and password

            try {
                String sql = "SELECT * FROM Login WHERE Username ==" + "\"" + usernameL + "\"";
                ps = con.prepareStatement(sql);
                //Preparing the sql statement and storing it
                rs = ps.executeQuery();
                //executing the statement and getting the result set before storing it
                while (rs.next()) {
                    String usernameS = rs.getString("Username");
                    String passwordS = rs.getString("Password");
                    //getting the username and password from the result set
                    if(passwordS.equals(passwordL)){
                        System.out.println("Logged in");
                        loginScreen.dispose();
                        //checking if the password is right and if so it goes to the start screen
                        startScreen = new StartScreen();
                    } else {
                        recognition.setText("The password that you have entered is wrong");
                        //checking if the password is right and if not it puts this message out
                    }

                }

            } catch (SQLException d) {
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
        else if (e.getActionCommand().equals("Register")){
            String usernameL = username.getText();
            String passwordL = password.getText();
            try {
                String sql = "SELECT COUNT(*) FROM Login";
                ps2 = con.prepareStatement(sql);
                rs = ps2.executeQuery();
                int numberOfUsers = rs.getInt(1);
                numberOfUsers+=1;
                //checks how many users there are and adds 1 to give the new ID for the new registered user
                sql = "SELECT COUNT(*) FROM Login WHERE Username = \"";
                sql = sql + usernameL + "\"";
                ps2= con.prepareStatement(sql);
                rs = ps2.executeQuery();
                int checker = rs.getInt(1);
                if(checker > 0){
                    recognition.setText("The username you have entered is taken");
                }
                //checks if the username entered is already in use
                else if(usernameL == "Enter your username here" && passwordL == "Enter your password here") {
                    System.out.println("Tap on the boxes to register");
                } else if (usernameL == "Enter your username here" && passwordL == "") {
                    System.out.print("Please put something in the boxes");
                } else if(usernameL == "" && passwordL == "Enter your password here"){
                    System.out.println("Please put something in the boxes");
                }
                //else if's used to check whether the user has actually entered into both boxes
                else {
                    sql = "INSERT INTO Login(User,Username,Password) VALUES(?,?,?) ";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, numberOfUsers);
                    ps.setString(2, usernameL);
                    ps.setString(3, passwordL);
                    ps.execute();
                    recognition.setText("The account has been registered in the system");
                    //inserts the username and password into the database if conditions are met.
                }



            } catch (SQLException d) {
                System.out.println(d.toString());
            } finally {
                try {
                    rs.close();
                    ps2.close();
                    con.close();


                } catch (SQLException d) {
                    System.out.print(d.toString());
                }
            }
        }
    }


    }
