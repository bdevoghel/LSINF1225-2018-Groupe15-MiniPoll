package p.poll.model;

/**
 * Created by Vahid Beyraghi on 22-04-18.
 */

public class Proposition {

    //Attributs
    private int tag;
    private String answer;

    //Constructeurs
    public Proposition(){}
    public Proposition(int tag){
        this.tag=tag;
    }
    public Proposition(String answer){
        this.answer=answer;
    }
    public Proposition(int tag, String answer){
        this.tag=tag;
        this.answer=answer;
    }

    //Getteurs et setteurs
    public void setTag(int tag){
        this.tag=tag;
    }
    public int getTag(){
        return tag;
    }
    public void setAnswer(String answerText){
        answer=answerText;
    }
    public String getAnswer(){
        return answer;
    }

    //Redefinition de la methode equals
    public boolean equals(Object o){
        if(o!=null){
            if(o instanceof Proposition){
                if(((Proposition) o).getAnswer()==answer && ((Proposition) o).getTag()==tag){
                    return true;
                }
            }
        }
        return false;
    }
}
