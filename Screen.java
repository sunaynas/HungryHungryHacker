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

import javax.swing.JButton;
import javax.swing.JTextField;

public class Screen extends JPanel implements KeyListener, ActionListener, MouseListener {
    private BufferedImage background01;
    private BufferedImage roomIm;
    private BufferedImage photo;
    private BufferedImage letter;
    private BufferedImage game;
    private BufferedImage texts;
    private BufferedImage lockedPage;

    private BufferedImage npc1;
    private BufferedImage npc2;
    private BufferedImage npc3;
    private BufferedImage npc4;
    private BufferedImage npc5;

    private boolean intro;
    private boolean explainMurder;
    private boolean npcIntro;
    private boolean searchRoom;
    private boolean drawPhoto;
    private boolean drawLetter;
    private boolean drawGame;
    private boolean drawTexts;
    private boolean locked;

    private int personAsked;
    private int questionType;
    private int count;

    private boolean tripletHint;
    private boolean edHint =false;
    private int edTalk = 0;
    private boolean chadHint;
    private boolean leslieHint;

    private JTextField inputPassword;
    private JButton submit;


    public Screen() {
        try {
            background01 = ImageIO.read(new File("background01.jpeg"));
            roomIm = ImageIO.read(new File("room.jpeg"));
            photo = ImageIO.read(new File("photo.png"));
            letter = ImageIO.read(new File("letter.png"));
            game = ImageIO.read(new File("game.png"));
            texts = ImageIO.read(new File("texts.png"));
            lockedPage = ImageIO.read(new File("password.png"));

            npc1 = ImageIO.read(new File("npc01.png"));
            npc2 = ImageIO.read(new File("npc2.png"));
            npc3 = ImageIO.read(new File("npc3.png"));
            npc4 = ImageIO.read(new File("npc4.png"));
            npc5 = ImageIO.read(new File("npc5.png"));
        } catch (IOException e) {
            System.out.println("Image failed to load");
        }

        intro = true;
        explainMurder = false;
        npcIntro = false;
        searchRoom = false;
        drawPhoto = false;
        drawLetter = false;
        drawGame = false;
        drawTexts = false;
        locked = true;

        count = 0;
        questionType = 0;
        personAsked = 0;

        inputPassword = new JTextField("Password");
        inputPassword.setBounds(570, 230, 200, 50);
        inputPassword.addActionListener(this);
        inputPassword.setFocusable(true);

        this.add(inputPassword);
        inputPassword.setVisible(false);

        submit = new JButton("Enter");
        submit.setBounds(1030, 230, 100, 50);
        submit.addActionListener(this);
        submit.setBackground(Color.GRAY);
        submit.setOpaque(true);
        submit.setBorderPainted(false);
        this.add(submit);
        submit.setVisible(false);



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

        // background
        g.drawImage(background01, 0, 0, null);

        // introduction: should only run once
        if (intro == true) {
            g.drawImage(npc1, 50, 70, null);
            drawText(g, "There's a murder at the local hackathon! Can you help, detective?");
            g.drawString("1: Sure", 100, 690);
            g.drawString("2: Hard pass.", 100, 730);
        }

        // when 2 is pressed and explainMurder == true
        if (searchRoom == true) {
            g.drawImage(roomIm, -225, 0, null);
            drawText(g, "Tap on the correct objects to gain information");
            g.drawString("Press e to switch to interview", 100, 730);
        }

        // when intro == true and 1 is pressed
        if (explainMurder == true) {
            g.drawImage(npc1, 50, 70, null);
            drawText(g, "");

            g.drawString("Thank you for helping!! The victim was the 2-time winner Cody.", 50, 630);
            g.drawString("Just hours before this year's hackathon, he was found hacked to death! With an arm missing!",
                    50, 670);
            // also include that there were few suspects that came early and were here
            // during his death
            g.drawString("1: Check his room first", 50, 710);
            g.drawString("2: Meet suspects first", 50, 750);

        }

        if (drawPhoto == true) {
            // System.out.println("testing photo");
            g.drawImage(photo, 0, 0, null);
            edHint = true;
            drawText(g, "Press e to exit");
        } else if (drawLetter == true) {
            g.drawImage(letter, 0, 0, null);
            drawText(g, "Press e to exit");
            chadHint=true;
        } else if (drawGame == true) {
            g.drawImage(game, 500, 0, null);
            drawText(g, "Press e to exit");
        } else if (drawTexts == true) {
            if (locked == true) {
                // draw interface
                g.drawImage(lockedPage,0,0,null);
                drawText(g,"press white X to quit");
                g.setColor(Color.WHITE);
                g.drawString("X",1150,40);
                inputPassword.setVisible(true);
                submit.setVisible(true);

                



            } else {
                g.drawImage(texts, 200, 0, null);
                drawText(g, "Press e to exit");
            }
        }

        // explainMurder == true and 2 is pressed
        if (npcIntro == true) {
            // count 1-3 for npc introductions
            if (count == 0) {
                g.drawImage(npc2, 50, 240, null);
                drawText(g, "*raps* We're the technocratic three. We're spittin fire at this crime scene.");
                g.drawString("Press SPACE to continue", 100, 730);
            } else if (count == 1) {
                g.drawImage(npc3, 50, 70, null);
                drawText(g, "I'm Ed. *sings* Ed Sheeran.");
                g.drawString("Press SPACE to continue", 100, 730);
            } else if (count == 2) {
                g.drawImage(npc4, 50, 70, null);
                drawText(g, "Leslie. Can I go now?");
                g.drawString("Press SPACE to continue", 100, 730);
            } else if (count == 3) {
                g.drawImage(npc5, 50, 70, null);
                drawText(g, "Yo, Chad's the name, weightlifting is my gaaaame.");
                g.drawString("Press SPACE to continue", 100, 730);
            } else if (count == 4) { // count == 4 for specific questions
                // add pics if time
                // if 1 inputted and count == 4 and npcIntro == true
                drawText(g, "Pick a suspect to ask or press e to search victim's room");
                g.drawString("1: Technocratic Three, 2: Ed Sheeran, ", 100, 690);
                g.drawString("3: Leslie, 4: Chad", 100, 730);
            } else if (personAsked == 1) {
                if (questionType == 0) {
                    g.drawImage(npc2, 50, 240, null);
                    drawText(g, "Ask a question or press e to interview someone else");
                    g.drawString("1: How did you know Cody?", 100, 690);
                    g.drawString("2: Where were you when the murder occurred?", 100, 730);

                } else if (questionType == 1) {
                    g.drawImage(npc2, 50, 240, null);
                    drawText(g, "Cody was our greatest rival. Annoying little bugger.");
                    g.drawString("Press E to continue", 100, 730);
                } else if (questionType == 2) {
                    g.drawImage(npc2, 50, 240, null);
                    drawText(g, "We were...");
                    g.drawString("Press E to continue", 100, 730);
                }
            } else if (personAsked == 2) {
                if (questionType == 0) {
                    g.drawImage(npc3, 50, 70, null);
                    drawText(g, "");
                    g.drawString("Ask a question or press e to interview someone else", 100, 630);
                    g.drawString("1: How did you know Cody?", 100, 670);
                    g.drawString("2: Where were you when the murder occurred?", 100, 720);
                    if (edHint && edTalk == 1) {
                        g.drawString("3: You said you didn't know Cody. How come he has pictures of you?", 100, 770);
                    }
                    if (edHint && edTalk == 2) {
                        g.drawString("3: But you just said you didn't know him.", 100, 770);
                    }
                } else if (questionType == 1) {
                    g.drawImage(npc3, 50, 70, null);
                    drawText(g, "Who's Cody?");
                    edTalk =+ 1;
                    g.drawString("Press E to continue", 100, 730);
                } else if (questionType == 2) {
                    g.drawImage(npc3, 50, 70, null);
                    drawText(g, "We were...");
                    g.drawString("Press E to continue", 100, 730);
                } else if (questionType == 3) {
                    g.drawImage(npc3, 50, 70, null);
                    if (edTalk == 1){
                        drawText(g, "What are you insinuating?! Me and Cody are just friends!!");
                        edTalk =+ 1;
                        g.drawString("Press E to continue", 100, 730);
                    }
                    else if (edTalk == 2){
                        drawText(g, "Ok fine, I was in love with the shape of him...");
                        g.drawString("Press E to continue", 100, 730);
                    }


            } else if (personAsked == 3) {
                if (questionType == 0) {
                    g.drawImage(npc4, 50, 70, null);
                    drawText(g, "");
                    g.drawString("Ask a question or press e to interview someone else", 100, 630);
                    g.drawString("1: How did you know Cody?", 100, 670);
                    g.drawString("2: Where were you when the murder occurred?", 100, 720);
                    if (leslieHint) {
                        g.drawString("3: What were your texts to Cody this morning about? ", 100, 710);
                    }
                } else if (questionType == 1) {
                    g.drawImage(npc4, 50, 70, null);
                    drawText(g, "I was on his team. and we were dating. Real tragedy. Can I go now?");
                    g.drawString("Press E to continue", 100, 730);
                } else if (questionType == 2) {
                    g.drawImage(npc4, 50, 70, null);
                    drawText(g, "We were...");
                    g.drawString("Press E to continue", 100, 730);
                }
            } else if (personAsked == 4) {
                if (questionType == 0) {
                    g.drawImage(npc5, 50, 70, null);
                    drawText(g, "Ask a question or press e to interview someone else");
                    g.drawString("1: How did you know Cody?", 100, 690);
                    g.drawString("2: Where were you when the murder occurred?", 100, 730);

                } else if (questionType == 1) {
                    g.drawImage(npc5, 50, 70, null);
                    drawText(g, "We were bros. Leslie, Cody and I have had some chadreffic times.");
                    g.drawString("Press E to continue", 100, 730);
                } else if (questionType == 2) {
                    g.drawImage(npc5, 50, 70, null);
                    drawText(g, "We were...");
                    g.drawString("Press E to continue", 100, 730);
                }

            }
        }

    }
    repaint();
}

    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 49) { // 1
            if (intro == true) {
                intro = false;
                explainMurder = true;
            } else if (explainMurder == true) {
                explainMurder = false;
                searchRoom = true;
            } else if (count == 4) {
                personAsked = 1;
                count = -1;
            } else if (count == -1) {
                questionType = 1;
            }
        } else if (e.getKeyCode() == 50) { // 2
            if (intro == true) {
                System.exit(0);
            } else if (explainMurder == true) {
                npcIntro = true;
                explainMurder = false;
            } else if (count == 4) {
                personAsked = 2;
                count = -1;
            } else if (count == -1) {
                questionType = 2;
            }
        } else if (e.getKeyCode() == 51) { // 3
            if (count == 4) {
                personAsked = 3;
                count = -1;
            }
            else if (count == -1) {
                questionType = 3;
            }
        } else if (e.getKeyCode() == 52) {// 4
            if (count == 4) {
                personAsked = 4;
                count = -1;
            }
        } else if (e.getKeyCode() == 32) { // space
            if (count < 4 && count > -1) {
                count++;
            }
        } else if (e.getKeyCode() == 69) {// e
            if (count == -1 && questionType != 0) {
                questionType = 0;
            } else if (count == -1 && questionType == 0) {
                personAsked = 0;
                count = 4;
            } else if (count == 4) {
                searchRoom = true;
                personAsked = 0;
                count = 0;
                npcIntro = false;
            } else if (searchRoom == true && drawPhoto != true && drawLetter != true && drawGame != true
                    && drawTexts != true) {
                personAsked = 0;
                questionType = 0;
                count = 4;
                searchRoom = false;
                npcIntro = true;
            } else if (drawPhoto == true || drawLetter == true || drawGame == true || (drawTexts == true && locked == false)) {
                drawPhoto = false;
                drawLetter = false;
                drawGame = false;
                drawTexts = false;
            }
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
        System.out.println("X: " + e.getX() + "Y: " + e.getY());
        if (e.getX() >= 600 && e.getX() <= 800 && e.getY() >= 290 && e.getY() <= 390) {
            System.out.println("Computer");
            drawTexts = true;
        } else if (e.getX() >= 450 && e.getX() <= 580 && e.getY() >= 380 && e.getY() <= 430) {
            System.out.println("Letter");
            drawLetter = true;
        } else if (e.getX() >= 310 && e.getX() <= 550 && e.getY() >= 240 && e.getY() <= 360) {
            System.out.println("Billboard");
            drawPhoto = true;
        } else if (e.getX() >= 1050 && e.getX() <= 1380 && e.getY() >= 310 && e.getY() <= 580) {
            System.out.println("Arcade");
            drawGame = true;
        } else if(locked == true) {
            if(e.getX() >= 1135 && e.getX() <= 1180 && e.getY() >= 10 && e.getY() <= 45) {
                drawTexts = false;
                inputPassword.setVisible(false);
                submit.setVisible(false);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            try {
                int nums = Integer.parseInt(inputPassword.getText());
                if(nums == 436) {
                    locked = false;
                    inputPassword.setVisible(false);
                    submit.setVisible(false);
                }
            } catch(Exception ex) {
                System.out.println(ex);
            }
        }
        
    }

    public void drawText(Graphics g, String txt) {
        g.setColor(Color.WHITE);
        // g.fillRect(25,600,1400,200);
        g.fillRoundRect(25, 575, 1400, 200, 15, 15);
        g.setColor(Color.BLACK);
        // g.fillRect(0,600,5,200);
        // g.fillRect(0,600,1800,5);
        // g.fillRect(1435,600,5,200);
        // g.fillRect(0,795,1800,5);
        Font font = new Font("Century Schoolbook L", Font.PLAIN, 30);
        g.setFont(font);
        g.drawString(txt, 100, 650);
    }

}
