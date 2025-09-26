package game.gui;

import game.Fight;
import game.Game;
import game.InvalidCommandException;
import game.entities.characters.Mage;
import game.entities.characters.Rogue;
import game.entities.characters.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class FightUI extends JPanel implements ActionListener {

    Game gameM = Game.getInstance();
    BackgroundPanel characterPanel, enemyPanel;
    JButton abilitiesButton, attackButton;
    JPanel characterStatistics, enemyStatistics;
    GameUI parent;


    public FightUI(GameUI parent) {
        this.parent = parent;
    }

    public void fightEnemy() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        characterPanel = setCharacterPanel();
        assert characterPanel != null;
        characterPanel.setLayout(null);
        enemyPanel = new BackgroundPanel("src\\game\\pictures\\enemy.png");
        enemyPanel.setLayout(null);

        characterStatistics = new BackgroundPanel("src\\game\\pictures\\purpleTexture.jpg");
        enemyStatistics = new BackgroundPanel("src\\game\\pictures\\purpleTexture.jpg");

        JSplitPane character = new JSplitPane(JSplitPane.VERTICAL_SPLIT, characterPanel, characterStatistics);
        character.setDividerLocation(600);
        character.setEnabled(false);
        character.setDividerSize(0);
        JSplitPane enemy = new JSplitPane(JSplitPane.VERTICAL_SPLIT, enemyPanel, enemyStatistics);
        enemy.setDividerLocation(600);
        enemy.setEnabled(false);
        enemy.setDividerSize(0);
        JSplitPane horizontal_split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, character, enemy);
        horizontal_split.setEnabled(false);
        horizontal_split.setDividerSize(0);
        horizontal_split.setDividerLocation(500);
        this.add(horizontal_split, BorderLayout.CENTER);


        attackButton = new JButton("Attack");
        attackButton.setBounds(400, 0, 100, 50);
        attackButton.addActionListener(this);
        characterPanel.add(attackButton);

        abilitiesButton = new JButton("Abilities");
        abilitiesButton.setBounds(400, 50, 100, 50);
        abilitiesButton.addActionListener(this);
        characterPanel.add(abilitiesButton);

        updateStatistics(characterStatistics, enemyStatistics);

        characterPanel.revalidate();
        characterPanel.repaint();
        enemyPanel.revalidate();
        enemyPanel.repaint();
        this.repaint();
        this.revalidate();

        if (gameM.getMyCharacter().getCurrentHealth() <= 0) {
            gameM.getMyCharacter().setCurrentHealth(100);
            gameM.getMyCharacter().setCurrentManna(60);
            JOptionPane.showMessageDialog(null, "You have lost!");
            parent.finalPage();
        }
        if (Fight.getInstance().getEnemy().getCurrentHealth() <= 0) {
            JOptionPane.showMessageDialog(null, "You have won!");
            parent.startBattle();
            return;
        }

    }

    private void updateStatistics(JPanel characterStatistics, JPanel enemyStatistics) {
        characterStatistics.removeAll();
        enemyStatistics.removeAll();

        characterStatistics.setLayout(new GridLayout(2, 2));
        JLabel health = new JLabel("Health");
        JLabel manna = new JLabel("Manna");
        JLabel currentHealth = new JLabel(gameM.getMyCharacter().getCurrentHealth() + "");
        JLabel currentManna = new JLabel(gameM.getMyCharacter().getCurrentManna() + "");

        health.setForeground(Color.WHITE);
        manna.setForeground(Color.WHITE);
        health.setFont(new Font("Serif", Font.BOLD, 24));
        manna.setFont(new Font("Serif", Font.BOLD, 24));
        currentHealth.setFont(new Font("Serif", Font.BOLD, 24));
        currentManna.setFont(new Font("Serif", Font.BOLD, 24));
        currentHealth.setForeground(Color.WHITE);
        currentManna.setForeground(Color.WHITE);

        characterStatistics.add(health);
        characterStatistics.add(currentHealth);
        characterStatistics.add(manna);
        characterStatistics.add(currentManna);

        enemyStatistics.setLayout(new GridLayout(2, 2));
        JLabel enemyHealth = new JLabel("Health");
        JLabel enemyManna = new JLabel("Manna");
        JLabel enemyCurrentHealth = new JLabel(Fight.getInstance().getEnemy().getCurrentHealth() + "");
        JLabel enemyCurrentManna = new JLabel(Fight.getInstance().getEnemy().getCurrentManna() + "");

        enemyHealth.setForeground(Color.WHITE);
        enemyHealth.setFont(new Font("Serif", Font.BOLD, 24));
        enemyCurrentHealth.setFont(new Font("Serif", Font.BOLD, 24));
        enemyCurrentHealth.setForeground(Color.WHITE);
        enemyManna.setForeground(Color.WHITE);
        enemyManna.setFont(new Font("Serif", Font.BOLD, 24));
        enemyCurrentManna.setFont(new Font("Serif", Font.BOLD, 24));
        enemyCurrentManna.setForeground(Color.WHITE);

        enemyStatistics.add(enemyHealth);
        enemyStatistics.add(enemyCurrentHealth);
        enemyStatistics.add(enemyManna);
        enemyStatistics.add(enemyCurrentManna);

        characterStatistics.repaint();
        enemyStatistics.repaint();
        characterStatistics.revalidate();
        enemyStatistics.revalidate();
    }

    private BackgroundPanel setCharacterPanel() {
        if (gameM.getMyCharacter() instanceof Mage)
            return new BackgroundPanel("src\\game\\pictures\\mage.png");
        if (gameM.getMyCharacter() instanceof Warrior)
            return new BackgroundPanel("src\\game\\pictures\\warrior.png");
        if (gameM.getMyCharacter() instanceof Rogue)
            return new BackgroundPanel("src\\game\\pictures\\rogue.png");
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameM.getMyCharacter().getCurrentHealth() > 0 && Fight.getInstance().getEnemy().getCurrentHealth() > 0) {
            if (e.getSource() == attackButton) {
                Fight.getInstance().setCommand(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                updateStatistics(characterStatistics, enemyStatistics);
            }
            if (e.getSource() == abilitiesButton) {
                Fight.getInstance().setCommand(2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if (Fight.getInstance().getErrorHandler().getErrorMessage() != null) {
                    JOptionPane.showMessageDialog(null, Fight.getInstance().getErrorHandler().getErrorMessage());
                } else
                    parent.abilities();
            }
        }
        if (gameM.getMyCharacter().getCurrentHealth() <= 0) {
            gameM.getMyCharacter().setCurrentHealth(100);
            gameM.getMyCharacter().setCurrentManna(60);
            JOptionPane.showMessageDialog(null, "You have lost!");
            parent.finalPage();
        }
        if (Fight.getInstance().getEnemy().getCurrentHealth() <= 0) {
            JOptionPane.showMessageDialog(null, "You have won!");
            parent.startBattle();
        }
    }
}
