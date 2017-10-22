import java.io.Serializable;

/**@author Marco Olivieri Federico Taschin
 * Interfaccia comune per utilizzare il database relativo agli scontrini.
 * Questa interfaccia definisce i metodi che la classe di gestione dei dati dovrà avere.  
 */
public interface IDatabase {
	
	/* Aggiunge uno scontrino nel database. Si deve occupare anche della gestione del salvataggio della foto. 
	 * L'implementazione del salvataggio della foto (file o database) è lasciata a discrezione. 
	 * @param ticket IPhotoItem oggetto da inserire nel database, non può essere null
	 * (Da definire quali campi dell'oggetto possono essere null e quali no)
	 * @return boolean, true se l'inserimento è andato a buon fine, false altrimenti
	*/
	public boolean addTicket(IPhotoItem ticket);
	
	/* Elimina uno scontrino dal database
	 * Se la foto non è salvata nel database, questo metodo si deve occupare della sua eliminazione 
	 * @param ticket IPhotoItem oggetto da eliminare dal database. L'oggetto può essere null, il campo  che identifica univocamente
	 * l'entità nel database non può essere null
	 * @return boolean, true se la rimozione è andata a buon fine, false altrimenti (es: l'oggetto non era presente nel database)
	*/
	public boolean deleteTicket(IPhotoItem ticket);
	
	/* Ritorna i dati di uno scontrino in base al suo identificatore
	 * !!Da decidere insieme quale deve essere l'identificatore di uno scontrino!!
 	 * Se si sceglie di utilizzare un ID, bisogna aggiungere all'interfaccia del gruppo 2 i metodi set e get di questo
	 * @param ID int (al momento proponiamo l'utilizzo di un identificatore di tipo INTEGER) l'ID dello scontrino a cui si vuole accedere 
	 * @return IPhotoItem oggetto contenente i dati dello sconntrino desiderato, restituisce null se non esiste 
	*/
	public IPhotoItem getTicket(int ID);
	
	/* Restituisce la lista degli scontrini presenti nel database
	 * @return List<IPhotoItem> oggetto List contenente tutti gli oggetti IPhotoItem corrispondenti agli scontrini nel database
	 * caso limite: restituisce una lista vuota (di dimensione zero, non un oggetto nullo!) nel caso i cui il database sia vuoto
	*/
	List<IPhotoItem> getAllTickets();
	
	/* Verifica se uno scontrino è già presente nel database
	 * @param IPhotoItem ticket oggetto di cui verificare la presenza nel database in base all'identificatore
	 * @return true se esiste già, false altrimenti
	*/
	boolean exists(IPhotoItem ticket);
}
