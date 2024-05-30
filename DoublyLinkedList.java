//package double_linkedList;
import java.lang.Exception;

/**
 * Cette classe représente une liste doublement chainée
 * 						classique (sans sentinelle).</br></br>
 * Une telle liste possède comme attribut : </br>
 * 	 &nbsp&nbsp- Une référence vers son premier noeud</br>
 * 	 &nbsp&nbsp- Une référence vers son dernier noeud</br></br>
 * 
 * Cette classe a été créée "from scratch". Il se pourrait donc que
 * vous y trouviez quelques incohérences. </br>
 * Merci de me contacter par email pour m'en faire part aux adresses :</br> 
 *  &nbsp&nbsp- <a href="mailto:ibry01@uqo.ca"> ibry01@uqo.ca</a> </br> ou </br>
 *  &nbsp&nbsp- <a href="mailto:yaminee.ca@gmail.com"> yaminee.ca@gmail.com</a> </br>  
 * 
 * @author Yamine Ibrahima
 * */
public class DoublyLinkedList<E> {

	/**  Le premier noeud ou la tête de la liste */
	private Noeud<E> head;

	/**  Le dernier noeud ou la queue de la liste */
	private Noeud<E> tail;

	/** Nombres d'éléments dans la liste */
	private int size; 
	
	/** Construit une nouvelle liste vide. */
	public DoublyLinkedList() {

		this.head = null;
		this.tail = null;
		this.size = 0;

	}
	
	
	//=========================================== Getters =======================================
	
	/**
	 * Retourne le size de la liste chainée
	 * @return size
	 * */
	public int getSize() {	
		return this.size;
	}
	
	/**
	 * Retourne une référence vers le premier élément de la liste
	 * 
	 * @return head : La tête de la liste
	 * @throws Exception 
	 */
	public Noeud<E> getHead() throws Exception {
		if(this.isEmpty())
			return null;

		if(this.head != null) {
			return this.head;
		} else {
			throw new Exception("La référence vers le head est \"null\" pourtant la liste n'est pas vide !");
		}
	
	}
	
	/**
	 * Retourne une référence vers le dernier élément de la liste
	 * 
	 * @return tail : La queue de la liste
	 * @throws Exception 
	 */
	public Noeud<E> getTail() throws Exception {
		if(this.isEmpty())
			return null;

		if(this.tail != null) {
			return this.tail;
		} else {
			throw new Exception("La référence vers le tail est \"null\" pourtant la liste n'est pas vide !");
		}
	
	}
	


	
	//================================ Methodes d'ajout d'éléments ============================
	
	/**
	 * Ajoute un nouvel élément en avant / au début de la liste </br>
	 * Le noeud contenant à cet élement devient le nouveau head.
	 * 
	 * @param e le nouvel élément à ajouter
	 */
	public void addFirst(E e) {
		
		//Cas ou la liste est vide
		if(this.isEmpty()) {
			this.head = new Noeud<E>(e,null,null);
			this.tail = this.head;
			this.size++;
		}else {
			//Cas ou la liste contient déjà un head
			this.addBetween(e, null, this.head);
		}
		
	}
	
	/**
	 * Ajoute un nouvel élément en arrière / à la fin de la liste </br>
	 * Le noeud contenant à cet élement devient le nouveau tail.
	 * 
	 * @param e le nouvel élément à ajouter
	 */
	public void addLast(E e) {

		//Cas ou la liste est vide
		if(this.isEmpty()) {
			this.tail = new Noeud<E>(e,null,null);
			this.head = this.tail;
			this.size++;
		}else {
			//Cas ou la liste contient déjà une queue
			this.addBetween(e, this.tail, null);
		}
				
	}
	
