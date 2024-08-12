package double_linkedList;

/**
 * Cette classe représente un noeud d'une liste doublemennt chainée.</br></br>
 * 
 * Un tel noeud possède comme attribut : </br>
 * 	 &nbsp&nbsp- Une référence vers le noeud qui le précède</br>
 * 	 &nbsp&nbsp- Une référence vers le noeud qui le succède
 * 
 * @author Yamine Ibrahima
 * 
 * */
public class Noeud<T> {

	@Override
	public String toString() {
		return "Noeud [ " + element + " ]";
	}

	// Attributs
	private T element; // Données stocké par le noeud
	private Noeud<T> previous; // Noeud précédent
	private Noeud<T> next; // Noeud suivant

	/**
	 * Constructeur de la classe.</br>
	 * 
	 * @param data la donnée stockée
	 * @param precedent référence vers le noeud précédent
	 * @param suivant référence vers le noeud suivant
	 */
	public Noeud(T data, Noeud<T> precedent, Noeud<T> suivant) {

		this.element = data;
		this.previous = precedent;
		this.next = suivant;

	}

	
	//============================== Getters
	
	public T getElement() {
		return this.element;
	}

	public Noeud<T> getPrevious() {
		return this.previous;
	}

	public Noeud<T> getNext() {
		return next;
	}


	//============================== Setters
	
	public void setPrevious(Noeud<T> prev) {
		this.previous = prev;
	}

	public void setNext(Noeud<T> nxt) {
		this.next = nxt;
	}
}
