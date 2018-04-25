package org.minions.devfund.ronald;

/**
 * Squarelotron.
 *
 * @author bsronald on 4/19/18.
 */
public class Squarelotron {


    private int[][] squarelotron;
    private int size;

    /**
     * Retrieve Squarelotron.
     *
     * @return matrix
     */
    public int[][] getSquarelotron() {
        int[][] squarelotronCopy = copySquarelotron();
        return squarelotronCopy;
    }

    /**
     * Copy Squarelotron 2 dimensional array.
     *
     * @return copy of squareletron
     */
    private int[][] copySquarelotron() {
        int[][] deepCopy = new int[size][size];
        for (int i = 0; i < squarelotron.length; i++) {
            for (int j = 0; j < squarelotron[i].length; j++) {
                deepCopy[i][j] = squarelotron[i][j];
            }
        }

        return deepCopy;
    }

    /**
     * Retrieve matrix size.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Constructor.
     *
     * @param n matrix size
     */
    Squarelotron(int n) {
        squarelotron = new int[n][n];
        size = n;
        fillsMatrix();
    }

    /**
     * Clone Squarelotron.
     *
     * @param entity another Squarelotron
     */
    Squarelotron(final Squarelotron entity) {
        this.size = entity.size;
        this.squarelotron = new int[this.size][this.size];
        int len = entity.squarelotron.length;
         for (int i = 0; i < len; i++) {
             for (int j = 0; j < len; j++) {
                 this.squarelotron[i][j] = entity.squarelotron[i][j];
             }
         }
    }

    /**
     * Fill Matrix with consecutive numbers.
     */
    private void fillsMatrix() {
        int value = 1;

        for (int i = 0; i < squarelotron.length; i++) {
            for (int j = 0; j < squarelotron[i].length; j++) {
                squarelotron[i][j] = value;
                value++;
            }
        }
    }

    /**
     * Upside down Flip matrix values.
     *
     * @param ring number to upside down
     * @return new Squarelotron()
     */
    public Squarelotron upsideDownFlip(int ring) {

        if (ring > getNumberOfRings() || ring < 0) {
            throw new IllegalArgumentException("Just allowed numbers between 1 and "
                    + String.valueOf(getNumberOfRings()));
        }

        int first = ring - 1;
        int last = size - ring;
        Squarelotron matrix = new Squarelotron(this);

        for (int i = 0; i < matrix.squarelotron.length / 2; i++) {
            for (int j = 0; j < matrix.squarelotron.length; j++) {
                if (i == first || i == last) {
                    if (j >= first && j <= last) {
                        matrix.squarelotron[i][j] = matrix.squarelotron[size - 1 - i][j];
                        matrix.squarelotron[size - 1 - i][j] = this.squarelotron[i][j];
                    }
                } else if (i > first && i < last && (j == first || j == last)) {
//                    if (j == first || j == last) {
                        matrix.squarelotron[i][j] = matrix.squarelotron[size - 1 - i][j];
                        matrix.squarelotron[size - 1 - i][j] = this.squarelotron[i][j];
//                    }
                }
            }
        }

        return matrix;
    }

    /**
     * Change value through main diagonal.
     *
     * @param ring value
     * @return new Squaletron entity
     */
    Squarelotron mainDiagonalFlip(int ring) {

        if (ring > getNumberOfRings() || ring < 0) {
            throw new IllegalArgumentException("Just allowed numbers between 1 and "
                    + String.valueOf(getNumberOfRings()));
        }

        int first = ring - 1; //  0, 1, 2
        int last = size - ring; // 3, 2, 1
        Squarelotron matrix = new Squarelotron(this);

        for (int i = 0; i < matrix.squarelotron.length; i++) {
            for (int j = i + 1; j < matrix.squarelotron[i].length; j++) {
                if (i == first || i == last) {
                    if (j >= first && j <= last) { // Control over columns
                        matrix.squarelotron[i][j] = this.squarelotron[j][i];
                        matrix.squarelotron[j][i] = this.squarelotron[i][j];

                    }
                } else if (i > first && i < last && (j == first || j == last)) { // control over rows
//                    if (j == first || j == last) {
                        matrix.squarelotron[i][j] = this.squarelotron[j][i];
                        matrix.squarelotron[j][i] = this.squarelotron[i][j];
//                    }
                }
            }
        }


        return matrix;
    }

    /**
     *  Rotate 90 degrees clockwise.
     *
     * @param numberOfTurns number of turns
     */
    void rotateRight(int numberOfTurns) {
        int len = squarelotron.length;
        final int numberOf90Degrees = 4; // 180 Degrees
        numberOfTurns = numberOfTurns % numberOf90Degrees;

        if (numberOfTurns != 0) {
            if (numberOfTurns > 0) {
                while (numberOfTurns > 0) {
                    transposeMatrix(); // reverse rows
                    for (int i = 0; i < len; i++) {
                        for (int j = 0; j < len / 2; j++) {
                            int temp = squarelotron[i][j];
                            squarelotron[i][j] =  squarelotron[i][len - 1 - j];
                            squarelotron[i][len - 1 - j] = temp;
                        }
                    }
                    numberOfTurns--;
                }
            } else {
                while (numberOfTurns < 0) {
                    rotateLeft();
                    numberOfTurns++;
                }
            }
        }
    }

    /**
     * Retrieve rings number.
     *
     * @return number of rings
     */
    int getNumberOfRings() {
        return  size % 2 == 0 ? size / 2 : (size / 2) + 1;
    }

    /**
     * Rotate 90 degrees anticlockwise.
     */
    void rotateLeft() {
        transposeMatrix();
        int len = squarelotron.length;

        // Reverse columns
        for (int i = 0; i < len / 2; i++) {
            for (int j = 0; j < len; j++) {
                int temp = squarelotron[i][j];
                squarelotron[i][j] = squarelotron[len - 1 - i][j];
                squarelotron[len - 1 - i][j] = temp;
            }
        }
    }

    /**
     * Transpose field through main diagonal.
     */
    void transposeMatrix() {
        for (int i = 0; i < squarelotron.length; i++) {
            for (int j = i + 1; j < squarelotron[i].length; j++) {
                int temp = squarelotron[i][j];
                squarelotron[i][j] = squarelotron[j][i];
                squarelotron[j][i] = temp;
            }
        }
    }

    /**
     * Print Grid.
     */
    void printGrid() {

        for (int[] row : squarelotron) {
            for (int place : row) {
                System.out.print(place + "\t");
            }
            System.out.println("\n");
        }
        System.out.println("\n");
    }
}
