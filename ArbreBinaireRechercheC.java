/** 
 * ArbreBinaireRechercheC.java
 * 26 juin 2024
 * @author Yamine Ibrahima
 */
package arbres;

import deque.Deque_y;

/**
 * 
 */
public class ArbreBinaireRechercheC<E extends Comparable<E>> extends ArbreBinaireC<E> {
	
	/**
	 * @param rootdata la donnée de la racine
	 */
	public ArbreBinaireRechercheC(E rootdata) {
		super(rootdata);
	}

	 /**
	  * Insère un noeud dans l'arbre.
	  **/
	@Override
	public void insererNoeud(E data) {
		
		// Si l'arbre est vide, set la racine
		if(this.racine.data == null) {
			this.setRacine(data);
			return;
		}
				
		/*
		 * Si l'arbre n'est pas vide et qu'il existe déjà un noeud 
		 * qui encapsule cette data, afficher un message et arrêter
		 * car il est proscrit d'avoir 2 noeuds avec la même donnée.
		 * 
		 * */		
		if(rechercherNoeud(data) == data) {
			System.out.println("Le noeud que vous souhaitez insérer existe déjà");
			return;
		}
					
		/*
		 * S'il n'existe pas déjà un noeud avec cette data dans l'arbre,
		 * parcourir en level-order l'arbre à partir de la racine.
		 * 
		 * Pour performer le parcours en level-order, il faut une file. J'utiliserai le deque que j'ai développé comme file.
		 * 
		 * Lors du parcours en level-order, si un noeud ne possède pas d'enfant, performer l'insertion
		 * 	
		 * La particularité avec les BST est filsgauche.data < noeud.data < filsdroit.data
		 * 
		 * */

		Deque_y <BSNoeud<E>> file = new Deque_y <BSNoeud<E>>();
		
		file.enqueue(this.racine);
		BSNoeud<E> temp = null;
		
		while(!file.isEmpty()) {
			
			
			temp = file.dequeue();
			
			if (data.compareTo(temp.getData()) < 0) {			// Si le nouveau noeud à insérer est inferieur au noeud actuel
				if (temp.filsgauche == null ) {
					temp.setFilsgauche(data);
					
					this.nodelist.add(temp.filsgauche);
					return;
				}else {
					file.enqueue(temp.filsgauche);
					continue;
				}
				
			} else if (data.compareTo(temp.getData()) > 0) {	// Si le nouveau noeud à insérer est inferieur au noeud actuel
				if (temp.filsdroit == null ) {
					temp.setFilsdroit(data);
					
					this.nodelist.add(temp.filsdroit);
					return;
				}else {
					file.enqueue(temp.filsdroit);
				}
			}
			
			
		} // Fin While
		
	}
	
	
	
	
	
	

}
