package fr.cda.projet;

import fr.cda.ihm.*;

// TODO: Auto-generated Javadoc
// Classe de definition de l'IHM principale du compte
/**
 * The Class IHMSite.
 */
//
public class IHMSite implements FormulaireInt
{
    
    /** The site. */
	private GUISite formPP;
    private Commande listCommandes;
    private Site site;  // Le site

    // Constructeur
    /**
     * Instantiates a new IHM site.
     *
     * @param site the site
     */
    //
    public IHMSite(Site site)
    {
        this.site = site;

        // Creation du formulaire
        Formulaire form = new Formulaire("Site de vente",this,1100,730);
        
        //  Creation des elements de l'IHM
        //
        form.addLabel("Afficher tous les produits du stock");
        form.addButton("AFF_STOCK","Tous le stock");
        form.addLabel("");
        form.addLabel("Afficher tous les bons de commande");
        form.addButton("AFF_COMMANDES","Toutes les commandes");
        form.addLabel("");
        form.addText("NUM_COMMANDE","Numero de commande",true,"1");
        form.addButton("AFF_COMMANDE","Afficher");
        form.addLabel("");
        
        form.addButton("AFF_MODIFIER","Modifier");
        form.addLabel("");
        form.addText("AFF_LIVRER","Livrer",true,"");
        form.addButton("RECHERCHE","Livrer");
        form.addLabel("");
               
        form.addButton("AFF_CALCULE_VENTES","Calcule Ventes");
        
        form.setPosition(400,0);
        form.addZoneText("RESULTATS","Resultats",true, "", 600,700);

        // Affichage du formulaire
        form.afficher();
    }

    // Methode appellee quand on clique dans un bouton
    /**
     * Submit.
     *
     * @param form the form
     * @param nomSubmit the nom submit
     */
    //
    public void submit(Formulaire form,String nomSubmit)
    {

        // Affichage de tous les produits du stock
        //
        if (nomSubmit.equals("AFF_STOCK"))
            {
                String res = site.listerTousProduits();
                form.setValeurChamp("RESULTATS",res);
            }

        // Affichage de toutes les commandes
        //
        if (nomSubmit.equals("AFF_COMMANDES"))
            {
                String res = site.listerToutesCommandes();
                form.setValeurChamp("RESULTATS",res);
            }

        // Affichage d'une commande
        //
        if (nomSubmit.equals("AFF_COMMANDE"))
            {
                String numStr = form.getValeurChamp("NUM_COMMANDE");
                int num = Integer.parseInt(numStr);
                String res = site.listerCommande(num);
                form.setValeurChamp("RESULTATS",res);
            }
        
        if (nomSubmit.equals("AFF_MODIFIER"))
        {
//            String numStr = form.getValeurChamp("AFF_MODIFIER");
//            int num = Integer.parseInt(numStr);
//            String res = site.listerCommande(num);
//            form.setValeurChamp("RESULTATS",res);
        	   for(int i=0; i < listCommandes.getReferences().size(); i++) {
        	        String reference = listCommandes.getReferences().get(i);
        	        int quantite = listCommandes.getQuantite().get(i);
        	        
        	        String fieldName = "item"+i;
        	        
        	        form.addText(fieldName, reference, true, String.valueOf(quantite));
        	        }
        }
        
        if (nomSubmit.equals("AFF_LIVRER"))
        {String numStr = site.isDelivered(); 
		form.setValeurChamp("RESULTATS", numStr);
		 
        }
        
        if (nomSubmit.equals("AFF_CALCULE_VENTES"))
        {
            String numStr = form.getValeurChamp("AFF_CALCULE_VENTES");
            int num = Integer.parseInt(numStr);
            String res = site.listerCommande(num);
            form.setValeurChamp("RESULTATS",res);
        }
        
    }

}