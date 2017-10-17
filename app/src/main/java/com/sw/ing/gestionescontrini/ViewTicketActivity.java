package com.sw.ing.gestionescontrini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import junit.framework.Test;

/**
 * Activity per visualizzare il singolo scontrino
 *
 * Created by marco.olivieri on 17/10/2017.
 */

public class ViewTicketActivity extends AppCompatActivity {

    private static String RESULT_NAME = "com.sw.ing.gestionescontrini.ticket_name";
    private String ticketName;
    private String ticketURL;

    private EditText text;
    private ImageView image;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);
    }

    //riceve lo scontrino selezionato e lo visualizza a schermo
    public void viewTicket(Ticket t){
        ticketName=t.getPictureName();
        ticketURL=t.getPictureName();
        text=(EditText) findViewById(R.id.textTicket);
        image=(ImageView) findViewById(R.id.ticketImage);

        text.setText(ticketName);
        //SERVE UN URI PER IMMAGINE
        //image.setImageURI(ticketURL);

    }

    private void exit(){
        data = new Intent();
        data.putExtra(RESULT_NAME,ticketName);
        setResult(RESULT_OK,data);
        finish();
    }
}
