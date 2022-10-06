// Projet 1 CDA
// 
// NOM,Prenom
// NOM,Prenom
//
package fr.cda.projet;

import java.io.*;
import java.util.*;

import fr.cda.util.*;

// 
// Classe principale d'execution du projet
/**
 * The Class Projet.
 */
//
public class Projet
{
    
    /**
     * The main method.
     *
     * @param a_args the arguments
     */
    public static void main(String a_args[])
    {
        Terminal.ecrireStringln("Execution du projet ");

        Site site = new Site();
        GUISite ihm = new GUISite(site);
    }
}
