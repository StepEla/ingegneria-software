package com.sw.ing.gestionescontrini;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Task on 12/10/2017.
 */

public class FileManager implements FileGestion{

    private DBManager dbmanager;
    private Context c;
    public FileManager(Context c){
        this.c = c;
        dbmanager = new DBManager(c);
    }

    @Override
    public File getNewFile(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "photo"+getLastPhotoId();
        File storageDir = new File(c.getResources().getString(R.string.photo_directory));
        if(!storageDir.mkdirs()){
            Log.d(c.getResources().getString(R.string.debug_tag),"Impossibile creare");
        }
        File image = null;
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
            if(!image.exists()){
                Log.d(c.getResources().getString(R.string.debug_tag),"Il file non esiste");
            }
        }catch (IOException e){
            Log.d(c.getResources().getString(R.string.debug_tag),e.getMessage());
        }

        // Save a file: path for use with ACTION_VIEW intents
        return image;
    }

    public int getLastPhotoId(){
        List<Ticket> tickets = dbmanager.getAllTickets();
        int maxId = 0;
        for(Ticket t : tickets){
            if (t.getID()>maxId)
                maxId = t.getID();
        }
        maxId++;
        return maxId;
    }
}
