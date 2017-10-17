package com.sw.ing.gestionescontrini;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import junit.framework.Test;

import java.io.File;

/**
 * Activity per visualizzare il singolo scontrino
 *
 * Created by marco.olivieri on 17/10/2017.
 */

public class ViewTicketActivity extends AppCompatActivity {

    private String ticketName;
    private String ticketURL;

    private FileManager fileManager;
    private File filePhoto;

    private EditText text;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);

        Intent intent = getIntent();
        Ticket t = (Ticket) intent.getSerializableExtra(MainActivity.SEND_TICKET_TO_ACTIVITY);
        viewTicket(t);
    }

    //riceve lo scontrino selezionato e lo visualizza a schermo
    public void viewTicket(Ticket t){
        ticketName=t.getPictureName();
        ticketURL=t.getPictureName();

        filePhoto= new File(ticketURL);
        Uri path = Uri.fromFile(filePhoto);
        text=(EditText) findViewById(R.id.textTicket);
        image=(ImageView) findViewById(R.id.ticketImage);


        text.setText(ticketName);
        image.setImageURI(path);

    }


}
