package game.gui;

import game.InvalidCommandException;

import javax.swing.*;

public class GameUI extends JFrame {
    AuthenticationUI auth;
    GridUI battle = new GridUI(this);
    FightUI fight = new FightUI(this);

    public GameUI() {
        this.setIconImage(new ImageIcon("src\\game\\pictures\\purpleTexture.jpg").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("League Of Warriors");
        auth = new AuthenticationUI(this);
        this.setContentPane(auth);
    }

    public void startBattle() {
        battle.setFrame();
        this.revalidate();
        this.repaint();
        this.setContentPane(battle);
        this.revalidate();
        this.repaint();
        System.out.println("Starting battle");
    }


    public void startFight() throws InvalidCommandException, InterruptedException {
        fight.fightEnemy();
        this.revalidate();
        this.repaint();
        this.setContentPane(fight);
        this.revalidate();
        this.repaint();
        System.out.println("Starting fight");
    }

    public void abilities() {
        AbilitiesUI abilitiesUI = new AbilitiesUI(this);
        this.revalidate();
        this.repaint();
        this.setContentPane(abilitiesUI);
        this.revalidate();
    }

    public void finalPage() {
        FinalUI finalPage = new FinalUI(this);
        this.setSize(500 , 800);
        this.setContentPane(finalPage);
        this.revalidate();
        this.repaint();
    }

    public static void main(String[] args) {
        GameUI game = new GameUI();
    }
}
