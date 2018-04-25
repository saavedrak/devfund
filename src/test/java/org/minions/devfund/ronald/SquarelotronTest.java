package org.minions.devfund.ronald;


import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Squarelotron Unit Tests.
 */
public class SquarelotronTest {

    private Squarelotron squarelotron;
    private static final int SIZE = 5;

    /**
     * Set up environment.
     */
    @Before
    public void setUp() {
        this.squarelotron = new Squarelotron(SIZE);
    }

    /**
     * Test Squarelotron Constructor.
     */
    @Test
    public void testSquarelotronInt() {
        // Given
        final int[][] expectedMatrix = {
                {1, 2, 3, 4, 5},

                {6, 7, 8, 9, 10},

                {11, 12, 13, 14, 15},

                {16, 17, 18, 19, 20},

                {21, 22, 23, 24, 25}
        };
        assertEquals("Sizes are not equals", SIZE, squarelotron.getSize());
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrix, squarelotron.getSquarelotron()));
    }

    /**
     * Test main diagonal flip Exception.
     */
    @Test
    public void testMainDiagonalFlipThrowException() {
        // Given
        final int edges = 0;

        try {
            // When
            Squarelotron newSquarelotron = squarelotron.mainDiagonalFlip(edges);
            System.out.println(newSquarelotron.getNumberOfRings());

        } catch (IllegalArgumentException ex) {
            // Then
            assertEquals("Just allowed numbers between 1 and 3", ex.getMessage());
        }
    }

    /**
     * Test Main Diagonal Flip.
     */
    @Test
    public void testMainDiagonalFlip() {
        // Given
        final int[][] expectedMatrixOuterRing = {
                {1, 6, 11, 16, 21},

                {2, 7, 8, 9, 22},

                {3, 12, 13, 14, 23},

                {4, 17, 18, 19, 24},

                {5, 10, 15, 20, 25}
        };

        final int[][] expectedOriginalMatrix = {
                {1, 2, 3, 4, 5},

                {6, 7, 8, 9, 10},

                {11, 12, 13, 14, 15},

                {16, 17, 18, 19, 20},

                {21, 22, 23, 24, 25}
        };

        final int[][] expectedMatrixMidRing = {
                {1, 2, 3, 4, 5},

                {6, 7, 12, 17, 10},

                {11, 8, 13, 18, 15},

                {16, 9, 14, 19, 20},

                {21, 22, 23, 24, 25}
        };


        //When
        final int outerRing = 1;
        final int innerRing = 3;
        final int midRing = 2;
        Squarelotron newSquarelotron = squarelotron.mainDiagonalFlip(outerRing);
        Squarelotron newSquarelotron1 = squarelotron.mainDiagonalFlip(innerRing);
        Squarelotron newSquarelotron2 = squarelotron.mainDiagonalFlip(midRing);

        // Then MainDiagonalFlip by Ring
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrixOuterRing,
                newSquarelotron.getSquarelotron()));
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedOriginalMatrix,
                newSquarelotron1.getSquarelotron()));
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrixMidRing,
                newSquarelotron2.getSquarelotron()));

        // And Entities are not equals
        assertNotEquals("Entities are equals", squarelotron, newSquarelotron);
        assertNotEquals("Entities are equals", squarelotron, newSquarelotron1);
        assertNotEquals("Entities are equals", squarelotron, newSquarelotron2);

        // And Original squarelotron has not been modified
        assertTrue("squarelotron has been modified", Arrays.deepEquals(expectedOriginalMatrix,
                squarelotron.getSquarelotron()));
        assertTrue("squarelotron has been modified", Arrays.deepEquals(expectedOriginalMatrix,
                squarelotron.getSquarelotron()));
        assertTrue("squarelotron has been modified", Arrays.deepEquals(expectedOriginalMatrix,
                squarelotron.getSquarelotron()));


    }

    /**
     * Test rotate matrix to Right.
     */
    @Test
    public void testRotateRight() {
        // Given
        final int[][] expectedMatrix = {
                {25, 24, 23, 22, 21},

                {20, 19, 18, 17, 16},

                {15, 14, 13, 12, 11},

                {10, 9, 8, 7, 6},

                {5, 4, 3, 2, 1}
        };

        // When
        final int rotate = 2;
        squarelotron.rotateRight(rotate);

        // Then Expected vs Actual
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrix,
                squarelotron.getSquarelotron()));

    }

    /**
     * Test Rotate Left.
     */
    @Test
    public void testRotateLeft() {
        // Given
        final int[][] expectedMatrix = {
                {5, 10, 15, 20, 25},

                {4, 9, 14, 19, 24},

                {3, 8, 13, 18, 23},

                {2, 7, 12, 17, 22},

                {1, 6, 11, 16, 21}
        };

        // When
        final int rotate = -1;
        squarelotron.printGrid();
        squarelotron.rotateRight(rotate);
        squarelotron.printGrid();

        // Then Expected vs Actual
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrix, squarelotron.getSquarelotron()));
    }

    /**
     * Test no rotate matrix.
     */
    @Test
    public void testNoRotate() {
        // Given
        final int[][] expectedMatrix = {
                {1, 2, 3, 4, 5},

                {6, 7, 8, 9, 10},

                {11, 12, 13, 14, 15},

                {16, 17, 18, 19, 20},

                {21, 22, 23, 24, 25}
        };

        // When
        final int rotate = 0;
        squarelotron.rotateRight(rotate);

        // Then Expected vs Actual
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrix, squarelotron.getSquarelotron()));
    }

    /**
     * Test Upside Down Flip Throw an Exception.
     */
    @Test
    public void testUpsideDownFlipThrowException() {
        // Given
        final int edges = 4;

        try {
            // When
            Squarelotron newSquarelotron = squarelotron.upsideDownFlip(edges);
            System.out.println(newSquarelotron.getNumberOfRings());

        } catch (IllegalArgumentException ex) {
            // Then
            assertEquals("Just allowed numbers between 1 and 3", ex.getMessage());
        }
    }

    /**
     * Test Upside Down Flip.
     */
    @Test
    public void testUpsideDownFlip() {
        // Given
        final int[][] expectedMatrix = {
                {21, 22, 23, 24, 25},

                {16, 7, 8, 9, 20},

                {11, 12, 13, 14, 15},

                {6, 17, 18, 19, 10},

                {1, 2, 3, 4, 5}
        };


        final int[][] expectedMatrixMidRing = {
                {1, 2, 3, 4, 5},
                {6, 17, 18, 19, 10},
                {11, 12, 13, 14, 15},
                {16, 7, 8, 9, 20},
                {21, 22, 23, 24, 25}
        };

        final int[][] expectedOriginalMatrix = {
                {1, 2, 3, 4, 5},

                {6, 7, 8, 9, 10},

                {11, 12, 13, 14, 15},

                {16, 17, 18, 19, 20},

                {21, 22, 23, 24, 25}
        };

        // When
        final int outerRing = 1;
        final int innerRing = 3;
        final int midRing = 2;
        Squarelotron newSquarelotron = this.squarelotron.upsideDownFlip(outerRing);
        Squarelotron newSquarelotron1 = this.squarelotron.upsideDownFlip(innerRing);
        Squarelotron newSquarelotron2 = this.squarelotron.upsideDownFlip(midRing);


        // Then Expected vs Actual
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrix,
                newSquarelotron.getSquarelotron()));
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedMatrixMidRing,
                newSquarelotron2.getSquarelotron()));
        assertTrue("Matrix are not equals", Arrays.deepEquals(expectedOriginalMatrix,
                newSquarelotron1.getSquarelotron()));

        // And Entities are not equals
        assertNotEquals("Entities are equals", squarelotron, newSquarelotron);
        assertNotEquals("Entities are equals", squarelotron, newSquarelotron1);
        assertNotEquals("Entities are equals", squarelotron, newSquarelotron2);

        // And Original squarelotron has not been modified
        assertTrue("squarelotron has been modified", Arrays.deepEquals(expectedOriginalMatrix,
                squarelotron.getSquarelotron()));
        assertTrue("squarelotron has been modified", Arrays.deepEquals(expectedOriginalMatrix,
                squarelotron.getSquarelotron()));
        assertTrue("squarelotron has been modified", Arrays.deepEquals(expectedOriginalMatrix,
                squarelotron.getSquarelotron()));
    }
}
