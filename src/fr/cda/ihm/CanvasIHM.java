package fr.cda.ihm;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import fr.cda.tore.*;
import fr.cda.ihm.*;

// TODO: Auto-generated Javadoc
/**
 *    Classe de d�finition d'un canvas dans lequel on peut afficher une grille IHM avec laquelle il est possible :<br>
 *    - de colorer ou effacer une case de la grille<br>
 *    - de realiser une action si on clique dans une des cases de la grille<br>
 *    - si la grille n'est pas affichee alors on peut tracer des lignes et ecrire du texte et <br>
 *    avoir une action quand on clique dans le canvas ou quand on deplace la souris.
 */
public class CanvasIHM 
{
  
  /** The Constant NB_MAX_MARQUE. */
  private final static int    NB_MAX_MARQUE = 10;
  
  /** The type marque. */
  public static int TYPE_MARQUE = 2;       
  // 1 = Carre avec une marge
  // 2 = Ronde avec une marge

  /** The fen. */
  private JFrame              fen;
  
  /** The panel PP. */
  private JPanel              _panelPP;
  
  /** The canvas. */
  private Canvas             _canvas;
  
  /** The actions. */
  private ControlesCanvasIHM _actions;
  
  /** The nb X. */
  private int                _nbX;
  
  /** The nb Y. */
  private int                _nbY;
  
  /** The taille case. */
  private int                _tailleCase;
  
  /** The grille. */
  private int                _grille[][];
  
  /** The lignes. */
  private ArrayList<Ligne>   _lignes;
  
  /** The textes. */
  private ArrayList<Texte>   _textes;
  
  /** The x canvas. */
  private int                _xCanvas;
  
  /** The y canvas. */
  private int                _yCanvas;
  
  /** The width. */
  private int                _width; // du canvas
  
  /** The height. */
  private int                _height;
  
  /** The couleurs. */
  private Color[]            _couleurs;
  
  /** The afficher grille. */
  private boolean           _afficherGrille;


  /**
   * Instantiates a new canvas IHM.
   *
   * @param nbX the nb X
   * @param nbY the nb Y
   * @param tailleCase the taille case
   */
  public CanvasIHM(int nbX,
                   int nbY,
                   int tailleCase)
  {
    _afficherGrille = true;
    initCanvasIHM(nbX,nbY,tailleCase);
  }

  /**
   * Instantiates a new canvas IHM.
   *
   * @param width the width
   * @param height the height
   */
  public CanvasIHM(int width,
                   int height)
  {
    _afficherGrille = false;
    initCanvasIHM(width,height,1);
  }
  
  // ======================================================================
  /** Constructeur de l'ihm.<br>
      L'objet cr�� doit ensuite �tre ins�rer dans une objet swing comme par exemple un Frame d'une fen�tre principale.<br>
      La m�thode getPanel() retourne le Panel du CanvasIHM.<br>
      @param nbX nombre colonne de la grille
      @param nbY nombre de ligne de la grille
      @param tailleCase la taille en pixel des cases de la grille
  */
  public void initCanvasIHM(int nbX,
                            int nbY,
                            int tailleCase)
  {
    // Si utilisation de la methode creerCanvasIhmDansFrame
    fen=null;

    // Creation du panel principal
    _panelPP = new JPanel();
    _panelPP.setLayout(null);


    // Caract�ristiques de l'ihm
    _tailleCase  = tailleCase;
    _nbX         = nbX;
    _nbY         = nbY;
    _xCanvas     = 5;
    _yCanvas     = 5;
    _width       = _nbX * _tailleCase+1;
    _height      = _nbY * _tailleCase+1;
    _couleurs    = new Color[NB_MAX_MARQUE];
    _couleurs[0] = Color.black;
    _couleurs[1] = Color.cyan;
    _couleurs[2] = Color.blue;
    _couleurs[3] = Color.gray;
    _couleurs[4] = Color.green;
    _couleurs[5] = Color.magenta;
    _couleurs[6] = Color.orange;
    _couleurs[7] = Color.yellow;
    _couleurs[8] = Color.red;
    _couleurs[9] = Color.pink;

    // Creation du canvas
    _canvas = new PaintCanvas(this);
    _canvas.resize(_width,_height);

    // Initialisation du tableau de la grille
    _grille = new int[_nbX][_nbY];
    razGrille();

    // Initialisation de la liste de ligne
    _lignes = new ArrayList<Ligne>();
    _textes = new ArrayList<Texte>();

    // Pas d'actions par defaut
    _actions = null;

    // On ajoute le canvas dans le panelPP
    // 
    _canvas.setBounds(_xCanvas,_yCanvas,_width,_height);
    _panelPP.add(_canvas);

    // Action de la souris dans le canevas
    _canvas.addMouseListener( new SourisAction(this));
  }

