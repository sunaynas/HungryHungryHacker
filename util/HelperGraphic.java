package util;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class HelperGraphic {

    public static void drawText(Graphics g, String txt) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(25, 575, 1385, 200, 15, 15);
        g.setColor(Color.BLACK);
        Font font = new Font("Century Schoolbook L", Font.PLAIN, 30);
        g.setFont(font);
        g.drawString(txt, 100, 640);
    }

    public static void drawText3Lines(Graphics g, String txt, String txt2, String txt3) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(25, 575, 1385, 200, 15, 15);
        g.setColor(Color.BLACK);
        Font font = new Font("Century Schoolbook L", Font.PLAIN, 30);
        g.setFont(font);
        if (txt2.charAt(0) == '1') {
            g.drawString(txt, 100, 640);
            g.drawString(txt2, 100, 690);
            g.drawString(txt3, 100, 730);
        } else {
            g.drawString(txt, 100, 640);
            g.drawString(txt2, 100, 680);
            g.drawString(txt3, 100, 730);
        }
    }

    public static void drawText2Lines(Graphics g, String txt, String txt2) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(25, 575, 1385, 200, 15, 15);
        g.setColor(Color.BLACK);
        Font font = new Font("Century Schoolbook L", Font.PLAIN, 30);
        g.setFont(font);
        g.drawString(txt, 100, 640);
        g.drawString(txt2, 100, 680);
    }
}
