package squarelotron;
/**
 * @author Karen Saavedra
 *
 */
public class Squarelotron {
 private int[][] squarelotron;
 private int size;
 /**
 *
 * @param n size input constructor
 */
 Squarelotron(int n) {
     if (n > 1) { //  && n < 9
         this.size = n;
         this.squarelotron = new int[size][size];
         for (int row = 0; row < size; row++) {
             for (int col = 0; col < size; col++) {
                 squarelotron[row][col] = (row * size) + col + 1;
             }
         }
     }
 }
 /**
  * method to display the square generated
  */
 void displaySquare()
 {
    try {
        for (int row = 0; row < this.squarelotron.length; row++) {
            for (int col = 0; col < this.squarelotron.length; col++) {     
                System.out.printf(" %2d ", this.squarelotron[row][col]);
             }
            System.out.println();
         }
     }
    catch(Exception e) {
        System.out.println("matrix must be greater than 1 and minor or equal to 8");
    }
 }
 /**
  * @return the number of ring for a matrix
  */
 public int QuantityOfRings()
 {
     return  (this.size % 2 == 0) ? (size / 2) : (size / 2) + 1;
 }
 /**
  * return the square swappped
  * @param s
  */
 public void swap(Squarelotron s) {
     for  (int row = 0; row < size; row++) {
         for (int col = 0; col < size; col++) {
             s.squarelotron[row][col] = this.squarelotron[row][col];
         }
     }
     System.out.println();
   }
 /**
  * @param ring
  * @return
  * @throws NumberFormatException
  */
 Squarelotron upsideDownFlip(int ring) throws NumberFormatException {
     if(ring > this.QuantityOfRings() || ring < 1) {
         throw new NumberFormatException();
     }
     Squarelotron square = new Squarelotron(size);
     this.swap(square);
     int first = ring - 1;
     int last = size - ring;
     for (int row = 0; row <= size - 1; row++) {
         for (int col = 0; col <= size - 1; col++) {
             if (row == first || row == last) {
                 if (col >= first && col <= last) {
                     square.squarelotron[row][col] = this.squarelotron[size - 1 - row][col];
                 }             
              }
             else if (row > first && row < last) {
                 if (col == first || col == last) {
                     square.squarelotron[row][col] = this.squarelotron[size - 1 - row][col];
                  }
              }
          }
      }
     return square;
     }
 // public int ringCondition(int ring)
 Squarelotron mainDiagonalFlip(int ring) throws NumberFormatException {
     if (ring > this.QuantityOfRings() || ring < 1)
         throw new NumberFormatException();
     int first = ring - 1;
     int last = size - ring;
     Squarelotron square = new Squarelotron(size);
     this.swap(square);
     for (int row = 0; row < size; row++) {
         for (int col = 0; col < size; col++) {
             if (row == first || row == last) {
                 if (col >= first && col <= last) {
                     square.squarelotron[row][col] = this.squarelotron[col][row];
                 }
              }
         else {
             if (row > first && row < last) {
                 if (col == first || col == last) {
                     square.squarelotron[row][col] = this.squarelotron[col][row];
                 }
             }
         }
         }
     }
     return square;
     }
 
  void rotateRight(int numberOfTurns) {
     int ring;
     boolean right = true;
     int maxRing = QuantityOfRings();
     int[][] tmpArr = new int[size][size];
 //
     for (int row = 0; row < size; row++) {
         for (int col = 0; col < size; col++) {
             tmpArr[row][col] = this.squarelotron[row][col];
         }
     }
     if (numberOfTurns < 0) {
         right = false;
         numberOfTurns *= -1;
     }
     for (int k = 0; k < numberOfTurns; k++) {        
         for (int l = 1; l <= maxRing; l++) {
             ring = l;
             int first = ring - 1;
             int last = size - ring;
             for (int row = 0; row < size; row++) {
                 for (int col = 0; col < size; col++) {
                     if (row == first) {
                         if (col >= first && col <= last) {
                         this.squarelotron[row][col] = (right) ? tmpArr[size - 1 - col][first]
                                 : tmpArr[col][last];
                         }
                     }
                     else if (row == last) {
                         if (col >= first && col <= last) {
                             this.squarelotron[row][col] = (right) ? tmpArr[size - 1 - col][last]
                                     : tmpArr[col][first];
                         }
                     }
                     else if (row > first && row < last) { 
                         if (col == last) {
                             this.squarelotron[row][col] = (right) ? tmpArr[first][row]
                                     : tmpArr[last][size - 1 - row];
                          }
                         else if (col == first) {
                         this.squarelotron[row][col] = (right) ? tmpArr[last][row]
                                 : tmpArr[first][size - 1 - row];
                         }
                     }
                 }
             }
            }  
         for (int row = 0; row < size; row++) {
             for (int col = 0; col < size; col++) {
                 tmpArr[row][col] = this.squarelotron[row][col];
             }
         }
     }
     }
}
