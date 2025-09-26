//package game;
//
//import java.io.IOException;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Test {
//    public static void main(String[] args) throws IOException, InterruptedException, InvalidCommandException {
//        while (true) {
//            Game game = Game.getInstance();
//            boolean gameOver = true;
//            game.run();
//            Grid.generateGridTest();
//            Grid.getInstance().printGrid();
//            while (Game.getMyCharacter().getCurrentHealth() > 0) {
//                boolean validCommand = true;
//                try {
//                    Scanner input = new Scanner(System.in);
//                    String move = input.nextLine().trim();
//                    if (move.equalsIgnoreCase("W")) {
//                        Cell nextMove = Grid.getInstance().goNorth();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (Game.getMyCharacter().getCurrentHealth() > 0)
//                            Grid.getInstance().printGrid();
//                        else
//                            break;
//                    } else if (move.equalsIgnoreCase("S")) {
//                        Cell nextMove = Grid.getInstance().goSouth();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (Game.getMyCharacter().getCurrentHealth() > 0)
//                            Grid.getInstance().printGrid();
//                        else
//                            break;
//                    } else if (move.equalsIgnoreCase("A")) {
//                        Cell nextMove = Grid.getInstance().goEast();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (Game.getMyCharacter().getCurrentHealth() > 0)
//                            Grid.getInstance().printGrid();
//                        else
//                            break;
//                    } else if (move.equalsIgnoreCase("D")) {
//                        Cell nextMove = Grid.getInstance().goWest();
//                        game.getStatus(nextMove);
//                        System.out.println();
//                        if (Game.getMyCharacter().getCurrentHealth() > 0)
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
//}
