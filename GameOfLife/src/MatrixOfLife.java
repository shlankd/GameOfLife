
// This class builds the matrix of life using random values and contains methods to update matrix values according to the rules of the game of life.
public class MatrixOfLife {
	private int[][] mat; //The matrix of life.
	
	// Constructor of MatrixOfLife
	public MatrixOfLife(int lengthOfMat) {
		mat = new int[lengthOfMat][lengthOfMat];
		generateRandMat(lengthOfMat); // Generate a random matrix.
	}
	
	
	// This method returns the matrix.
	public int[][] getMat(){
		return mat;
	}
	
	// This method generates a matrix with random values of zeros and ones.
	private void generateRandMat(int lengthOfMat) {
		int randVal;
		
		// Two loops for running the elements of the matrix.
		for(int i=0; i<lengthOfMat; i++) { // Loop that runs the rows of the matrix.
			for(int j=0; j<lengthOfMat; j++) { // Loop that runs the columns of the matrix.
				randVal = (int)Math.round(Math.random()); // Generates random values of ones and zeros.
				mat[i][j] = randVal;
			}
		}
	}
	
	// This method counters neighbors according to the matrix bounds.
	private int counterNeighbors(int i, int j) {
		int matLength = mat.length;
		int res;
		
		if(i == 0) {
			
			if(j == 0) { // Condition if the coordinates points at the top left of the matrix.
				res = mat[i+1][j+1] + mat[i][j+1] + mat[i+1][j];
			}
			
			else if(j == matLength-1) { // Condition if the coordinates points at the bottom left of the matrix.
				res = mat[i+1][j-1] + mat[i][j-1] + mat[i+1][j];
			}
			
			else { // Condition if the coordinates points at the left side of the matrix.
				res = mat[i+1][j-1] + mat[i+1][j+1] + mat[i][j-1] + mat[i][j+1] + mat[i+1][j];
			}
		}
		
		else if(i == matLength-1) {
			
			if(j == matLength-1) { // Condition if the coordinates points at the bottom right of the matrix.
				res = mat[i-1][j-1] + mat[i][j-1] + mat[i-1][j];		
			}
			
			else if(j == 0) { // Condition if the coordinates points at the top right of the matrix.
				res = mat[i-1][j+1] + mat[i][j+1] + mat[i-1][j];
			}
			
			else { // Condition if the coordinates points at the right side of the matrix.
				res = mat[i-1][j-1] + mat[i-1][j+1] + mat[i][j-1] + mat[i][j+1] + mat[i-1][j];
			}
		}
		// Conditions when i values are: 0<i<matLength.
		else if(j == 0) { // Condition if the coordinates points at the top side of the matrix. 
			res = mat[i+1][j+1] + mat[i-1][j+1] + mat[i+1][j] + mat[i-1][j] + mat[i][j+1];
		}
		
		else if(j == matLength-1) { // Condition if the coordinates points at the bottom side of the matrix. 
			res = mat[i+1][j-1] + mat[i-1][j-1] + mat[i+1][j] + mat[i-1][j] + mat[i][j-1];
		}
		
		// Conditions if the coordinates do not point at the edges of the matrix. When i and j values are: 0<i,j<matLength.
		else { 
			res = mat[i-1][j-1] + mat[i-1][j+1] + mat[i-1][j] + mat[i+1][j] + mat[i+1][j-1] + mat[i+1][j+1] + mat[i][j+1] + mat[i][j-1];
		}
		
		return res;
	}
		
	// This method updates the matrix of life for the next generation according to the rules of the game of life.
	public void updateMat() {
		int matLength = mat.length; // The length of the matrix.
		int[][] newMat = new int[matLength][matLength]; // The updated matrix of life.
		
		// Two loops for running the elements of the matrix.
		for(int i=0; i<matLength; i++) { // Loop that runs the rows of the matrix.
			for(int j=0; j<matLength; j++) { // Loop that runs the columns of the matrix.
				int numOfLivingNeigh = counterNeighbors(i,j); // The number of living neighbors.
				
				if(mat[i][j] == 1) { // Condition if the current spot alive.
					
					if(numOfLivingNeigh < 2 || numOfLivingNeigh>3) { // Condition for death spot at the next generation if the number of living neighbors greater than 3 or less than 2. 
						newMat[i][j] = 0;
					}
					
					else { // Condition for keep living at the next generation if the number of living neighbors is 2 or 3
						newMat[i][j] = 1;
					}
				}
				
				else { // Condition if the current spot dead.
					
					if(numOfLivingNeigh == 3) { // Condition for birth at the next generation if the number of living neighbors is 3. 
						newMat[i][j] = 1;
					}
					
					else { // Condition for death at the next generation if the number of living neighbors is not 3.
						newMat[i][j] = 0;
					}
				}
				
			}
		}
		mat = newMat;
	}
	
}
