package p.poll.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import p.poll.MySQLiteHelper;

/**
 * Created by Vahid Beyraghi on 21-04-18.
 */

public class Question {

    //Attributs
    private String title;
    private int idQuestion;
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
    public Question(String title, ArrayList<Proposition> propositionList,int idQuestion){
        this.title=title;
        this.PropositionList=PropositionList;
        this.idQuestion=idQuestion;
    }


    //Getteurs et setteurs
    public void setIdQuestion(int id){idQuestion=id;}
    public int getIdQuestion(){return idQuestion;}
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

    //DataBase

    public static int setIdQuestion(String idpoll){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        int id = 1;
        String[] colonnes = {"idquestion"};
        Cursor cursor = db.query("Question_list", colonnes, null, null,null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String idnew = cursor.getString(0);
            if(Integer.parseInt(idnew)>id)
            {
                id=Integer.parseInt(idnew);
            }
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return id+1;
    }

}