  // ======================================================================
  /**
   *      Initialise ou change les actions utilise dans le Canvas.
   *
   * @param actions the new actions
   */
  public void setActions(ControlesCanvasIHM actions)
  {
    _actions = actions;
  }
  
  // ======================================================================
  /**
   *      Retourne le panel prinicipal de l'IHM.
   *
   * @return le panel
   */
  public JPanel getPanel()
  {
    return(_panelPP);
  }

  /**
   *  Retourne la largeur du canvas de la grille.
   *
   * @return largeur
   */
  public int getWidth(){return _width;}

  /**
   *  Retourne la hauteur du canvas de la grille.
   *
   * @return largeur
   */
  public int getHeight(){return _height;}

  // ======================================================================
  /**
   *  Efface le contenu de la grille<br>
   *       (La valeur 0 est mise dans chaque case).
   */
  public void razGrille()
  {
    for(int i=0;i<_nbX;i++)
      {
        for(int j=0;j<_nbY;j++)
          {
            _grille[i][j]=0;
          }
      }
  }

  // ======================================================================
  /**
   *  Affecte � une case de la grille la marque (couleur).
   *
   * @param marque une valeur de 1 � 10 (couleur) ou 0 (case vide)
   * @param x coordonn�e en x de la case
   * @param y coordonn�e en y de la case
   */
  public void setMarque(int marque,int x,int y)
  {
    _grille[x][y] = marque;
    marquer(marque,x,y);
  }


  // ======================================================================
  /**
   *  Ajout d'une ligne de (x1,y1) a (x2,y2) et de couleur.
   *
   * @param couleur (valeur de 1 � 10)
   * @param x1 the x 1
   * @param y1 the y 1
   * @param x2 the x 2
   * @param y2 the y 2
   */
  public void ajouterLigne(int couleur,int x1,int y1,int x2,int y2)
  {
    _lignes.add(new Ligne(couleur,x1,y1,x2,y2));
    dessinerLignes();
  }

  // ======================================================================
  /**
   *  Ajout d'un texte dans le canvas a une position (x,y).
   *
   * @param texte La valeur de la chaine
   * @param x the x
   * @param y the y
   * @param couleur (valeur de 1 � 10)
   */
  public void ajouterTexte(String texte,int x,int y,int couleur)
  {
    _textes.add(new Texte(texte,x,y,couleur));
    dessinerTextes();
  }

  // ======================================================================
  /**
   *  Retourne la marque de la case.
   *
   * @param x coordonn�e en x de la case
   * @param y coordonn�e en y de la case
   * @return la valeur de la case (de 0 � 10)
   */
  public int getMarque(int x,int y)
  {
    return(_grille[x][y]);
  }
  
  // ======================================================================
  /**
   *  Teste si la case est libre (diff�rente de 0).
   *
   * @param x coordonn�e en x de la case
   * @param y coordonn�e en y de la case
   * @return true si la case est libre sinon false
   */
  public boolean siCaseLibre(int x,int y)
  {
    return( getMarque(x,y)==0 );
  }
  
  // ======================================================================
  /**
   *  Retourne le nombre de colonne de la grille.
   *
   * @return le nombre de colonne
   */
  public int getNbX()
  {
    return(_nbX);
  }
  
  // ======================================================================
  /**
   *  Retourne le nombre de ligne de la grille.
   *
   * @return le nombre de ligne
   */
  public int getNbY()
  {
    return(_nbY);
  }

  // ======================================================================
  /**
   *  Retourne le nombre max de couleur .
   *
   * @return nombre max
   */
  public int getNbMaxMarqueur()
  {
    return(NB_MAX_MARQUE);
  }  

  // ======================================================================
  /**
   *  Desaffichage de la grille du canvas.
   */
  public void desafficherGrille()
  {
    Graphics g = _canvas.getGraphics();
    _afficherGrille=false;
    // On efface toute la grille
    g.clearRect(0,0,_width+1,_height+1);
  }

