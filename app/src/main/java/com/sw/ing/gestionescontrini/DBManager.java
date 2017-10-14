package com.sw.ing.gestionescontrini;

import android.database.sqlite.SQLiteDatabase;

/**
 * Classe di gestione/interazione con il database che contiene gli scontrini
 *
 * Created by matteo.mascotto on 14/10/2017.
 */

public class DBManager {

    // Istanziazione delle variabili utili per la creazione del DB
    private static final String DB_NAME = "ticketManager";
    private static final String TABLE_NAME = "ticket";
    private static final String KEY_ID = "id_ticket";
    private static final String KEY_DATE = "dateTicket";
    private static final String KEY_URLPICTURE = "urlPicture";

    // Creazione del Database
    public void onCreate(SQLiteDatabase db) {
        String CreateTable;

        CreateTable = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY, " +
                KEY_DATE + " TEXT, " +
                KEY_URLPICTURE + " TEXT);";
        // !! La data è di tipo text perchè in SQLLite non esiste un tipo di dato datetime.
        // Sarà cura dello sviluppatore prestare attenzione al format della data in fase di inserimento

        db.execSQL(CreateTable);
    }

    // Inserimento di un nuovo scontrino tramite la foto
    void addTicket(){

    }

    // Cancellazione di uno scontrino
}
