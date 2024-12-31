import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// This class exhibits the matrix of life and allows to see the update of the matrix by pressing the button "next generation".   
public class GameOfLifeController {
	
	private final int SIZEMAT = 10; // The size of the square matrix.
	private MatrixOfLife matOfLife; 

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;
    
    
    public void initialize() {
    	gc = canvas.getGraphicsContext2D();
    	matOfLife = new MatrixOfLife(SIZEMAT); // Generates the matrix of life with random values.
    	paint(); // Paints the sites of matrix of life.
    	drawMat(); // Draws the lines of the matrix.
    }
    
    // This method draws the lines of the matrix.
    public void drawMat() {
    	int matSize = SIZEMAT;
    	int stepX = (int) canvas.getWidth()/matSize; // The step increment of the row index i in the canvas measurements.
    	int stepY = (int) canvas.getHeight()/matSize; // The step increment of the column index i in the canvas measurements.    	
    	
    	for(int i=0; i<=matSize; i++) { 
    		gc.strokeLine(i*stepX, 0, i*stepX, canvas.getHeight()); // Draws the column lines.
    		gc.strokeLine(0, i*stepY, canvas.getWidth(), i*stepY); // Draws the row lines.
    	}
    	
    }
    
    // This method paints the sites of the matrix according to the values of the elements of the matrix of life.
    public void paint() {
    	int[][] mat = matOfLife.getMat();
    	int matSize = SIZEMAT;
    	int stepI = (int) canvas.getWidth()/matSize; // The step increment of the row index i in the canvas measurements.
    	int stepJ = (int) canvas.getHeight()/matSize; // The step increment of the column index j in the canvas measurements.    	
    	
    	
    	gc.setFill(Color.LIGHTGRAY);
    	//gc.setStroke(Color.RED);
        
    	// A nested loop fills the sites of the matrix according to the values of the matrix of life. 
        for(int i=0; i<matSize; i++) { 
        	for(int j=0; j<matSize; j++) { 
        		        		
        		if(mat[i][j] ==  1) { // Condition if the site is alive.  
        			gc.fillRect(i*stepI, j*stepJ, stepI, stepJ); // Fills the site of the matrix with color. 
        		}
        		
        		else { // Condition if the site is dead.
        			gc.clearRect(i*stepI, j*stepJ, stepI, stepJ); // Clears the color of the site of the matrix of life.
        		}
        	}
        }
    	
    }

    // The event of pressing the button.
    @FXML
    void updateMat(ActionEvent event) {
    	matOfLife.updateMat(); // Updates the matrix of life for the next generation according to the rules of the game of life.
    	paint(); // Paints the sites of the updated matrix of life.
    	drawMat(); // Draws the lines of the matrix.
    }

}
