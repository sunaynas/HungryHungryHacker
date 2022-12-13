package assets;

public class Question {
    Boolean revealed;
    String question;
    String[] answer;

    Question(String question, String[] ans) {
        this.revealed = false;
        this.question = question;
        this.answer = ans;
    }

    public String getQuestion() {
        return this.question;
    }

    public String[] getAnswer() {
        return this.answer;
    }

    public void hintRevealed() {
        this.revealed = true;
    }

    public Boolean getRevealedStatus() {
        return this.revealed;
    }
}
