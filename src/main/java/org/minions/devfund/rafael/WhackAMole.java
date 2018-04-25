package org.minions.devfund.rafael;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Implements the Whack-A-Mole class.
 *
 * @author Rafael Alfaro
 * @version 1.0
 */
public class WhackAMole {
    // Game constants
    private static final char MOLE = 'M';
    private static final char WHACK = 'W';
    private static final char CELL = '*';
    private static final int SCORE = 10;

    //Scores
    private int score;
    private int molesLeft;
    private int attemptsLeft;

    //Battlefield
    private int rows;
    private int columns;
    private char[][] moleGrid;
    private static final int SIZE_BATTLEFIELD = 10;
    private static final int NUM_ATTEMPTS = 50;
    private static final int MOLES = 10;

    /**
     * Gets attemptsLeft variable.
     *
     * @return int attemptsLeft
     */
    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    /**
     * Gets Score variable.
     *
     * @return int score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets molesLeft variable.
     *
     * @return int molesLeft
     */
    public int getMolesLeft() {
        return molesLeft;
    }


    /**
     * Constructor of the class.
     *
     * @param numAttempts   Whacks allowed
     * @param gridDimension Grid Dimension
     */
    public WhackAMole(int numAttempts, int gridDimension) {
        this.score = 0;
        this.molesLeft = 0;
        this.attemptsLeft = numAttempts;

        rows = gridDimension;
        columns = gridDimension;
        this.moleGrid = new char[rows][columns];

        //Battlefield
        initBattlefield();
    }

    /**
     * Initializes the Battlefield with the value of the CELL constant.
     */
    private void initBattlefield() {
        for (char[] r : moleGrid) {
            Arrays.fill(r, CELL);
        }
    }

    /**
     * Creates the Whack A Mole Battlefield and put the moles in the battlefield.
     *
     * @param moles Moles number
     */
    public void drawField(int moles) {
        Random random = new Random();
        int cells = rows * columns;
        if (moles > cells) {
            moles = cells;
        }
        for (int index = 0; index < moles; index++) {
            boolean isAddedMole = false;
            while (!isAddedMole) {
                isAddedMole = place(random.nextInt(rows), random.nextInt(columns));
            }
        }
    }

    /**
     * Puts a mole at that location and updates number of moles left.
     *
     * @param x row
     * @param y column
     * @return Return true if it is possible to place in the battlefield
     */
    private boolean place(int x, int y) {
        if (moleGrid[x][y] == MOLE) {
            return false;
        }
        moleGrid[x][y] = MOLE;
        molesLeft += 1;
        return true;
    }

    /**
     * Takes a whack at that location (x,y) and the variables score and molesLeft are updated.
     *
     * @param x row
     * @param y column
     */
    public void whack(int x, int y) {
        if (moleGrid[x][y] == MOLE) {
            score += SCORE;
            molesLeft--;
            moleGrid[x][y] = WHACK;
        }
        attemptsLeft--;
    }

    /**
     * Prints the grid without showing the moles.
     */

    public void printGridToUser() {
        for (char[] row : moleGrid) {
            for (char cell : row) {
                if (cell != WHACK) {
                    System.out.print(CELL);
                } else {
                    System.out.print(WHACK);
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the grid with showing the moles.
     */
    public void printGrid() {
        for (char[] row : moleGrid) {
            for (char cell : row) {
                if (cell == MOLE) {
                    System.out.print(MOLE);
                } else if (cell == WHACK) {
                    System.out.print(WHACK);
                } else {
                    System.out.print(CELL);
                }
            }
            System.out.println();
        }
    }

    /**
     * Main Program.
     *
     * @param args No arguments are used in this program
     */
    public static void main(final String[] args) {

        WhackAMole game = new WhackAMole(NUM_ATTEMPTS, SIZE_BATTLEFIELD);

        game.drawField(MOLES);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the WackAMole game!");

        try {
            do {
                System.out.println("Attempts left: " + game.getAttemptsLeft());
                System.out.println("Score: " + game.getScore());
                game.printGridToUser();
                System.out.println("Select a cell (x,y)? ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                if (x == -1 && y == -1) {
                    game.printGrid();
                    break;
                }

                game.whack(x, y);
            } while (game.getAttemptsLeft() > 0 && game.getMolesLeft() > 0);

        } catch (InputMismatchException exception) {
            System.out.println("Integers only, please.!");
        }
        System.out.println("Game over...Bye!");
    }
}
