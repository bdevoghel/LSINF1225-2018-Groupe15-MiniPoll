package p.poll.model;

import java.util.ArrayList;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class Question {

    //Attributs
    private String title;
    private ArrayList<Proposition> PropositionList;

    //Constructeurs
    public Question(){
        PropositionList=new ArrayList<>();
    }
    public Question(String title) {
        this.title = title;
        PropositionList=new ArrayList<>();
    }
    public Question(String title, Proposition proposition){
        this.title=title;
        PropositionList=new ArrayList<>();
        this.addProposition(proposition);
    }
    public Question(String title, ArrayList<Proposition> propositionList){
        this.title=title;
        this.PropositionList=PropositionList;
    }

    //Getteurs et setteurs
    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }
    public ArrayList<Proposition> getPropositionList(){
        return PropositionList;
    }
    public void setPropositionList(ArrayList<Proposition> propositionList){
        this.PropositionList=propositionList;
    }

    //Addeurs et removers
    public void addProposition(Proposition proposition){
        PropositionList.add(proposition);
    }
    public void addProposition(ArrayList<Proposition> propositionList){
        for(int i=0;i<propositionList.size();i++){
            this.addProposition(propositionList.get(i));
        }
    }
    public void rmvProposition(Proposition proposition){
        PropositionList.remove(proposition);
    }
    public void rmvProposition(ArrayList<Proposition> propositions){
        for(int i=0;i<propositions.size();i++){
            this.rmvProposition(propositions.get(i));
        }
    }
    //Redefinition de la methode equals
    public boolean equals(Object o){
        if (o != null) {
            if (o instanceof Question) {
                if (((Question) o).getTitle() == (this.getTitle())) {
                        return true;
                }
            }
        }
        return false;
    }
}