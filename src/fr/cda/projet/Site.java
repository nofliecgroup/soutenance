package fr.cda.projet;

import java.util.*;

import fr.cda.util.*;

// TODO: Auto-generated Javadoc
// Classe de definition du site de vente
/**
 * The Class Site.
 */
//
public class Site
{
    
    /** The stock. */
    private ArrayList<Produit> stock;       // Les produits du stock
    
    /** The commandes. */
    private ArrayList<Commande> commandes;  // Les bons de commande

    // Constructeur
    /**
     * Instantiates a new site.
     */
    //
    public Site()
    {
        stock = new ArrayList<Produit>();
        commandes = new ArrayList<Commande>();

        // lecture du fichier data/Produits.txt
        //  pour chaque ligne on cree un Produit que l'on ajoute a stock
        initialiserStock("data/Produits.txt");

        //  lecture du fichier data/Commandes.txt
        //  pour chaque ligne on cree une commande ou on ajoute une reference
        //  d'un produit a une commande existante.
        initialiserCommandes("data/Commandes.txt");

    }
    

	// Methode qui retourne sous la forme d'une chaine de caractere
    //  tous les produits du stock
    /**
	 * Lister tous produits.
	 *
	 * @return the string
	 */
	//
    public String listerTousProduits()
    {
        String res="";
        for(Produit prod : stock)
            res=res+prod.toString()+"\n";

        return res;
    }

    // Methode qui retourne sous la forme d'une chaine de caractere
    //  toutes les commandes
    /**
     * Lister toutes commandes.
     *
     * @return the string
     */
    //
    public String listerToutesCommandes()
    {
    	  String res="";
          for(Commande cmd : commandes)
              res=res+cmd.toString()+"\n";

          return res;
    }

    /**
     * Lister commande.
     *
     * @param numero the numero
     * @return the string
     */
    // Methode qui retourne sous la forme d'une chaine de caractere  une commande
     public String listerCommande(int numero)
    {
    	String res="";
    	for(Commande cmd: commandes) {
    		if(cmd.getNumero() == numero) {
    			//sres = res+=numero;
    			res = " " +cmd;
    			//res = res + "Cette Command n'exist pas.\n" + cmd;
    			return res;
    		} 
    		
    	}
		return "Cette Command n'exist pas.\n saissiser un numero pour cherche";
        //String res="Cette methode n'est pas codee\n";
        //res=res+"Numero de commande : "+numero+"\n";
        //res=res+"Elle doit retourner le contenu d'une commande\n";
       // res = res + numero;
        //return res;
    } 
  
    

