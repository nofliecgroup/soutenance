package fr.cda.projet;

import java.util.*;

// TODO: Auto-generated Javadoc
// Classe de definition d'un produit du stock
/**
 * The Class Produit.
 */
//
public class Produit
{
    // Les caracteristiques d'un Produit
    /** Les caracteristiques d'un Produit, The reference. */
    //
    private String  reference;      // reference du produit
    
    /** The nom. */
    private String  nom;            // nom du produit
    
    /** The prix. */
    private double  prix;           // prix du produit
    
    /** The quantite. */
    private int     quantite;       // quantit� du produit

    // Constructeur
    /**
     * Instantiates a new produit.
     *
     * @param reference the reference
     * @param nom the nom
     * @param prix the prix
     * @param quantite the quantite
     */
    //
    public Produit(String reference,String nom,double prix,int quantite)
    {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    
    
    /**
     * Gets the reference.
     *
     * @return the reference
     */
    public String getReference() {
		return reference;
	}



	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}



	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}



	/**
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}



	/**
	 * Gets the prix.
	 *
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}



	/**
	 * Sets the prix.
	 *
	 * @param prix the new prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}



	/**
	 * Gets the quantite.
	 *
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}



	/**
	 * Sets the quantite.
	 *
	 * @param quantite the new quantite
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}



	// Conversion en chaine
    /**
	 * To string.
	 *
	 * @return the string
	 */
	//
    public String toString()
    {
        return String.format("%-15s %-50s %3.2f   %3d" , reference ,nom, prix, quantite);
    }

}