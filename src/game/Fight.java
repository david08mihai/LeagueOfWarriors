package game;

import game.entities.Characters;
import game.entities.Enemy;
import game.entities.Entity;
import game.gui.ErrorHandler;
import game.spells.Spell;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Fight {

    private Enemy enemy;

    private static Fight fight;
    private int command = -1;
    private final Object lock = new Object();
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    private ErrorHandler errorHandler;

    public void setCommand(int command) {
        synchronized (lock) {
            this.command = command;
            lock.notify();
        }
    }

    private int getCommand() {
        synchronized (lock) {
            while (command == -1) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread interrupted", e);
                }
            }
            int cmd = command;
            command = -1;
            return cmd;
        }
    }


    private Fight() {
        enemy = null;
    }

    public static Fight getInstance() {
        if (fight == null) {
            synchronized (Fight.class) {
                if (fight == null) {
                    fight = new Fight();
                }
            }
        }
        return fight;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public static void attack(Entity attacker, Entity defender) {
        defender.recieveDamage(attacker.getDamage());
    }

    //check whether the spell is in the arraylist of the attacker and if it is, the method will
    //proceed the attack using it and remove it from the spells
    public void checkAndRemove(int numberOfSpell, Entity attacker, Entity defender) throws InvalidCommandException {
        boolean correct = false;
        Spell spell = null;


        if (attacker.getSpells().get(numberOfSpell).getMannaCost() <= attacker.getCurrentManna()) {
            spell = attacker.getSpells().get(numberOfSpell);
            attacker.getSpells().remove(numberOfSpell);
            correct = true;
        }
        //if it is in range and i have enough manna
        if (correct) {
            defender.accept(spell);
            attacker.setCurrentManna((int)(attacker.getCurrentManna() - spell.getMannaCost()));
            errorHandler = new ErrorHandler(false, null);
        } else { // if it s not or I don't enough manna for that one, I allow the player to select the proper spell
            while (!correct) {
                errorHandler = new ErrorHandler(true, "You have to pick again!");
                    int number;
                    number = getCommand();
                    Spell whatToUse;


                    if (attacker.getSpells().get(number).getMannaCost() <= attacker.getCurrentManna()) {
                        whatToUse = attacker.getSpells().get(number);
                        attacker.getSpells().remove(number);
                        errorHandler = new ErrorHandler(false, null);
                        defender.accept(whatToUse);
                        attacker.setCurrentManna((int)(attacker.getCurrentManna() - whatToUse.getMannaCost()));
                        correct = true;

                    }
            }
        }
    }


    public void abilities(Entity attacker, Entity defender) throws InvalidCommandException {
        int value = 0;
        boolean commandTrue = true;
        boolean success = false;
                if (attacker instanceof Characters) {
                    int i = 1;
                    for (Spell s : attacker.getSpells()) {
                        System.out.print(i + " -> ");
                        s.display();
                        i++;
                    }
                    System.out.println("Select an ability!");
                    value = getCommand();
                } else if (attacker instanceof Enemy) {
                    for (int i = 0; i < attacker.getSpells().size(); ++i) {
                        if (attacker.getCurrentManna() >= attacker.getSpells().get(i).getMannaCost()) {
                            value = i;
                            break;
                        }
                    }
                }// if the value is correct, i will check whether i have or no manna or the ability
        checkAndRemove(value, attacker, defender);

    }

    public void fight(Characters myCharacter) throws InterruptedException, InvalidCommandException {
        this.enemy = new Enemy();
        int isMyTurn = 0;
        System.out.println(myCharacter.getCurrentHealth() + " " + enemy.getCurrentHealth());
        //while at least one is alive
        while (myCharacter.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0) {
            if (isMyTurn % 2 == 0) {
                int minimumMannaCost = 100;
                int command = getCommand();
                if (command == 1) {
                    errorHandler = new ErrorHandler(true, null);
                    attack(myCharacter, enemy);
//                    connectionToFront(new ErrorHandler(true, null));
                    System.out.println(errorHandler.getErrorMessage());
                } else if (command == 2) {
                    if (myCharacter.getSpells().isEmpty()) {
                        errorHandler = new ErrorHandler(false, "You don't have abilities anymore, an attack will proceed!");
                        attack(myCharacter, enemy);
                    } else { // i check minimumMannaCost
                        for (Spell spell : myCharacter.getSpells())
                            if (spell.getMannaCost() <= minimumMannaCost) {
                                minimumMannaCost = (int) spell.getMannaCost();
                            }// i have enough manna for at least one spell
                        if (myCharacter.getCurrentManna() >= minimumMannaCost) {
                            errorHandler = new ErrorHandler(true, null);
                            abilities(myCharacter, enemy);
                        } else { // i dont have enough manna
                            errorHandler = new ErrorHandler(false, "You don't have enough manna, an attack will proceed!");
                            attack(myCharacter, enemy);
//                            connectionToFront(new ErrorHandler(false, "You don't have enough manna"));
                        }
                    }
                }
            } else { //pc turn's
                System.out.println("Enemy's turn");
                // pc turn's, I verify if the pc can execute an ability
                boolean hasEnoughManna = false;
                for (Spell spell : enemy.getSpells())
                    if (spell.getMannaCost() <= enemy.getCurrentManna()) {
                        hasEnoughManna = true;
                        break;
                    }

                if (hasEnoughManna) {
                    abilities(enemy, myCharacter);
                } else {
                    attack(enemy, myCharacter);
                }
//                entitiesStatus(enemy);
            }
            isMyTurn++;
        }
        if (enemy.getCurrentHealth() <= 0)
            System.out.println("The enemy was defeted!");
    }


//    public void connectionToFront(ErrorHandler errorHandler) {
//        return errorHandler;
//    }
}

//    public static void entitiesStatus(Enemy enemy) {
//        System.out.println("╔═══════════════════════════════════════════════════╗");
//        System.out.println("║       Entities       ║    Health    ║    Manna    ║");
//        System.out.println("╠══════════════════════╦══════════════╦═════════════╣");
//        System.out.printf("║ %-20s ║ %12d ║ %11d ║%n",
//                myCharacter.getCname(),
//                myCharacter.getCurrentHealth(),
//                myCharacter.getCurrentManna());
//        System.out.println("╠══════════════════════╬══════════════╬═════════════╣");
//        System.out.printf("║ %-20s ║ %12d ║ %11d ║%n",
//                "Enemy",
//                enemy.getCurrentHealth(),
//                enemy.getCurrentManna());
//        System.out.println("╚══════════════════════╩══════════════╩═════════════╝");
//    }