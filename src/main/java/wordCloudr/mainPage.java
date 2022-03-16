package wordCloudr;

import javax.swing.*;

public class mainPage {

    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainPage");
        frame.setContentPane(new mainPage().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
