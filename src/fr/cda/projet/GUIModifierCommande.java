package fr.cda.projet;

import fr.cda.ihm.*;

// TODO: Auto-generated Javadoc
// Classe de definition de l'IHM principale du compte
/**
 * The Class GUISite.
 */
//

public class GUIModifierCommande implements FormulaireInt {

	/** The site. */
	//private GUISite formPP;
	private Site site; // Le site
	private Commande listCommandes;
	private Formulaire parent;

	// Constructeur
	/**
	 * Instantiates a new GUI site.
	 *
	 * @param site the site
	 */
	//
	public GUIModifierCommande(Site site, Commande commands, Formulaire parent) {

		this.site = site;
		this.listCommandes = commands;
		//this.formPP = formPP;
		this.parent = parent;

		// Creation du formulaire
		Formulaire form = new Formulaire("Site de vente", this, 1100, 730);
		// Creation des elements de l'IHM

		// On permet la saisie du media
		form.addLabel("");
		System.out.println("Reference size" + this.listCommandes.getReferences().size());
		System.out.println("Quantity size" + this.listCommandes.getQuantite().size());

		for (int i = 0; i < listCommandes.getReferences().size(); i++) { // ok
			String reference = listCommandes.getReferences().get(i); // ok
			System.out.println(reference);
			int quantite = listCommandes.getQuantite().get(i);
			// String fieldName = "item"+i;
			form.addText(reference, reference, true, String.valueOf(quantite));
		}
		// Pour valider la modification
		form.addLabel("");
		form.addButton("VALIDER", "Valider");
		form.addLabel("");
		form.addButton("ANNULER", "Annuler");
		form.afficher();

	}

	// Methode appellee quand on clique dans un bouton
	/**
	 * Submit.
	 *
	 * @param form      the form
	 * @param nomSubmit the nom submit
	 */
	//
	public void submit(Formulaire form, String nomSubmit) {
		String err = "";

		// Valider la saisie
		//
		if (nomSubmit.equals("VALIDER")) {
			int[] quantity;
			String[] reference;
			
			/*
			String quantiy2 = form.getValeurChamp("LIVRE-2");
			String quantiy3 = form.getValeurChamp("VETEMENT-1");
			*/
			reference = new String[listCommandes.size()];
			quantity = new int[listCommandes.size()];
			for (int i = 0 ; i < listCommandes.size(); i++)
			{
				reference[i] = listCommandes.getReferenceByIndex(i);
				if (reference[i].length() != 0)
				{
					try {
						quantity[i] = Integer.parseInt(form.getValeurChamp(reference[i]));
					} catch (Exception e) {
						quantity[i] = listCommandes.getQuantite().get(i);
						e.printStackTrace();
						continue; 
					} // 
				}

			}
			for (int i = 0; i < listCommandes.size(); i++)
				listCommandes.setQuantite(reference[i], quantity[i]);
			site.setCommande(listCommandes);
			if (err.length() != 0)
				parent.setValeurChamp("RESULTATS", err);
			else
				parent.setValeurChamp("RESULTATS", listCommandes.toString());
			form.fermer();
		}
		//if (nomSubmit.equals("FERMER")) {
		form.fermer();
	}

}