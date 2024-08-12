/** 
 * ArbreBinaireC.java
 * 13 juin 2024
 * @author Yamine Ibrahima
 */
package arbres;

import java.util.ArrayList;
import java.util.List;

import deque.Deque_y;

/**
 * Cette classe représente un arbre binaire implémenter avec une structure chainée.
 *  
 * @author Yamine Ibrahima
 */
public class ArbreBinaireC<E> implements ArbreBinaire<E> {
	
	//ATTRIBUTS - FIELDS
	protected BSNoeud<E> racine; 
	protected List<BSNoeud<E>> nodelist ;  //Liste de tous les noeuds de l'arbre
	
	//CONSTRUCTEURS
	 /**
	  * Construit un arbre binaire ayant une racine
	  * */
	public ArbreBinaireC (E rootdata) {	
		this.racine = new BSNoeud<E>(rootdata);
		this.nodelist = new ArrayList<BSNoeud<E>>();
		this.nodelist.add(racine);
	}



	/**
	  * Construit un arbre binaire vide
	  * */
	public ArbreBinaireC() {	this(null);}
	
	 /**
	  * Renvoie le data stocké par la racine
	  * */	
	public E getRacine() { return this.racine.data; }
	
	 /**
	  * Set la racine uniquement si l'arbre est vide
	  * */
	protected void setRacine(E rootdata) {	
		this.racine = (this.racine.data == null)? new BSNoeud<E>(rootdata) : this.racine ;
		this.nodelist.set(0, racine);
	}
	
	 /**
	 * @return the nodelist
	 */
	public List<BSNoeud<E>> getNodelist() {	return nodelist; }

	 /**
	  * Vérifie si l'arbre est vide
	  * */
	public boolean isEmpty() {	return this.racine.data == null; }
		
	/**
	  * Recherche un noeud dans l'arbre
	  * */
	@Override
	public E rechercherNoeud(E element) {
		
		return (contains(this.racine, element) ? element : null);
	}

	 /**
	  * Parcours en "level-order" le sous-arbre dont la racine est source
	  * et vérifie s'il existe un noeud dont le data est element
	  * @param source le noeud à partir duquel commencé le parcours
	  * @param element la data dont l'existence doit être vérifiée
	  * */
	private boolean contains(BSNoeud<E> source, E element) {
		
		if (this.isEmpty())	return false;
			
		Deque_y <BSNoeud<E>> file = new Deque_y <BSNoeud<E>>();
		
		file.enqueue(source);
		
		BSNoeud<E> temp = null;
		while(!file.isEmpty()) {
			
			temp = file.dequeue();
			
			//Vérifier si le data du noeud actuel correspond à l'élément recherché
			if (temp.getData() == element) {
				return true;
			} else {				//Sinon enfiler ses enfants
				
				if(temp.getFilsgauche() != null)
					file.enqueue(temp.filsgauche);
				
				if(temp.getFilsdroit() != null)
					file.enqueue(temp.filsdroit);				
				
			}
			
		}
			
		return false;
	}
	
