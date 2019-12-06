package com.londonappbrewery.quizzler;

class TrueFalse {
    private int QuestionID;
    private boolean Answer;

    TrueFalse(int ques, boolean ans) {
        QuestionID = ques;
        Answer = ans;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public boolean isAnswer() {
        return Answer;
    }

    public void setAnswer(boolean answer) {
        Answer = answer;
    }
}
