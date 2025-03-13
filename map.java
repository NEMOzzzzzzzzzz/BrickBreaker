import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class map {
    public int map[][];
    public int BrickHeight;
    public int BrickWidth;
    
    
    public map(int row, int col) {
    	map = new int[row][col];
    	for(int i = 0; i < map.length; i++) {
    		for(int j = 0; j < map[0].length; j++) {
    			map[i][j] = 1;
    		}
    	}
    	
    	BrickHeight = 380/col;
    	BrickWidth = 160/row;
    }
    
    public void draw(Graphics2D g) {
    	for(int i = 0; i < map.length; i++) {
    		for(int j = 0; j < map[0].length; j++) {
    			if(map[i][j] > 0) {
    				g.setColor(Color.CYAN);
    				g.fillRect(j * BrickWidth + 60, i * BrickHeight + 50, BrickWidth, BrickHeight);
    				
    				g.setStroke(new BasicStroke(3));
    				g.setColor(Color.black);
    				g.drawRect(j * BrickWidth + 60, i * BrickHeight + 50, BrickWidth, BrickHeight);
    			} 
    		}
    	}
    }
    
    public void brickValue(int value, int row, int col) {
    	map[row][col] = value;
    }
}
