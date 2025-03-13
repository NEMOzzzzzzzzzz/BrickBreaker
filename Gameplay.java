
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;


public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    
    int total = 21;
    
    int score = 0;

    private map mapgen;

    Timer timer;
    int speed = 8;

    int barX = 200;

    int ballposX  = 241;
    int ballposY  = 329;

    int balldirX = -2;
    int balldirY = -4;

    public Gameplay() {
    	mapgen = new map(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(speed, this);
        timer.start();
    }

    public void paint (Graphics g) {
    
    	
    	//background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 492, 692);
        
        //map
        mapgen.draw((Graphics2D) g);

        //Container: Width = 482, Height = 658
        g.setColor(Color.RED);
        g.fillRect(0, 0, 3, 658);
        g.fillRect(0, 0, 492, 3);
        g.fillRect(482, 0, 3, 658);
        

        //Bar
        g.setColor(Color.GREEN);
        g.fillRect(barX, 640, 100, 8);

        //ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);

        //scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("Sanserif", Font.BOLD, 20));
        g.drawString("Score: "+score, 385, 30);
        
        if(total <=  0) {
        	play = false;
        	g.setColor(Color.red);
		    g.setFont(new Font("Sanserif", Font.BOLD, 20));
		    g.drawString("You won!!!", 150, 290);
        }
        
        if(ballposY > 660) {
        	play = false;
        	balldirX = 0;
		    balldirY = 0;
		    g.setColor(Color.red);
		    g.setFont(new Font("Sanserif", Font.BOLD, 20));
		    g.drawString("Game Over!!! You Lost.", 150, 290);
		    g.drawString("Press [ENTER] to restart.", 150, 320);
		    
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	timer.start();
    	if (play) {
    		
    		if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(barX, 640, 100, 8))) {
    			balldirY = -balldirY;
    		}
    		
    		A: for(int i = 0; i < mapgen.map.length; i++) {
        		for(int j = 0; j < mapgen.map[0].length; j++) {
        			if(mapgen.map[i][j] > 0) {
        				int brickX = j * mapgen.BrickWidth + 60;
        				int brickY = i * mapgen.BrickHeight + 50;
        				int brickHeight = mapgen.BrickHeight;
        				int brickWidth = mapgen.BrickWidth;
        				
        				Rectangle brickRect = new Rectangle(brickX ,brickY, brickWidth, brickHeight);
        				Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
        				
        				if(brickRect.intersects(ballRect)) {
        					mapgen.brickValue(0, i, j);
        					total--;
        					score += 5;
        					
        					if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
        						balldirX = -balldirX; 			
        					} else {
        						balldirY = -balldirY;        						
        					}	
        				}
        				if(total <= 0) {
        					ballposX  = 241;
        				    ballposY  = 329;
        				    balldirX = -2;
        				    balldirY = -4;
        				  
        				    break A;
        				}
        			}
    			}
    		}
    		
    		ballposX += balldirX;
    		ballposY += balldirY;
    		if(ballposX < 0) {
    			balldirX = -balldirX;
    		}
    		if(ballposY < 0) {
    			balldirY = -balldirY;
    		}
    		if(ballposX > 462) {          //Right Border = 462
    			balldirX = -balldirX;
    		}     		
    	}
    	repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		if(barX >= 382) {
    			barX = 382;
    		} else {
    			moveRight();    			
    		}    		
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
    		if(barX < 10) {
    			barX = 0;
    		} else {
    			moveLeft();
    		}    		
    	}
    	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
    		if(!play) {
    			play = true;
    			ballposX  = 241;
			    ballposY  = 329;
				
			    balldirX = -2;
			    balldirY = -4;
    			
			    barX = 200;
			    total = 21;
			    score = 0;
			    
			    mapgen = new map(3, 7);
			    
			    repaint();
    		}
    	}
    }
    
    public void moveRight() {
    	play = true;
    	barX += 20;
    }
    
    public void moveLeft() {
    	play = true;
    	barX -= 20;
    }
    
}
