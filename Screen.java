import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

import assets.GameLogic;
import assets.Interview;
import assets.Intro;
import assets.SearchRoom;
import javax.swing.JTextField;

public class Screen extends JPanel implements KeyListener, MouseListener {

    private SearchRoom msearchRoom;
    private GameLogic mGameLogic;
    private Interview mInterview;
    private Intro mIntro;

    public Screen(JFrame fr) {

        mGameLogic = new GameLogic();
        msearchRoom = new SearchRoom(mGameLogic, this);
        mInterview = new Interview(mGameLogic);
        mIntro = new Intro(mGameLogic);

        fr.add(this);

        addKeyListener(this);
        addMouseListener(this);
        setLayout(null);
        setFocusable(true);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1800, 800);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mGameLogic.currentRoom == mGameLogic.ROOM_INTRO) {
            mIntro.renderScene(g);
        }
        if (mGameLogic.currentRoom == mGameLogic.ROOM_SEARCH) {
            msearchRoom.renderScene(g);
        }

        if (mGameLogic.currentRoom == mGameLogic.ROOM_INTERVIEW) {
            mInterview.renderScene(g);
        }

        repaint();

    }

    public void keyPressed(KeyEvent e) {
        if (mGameLogic.currentRoom.equals(mGameLogic.ROOM_INTRO)) {
            mIntro.keyEvent(e);
        } else if (mGameLogic.currentRoom.equals(mGameLogic.ROOM_INTERVIEW)) {
            mInterview.keyEvent(e);
        } else if (mGameLogic.currentRoom == mGameLogic.ROOM_SEARCH) {
            msearchRoom.keyEvent(e);
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (mGameLogic.currentRoom.equals(mGameLogic.ROOM_SEARCH)) {
            msearchRoom.mouseEvent(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
