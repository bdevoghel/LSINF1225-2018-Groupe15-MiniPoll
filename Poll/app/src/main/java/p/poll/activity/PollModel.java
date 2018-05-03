package p.poll.activity;

/**
 * Created by Nicolas on 03/05/2018.
 */

public class PollModel {
    private int color;
    private String pseudo;
    private String text;

    public PollModel(int color, String pseudo, String text) {
        this.color = color;
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
    public int getColor()
    {
        return this.color;
    }
}
