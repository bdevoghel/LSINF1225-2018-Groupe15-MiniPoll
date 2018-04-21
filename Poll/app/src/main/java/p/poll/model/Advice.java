package p.poll.model;

import java.util.ArrayList;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class Advice extends Poll {

    //Attributs
    private final int NUMBER_PROPOSITION=2;
    private Question question;

    //Constructeurs
    public Advice(){
        super();
    }
    public Advice(String title, String description, java.sql.Date deadline,
                  Character type, User owner) {
        super(title, description, deadline, type, owner);
    }
    public Advice(String title, String description, java.sql.Date deadline,
                  Character type, User owner, Question question){
        super(title, description, deadline, type, owner);
        this.question=question;
    }
    public Advice(Question question){
        super();
        this.question=question;
    }

    //Getteurs et setteurs
    public void setQuestion(Question question){
        this.question=question;
    }
    public Question getQuestion(){
        return question;
    }
    public int getNUMBER_PROPOSITION() {
        return NUMBER_PROPOSITION;
    }
}
