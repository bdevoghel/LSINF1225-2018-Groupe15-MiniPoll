package p.poll.model;

import java.util.ArrayList;

/**
 * Created by Vahid Beyraghi on 22-04-18.
 */

public class Quizz extends Poll {
    //Attributs
    private ArrayList<Question> questionList;

    //Constructeurs
    public Quizz(){
        super();
        questionList=new ArrayList();
    }
    public Quizz(Question question){
        super();
        questionList=new ArrayList();
        this.addQuestion(question);
    }
    public Quizz(String title, String description, java.sql.Date deadline,
                  char type, User owner){
        super(title, description, deadline, type, owner);
        questionList=new ArrayList();
    }
    public Quizz(String title, String description, java.sql.Date deadline,
                  char type, User owner, Question question){
        super(title, description, deadline, type, owner);
        questionList=new ArrayList();
        this.addQuestion(question);
    }
    public Quizz(String title, String description, java.sql.Date deadline,
                 char type, User owner, ArrayList<Question> questions){
        super(title, description, deadline, type, owner);
        questionList=questions;
    }

    //Getteurs et setteurs
    public void setQuestionList(ArrayList<Question> questions){
        this.questionList=questions;
    }
    public ArrayList<Question> getQuestionList(){
        return questionList;
    }

    //Addeurs et removers
    public void addQuestion(Question question){
        questionList.add(question);
    }
    public void addQuestion(ArrayList<Question> questions){
        for(int i=0;i<questions.size();i++){
            this.addQuestion(questions.get(i));
        }
    }
    public void rmvQuestion(Question question){
        questionList.remove(question);
    }
    public void rmvQuestion(ArrayList<Question> questions){
        for(int i=0;i<questions.size();i++){
            this.rmvQuestion(questions.get(i));
        }
    }
}