	/**
	 * Ajoute un élément dans la liste entre les noeuds predecesseur et successeur
	 * en param. Ces deux noeuds doivent etre voisins avant l'appel
	 *
	 * @param predecessor le noeud avant la location où on veut ajouter un noeud
	 * @param successor   le noeud apres la location où on veut ajouter un noeud
	 */
	private void addBetween(E e, Noeud<E> predecessor, Noeud<E> successor) {
		/**
		 * Etapes générale : 
		 * 		1. Créer le noeud à insérer
		 * 		Faire checkup c0
		 * 		Parcourir la liste actuelle, faire les checkup c1 et c2 et :
		 * 		2. Faire pointer le noeud à insérer vers son successeur
		 * 		3. Faire pointer le noeud à insérer vers son prédécesseur
		 * 		4. Faire pointer le prédécesseur vers le noeud à insérer
		 * 		5. Faire pointer le successeur vers le noeud à insérer
		 * 		6. Incrémenter la taille de la liste
		 * 
		 * Checkup à faire :
		 * 		c0. les noeuds predecessor et successor doivent être consécutifs
		 * 		c1. Vérifier que le noeud predecesseur existe bien dans la liste
		 * 		c2. Vérifier que le noeud successeur existe bien dans la liste
		 * 
		 * @author Yamine Ibrahima
		 * */
		
		Noeud<E> to_insert = new Noeud<E>(e,null,null); 		//Créer le noeud à insérer
		
		//Vérifier si la liste contient les noeuds predecessor et successor
		if(this.contains(predecessor) && this.contains(successor)) {
			
			
			//Cas ou le prédecesseur et le successeur ne sont pas consécutifs
			if(!(predecessor.getNext().equals(successor)) || !(successor.getPrevious().equals(predecessor))) {
				System.err.println("Impossible d'effectuer l'insertion de \"" + e +"\""
						+ " entre \"" +predecessor.getElement().toString() + "\" et "
								+ "\""+successor.getElement().toString() + "\""
										+ " car ces derniers ne sont pas consécutifs." );
				return;
				
			//Cas ou le prédecesseur et le successeur sont effectivement consécutifs	
			}else {
				to_insert.setNext(successor);
				to_insert.setPrevious(predecessor);
				
				predecessor.setNext(to_insert);
				successor.setPrevious(to_insert);
				
				this.size ++;
				
			}		
			
		}
		
		//Cas ou on veut insérer un nouveau head
		if(predecessor == null && successor.equals(this.head)) {
			
			to_insert.setNext(successor);
			to_insert.setPrevious(null);
			
			successor.setPrevious(to_insert);
			
			this.head = to_insert;
			this.size++;
					
			
		}
		
		//Cas ou on veut insérer un nouveau tail
		if(predecessor.equals(this.tail) && successor == null) {
			
			to_insert.setNext(null);
			to_insert.setPrevious(this.tail);
			
			predecessor.setNext(to_insert);
			
			this.tail = to_insert;
			this.size++;
			
		}
		
	}
	
	
	//=============================== Methodes de suppression d'éléments ========================
	
