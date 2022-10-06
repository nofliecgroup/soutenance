package fr.cda.util;

// TODO: Auto-generated Javadoc
/** Classe de définition de l'exception TerminalException qui peut être retournée dans l'usage des méthodes de la classe Terminal. */
public class TerminalException extends RuntimeException{
    
    /** The ex. */
    Exception ex;
    
    /**
     * Instantiates a new terminal exception.
     *
     * @param e the e
     */
    TerminalException(Exception e){
        ex = e;
    }
}
