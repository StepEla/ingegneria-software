package com.sw.ing.gestionescontrini;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int REQUEST_PHOTO = 1;
    private File newPhoto;
    private FileManager fileManager;
    private List<Ticket> tickets;
    private ArrayList<String> listViewContent;

    //Components
    private Button photoButton;
    private ListView listView;
    private ArrayAdapter<Ticket> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //metodo che viene chiamato all'avvio dell'applicazione
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoButton = findViewById(R.id.photo_button);  //oggetto corrispondente al bottone per scattare la foto
        listView = findViewById(R.id.list);
        fileManager = new FileManager(this); //istanzio un oggetto della classe che gestisce i file
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) { //controllo i permessi
            //controllo i permessi, se l'utente non ha autorizzato l'app ad usare la fotocamera
            photoButton.setEnabled(false); //disabilito il bottone
            //richiedo i permessi
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        loadTickets();
        adapter = new ArrayAdapter<Ticket>(this, android.R.layout.simple_list_item_1, tickets);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Ticket t = (Ticket)listView.getItemAtPosition(position);
                chiamaViewTicketActivity(t);
            }
        });
    }



        public void takePicture(View v){ //metodo che viene chiamato quando viene premuto il bottone "scatta foto"
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //creo l'intent

        if(takePictureIntent.resolveActivity(getPackageManager())!=null){ //controllo che la camera sia disponibile
            newPhoto = fileManager.getNewFile();
            Uri imageUri = Uri.fromFile(newPhoto);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            Log.d(getResources().getString(R.string.debug_tag),newPhoto.getAbsolutePath());
            startActivityForResult(takePictureIntent, REQUEST_PHOTO); //chiama l'Activity della fotocamera e attendo che l'utente abbia scattato la foto

            /*
            SUGGERIMENTO PER FEDE: ho ipotizzato un controllo di questo tipo. Avendo gestito te questa parte di codice lascio
            a te valutare se può andar bene e come inserirla nel modo corretto all'interno del codice
            // Controllo se il dispositivo supporta la memoria esterna e se è presente
            Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

            if (isSDPresent && isSDSupportedDevice) {
                startActivityForResult(takePictureIntent, REQUEST_PHOTO); //chiama l'Activity della fotocamera e attendo che l'utente abbia scattato la foto
            } else {
                // E' necessario gestire il salvataggio in locale della fotocamera
            }
            */
        }
    }

    @Override //metodo che riconosce quando l'utente ha autorizzato l'applicazione ad utilizzare la fotocamera
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED // controlla i permessi
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                photoButton.setEnabled(true); //abilita il bottone
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==REQUEST_PHOTO && resultCode == RESULT_OK){
            String timeStamp = new SimpleDateFormat("dd.MM.yyyy 'ore' HH:mm").format(new Date());
            String path = newPhoto.getAbsolutePath();
            addTicketToView(fileManager.createTicketAndInsert(path,timeStamp));
        }
    }

    public void addTicketToView(Ticket t){
        tickets.add(t);
        adapter.notifyDataSetChanged();
    }

    private void loadTickets(){
        tickets = fileManager.getTickets();
        for(Ticket t : tickets){
            Log.d(getResources().getString(R.string.debug_tag),"Foto in db: ");
            Log.d(getResources().getString(R.string.debug_tag),"ID: "+t.getID());
            Log.d(getResources().getString(R.string.debug_tag),"Nome "+t.getPictureName());
            Log.d(getResources().getString(R.string.debug_tag),"URL: "+t.getUrlPicture());
        }
    }

    //chiama activity che visualizza lo scontrino selezionato
    //scritto da Olivieri con la spiegazione di Taschin
    public void chiamaViewTicketActivity(Ticket t){
        Intent intent = new Intent();
        intent.putExtra("PassaggioTicket",t);
        startActivity(intent);
    }
    

    @Override
    protected void onResume() {
        super.onResume();
        tickets = fileManager.getTickets();
    }
}
