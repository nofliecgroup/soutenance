package fr.cda.projet;

import java.lang.ref.Reference;
import java.util.*;

// TODO: Auto-generated Javadoc
// Classe de definition d'une commande
/**
 * The Class Commande.
 */
//
public class Commande
{
    // Les caracteristiques d'une commande
    /** The numero. */
    //
    private int     numero;         // numero de la commande
    
    /** The date. */
    private String  date;           // date de la commande. Au format JJ/MM/AAAA
    
    /** The client. */
    private String  client;         // nom du client
    
    /** The references. */
    private ArrayList<String> references = new ArrayList<String>(); // les references des produits de la commande
    private ArrayList<Integer> quantite = new ArrayList<Integer>();
    
    private boolean isDelivred;
	    
    /**
     * Instantiates a new commande.
     *
     * @param numero the numero
     * @param date the date
     * @param client the client
     */
    //constructeur
    public Commande(int numero, String date, String client) {
		super();
		this.numero = numero;
		this.date = date;
		this.client = client;
		isDelivred = false;
		//this.references = references;
	}
    
    /**
     * Instantiates a new commande.
     */
    //constructeur vide
    public Commande() {
    	
    }

    public int size()
    {
    	return (references.size());
    }
    
    public String getReferenceByIndex(int index)
    {
    	if (index < 0 || index >= size())
    		return ("");
    	return (references.get(index));
    }
    
    public int getIndexByReference(String ref)
    {
    	for (int i = 0; i < size(); i++)
    		if (ref.equals(references.get(i)))
    			return (i);
    	return (-1);
    }
    
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "\nCommande:" + "          "+ numero+ "          \n" 	+"\nDate:           "+ date 
				+"\nClient:         "+ client 
				+"\nRefProduits:\n          "+  references + quantite +  "\n ----------------------\n";
	}



	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public int getNumero() {
		
		return numero;
	}

	/**
	 * Adds the references.
	 *
	 * @param reference the reference
	 */
	//method qui ajoute de reference sil exit pas necessaire de la ajoute deux fois 
	public void addReferences(String reference) {
		for (String ref : references)
			if (ref.equals(reference))
				return;
		references.add(reference + "\n");
	}
	
	public void addQuantite(int qty) {
		if (qty >= 0)
			quantite.add(qty);
		
	}
	
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public ArrayList<String> getReferences() {
		return references;
	}

	public void setReferences(ArrayList<String> references) {
		this.references = references;
	}

	public ArrayList<Integer> getQuantite() {
		return quantite;
	}

	public int getQuantite(String ref) {
		int index;
		
		index = this.getIndexByReference(ref);
		if (index == -1 || index >= size())
			return (-1);
		return (quantite.get(index));
	}
	
	public void setQuantite(String ref, int value) {
		int index;
		
		index = this.getIndexByReference(ref);
		if (index == -1 || index >= size())
			return ;
		quantite.set(index, value);
	}
	
	public void setQuantite(ArrayList<Integer> quantite) {
		this.quantite = quantite;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean delivred()
	{
		return (isDelivred);
	}
	
	public void setDelivred(boolean deliver)
	{
		isDelivred = deliver;
	}

}