package com.sw.ing.gestionescontrini;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import junit.framework.Test;

import java.io.File;

import static java.security.AccessController.getContext;

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

    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);

        Intent intent = getIntent();
        Ticket t = (Ticket) intent.getSerializableExtra(MainActivity.SEND_TICKET_TO_ACTIVITY);
        Log.d(getResources().getString(R.string.debug_tag),"PERCORSO FOTO: "+t.getUrlPicture());
        viewTicket(t);
    }

    //riceve lo scontrino selezionato e lo visualizza a schermo
    public void viewTicket(Ticket t){
        ticketName=t.getPictureName();
        ticketURL=t.getUrlPicture();

        filePhoto= new File(ticketURL);
        Uri path = Uri.fromFile(filePhoto);
        text=(TextView) findViewById(R.id.nome_scontrino);
        image=(ImageView) findViewById(R.id.ticketImage);


        text.setText(ticketName);
        Bitmap bmImg = BitmapFactory.decodeFile(ticketURL);
        image.setImageBitmap(bmImg);
        //Picasso.with(this).load(path).fit().into(image);
        //image.setImageURI(path);

    }


}
