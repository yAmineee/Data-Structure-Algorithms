/**
 * 
 */
package arbres;

/**
 * 
 */
public interface ArbreBinaire<E>  { //extends Iterable<E>
	
		
	//Fonction d'insertion d'éléments
	public void insererNoeud(E e);

	//Fonction de suppression d'éléments
	public E supprimerNoeud(E e);
	
	//Fonction de recherche d'un élément
	public E rechercherNoeud(E e);
	
	
	
	//--------------------------------
	
	//Fonction pour déterminer le size de l'arbre
	
	//Fonction pour déterminer la hauteur de l'arbre
	
	//Fonction pour déterminer le plus petit commun ancestre d'un ensemble de noeuds
	
	
}
