package p.poll.model;

/**
 * Created by Vahid Beyraghi on 22-04-18.
 */

public class Survey extends Poll {
    //Attributs
    private final int NUMBER_PROPOSITION=5;
    private Question question;

    //Constructeurs
    public Survey(){
        super();
    }
    public Survey(Question question){
        super();
        this.question=question;
    }
    public Survey(String title, String description, java.sql.Date deadline,
                  char type, User owner){
        super(title, description, deadline, type, owner);
    }
    public Survey(String title, String description, java.sql.Date deadline,
                  char type, User owner, Question question){
        super(title, description, deadline, type, owner);
        this.question=question;
    }

    //Getteurs et setteurs
    public void setQuestion(Question question){
        this.question=question;
    }
    public Question getQuestion(){
        return question;
    }
    public int getNUMBER_PROPOSITION(){
        return NUMBER_PROPOSITION;
    }
}
