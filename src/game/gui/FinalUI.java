package game.gui;

import game.Game;
import game.entities.characters.Mage;
import game.entities.characters.Rogue;
import game.entities.characters.Warrior;

import javax.swing.*;
import java.awt.*;

public class FinalUI extends JPanel {
    JButton exit;
    JButton newGame;
    JPanel character;
    String typeS;
    public FinalUI(GameUI gameUI) {
        if (Game.getInstance().getMyCharacter() instanceof Mage) {
            character = new BackgroundPanel("src\\game\\pictures\\mage.png");
            typeS = "Mage";
        }
        if (Game.getInstance().getMyCharacter() instanceof Rogue) {
            character = new BackgroundPanel("src\\game\\pictures\\rogue.png");
            typeS = "Rogue";
        }
        if (Game.getInstance().getMyCharacter() instanceof Warrior) {
            character = new BackgroundPanel("src\\game\\pictures\\warrior.png");
            typeS = "Warrior";
        }

        JPanel statistics = new BackgroundPanel("src\\game\\pictures\\purpleTexture.jpg");
        statistics.setLayout(new GridLayout(3, 2));

        JLabel name = new JLabel("Name");
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel currentName = new JLabel(Game.getInstance().getMyCharacter().getCname());
        currentName.setForeground(Color.WHITE);
        currentName.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel type = new JLabel("Type");
        type.setForeground(Color.WHITE);
        type.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel currentType = new JLabel(typeS);
        currentType.setForeground(Color.WHITE);
        currentType.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel experience = new JLabel("Experience");
        experience.setForeground(Color.WHITE);
        experience.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel currentExperience = new JLabel(String.valueOf(Game.getInstance().getMyCharacter().getExperience()));
        currentExperience.setForeground(Color.WHITE);
        currentExperience.setFont(new Font("Serif", Font.BOLD, 24));

        exit = new JButton("Exit");
        exit.setBounds(0, 50, 100, 50);
        exit.addActionListener(e -> {
            System.exit(0);
        });

        character.setLayout(null);
        newGame = new JButton("New Game");
        newGame.setBounds(0, 0, 100, 50);
        newGame.addActionListener(e -> {
            Game.getInstance().getMyCharacter().setCurrentHealth(100);
            Game.getInstance().getMyCharacter().setCurrentManna(60);
            Game.getInstance().getMyCharacter().abilitiesRegenerate();
            new GameUI();
            gameUI.setVisible(false);
        });

        statistics.add(name);
        statistics.add(currentName);
        statistics.add(type);
        statistics.add(currentType);
        statistics.add(experience);
        statistics.add(currentExperience);
        character.add(exit);
        character.add(newGame);


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, character, statistics);
        splitPane.setEnabled(false);
        splitPane.setDividerSize(0);
        splitPane.setDividerLocation(550);
        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);
        statistics.revalidate();
        statistics.repaint();
        splitPane.revalidate();
        splitPane.repaint();
        this.repaint();
        this.revalidate();

    }


}
