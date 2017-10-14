package com.sw.ing.gestionescontrini;

import java.net.URL;

/**
 * Classe Ticket contenente ID, data di inserimento dello scontrino e percorso del file contenente
 * l'immagine dello scontrino
 *
 * Created by matteo.mascotto on 14/10/2017.
 */

public class Ticket {
    int ID;
    static String Date; // MAT## verificare l'utilizzo della variabile di tipo statico, messo perchè
    // non getDate e getUrlPicture non permettevano la restituzione di tali valori
    static String UrlPicture;

    public Ticket() {

    }

    public Ticket(int id, String Date, String UrlPicture) {
        this.ID = id;
        this.Date = Date;
        this.UrlPicture = UrlPicture;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public static String getUrlPicture() {
        return UrlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        UrlPicture = urlPicture;
    }
}
