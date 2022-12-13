package assets;

import assets.Image;
import assets.Suspect;
import assets.GameLogic;
import util.HelperGraphic;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import util.HelperGraphic;

public class Interview extends Room {

    public static String IMAGE_BACKGROUND = "0.InterviewBackground";
    public static String IMAGE_TECHNOCRATIC_THREE = "1.technocraticThree";
    public static String IMAGE_ED_SHEERAN = "2.edSheeran";
    public static String IMAGE_LESLIE = "3.leslie";
    public static String IMAGE_CHAD = "4.chad";

    private String interviewStatus;
    public static String INTERVIEW_INTRO = "npcintro";
    public static String INTERVIEW_PICK_NPC = "npcPicking";
    public static String INTERVIEW_ASK_QUESTIONS = "questioning";
    public static String INTERVIEW_ANSWER_QUESTIONS = "answering";
    public static String QUESTION_CODY_RELATION = "How did you know Cody?";
    public static String QUESTION_ALIBI = "Where were you when the murder occurred?";

    private int introCount;
    private int alibiCount;
    private int personAsked;
    private int questionAsked;
    private String[] suspects;
    private boolean longText;
    private boolean longTextEnd;
    private int longTextCounter;

    public Interview(GameLogic m) {
        super(m);
        this.introCount = 0;
        this.alibiCount = 0;
        this.personAsked = -1;
        this.questionAsked = 0;
        this.longText = false;
        this.longTextCounter = 0;
        addImage(IMAGE_BACKGROUND, 0, 0, "background01.jpeg");

        String[] t3alibi = {
                "We fought for our favorite room with that orange headed tyrant. Tell that LOSER",
                "finders keepers.",
                "He tried to steal our rightful room way AFTER we set up! That useless slowpoke",
                "even got to the Hackathon BEFORE us!!",
                "The AUDACITY of that monstrous ginger sloth!" };
        addSuspect(IMAGE_TECHNOCRATIC_THREE, 50, 240, "npc2.png",
                "*raps* We're the technocratic three. We're spittin fire at this crime scene.");

        String[] edAlibi = { "I was defending my poor room from those indecent gangsters. There was",
                "only one minion there, they didn't need the whole room!" };
        addSuspect(IMAGE_ED_SHEERAN, 50, 70, "npc3.png", "I'm Ed. *sings* Ed Sheeran.");

        String[] lesAlibi = { "Where was I? Where were YOU, mister? The team room OBVIOUSLY. I'll have",
                "you nosy dogs know Chad was with me. My alibi is as solid as pumpkin in spring" };
        addSuspect(IMAGE_LESLIE, 50, 70, "npc4.png", "Leslie. I'm as busy as a one-eyed dog in a smokehouse");

        String[] chadAlibi = {
                "I was researching some rad protein shakes in our team's room. I was waiting for poor ",
                "codster... I was gonna tell him about my new arm shake *sniff* all that protein! *sniff*",
                "*sobs* Leslie wouldn't even try my shake- MY PROTEINS!! she said she had womanly",
                "duties to attend to.. whatever that means... so I went to the kitchen and made my shake alone." };
        addSuspect(IMAGE_CHAD, 50, 70, "npc5.png", "Yo, Chad's the name, weightlifting is my gaaaame.");

        getSuspect(IMAGE_TECHNOCRATIC_THREE).addQuestion(1, QUESTION_CODY_RELATION,
                "Cody was our greatest rival. Annoying little bugger.");
        getSuspect(IMAGE_ED_SHEERAN).addQuestion(1, QUESTION_CODY_RELATION, "Who's Cody?");
        getSuspect(IMAGE_LESLIE).addQuestion(1, QUESTION_CODY_RELATION,
                "His teammate was I. Dating was we- now woeful am I. Excused may I be?");
        getSuspect(IMAGE_CHAD).addQuestion(1, QUESTION_CODY_RELATION,
                "We were bros. Leslie, Cody and I have had some chadreffic times.");

        getSuspect(IMAGE_TECHNOCRATIC_THREE).addQuestion(2, QUESTION_ALIBI, t3alibi);
        getSuspect(IMAGE_ED_SHEERAN).addQuestion(2, QUESTION_ALIBI, edAlibi);
        getSuspect(IMAGE_LESLIE).addQuestion(2, QUESTION_ALIBI, lesAlibi);
        getSuspect(IMAGE_CHAD).addQuestion(2, QUESTION_ALIBI, chadAlibi);

        changeVisibility(IMAGE_BACKGROUND, true);
        changeVisibility(IMAGE_TECHNOCRATIC_THREE, true);

        this.suspects = new String[] { IMAGE_TECHNOCRATIC_THREE, IMAGE_ED_SHEERAN, IMAGE_LESLIE, IMAGE_CHAD };

        this.interviewStatus = INTERVIEW_INTRO;
    }

