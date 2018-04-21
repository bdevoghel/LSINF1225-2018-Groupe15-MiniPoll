package p.poll;

import android.app.Application;
/**
 * Created by Nicolas on 21/04/2018.
 */

public class Pollapp extends Application
{
    /**
     * Référence au contexte de l'application
     */
    private static Pollapp context;

    /**
     * Fournit le contexte de l'application.
     *
     * @return Contexte de l'application.
     */
    public static Pollapp getContext()
    {
        return context;
    }

    public void onCreate() {
        super.onCreate();
        context = (Pollapp) getApplicationContext();
    }
}
