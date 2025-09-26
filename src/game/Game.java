package game;

import game.accounts.Account;
import game.accounts.JsonInput;
import game.entities.Characters;
import game.entities.Enemy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Account myAccount;
    private Enemy enemy;
    private ArrayList<Account> accounts;
    private Characters myCharacter;
    private static Game game;





    private Game() {
        try {
            accounts = JsonInput.deserializeAccounts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myAccount = null;
        enemy = null;
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public void setMyAccount(Account myAccount) {
        this.myAccount = myAccount;
    }

    public Grid getGrid() {
        return Grid.generateGrid(new Random().nextInt(5) + 5, new Random().nextInt(5) + 5);
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }


    public Characters getMyCharacter() {
        return myCharacter;
    }

    public void setMyCharacter(Characters myCharacter) {
        this.myCharacter = myCharacter;
    }

    public boolean run(String email, String password) throws IOException {
        boolean isAuthenticated = false;
        for (Account a : accounts) {
            if (a.information.getCredentials().getMail().equalsIgnoreCase(email) && a.information.getCredentials().getPassword().equals(password)) {
                myAccount = a;
                isAuthenticated = true;
            }
        }
        if (myAccount == null) {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }


    public void getStatus(CellEntityType nextMove) {
        switch (nextMove) {

            case SANCTUARY -> {
                System.out.println("You have arrived in a SANCTUARY!");
                myCharacter.healthRegenerate(100);
                myCharacter.manaRegenerate(20);
            }

            case ENEMY -> {
                System.out.println("You are going to fight an enemy.");

                new Thread(() -> {
                    try {
                        Fight.getInstance().fight(myCharacter);
                        if (myCharacter.getCurrentHealth() > 0) {
                            myCharacter.healthRegenerate(50);
                            myCharacter.manaRegenerate(30);
                            myCharacter.abilitiesRegenerate();
                            myCharacter.setExperience(myCharacter.getExperience() + 1);
                            myCharacter.experienceToLevel();
                        }// Metoda care conține bucla backend-ului
                    } catch (InterruptedException | InvalidCommandException e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            }

            case PORTAL -> {
                System.out.println("You have found the portal! ");
                System.out.println("The new map is being generated, please wait...");
                Grid.generateGrid(new Random().nextInt(5) + 5, new Random().nextInt(5) + 5);
                myCharacter.healthRegenerate(100);
                myCharacter.manaRegenerate(10);
            }

        }
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        while (true) {
//            game = getInstance();
//            boolean gameOver = true;
//            game.run();
//            Grid.getInstance().setCharacter(Game.getInstance().getMyCharacter());
//            Grid.getInstance().printGrid();
//            while (Game.getInstance().getMyCharacter().getCurrentHealth() > 0) {
//                boolean validCommand = true;
//                try {
//                    Scanner input = new Scanner(System.in);
//                    String move = input.nextLine().trim();
//                    if (move.equalsIgnoreCase("W")) {
//                            Cell nextMove = Grid.getInstance().goNorth();
//                            game.getStatus(nextMove);
//                            System.out.println();
//                            if (myCharacter.getCurrentHealth() > 0)
//                                Grid.getInstance().printGrid();
//                            else
//                                break;
//                    } else if (move.equalsIgnoreCase("S")) {
//                        Cell nextMove = Grid.getInstance().goSouth();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (myCharacter.getCurrentHealth() > 0)
//                            Grid.getInstance().printGrid();
//                        else
//                            break;
//                    } else if (move.equalsIgnoreCase("A")) {
//                        Cell nextMove = Grid.getInstance().goEast();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (myCharacter.getCurrentHealth() > 0)
//                            Grid.getInstance().printGrid();
//                        else
//                            break;
//                    } else if (move.equalsIgnoreCase("D")) {
//                        Cell nextMove = Grid.getInstance().goWest();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (myCharacter.getCurrentHealth() > 0)
//                            Grid.getInstance().printGrid();
//                        else
//                            break;
//                    } else if (move.equalsIgnoreCase("Q")) {
//                        gameOver = false;
//                        break;
//                    } else {
//                        System.out.println("Invalid move! Try again!");
//                    }
//                } catch (ImpossibleMoveException | InputMismatchException | InterruptedException e) {
//                    System.out.println(e.getMessage());
//                    System.out.println("Try again!");
//                }
//            }
//
//            if (gameOver)
//                System.out.println("GAME OVER");
//        }
//    }
}
