package com.sw.ing.gestionescontrini;

import java.io.Serializable;
import java.net.URL;

/**
 * Classe Ticket contenente ID, data di inserimento dello scontrino e percorso del file contenente
 * l'immagine dello scontrino e nome del file.
 *
 * Created by matteo.mascotto on 14/10/2017.
 */

public class Ticket implements Serializable{
    int ID;
     String Date;
     String UrlPicture;
     String PictureName;

    public Ticket() {

    }

    public Ticket(int id, String Date, String UrlPicture, String string) {
        this.ID = id;
        this.Date = Date;
        this.PictureName = PictureName;
        this.UrlPicture = UrlPicture;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public  String getUrlPicture() {
        return UrlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        UrlPicture = urlPicture;
    }

    public  String getPictureName() {
        return PictureName;
    }

    public void setPictureName(String pictureName) {
        PictureName = pictureName;
    }
    public String toString(){
        return getPictureName()+" "+getDate();
    }

}