	/**
	 * Supprime le premier noeud de la liste
	 * 
	 * @return la donnée stockée par le noeud supprimé (ou null si la liste est vide)
	 */
	public E removeFirst() {
		try {
			return this.remove(this.head);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Supprime le dernier noeud de la liste
	 * 
	 * @return la donnée stockée par le noeud supprimé (ou null si la liste est vide)
	 */
	public E removeLast() {
		try {
			return this.remove(this.tail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Supprime un noeud
	 * 
	 * @param node le noeud à supprimer 
	 */
	private E remove(Noeud<E> node) throws Exception {
		
		//Vérifier que la le noeud existe
		if(!this.contains(node))
			throw new Exception("Le noeud que vous essayer de "
					+ "supprimer n'existe pas");
		
		//Si le noeud à supprimer est à la fois le head et le tail
		if(this.size == 1 || (this.tail.equals(this.head))) {
			
			this.tail = null;
			this.head = null;
			this.size--;
			return node.getElement();
		}
		
		
		//Si le noeud à supprimer est le head
		if(node.equals(this.head)) {
			
			node.getNext().setPrevious(null);	//Supprimer les liens vers le head
			this.head = node.getNext();			//Définir le nouveau head
			
			node.setNext(null);					//Supprimer les liens issus du de l'ancien head
			this.size--;						//Décrémenter la taille
			
			return node.getElement();
		} 
		
		//Si le noeud à supprimer est le tail
		if (node.equals(this.tail)){
			
			node.getPrevious().setNext(null);	// Supprimer les liens vers le tail
			this.tail = node.getPrevious();		// Définier le nouveau tail
			
			node.setPrevious(null);				// Supprimer les liens issus de l'ancien tail
			this.size--;						// Décrémenter la taille
		
			return node.getElement();
		}
		
		//Si le noeud à supprimer est à l'intérieur de la liste
		for(Noeud<E> current = this.head ; current != null ; current = current.getNext()) {
			
			if(current.equals(node)) {
				
				//Faire pointer le précédent vers le suivant
				current.getPrevious().setNext(current.getNext());
				
				//Faire pointer le suivant vers le précédent
				current.getNext().setPrevious(current.getPrevious());
				
				//Supprimer les liens issues du noeud
				current.setNext(null);
				current.setPrevious(null);
				
				//Décrémenter la taille de la liste
				this.size--;
				
				return current.getElement();
				
			}
		
		}
		
		/* Lancer une exception s'il arrive une situation non prise en 
		 * charge par le code définit plus haut dans de cette fonction */
		throw new Exception ("Un problème est survenue lors de la suppression de "
				+ node.getElement().toString() + "" );
	}

	
	//===================================== Autres Methodes  ===================================== 

	/**
	 * Vérifie si un noeud appartient à la liste chainée
	 * @return true si le noeud est contenu, false sinon
	 * */
	private boolean contains(Noeud<E> noeud){
		
		if(this.isEmpty())
			return false;
		
		for(Noeud<E> current = this.head ; current != null ; current = current.getNext()) {	
			if(current.equals(noeud))
				return true;
		}

		
		return false;
	}
		
	/**
	 * Cette fonction parcours la liste doublement chainée 
	 * et calcul le nombre total d'élément. </br></br>
	 * 
	 * ***Attention: cette fonction ne doit être utiliser qu'à des fins de test
	 * car elle effectue un parcours total de l'instance.</br></br>
	 * 
	 * Préconiser la fonction getSize()
	 * 
	 * @return le nombre d'éléments dans la liste
	 */
	protected int computeSize() {
		
		/*
		 * Parcourir la liste du head au tail et :
		 * 	+ incrémenter la taille de la liste
		 * 	+ arrêter le parcours une fois la queue atteinte
		 */
		
		if(this.isEmpty())
			return 0 ;
		
		this.size = 0 ;
		
		Noeud<E> current = this.head ;
	
		while(!current.equals(null)) {
			this.size ++ ;
			if (current.equals(tail))
				break; 		//Pour éviter une boucle infini dans le cas où on aurait une liste doublement chainée circulaire.
			
			if(!current.getNext().equals(null)) {
				current = current.getNext();
			}else {
				System.err.println("Un problème est survenue.\n"
						+ "Le noeud \""+current.getElement().toString()+"\" "
								+ "ne pointe vers aucun noeud, pourtant il n'est pas le tail.\n"
								+ "Le tail actuel de la liste est " + this.tail.toString());
				System.exit(-1);				
			}			
		}
		
		if(this.head == this.tail)
			System.out.println("Header == tail");
		
		return this.size;
	}
	
	/**
	 * Vérifie si la liste est vide
	 * 
	 * @return true si la liste est vide, false sinon
	 */
	public boolean isEmpty() {
		if(this.head != null || this.tail != null)
			return false;
		
		return true;
	}

	/**
	 * Affichage de la liste
	 */
	public String toString() {
		
		if(this.isEmpty())
			return "\\n============= Liste doublement chainée ================\n\n\t\t{"
					+ "-empty-"
					+ "}\n\n============= Liste doublement chainée ================";
		
		//String str = "Liste doublement chainée : {";
		String str = "\n============= Affichage de la liste ================\n\n\t\t{";

		
		for(Noeud<E> current = this.head ; current != null ; current = current.getNext()) {
			if(current.getPrevious() == null)
				str += "x - " ;
			
			str += current.getElement() + " - ";
			
			if(current == this.tail)
				str += "x" ;
		}
				
		str += "}\n\n" + "\tHead = "+this.head.getElement().toString()+"\t Tail = "+ this.tail.getElement().toString()+"\tSize = " +this.size+ 
						"\n\n============= Affichage de la liste ================\n";
		return str;
	}



}