	 /**
	  * Insère un noeud dans l'arbre.
	  **/
	@Override
	public void insererNoeud(E data) {
		
		// Si l'arbre est vide, set la racine
		if(isEmpty()) {
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
		 * parcourir en largeur (BFS) l'arbre à partir de la racine et vérifier pour chaque noeud existant:
		 * 
		 * Pour performer le parcours en BFS, il faut une file. J'utiliserai le deque que j'ai développé comme file
		 * 
		 * 	Lors du parcours en BFS, si un noeud ne possède pas d'enfant, insérer 
		 * 	
		 * 	
		 * 
		 * */
		Deque_y <BSNoeud<E>> file = new Deque_y <BSNoeud<E>>();
		
		file.enqueue(this.racine);
		
		BSNoeud<E> temp = null;
		while(!file.isEmpty()) {
			
			temp = file.dequeue();
			
			//--------------------------------- Performer l'insertion
			if(temp.getFilsgauche()==null) {		// Si le fils gauche n'existe pas
				temp.setFilsgauche(data);			// Set avec le nouveau noeud
				this.nodelist.add(temp.filsgauche);
				return; 							// Sortir de le fonction pour éviter des duplicat
			}else { 							//Dans le cas ou le fils de gauche existe
				file.enqueue(temp.filsgauche);		//Enfiler le fils gauche pour continuer le parcours
				
			}
			
			if(temp.getFilsdroit() == null) {	
				temp.setFilsdroit(data);
				this.nodelist.add(temp.filsgauche);
				return;
			}else {
				file.enqueue(temp.filsdroit);		//Enfiler le fils droit pour continuer le parcours
				
			}
			
		}
		
		
	}

	
	/**---------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	/**
	 * Supprime un noeud si ce dernier n'a pas d'enfant
	 * */
	@Override
	public E supprimerNoeud(E e) {
				
		//Vérifier que le noeud existe
		if(this.rechercherNoeud(e) == null) {
			throw new NullPointerException("Le noeud que vous essayez de supprimer n'existe pas !") ;
		}
		
		BSNoeud<E> deleted = suppression(this.racine, e);
		
		 if ( deleted != null && deleted.getData()==e) {
			 
			 deleted.parent = null ;
			 deleted.filsgauche = null;
			 deleted.filsdroit = null ;
			 deleted.hauteur = -1;
			 deleted.data = null;
			 
			 return e;
		 }
			
		 
		return null;
	}
	
	private BSNoeud<E> suppression(BSNoeud<E> source , E e){
		
		// Pas implémenté
		
		return null;

		
	}


	/**---------------------------------------------------------------------------------------------------------------------------------------------------------------*/

	
	/**
	 * @return un string contenant le nombre de tabulation mentionné en paramètre
	 * @param nombre_tab nombre de tabulation désiré
	 * */
	protected String tabuler(int nombre_tab) {
		
		String tab = "\t";
		for(int i = 0 ; i < nombre_tab; i++) {
			
			tab += tab ;
			
		}
		return tab;
	}
	
	/**
	 * 
	 * @return Une représentation textuelle  (en level-order)  de l'arbre
	 * 
	 * */
	public String toprint() {
		
		String prompt = "";
		
		Deque_y <BSNoeud<E>> file = new Deque_y <BSNoeud<E>>();
		
		file.enqueue(this.racine);
		
		BSNoeud<E> temp = null;
		
		//Faire un for qui va jusqu'à la profondeur de l'arbre
		
		while(!file.isEmpty()) {
			
			temp = file.dequeue(); //Noeud current
			
			if(temp != null) {
				if (temp == this.racine) {
					
					prompt += "Noeud : " + temp.getData().toString()  + " \n"
							+ "\tParent :" + " none " + " \n"
							+ "\tHauteur : " + temp.getHauteur() + " \n----------------------";	
					
				}else {

					prompt += "\nNoeud : " + temp.getData().toString()  + " \n"
							+ "\tParent :" + temp.getParent().toString() + " \n"
							+ "\tHauteur : " + temp.getHauteur() + " \n----------------------" ;
				}
				
				
				if(temp.getFilsgauche()!=null) {	
					
					file.enqueue(temp.filsgauche); 
				}
				
				if(temp.getFilsdroit() !=null) {
					
					file.enqueue(temp.filsdroit); 
				}
				
			}
							
		
	
			/*
			
			if(temp != null) {
				
				prompt += whiteSpace(temp.getHauteur()) + " " + temp.getData().toString();
				
				if(temp.getFilsgauche()!=null) {	
										
					file.enqueue(temp.filsgauche); 
				}
				
				if(temp.getFilsdroit() !=null) {
					
					file.enqueue(temp.filsdroit); 
				}
				
				
			}
			
			*/
		}
		
		prompt += " ";
			
		return prompt;
	}
	
	
	
	
	//------------------------------------------------------------------------
	/**
	 * Utiliser la méthode print à la place
	 * */
	@Override
	public String toString() {
		
		//A modifier
		
		return "ArbreBinaireC [racine=" + racine + ", nodelist=" + nodelist + "]";
	}




	/**
	 * Cette classe représente un noeud de l'arbre
	 * */
	protected class BSNoeud<T> {
		
		//Attributs
		protected T data;
		protected int hauteur ;
		protected BSNoeud<T> parent;
		protected BSNoeud<T> filsgauche;
		protected BSNoeud<T> filsdroit;
		
		//Constructeur
		BSNoeud(T d) {
			
			this.data = d;
			this.filsgauche = null;
			this.filsdroit = null;
			this.parent = null;
			
		}
		
		public BSNoeud(T d, BSNoeud<T> p) {
			
			this(d);
			this.parent = p ;
			this.hauteur = p.hauteur + 1;
		}
		
		//Getters
		public T getData() {	return this.data; }
		public int getHauteur() { return this.hauteur ;	}
		public T getParent() {	return (this.parent != null )? this.parent.data : null ;	}
		public T getFilsdroit() {	return (this.filsdroit != null )? this.filsdroit.data : null ; }
		public T getFilsgauche() {	return (this.filsgauche != null )? this.filsgauche.data : null ; }
		
		//Setters
		void setFilsgauche(T data) {	
			this.filsgauche = new BSNoeud<T>(data , this);
		}
		
		void setFilsdroit(T data) {	
			this.filsdroit = new BSNoeud<T>(data, this);
		}
		
		@Override
		public String toString() {
			return "[data=" + data + ""
					+ ", parent= " + ((parent != null )? parent.getData() : "null") + ""
							+ ", filsgauche=" + ((filsgauche != null )? filsgauche.getData() : "null") + ""
									+ ", filsdroit="+ ((filsdroit != null )? filsdroit.getData() : "null") + "]\n\t\t";
		}
		
		
	} // Fin classe Noeud

} //Fin de la classe ArbreBinaireC
