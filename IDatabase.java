import java.io.Serializable;

/**
 * Interfaccia comune per utilizzare il database relativo agli scontrini.
 */
public interface IPhotoItem extends Serializable {
	
	//aggiunge scontrino al db
	//true se aggiunto correttamente
	boolean addTicket(IPhotoItem ticket);
	
	//elimina scontrino dal db
	boolean delateTicket(IPhotoItem ticket);
	
	//IMPORTANTE !!
	//bisogna far aggiungere alla interfaccia del gruppo 2 la gestione del ID (in particolare set e get)
	//restituisce uno scontrino tramite id
	IPhotoItem getTicket(int ID);
	
	//restituisce tutti gli scontrini da db
	List<IPhotoItem> getAllTickets();
	
	//verifica se esiste già
	boolean isTicket(IPhotoItem ticket);
}