  // ======================================================================
  /**
   *  Affichage de la grille dans le canvas.
   */
  public void afficherGrille()
  {
    _afficherGrille=true;
    dessinerGrille();
  }

  // ======================================================================
  /**
   *  Methode static qui permet de creer le canvas inclus dans un Frame.
   *
   * @param nbX nombre de colonnes de la grille
   * @param nbY nombre de lignes de la grille
   * @param tailleCase taille de chaque case en pixel
   * @return the canvas IHM
   */
  static public CanvasIHM creerCanvasIhmDansFrame(int nbX,
                                                  int nbY,
                                                  int tailleCase
                                                  )
  {
    // On cr�e la fen�tre
    JFrame fen = new JFrame();

    // On y ajoute la grille
    CanvasIHM ihm = new CanvasIHM(nbX,nbY,tailleCase);
    ihm.fen = fen;

    // Panel in Frame
    fen.add(ihm.getPanel());

    // Pour fermer la fenetre
    GrilleWindowAdapter a = new GrilleWindowAdapter();
    fen.addWindowListener((WindowListener)a);
        
    return(ihm);
  }

  /**
   * Afficher frame.
   *
   * @param posX the pos X
   * @param posY the pos Y
   */
  // Affiche la fenetre
  public void afficherFrame(int posX,int posY)
  {
    fen.setLocation(posX,posY);
    fen.setPreferredSize(new Dimension(_width+20,_height+20));
    fen.pack();
    fen.show();
  }

  /**
   * Gets the frame.
   *
   * @return the frame
   */
  public JFrame getFrame()
  {
    return fen;
  }


  // **************************** METHODES PRIVEES ************************
    
  // Code pour dessiner la grille et colorer les cases de la grille
  /**
   * Dessiner grille.
   */
  //
  private void dessinerGrille()
  {
    Graphics g = _canvas.getGraphics();

    // On efface toute la grille
    g.clearRect(0,0,_width+1,_height+1);

    // Tracer des lignes verticales
    for(int i=0;i<_nbX+1;i++)
    {
    Point p1= new Point( (_width/_nbX)*i,0);
    Point p2= new Point( (_width/_nbX)*i,_height);
    g.drawLine(p1.x,p1.y,p2.x,p2.y);
    }

    // Tracer des lignes horizontales
    for(int i=0;i<_nbY+1;i++)
    {
    Point p1= new Point( 0,      (_height/_nbY)*i);
    Point p2= new Point( _width, (_height/_nbY)*i);
    g.drawLine(p1.x,p1.y,p2.x,p2.y);
    }

    // Tracer des �l�ments
    for(int i=0;i<_nbX;i++)
      {
        for(int j=0;j<_nbY;j++)
          {
            marquer(_grille[i][j],i,j);
          }
      }
  }

  // Dessine touts les lignes
  /**
   * Dessiner lignes.
   */
  //
  private void dessinerLignes()
  {
    Graphics g = _canvas.getGraphics();
    for(Ligne l:_lignes)
      {
        g.setColor(_couleurs[l.marque-1]);
        g.drawLine(l.p1.x,l.p1.y,l.p2.x,l.p2.y);
      }
  }

  // Dessine tous les textes
  /**
   * Dessiner textes.
   */
  //
  private void dessinerTextes()
  {
    Graphics g = _canvas.getGraphics();
    for(Texte t:_textes)
      {
        g.setColor(_couleurs[t.couleur-1]);
        g.drawString(t.texte,t.p.x,t.p.y);
      }
  }

  // Effacer tout le canvas
  /**
   * Effacer canvas.
   */
  //
  public void effacerCanvas()
  {
    _lignes.clear();
    _textes.clear();

    Graphics g = _canvas.getGraphics();

    // On efface toute la zone
    g.clearRect(0,0,_width+1,_height+1);
        
  }