    public void renderScene(Graphics g) {
        super.renderScene(g);

        if (interviewStatus.equals(INTERVIEW_INTRO)) {
            HelperGraphic.drawText(g, getSuspect(suspects[introCount]).getIntroText());
            g.drawString("Press SPACE to continue", 100, 730);
        } else if (interviewStatus.equals(INTERVIEW_PICK_NPC)) {
            HelperGraphic.drawText3Lines(g, "Pick a suspect to ask or press E to change rooms",
                    "1: Technocratic Three, 2: Ed Sheeran, ",
                    "3: Leslie, 4: Chad");
        } else if (interviewStatus.equals(INTERVIEW_ASK_QUESTIONS)) {
            HelperGraphic.drawText3Lines(g,
                    "Ask a question or press E to interview someone else",
                    ("1:" + getSuspect(suspects[personAsked]).getQuestion(1)),
                    ("2:" + getSuspect(suspects[personAsked]).getQuestion(2)));
        } else if (interviewStatus.equals(INTERVIEW_ANSWER_QUESTIONS)) {
            longTextDisplay(g, getSuspect(suspects[personAsked]).getAnswer(questionAsked));
        }
    }

    public void longTextDisplay(Graphics g, String[] text) {
        this.longText = true;

        if ((this.longTextCounter + 2) < text.length) {
            this.longTextEnd = false;
            HelperGraphic.drawText3Lines(g, text[longTextCounter], text[longTextCounter + 1],
                    "Press SPACE to continue");
        } else if (this.longTextCounter + 2 == text.length) {
            this.longTextEnd = true;
            HelperGraphic.drawText3Lines(g, text[longTextCounter], text[longTextCounter + 1],
                    "Press E to ask another question");
        } else if (this.longTextCounter + 1 == text.length) {
            this.longTextEnd = true;
            HelperGraphic.drawText3Lines(g, text[longTextCounter], "   ", "Press E to ask another question");
        }
    }

    public void keyEvent(KeyEvent e) {
        if (longText) {
            if (e.getKeyCode() == 32 && this.longTextEnd == false) { // space when going thru long text
                this.longTextCounter += 2;
            }
            if (e.getKeyCode() == 69 && this.longTextEnd == true) { // E @ end of long text
                this.longText = false;
                this.longTextCounter = 0;
            }
        }

        if (interviewStatus.equals(INTERVIEW_INTRO)) {
            if (e.getKeyCode() == 32) { // space
                introCount++;
                if (introCount > 0) {
                    getImage(suspects[introCount - 1]).changeVisibility(false);
                }
                if (introCount <= 3) {
                    getImage(suspects[introCount]).changeVisibility(true);
                } else {
                    interviewStatus = INTERVIEW_PICK_NPC;
                }
            }
        } else if (interviewStatus.equals(INTERVIEW_PICK_NPC)) {
            if (e.getKeyCode() == 69) {// e
                mGameLogic.currentRoom = mGameLogic.ROOM_SEARCH;
            } else {
                switch (e.getKeyCode()) {
                    case 49: { // 1
                        personAsked = 0;
                        break;
                    }
                    case 50: { // 2
                        personAsked = 1;
                        break;
                    }
                    case 51: { // 3
                        personAsked = 2;
                        break;
                    }
                    case 52: { // 4
                        personAsked = 3;
                        break;
                    }
                    default:
                        break;
                }
                if (personAsked != -1) {
                    interviewStatus = INTERVIEW_ASK_QUESTIONS;
                    getImage(suspects[personAsked]).changeVisibility(true);
                }
            }

        } else if (interviewStatus.equals(INTERVIEW_ASK_QUESTIONS)) {

            if (e.getKeyCode() == 69) {// e
                interviewStatus = INTERVIEW_PICK_NPC;
                getImage(suspects[personAsked]).changeVisibility(false);
                personAsked = -1;
            } else {
                switch (e.getKeyCode()) {
                    case 49: { // 1
                        questionAsked = 1;
                        break;
                    }
                    case 50: { // 2
                        questionAsked = 2;
                        break;
                    }
                    case 51: { // 3
                        questionAsked = 3;
                        break;
                    }
                    case 52: { // 4
                        questionAsked = 4;
                        break;
                    }
                    default:
                        break;
                }
                if (questionAsked != 0) {
                    interviewStatus = INTERVIEW_ANSWER_QUESTIONS;
                }
            }
        } else if (interviewStatus.equals(INTERVIEW_ANSWER_QUESTIONS)) {
            if (e.getKeyCode() == 69 && longText == false) { // e
                interviewStatus = INTERVIEW_ASK_QUESTIONS;
                questionAsked = 0;
            }
        }
    }
}