package assets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import assets.Question;

public class Suspect extends Image {
    private String intro;
    private String[] alibi;
    private String codyRelation;
    private int numOfNewQuestions;

    private HashMap<Integer, Question> questions;

    Suspect(int x, int y, String filename, String intro, String whosCody, String[] alibi) {
        super(x, y, filename);
        this.intro = intro;
        this.codyRelation = whosCody;
        this.alibi = alibi;
        this.questions = new HashMap<Integer, Question>();

    }

    Suspect(int x, int y, String filename, String intro) {
        super(x, y, filename);
        this.intro = intro;
        this.questions = new HashMap<Integer, Question>();
    }

    public String getIntroText() {
        return this.intro;
    }

    public String getWhosCodyText() {
        return this.codyRelation;
    }

    public String[] getAlibi() {
        return this.alibi;
    }

    public void addQuestion(int num, String question, String[] answer) {
        this.questions.put(Integer.valueOf(num), new Question(question, answer));

        // System.out.println("Successfully added answer for question " + num + " as:");
        // for (int i = 0; i < answer.length; i++) {
        // System.out.println(answer[i]);
        // }
    }

    public void addQuestion(int num, String question, String answer) {
        String[] answerString = { answer };
        addQuestion(num, question, answerString);
    }

    public String getQuestion(int num) {
        Question q = this.questions.get(Integer.valueOf(num));
        return q.getQuestion();
    }

    public String[] getAnswer(int num) {
        Question q = this.questions.get(Integer.valueOf(num));
        return q.getAnswer();
    }

}
