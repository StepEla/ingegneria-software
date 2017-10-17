package com.sw.ing.gestionescontrini;

import java.io.File;

/**
 * Created by Task on 12/10/2017.
 */

public interface FileGestion {

    public File getNewFile();
    public Ticket createTicketAndInsert(String path, String date);
}
