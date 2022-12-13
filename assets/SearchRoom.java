package assets;

import assets.Room;
import assets.Image;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import util.HelperGraphic;
import assets.GameLogic;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import util.HelperGraphic;

public class SearchRoom extends Room implements ActionListener {
    // any special attributes of the search room

    public static String IMAGE_BACKGROUND = "0.background";
    public static String IMAGE_LETTER = "1.letter";
    public static String IMAGE_ARCADE = "2.arcade";
    public static String IMAGE_COMPUTER = "3.computer";
    public static String IMAGE_TEXT_MESSAGES = "4.textMessages";
    public static String IMAGE_CODY_ED_PHOTO = "5.codyEdPhoto";

    private JTextField inputPassword;
    private JButton submit;

    public SearchRoom(GameLogic m, JPanel screen) {
        super(m);
        addImage(IMAGE_BACKGROUND, -225, 0, "room.jpeg");
        addImage(IMAGE_CODY_ED_PHOTO, 0, 0, "photo.png");
        addImage(IMAGE_ARCADE, 0, 0, "letter.png");
        addImage(IMAGE_ARCADE, 500, 0, "game.png");
        addImage(IMAGE_COMPUTER, 0, 0, "password.png");
        addImage(IMAGE_TEXT_MESSAGES, 200, 0, "texts.png");
        changeVisibility(IMAGE_BACKGROUND, true);

        inputPassword = new JTextField("Password");
        inputPassword.setBounds(570, 230, 200, 50);
        inputPassword.addActionListener(this);
        inputPassword.setFocusable(true);

        screen.add(inputPassword);
        inputPassword.setVisible(false);

        submit = new JButton("Enter");
        submit.setBounds(1030, 230, 100, 50);
        submit.addActionListener(this);
        submit.setBackground(Color.GRAY);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        screen.add(submit);
        submit.setVisible(false);

        this.mGameLogic = mGameLogic;

    }

    public void renderScene(Graphics g) {
        super.renderScene(g);
        if (getVisibility(IMAGE_CODY_ED_PHOTO)) {
            HelperGraphic.drawText(g, "Press e to exit");
            mGameLogic.edHint = true;
        } else if (getVisibility(IMAGE_ARCADE)) {
            HelperGraphic.drawText(g, "Press e to exit");
            mGameLogic.chadHint = true;
        } else if (getVisibility(IMAGE_ARCADE)) {
            HelperGraphic.drawText(g, "Press e to exit");
        } else if (getVisibility(IMAGE_COMPUTER)) {
            // why doesnt it draw the computer on its own?????
            displayImage(g, IMAGE_COMPUTER);
            HelperGraphic.drawText(g, "press white X to quit");
            g.setColor(Color.WHITE);
            g.drawString("X", 1150, 40);
            inputPassword.setVisible(true);
            submit.setVisible(true);
        } else if (getVisibility(IMAGE_TEXT_MESSAGES)) {
            // same issue here
            displayImage(g, IMAGE_TEXT_MESSAGES);
            mGameLogic.leslieHint = true;
            HelperGraphic.drawText(g, "Press e to exit");
        } else if (getVisibility(IMAGE_BACKGROUND)) {
            HelperGraphic.drawText(g, "Press e to switch to interview");
        }
    }

    public void mouseEvent(MouseEvent e) {
        System.out.println("X: " + e.getX() + "Y: " + e.getY());
        if (e.getX() >= 600 && e.getX() <= 800 && e.getY() >= 290 && e.getY() <= 390) {
            System.out.println(IMAGE_COMPUTER);
            if (mGameLogic.locked) {
                changeVisibility(IMAGE_COMPUTER, true);
            } else if (!mGameLogic.locked) {
                changeVisibility(IMAGE_COMPUTER, false);
                changeVisibility(IMAGE_TEXT_MESSAGES, true);
            }
        } else if (e.getX() >= 450 && e.getX() <= 580 && e.getY() >= 380 && e.getY() <= 430) {
            System.out.println(IMAGE_ARCADE);
            changeVisibility(IMAGE_ARCADE, true);
        } else if (e.getX() >= 310 && e.getX() <= 550 && e.getY() >= 240 && e.getY() <= 360) {
            System.out.println("Billboard");
            changeVisibility(IMAGE_CODY_ED_PHOTO, true);
        } else if (e.getX() >= 1050 && e.getX() <= 1380 && e.getY() >= 310 && e.getY() <= 580) {
            System.out.println(IMAGE_ARCADE);
            changeVisibility(IMAGE_ARCADE, true);
        } else if (mGameLogic.locked == true) {
            if (e.getX() >= 1135 && e.getX() <= 1180 && e.getY() >= 10 && e.getY() <= 45) {
                changeVisibility(IMAGE_COMPUTER, false);
                inputPassword.setVisible(false);
                submit.setVisible(false);
            }
        }
    }

    public void keyEvent(KeyEvent e) {
        if (e.getKeyCode() == 69) {
            if (getVisibility(IMAGE_CODY_ED_PHOTO) != true
                    && getVisibility(IMAGE_ARCADE) != true
                    && getVisibility(IMAGE_ARCADE) != true
                    && getVisibility(IMAGE_COMPUTER) != true
                    && getVisibility(IMAGE_TEXT_MESSAGES) != true) {
                mGameLogic.currentRoom = mGameLogic.ROOM_INTERVIEW;
            } else if (getVisibility(IMAGE_CODY_ED_PHOTO) == true
                    || getVisibility(IMAGE_TEXT_MESSAGES) == true
                    || getVisibility(IMAGE_ARCADE) == true
                    || getVisibility(IMAGE_ARCADE) == true) {

                changeVisibility(IMAGE_CODY_ED_PHOTO, false);
                changeVisibility(IMAGE_ARCADE, false);
                changeVisibility(IMAGE_ARCADE, false);
                changeVisibility(IMAGE_TEXT_MESSAGES, false);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                int nums = Integer.parseInt(inputPassword.getText());
                System.out.println("key =" + nums);
                if (nums == 436) {
                    mGameLogic.locked = false;
                    inputPassword.setVisible(false);
                    submit.setVisible(false);
                    changeVisibility(IMAGE_COMPUTER, false);
                    changeVisibility(IMAGE_TEXT_MESSAGES, true);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }
}