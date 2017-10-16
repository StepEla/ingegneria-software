package com.sw.ing.gestionescontrini;

import android.content.Context;

import java.io.File;
import java.io.IOException;
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
        int id = getNewPhotoId();
        String imageFileName = "photo"+ getNewPhotoId();
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

    @Override
    public boolean createTicketAndInsert(String path, String date) {
        Ticket ticket = new Ticket();
        ticket.setUrlPicture(path);
        ticket.setDate(date);
        ticket.setID(getNewPhotoId());
        dbmanager.addTicket(ticket);
        return false;
    }


    public int getNewPhotoId(){
        List<Ticket> tickets = dbmanager.getAllTickets();
        int maxId = 0;
        for(Ticket t : tickets){
            if (t.getID()>maxId)
                maxId = t.getID();
        }
        maxId++;
        return maxId;
    }

    public List<Ticket> getTickets(){
        return dbmanager.getAllTickets();
    }
}
