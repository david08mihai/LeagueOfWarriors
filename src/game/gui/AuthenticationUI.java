package game.gui;

import game.Game;
import game.entities.Characters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AuthenticationUI extends BackgroundPanel implements ActionListener {

    JLabel loginMessage, usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton resetButton;
    JCheckBox showPassword;
    JButton selectButton;
    JList<String> charactersList;
    GameUI parent;
    Game game;

    public AuthenticationUI(GameUI parent) {
        super("src\\game\\pictures\\background.jpg");
        this.parent = parent;
        this.setSize(1000, 800);
        this.setLayout(null);
        this.setVisible(true);

        this.setLayout(null);


        this.loginMessage = new JLabel("League of Warriors");
        loginMessage.setBounds(400, 50, 250, 100);
        loginMessage.setFont(new Font("Arial", Font.BOLD, 25));
        loginMessage.setForeground(Color.BLACK);
        loginMessage.setForeground(Color.WHITE);

        this.usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(200, 255, 250, 100);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setForeground(Color.WHITE);

        this.passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200, 300, 250, 100);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);

        this.usernameField = new JTextField();
        usernameField.setBounds(300, 290, 200, 30);

        this.passwordField = new JPasswordField();
        passwordField.setBounds(300, 335, 200, 30);

        this.loginButton = new JButton("Login");
        loginButton.setBounds(300, 410, 100, 30);
        loginButton.addActionListener(this);

        this.resetButton = new JButton("Reset");
        resetButton.setBounds(400, 410, 100, 30);
        resetButton.addActionListener(this);

        this.showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(300, 370, 150, 30);
        showPassword.addActionListener(this);

        this.add(loginMessage);
        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(usernameField);
        this.add(passwordField);
        this.add(loginButton);
        this.add(resetButton);
        this.add(showPassword);

        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            game = Game.getInstance();

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            boolean isAuthenticated;
            try {
                isAuthenticated = game.run(username, password);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if (isAuthenticated) {
                JOptionPane.showMessageDialog(null, "Succes!");
                selectCharacter();
            } else {
                JOptionPane.showMessageDialog(null, "Fail");
                usernameField.setText("");
                passwordField.setText("");
            }
        }
        if (e.getSource() == resetButton) {
            usernameField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if(showPassword.isSelected()){
                    passwordField.setEchoChar((char)0);
            } else
                passwordField.setEchoChar('*');
        }
        if (e.getSource() == selectButton) {
            if (charactersList.getSelectedIndex() != -1) {
                game.setMyCharacter(game.getMyAccount().characters.get(charactersList.getSelectedIndex()));
                this.removeAll();
                this.setVisible(false);
                parent.startBattle();
            }
        }

    }

    public void selectCharacter() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        DefaultListModel<String> characters = new DefaultListModel<>();

        for (Characters c : game.getMyAccount().characters)
            characters.addElement(c.toString());

        charactersList = new JList<>(characters);
        charactersList.setBounds(200, 300, 200, 200);

        selectButton = new JButton("Select");
        selectButton.setBounds(400, 300, 100, 30);
        selectButton.addActionListener(this);

        this.add(selectButton);
        this.add(charactersList);
        this.setVisible(true);

    }
}