    /**
     * Initialiser stock.
     *
     * @param nomFichier the nom fichier
     */
    // Chargement du fichier de stock
    private void initialiserStock(String nomFichier)
    {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);
        for(String ligne :lignes)
            {
                System.out.println(ligne);
                String[] champs = ligne.split("[;]",4);
                String reference = champs[0];
                String nom = champs[1];
                double prix = Double.parseDouble(champs[2]);
                int quantite =  Integer.parseInt(champs[3]);
                Produit p = new Produit(reference,nom,prix,quantite);
                stock.add(p);
            }
    }// end of method 
    
    /**
     * Initialiser commandes.
     *
     * @param nomFichier the nom fichier
     */
    /* Cette method initialised  le bon des command du ficher donc quon ajute a 
     * la list des commands 
      */
    private void initialiserCommandes(String nomFichier) {
    	boolean trouve;
		String [] lignesCommands = Terminal.lireFichierTexte(nomFichier);
		for(String lcmd: lignesCommands) {
			System.out.println("List of commands " +lcmd);
			 String[] champs = lcmd.split("[;=]",5);
			   int numero =  Integer.parseInt(champs[0]);
			    String date = champs[1];
		        String client = champs[2];
		        String reference = champs[3];
		        int quantite = Integer.parseInt(champs[4]);
		        trouve = false;
		        for(Commande comm: commandes) {
		        	if(comm.getNumero() == numero) {
		        		comm.addReferences(reference);
		        		comm.addQuantite(quantite);
		           		trouve = true;
		        		break;
		        	} else {
		        	
		        		//System.out.println("Ref Not Found");
		        	}
		        }
             
		        if (trouve == false)
		        {
		        Commande cmd = new Commande(numero,date,client);
                cmd.addReferences(reference);
                cmd.getQuantite().add(quantite);
               //cmd.commandDelivered(numero, reference);
                commandes.add(cmd);

		        }
			
		}
		
   	} // end of method initialization of commands.
    
  
    public int getIndexProduitByReference(String ref)
    {
    	if (ref == null || ref.length() == 0)
    		return (-1);
    	for (int i = 0; i < stock.size(); i++)
    		if (stock.get(i).getReference().equals(ref))
    			return (i);
    	return (-1);
    }
    
    public Produit getProduitByReference(String ref)
    {
    	if (ref == null || ref.length() == 0)
    		return (null);
    	for (int i = 0; i < stock.size(); i++)
    		if (stock.get(i).getReference().equals(ref))
    			return (stock.get(i));
    	return (null);
    }
    
    public int getIndexCommandeByNumero(int numero)
    {
    	for (int i = 0; i < stock.size(); i++)
    		if (commandes.get(i).getNumero() == numero)
    			return (i);
    	return (-1);
    }
    
    public Commande getCommandeByNumero(int numero)
    {
    	for (int i = 0; i < stock.size(); i++)
    		if (commandes.get(i).getNumero() == numero)
    			return (commandes.get(i));
    	return (null);
    }
    
    public String isDelivered() {
    	String res = "Les Commandes non livres: \n";
    	res = res + "===================== \n";
    	for(Commande c: commandes) {
    		if (!c.delivred()) {
    			boolean valide = true;
    			String info = "";
    			for(String ref: c.getReferences()) {
    				String [] champs= ref.split("=", 2);
    				String reference = champs[0];
    				//System.out.println(reference + "????");
    				int quantite = Integer.parseInt(champs[1]);
    				
    				System.out.println(reference + quantite + "????");
    				
    				Produit p = getProduitByReference(reference);
    				
    				int calc = p.getQuantite() - quantite;
    				if(calc < 0) {
    					valide = false;
    					info = info + "il manque " + calc + " " + champs[0] + "\n";
   
    					}
    					
    					}
    					if(valide) {
    						c.setDelivred(true);
    						for(String ref: c.getReferences()) {
    							String [] champs= ref.split("=", 2);
    							String reference = champs[0];
    							int quantite = Integer.parseInt(champs[1]);
	    				
    							Produit p = getProduitByReference(reference);
    							p.setQuantite(p.getQuantite() - quantite);
    				
    						}
    						
    					}else {
    						c.setDelivred(false);
    						res = res + c + "\n";
    					}
    					
    					
    				}
    	}
    	

    	return res;
    }

  /**
   * Command delivered.
   *
   * @param numero the numero
   * @return true, if successful
   */
  //Le method qui me permet de savoir si la commands  a ete livree  ou non
 /*
    public boolean commandDelivered(int numero) {
  		Commande c;
  		
  		c = getCommandeByNumero(numero);
  		if (c == null)
  			return (false);
  		return (c.delivred());

  		/*
  		boolean isDelivered = false;
  		for (int i = 0; i < stock.size(); i++) {
             String productRefs = stock.get(i).getReference();
             int id = commandes.get(i).getNumero();
             
             if(numero == id) {
            	 isDelivered = true;
            	 System.out.println("Item is delivered..." + numero + " " + id);
             }else if(numero < id){
            	 System.out.println("Item is not delivered " + numero + " " + id );
             } else {
            	 System.out.println("Command non exist " + numero );
             }
          }
          return isDelivered;
  		
  	}
*/
  	public void setCommande(Commande c)
  	{
  		int i; 

  		i = 0;
  		while (i < commandes.size())
  		{
  			if (commandes.get(i).getNumero() == c.getNumero()) // Si existe deja, mettre a jour
  				break;
  			i++;
  		}
  		if (i >= commandes.size()) // Pas trouve
  			commandes.add(c);
  		else // Trouve
  			commandes.get(i).setQuantite(c.getQuantite());
  			//commandes.set(i, commandes.get(i).setQuantite(c.getQuantite()));
  	}
  	
  	//cherche une command par id
  	public Commande searchCommands(int id) {
  		Commande res = new Commande();
  		for(int i =0; i< commandes.size(); i++) {
  			Commande comm =commandes.get(i);
  			if(comm.getNumero()==id) {
  				System.out.println();
  				res =comm;
  			}
  		}
  		
  		return res;
  	}
	
    public String sumVente() {
    	
    	double sum= 0.0;
    	String res = "";
    	
    	for (Commande c : commandes) {
    		res = "COMMANDE: " + c.getNumero() + "\n" ;
    		if(c.delivred()) {
    			for(String ref : c.getReferences()) {
    				String [] champs=ref.split("=", 2);
    				String reference = champs[0];
    				int quantite = Integer.parseInt(champs[1]);
    				
    				Produit p =getProduitByReference(reference);
    				sum = sum + p.getPrix() * (double) quantite;
    				res = (p.getNom() + quantite + p.getPrix() + "\n").toString();
    				
    			}
    			res = res + "\n"; 
    		}
    		res = res + "===================\n";
    		res = res + "SOMME: " + sum + "euros" ;
    		
    		
    	}
		return res;
    	
    }
    
    
}