package p.poll.activity;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class PollModel {
    private String picture=null;
    private String pseudo;
    private String text;

    public PollModel(String color, String pseudo, String text) {
        this.picture = color;
        this.pseudo = pseudo;
        this.text = text;
    }

    public String getPseudo()
    {
        return this.pseudo;
    }
    public String getText()
    {
        return this.text;
    }
    public String getPicture()
    {
        return this.picture;
    }
}
