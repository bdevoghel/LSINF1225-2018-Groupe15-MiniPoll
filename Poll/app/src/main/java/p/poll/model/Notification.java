package p.poll.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import p.poll.MySQLiteHelper;
import p.poll.R;
import p.poll.model.struc.LimitedNumber;
import p.poll.model.struc.ListNumber;

public class Notification{

    //Attributs
    private String text;
    private int state;
    private String username;
    private int poll;

    //Constructeurs
    public Notification(){
    }
    public Notification(String text, int state, String username, int poll){
        this.text=text;
        this.state=state;
        this.username=username;
        this.poll=poll;
    }

    //Redefinition de la methode equals
    public boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Notification) {
                if (((Notification) o).getText() == (this.getText())) {
                    return true;
                }
            }
        }
        return false;
    }

    //Getteurs et setteurs
    public int getState(){
        return this.state;
    }
    public void setState(int i){
        this.state=i;
    }
    public String getText(){
        return this.text;
    }
    public void setText(String text){
        this.text=text;
    }
    public int getPoll(){
        return poll;
    }
    public void setPoll(int p){
        poll=p;
    }
    public void setUsername(String s){
        username=s;
    }
    public String getUsername()
    {
        return username;
    }

    public static ArrayList<Notification> getNotifications(){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Notification> notifications = new ArrayList<>();
        String[] colonnes = {"message", "username_notif", "poll_notif"};
        Cursor cursor = db.query("Notification", colonnes, "username"+"=? AND etat=?", new String[]{User.loggedUser.getUsername(),String.valueOf(0)}, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String uMessage = cursor.getString(0);
            String uUsernameNotif = cursor.getString(1);
            String uPoll_notif = cursor.getString(2);
            if(uPoll_notif==null)
            {
                notifications.add(new Notification(uMessage,0,uUsernameNotif,0));
                Log.i("display",uMessage);
            }
            else
            {
                notifications.add(new Notification(uMessage,0,uUsernameNotif,Integer.parseInt(uPoll_notif)));
            }
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return notifications;
    }

    public static void setDone(Notification n){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ContentValues values = new ContentValues();
        String username=n.getUsername();
        String text=n.getText();
        String poll=String.valueOf(n.getPoll());
        String state=String.valueOf(1);
        values.put("etat", state);
        values.put("message", text);
        values.put("username_notif", username);
        values.put("poll_notif", poll);
        Log.i("test","update");
        db.update("Notification", values, "username=? AND message=?", new String[]{User.loggedUser.getUsername(),n.getText()});
        Log.i("test","done");
        db.close();
    }

    public static String[] getText(ArrayList<Notification> n){
        String[]  s= new String[n.size()];
        for(int i=0;i<n.size();i++){
            s[i]=n.get(i).getText();
        }
        return s;
    }

    public static Integer[] getImage(ArrayList<Notification> n)
    {
        Integer[] in=new Integer[n.size()];
        for(int i=0;i<n.size();i++)
        {
            if(n.get(i).getPoll()==0)
            {
                in[i]= R.drawable.default_pic;
            }
            else
            {
                in[i]=R.drawable.launcher;
            }
        }
        return in;
    }
}