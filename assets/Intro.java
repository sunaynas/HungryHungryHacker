package assets;

import assets.Image;
import assets.GameLogic;
import util.HelperGraphic;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import util.HelperGraphic;

public class Intro extends Room {
    public static String IMAGE_BACKGROUND = "0.background";
    public static String IMAGE_COP = "1.officer";

    public String introStatus;
    public static String INTRO_ASK_HELP = "askHelp";
    public static String INTRO_EXPLAIN_MURDER = "explainMurder";

    public Intro(GameLogic m) {
        super(m);
        addImage(IMAGE_BACKGROUND, 0, 0, "background01.jpeg");
        addImage(IMAGE_COP, 50, 70, "npc01.png");
        this.mGameLogic = mGameLogic;
        this.introStatus = INTRO_ASK_HELP;
        changeVisibility(IMAGE_COP, true);
        changeVisibility(IMAGE_BACKGROUND, true);
    }

    public void renderScene(Graphics g) {
        super.renderScene(g);
        if (introStatus.equals(INTRO_ASK_HELP)) {
            HelperGraphic.drawText(g, "There's a murder at the local hackathon! Can you help, detective?");
            g.drawString("1: Sure", 100, 690);
            g.drawString("2: Hard pass.", 100, 730);
        } else if (introStatus.equals(INTRO_EXPLAIN_MURDER)) {
            HelperGraphic.drawText(g, "");

            g.drawString("Thank you for helping!! The victim Cody was on the 2-time winning team. Just hours", 100,
                    625);
            g.drawString("before the yearly hackathon, he was found hacked to death! With an arm MISSING!",
                    100, 665);
            // change this to go to an options menu
            // g.drawString("Press SPACE to continue", 100, 730);
            g.drawString("1: Search Cody's room", 100, 710);
            g.drawString("2: Talk to the suspects", 100, 750);
        }
    }

    public void keyEvent(KeyEvent e) {
        if (introStatus.equals(INTRO_ASK_HELP)) {
            if (e.getKeyCode() == 49) {
                introStatus = INTRO_EXPLAIN_MURDER;
            } else if (e.getKeyCode() == 50) {
                System.exit(0);
            }
        } else if (introStatus.equals(INTRO_EXPLAIN_MURDER)) {
            if (e.getKeyCode() == 49) {
                mGameLogic.currentRoom = mGameLogic.ROOM_SEARCH;
            } else if (e.getKeyCode() == 50) {
                mGameLogic.currentRoom = mGameLogic.ROOM_INTERVIEW;
            }
        }
    }

}
