package com.sw.ing.gestionescontrini;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.DatabaseErrorHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe di gestione/interazione con il database che contiene gli scontrini
 *
 * Created by matteo.mascotto on 14/10/2017.
 */

public class DBManager extends SQLiteOpenHelper {

    // Istanziazione delle variabili utili per la creazione del DB
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ticketManager";
    private static final String TABLE_NAME = "ticket";
    private static final String KEY_ID = "id_ticket";
    private static final String KEY_DATE = "dateTicket";
    private static final String KEY_URLPICTURE = "urlPicture";

    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override // Creazione del Database
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

    @Override // Cancellazione della tabella pre creazione del DB
    public void onUpgrade(SQLiteDatabase DB, int i, int j) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(DB);
    }

    // Inserimento di un nuovo scontrino tramite la foto
    void addTicket() {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, Ticket.getDate());
        values.put(KEY_URLPICTURE, Ticket.getUrlPicture());

        DB.insert(TABLE_NAME, null, values);
        DB.close();
    }

    // Cancellazione di uno scontrino

    // Restituisce la lista degli scontrini presenti
    public List<Ticket> getAllTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        String SelectQuery;

        SelectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery(SelectQuery, null);

        // Se è presente almeno un elemento all'interno del DB inizia a ciclare aggiungendo gli
        // elementi all'interno della lista, termina quando la condizione che ci sia un prossimo
        // elemento da visitare non è verificata
        if (cursor.moveToFirst()) {
            do {
                Ticket ticket = new Ticket();
                ticket.setID(Integer.parseInt(cursor.getString(0))); // 0 - prima colonna
                ticket.setDate(cursor.getString(1)); // 1 - seconda colonna
                ticket.setUrlPicture(cursor.getString(2)); // 2 - terza colonna

                ticketList.add(ticket);

            } while (cursor.moveToNext());
        }

        return ticketList;
    }

}
