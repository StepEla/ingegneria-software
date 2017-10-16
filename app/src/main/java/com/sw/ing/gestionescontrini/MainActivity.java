package com.sw.ing.gestionescontrini;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static int REQUEST_PHOTO = 1;
    private File newPhoto;
    private FileManager fileManager;
    public static String CAPTURE_IMAGE_FILE_PROVIDER = "com.sw.ing.gestionescontrini";

    //Components
    private Button photoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //metodo che viene chiamato all'avvio dell'applicazione
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileManager = new FileManager(this); //istanzio un oggetto della classe che gestisce i file
        photoButton = (Button) findViewById(R.id.photo_button);  //oggetto corrispondente al bottone per scattare la foto
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) { //controllo i permessi
            //controllo i permessi, se l'utente non ha autorizzato l'app ad usare la fotocamera
            photoButton.setEnabled(false); //disabilito il bottone
            //richiedo i permessi
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

    }

    public void takePicture(View v){ //metodo che viene chiamato quando viene premuto il bottone "scatta foto"
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //creo l'intent
        if(takePictureIntent.resolveActivity(getPackageManager())!=null){ //controllo che la camera sia disponibile
            newPhoto = fileManager.getNewFile();
            Log.d(getResources().getString(R.string.debug_tag),newPhoto.getAbsolutePath());
            Uri imageUri = Uri.fromFile(newPhoto);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            Log.d(getResources().getString(R.string.debug_tag),newPhoto.getAbsolutePath());
            startActivityForResult(takePictureIntent,REQUEST_PHOTO); //chiama l'Activity della fotocamera e attendo che l'utente abbia scattato la foto
            Log.d(getResources().getString(R.string.debug_tag),"Activity called");
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
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
