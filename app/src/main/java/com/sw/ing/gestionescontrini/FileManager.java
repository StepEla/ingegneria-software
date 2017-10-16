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
        storageDir.mkdirs();
        File image = new File(storageDir,imageFileName+".jpg");
        try {
            image.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        Log.d(c.getResources().getString(R.string.debug_tag),"ID: "+maxId);
        return maxId;
    }
}