  // dessiner le contenu d'une case de la grille
  /**
   * Marquer.
   *
   * @param marque the marque
   * @param x the x
   * @param y the y
   */
  //
  private void marquer(int marque,int x,int y)
  {
    Graphics g = _canvas.getGraphics();
    int niX1 = x*(_width/_nbX);
    int niX2 = (x+1)*(_width/_nbX);
    int niY1 = y*(_height/_nbY);
    int niY2 = (y+1)*(_height/_nbY);

    g.clearRect(niX1+1,niY1+1,niX2-niX1-2,niY2-niY1-2);
    if (marque >0)
      {
        g.setColor(_couleurs[marque-1]);
        if (TYPE_MARQUE==1)
          g.fillRect(niX1+2,niY1+2,niX2-niX1-4,niY2-niY1-4);
        else
          g.fillOval(niX1+2,niY1+2,niX2-niX1-4,niY2-niY1-4);
      }
  }

  // determine les corrdonees de la case en fonction du point de la souris
  /**
   * Point to case.
   *
   * @param p the p
   * @return the point
   */
  //
  private Point pointToCase(Point p)
  {
    int px = p.x;
    int py = p.y;

    int x = px/_tailleCase;
    int y = py/_tailleCase;

    if ( (x<0) || (x>=_nbX) ||
         (y<0) || (y>=_nbY) )
      return(null);
    else
      return( new Point(x,y));
  }


  //********************   CLASSES INTERNES ************************

  // Classe interne de definition de la surcharge de paint du canvas
  /**
   * The Class PaintCanvas.
   */
  //
  class PaintCanvas extends Canvas
  {
    
    /** The g. */
    CanvasIHM _g;
        
    /**
     * Instantiates a new paint canvas.
     *
     * @param g the g
     */
    public PaintCanvas(CanvasIHM g)
    {
      _g = g;
    }

    /**
     * Paint.
     *
     * @param g the g
     */
    public void paint(Graphics g)
    {
      if (_afficherGrille)
        _g.dessinerGrille();
      _g.dessinerLignes();
      _g.dessinerTextes();
    }
  }

  // Classe interne de definition de la surcharge de la souris
  /**
   * The Class SourisAction.
   */
  //
  class SourisAction extends MouseAdapter
  {
    
    /** The ihm. */
    CanvasIHM _ihm;

    /**
     * Instantiates a new souris action.
     *
     * @param ihm the ihm
     */
    public SourisAction(CanvasIHM ihm)
    {
      _ihm=ihm;
    }
    
    /**
     * Mouse clicked.
     *
     * @param e the e
     */
    public void mouseClicked(MouseEvent e)
    {
      if (_afficherGrille)
        {
          // On d�termine la case s�lectionn�e
          Point p = pointToCase(e.getPoint());
          if  ( (p!=null) && (_actions!=null) )
            {
              _actions.pointerCaseGrille((int)p.x,(int)p.y,_ihm);
            }
        }
      else
        {
          Point p = e.getPoint();
          if (_actions!=null)
            _actions.pointerCanvas((int)p.x,(int)p.y,_ihm);
        }
    }

  }

    
  // Classe interne de definition d'une ligne
  /**
   * The Class Ligne.
   */
  //
  class Ligne
  {
    
    /** The marque. */
    int marque;
    
    /** The p 1. */
    Point p1;
    
    /** The p 2. */
    Point p2;

    /**
     * Instantiates a new ligne.
     *
     * @param marque the marque
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Ligne(int marque,int x1,int y1,int x2,int y2)
    {
      this.marque=marque;
      p1=new Point();
      p2=new Point();
      p1.x=x1;
      p1.y=y1;
      p2.x=x2;
      p2.y=y2;
    }

  }

  // Classe interne de definition d'un texte dessine
  /**
   * The Class Texte.
   */
  //
  class Texte
  {
    
    /** The texte. */
    String texte;
    
    /** The p. */
    Point p;
    
    /** The couleur. */
    int   couleur;  // de 1 � 10
    
    /** The font. */
    Font  font;

    /**
     * Instantiates a new texte.
     *
     * @param texte the texte
     * @param x the x
     * @param y the y
     * @param couleur the couleur
     */
    public Texte(String texte,int x,int y,int couleur)
    {
      this.texte=texte;
      this.p=new Point();
      this.p.x=x;
      this.p.y=y;
      this.couleur=couleur;
      this.font=null;
    }
  }

} // Fin de CanvasIHM



// ===============================================================
// Classe privee de fermeture de la fenetre
// ===============================================================
// L'adaptateur d'une window
//
class GrilleWindowAdapter extends WindowAdapter
{
  // On ne s'interesse que a l'action de fermeture de
  // la fenetre
  public void windowClosing(WindowEvent e) 
  {
    System.exit(0);
  }
}




        