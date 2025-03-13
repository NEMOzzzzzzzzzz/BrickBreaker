import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {
        JFrame objframe = new JFrame();

        Gameplay gameplay = new Gameplay();
        
        objframe.setTitle("Brick Breaker");
        objframe.setVisible(true);
        objframe.setResizable(false);
        objframe.setBounds(10, 10, 500, 700);
        objframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        objframe.add(gameplay);
        gameplay.requestFocusInWindow();
   
    }
}
