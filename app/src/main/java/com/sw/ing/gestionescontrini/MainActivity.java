package com.sw.ing.gestionescontrini;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    static int REQUEST_PHOTO = 1;
    Uri newPhoto;

    Button photoButton;
    FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //metodo che viene chiamato all'avvio dell'applicazione
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileManager = new FileManager(); //istanzio un oggetto della classe che gestisce i file 
        photoButton = (Button) findViewById(R.id.photo_button);  //oggetto corrispondente al bottone per scattare la foto
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) { //controllo i permessi
            //controllo i permessi, se l'utente non ha autorizzato l'app ad usare la fotocamera
            photoButton.setEnabled(false); //disabilito il bottone
            //richiedo i permessi
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
    }

    public void takePicture(View v){ //metodo che viene chiamato quando viene premuto il bottone "scatta foto"
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //creo l'intent
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){ //controllo che la camera sia disponibile
            newPhoto = Uri.fromFile(fileManager.getNewFileName());
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, newPhoto);
            startActivityForResult(takePictureIntent,REQUEST_PHOTO); //chiama l'Activity della fotocamera e attendo che l'utente abbia scattato la foto
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
        if (requestCode == REQUEST_PHOTO) {
            if (resultCode == RESULT_OK) {

            }
        }
    }
}
