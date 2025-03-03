package game.gui;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridUI extends JPanel implements ActionListener {
    JPanel movements;
    JPanel details;
    JPanel game;
    JSplitPane verticalSplit;
    JSplitPane mainSplit;
    Game gameM = Game.getInstance();
    Grid grid = gameM.getGrid();
    Cell nextMove;
    GameUI parent;
    JButton northButton, southButton, eastButton, westButton;
    JLabel info = new JLabel();

    public GridUI(GameUI parent) {
        this.parent = parent;
    }

    public void setFrame() {
        this.removeAll();
        this.setLayout(new BorderLayout());
        movements = new BackgroundPanel("src\\game\\pictures\\movements.png");
        movements.setLayout(new BorderLayout());

        details = new BackgroundPanel("src\\game\\pictures\\purple.jpg");

        game = new JPanel();

        verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, movements, details);
        verticalSplit.setEnabled(false);
        verticalSplit.setDividerSize(0);
        verticalSplit.setDividerLocation(375);

        mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, verticalSplit, game);
        mainSplit.setDividerLocation(375);

        mainSplit.setEnabled(false);
        mainSplit.setDividerSize(0);
        this.add(mainSplit, BorderLayout.CENTER);

        this.repaint();
        this.setVisible(true);

        addMovementsButtons();
        updateCharacterDetails();
        updateGrid();
    }


    private void updateGrid() {
        int x = grid.size();
        int y = grid.getFirst().size();

        game.removeAll();
        game.setLayout(new GridLayout(x, y));
        JLabel label = new JLabel();
        ImageIcon originalIconP = new ImageIcon("src\\game\\pictures\\P.jpg");
        Image scaledImageP = originalIconP.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIconP = new ImageIcon(scaledImageP);
        ImageIcon originalIconV = new ImageIcon("src\\game\\pictures\\V.png");
        Image scaledImageV = originalIconV.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIconV = new ImageIcon(scaledImageV);
        ImageIcon originalIconN = new ImageIcon("src\\game\\pictures\\N.jpg");
        Image scaledImageN = originalIconN.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIconN = new ImageIcon(scaledImageN);
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                if (grid.get(i).get(j).toString().equalsIgnoreCase("P")) {
                    label = new JLabel(scaledIconP);
                }
                if (grid.get(i).get(j).toString().equalsIgnoreCase("V")) {
                    label = new JLabel(scaledIconV);
                }
                if (grid.get(i).get(j).toString().equalsIgnoreCase("N")) {
                    label = new JLabel(scaledIconN);
                }
                game.add(label);
            }
        }

        game.repaint();
        game.revalidate();
    }

    public void addMovementsButtons() {
        northButton = new JButton("NORTH");
        movements.add(northButton, BorderLayout.NORTH);
        northButton.addActionListener(this);

        southButton = new JButton("SOUTH");
        movements.add(southButton, BorderLayout.SOUTH);
        southButton.addActionListener(this);

        eastButton = new JButton("EAST");
        movements.add(eastButton, BorderLayout.EAST);
        eastButton.addActionListener(this);

        westButton = new JButton("WEST");
        movements.add(westButton, BorderLayout.WEST);
        westButton.addActionListener(this);

        JButton but = new JButton("");
        movements.add(info, BorderLayout.CENTER);
    }

    public void updateCharacterDetails() {
        //details frame
        details.removeAll();
        details.setLayout(new GridLayout(4, 2));

        JLabel level = new JLabel("Level", SwingConstants.CENTER);
        level.setForeground(Color.WHITE);
        level.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel currentLevel = new JLabel();
        currentLevel.setFont(new Font("Serif", Font.BOLD, 24));
        currentLevel.setForeground(Color.WHITE);
        currentLevel.setText(String.valueOf(gameM.getMyCharacter().getLevel()));

        JLabel experience = new JLabel("Experience", SwingConstants.CENTER);
        experience.setForeground(Color.WHITE);
        experience.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel currentExperience = new JLabel();
        currentExperience.setForeground(Color.WHITE);
        currentExperience.setText(String.valueOf(gameM.getMyCharacter().getExperience()));
        currentExperience.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel health = new JLabel("Health", SwingConstants.CENTER);
        health.setForeground(Color.WHITE);
        health.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel currentHealth = new JLabel();
        currentHealth.setForeground(Color.WHITE);
        currentHealth.setFont(new Font("Serif", Font.BOLD, 24));
        currentHealth.setText(String.valueOf(gameM.getMyCharacter().getCurrentHealth()));

        JLabel manna = new JLabel("Manna", SwingConstants.CENTER);
        manna.setFont(new Font("Serif", Font.BOLD, 24));
        manna.setForeground(Color.WHITE);

        JLabel currentManna = new JLabel();
        currentManna.setFont(new Font("Serif", Font.BOLD, 24));
        currentManna.setForeground(Color.WHITE);
        currentManna.setText(String.valueOf(gameM.getMyCharacter().getCurrentManna()));



        details.add(level);
        details.add(currentLevel);
        details.add(experience);
        details.add(currentExperience);
        details.add(health);
        details.add(currentHealth);
        details.add(manna);
        details.add(currentManna);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == northButton || e.getSource() == southButton || e.getSource() == eastButton || e.getSource() == westButton) {
            try {
                if (e.getSource() == northButton) {
                    nextMove = grid.goNorth();
                } else if (e.getSource() == southButton) {
                    nextMove = grid.goSouth();
                } else if (e.getSource() == eastButton) {
                    nextMove = grid.goEast();
                } else if (e.getSource() == westButton) {
                    nextMove = grid.goWest();
                }


                if (nextMove.type != null) {
                    gameM.getStatus(nextMove.type);
                    handleCellAction(nextMove.type);
                }
            } catch (ImpossibleMoveException ex) {
                System.out.println(ex.getMessage());
            } catch (InvalidCommandException | InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    private void handleCellAction(CellEntityType type) throws InvalidCommandException, InterruptedException {
        switch (type) {
            case PORTAL -> {
                JOptionPane.showMessageDialog(null, "You have found the portal!");
                gameM.getStatus(type);
                grid = Grid.getInstance();
                movements.repaint();
                movements.revalidate();
                updateGrid();
            }
            case ENEMY -> {
                JOptionPane.showMessageDialog(null, "You are going to fight an enemy!");
                updatePanels();
                parent.startFight();
            }
            case SANCTUARY -> {
                JOptionPane.showMessageDialog(null, "You have reached the sanctuary!");
                gameM.getStatus(CellEntityType.SANCTUARY);
                updateGrid();
                updateCharacterDetails();
            }
            default -> updateGrid();
        }
    }
    private void updatePanels() {
        movements.removeAll();
        game.removeAll();
        details.removeAll();
        game.repaint();
        movements.repaint();
        details.repaint();
        movements.revalidate();
        game.revalidate();
        details.revalidate();
        removeAll();
        repaint();
        revalidate();
    }

    public static void showAutoDismissMessage(String message, int duration) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JLabel(message, SwingConstants.CENTER), BorderLayout.CENTER);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(null);

        // Set a timer to close the dialog after the specified duration
        Timer timer = new Timer(duration, e -> dialog.dispose());
        timer.setRepeats(false); // Ensure it only runs once
        timer.start();

        dialog.setVisible(true);
    }
}

