package game.gui;

import game.Fight;
import game.Game;
import game.InvalidCommandException;
import game.spells.Earth;
import game.spells.Fire;
import game.spells.Ice;
import game.spells.Spell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AbilitiesUI extends JPanel implements ActionListener {
    GameUI parent;
    Game gameM = Game.getInstance();
    int numberOfAbilities = gameM.getMyCharacter().getSpells().size();
    ArrayList<JButton> buttons = new ArrayList<>();

    public AbilitiesUI(GameUI parent) {
        this.parent = parent;

        this.setLayout(new GridLayout(1, numberOfAbilities));

        for (int i = 0; i < numberOfAbilities; i++) {
            BackgroundPanel abilityPicture = assertAbilityPicutre(i);
            JPanel abilityNumbers = new JPanel();
            assert abilityPicture != null;
            abilityPicture.setLayout(null);
            JButton select = new JButton("Select");
            buttons.add(select);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setBounds(0, 0, 100, 100);
            abilityPicture.add(buttons.get(i));
            abilityNumbers.setLayout(new GridLayout(3, 3));
            Spell spell = gameM.getMyCharacter().getSpells().get(i);
            abilityNumbers.add(new JLabel("Name"));
            abilityNumbers.add(new JLabel(spell.toString()));
            abilityNumbers.add(new JLabel("Mana Cost"));
            abilityNumbers.add(new JLabel(String.valueOf(spell.getMannaCost())));
            abilityNumbers.add(new JLabel("Damage"));
            abilityNumbers.add(new JLabel(String.valueOf(spell.getGivenDamage())));

            JSplitPane horizontal_split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, abilityPicture, abilityNumbers);
            horizontal_split.setEnabled(false);
            horizontal_split.setDividerSize(0);
            horizontal_split.setDividerLocation(700);
            this.add(horizontal_split);
        }
    }

    private BackgroundPanel assertAbilityPicutre(int i) {
        if (gameM.getMyCharacter().getSpells().get(i) instanceof Ice)
            return new BackgroundPanel("src\\game\\pictures\\ice.png");
        if (gameM.getMyCharacter().getSpells().get(i) instanceof Fire)
            return new BackgroundPanel("src\\game\\pictures\\fire.png");
        if (gameM.getMyCharacter().getSpells().get(i) instanceof Earth)
            return new BackgroundPanel("src\\game\\pictures\\earth.png");
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < numberOfAbilities; i++) {
            if (e.getSource() == buttons.get(i)) {
                System.out.println(i);
                Fight.getInstance().setCommand(i);

                Timer timer = new Timer(100, _ -> {
                    if (Fight.getInstance().getErrorHandler().isError()) {
                        JOptionPane.showMessageDialog(null, Fight.getInstance().getErrorHandler().getErrorMessage());
                    } else {
                        try {
                            if (Fight.getInstance().getEnemy().getCurrentHealth() > 0 &&
                                    Game.getInstance().getMyCharacter().getCurrentHealth() > 0)
                                parent.startFight();
                            else
                                if (Fight.getInstance().getEnemy().getCurrentHealth() <= 0) {
                                    JOptionPane.showMessageDialog(null, "You have won!");
                                    parent.startBattle();
                                    this.removeAll();
                                    return;
                                }
                                if (Game.getInstance().getMyCharacter().getCurrentHealth() <= 0) {
                                    Game.getInstance().getMyCharacter().setCurrentHealth(100);
                                    Game.getInstance().getMyCharacter().setCurrentManna(60);
                                    JOptionPane.showMessageDialog(null, "You have lost!");
                                    this.removeAll();
                                    parent.finalPage();
                                }
                        } catch (InvalidCommandException | InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                timer.setRepeats(false); // Se execută o singură dată
                timer.start();
            }
        }
    }
}
