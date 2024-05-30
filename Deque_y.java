package deque;
import double_linkedList.DoublyLinkedList;
import double_linkedList.Noeud;

/**
 * Cette classe représente une double-ended-queue (Deque).</br></br>
 * 
 * Cette classe implémente la Deque à l'aide d'une liste doublement chainée.
 * 
 * Elle extend la classe {@link double_linkedList.DoublyLinkedList<E>} et crée des methodes propres aux deque.</br>
 * 
 * Toutes les méthodes de cette classe font appel aux méthodes de la classe parente.
 * 
 * Cette implementation permet d'utiliser la Deque :</br>
 * 
 * &nbsp&nbsp- Soit comme une pile, avec les methodes {@link #push(Object)}, {@link #pop()}, {@link #getRear()} [equivalent du peek()]</br>
 * &nbsp&nbsp- Soit comme une file, avec les methodes {@link #enqueue(Object)} [equivalent au {@link #push(Object)}], {@link #dequeue()}</br>
 * &nbsp&nbsp- Soit comme une deque, en tirant avantages de la combinaison des methodes citées plus haut.</br></br>
 * 
 * Cette classe a été créée "from scratch". Il se pourrait donc que vous y trouviez quelques incohérences. </br>
 * Merci de me contacter par email pour m'en faire part aux adresses :</br> 
 *  &nbsp&nbsp- <a href="mailto:ibry01@uqo.ca"> ibry01@uqo.ca</a> </br> ou </br>
 *  &nbsp&nbsp- <a href="mailto:yaminee.ca@gmail.com"> yaminee.ca@gmail.com</a> </br>  
 * 
 * @author Yamine Ibrahima
 * */
public class Deque_y<E> extends DoublyLinkedList<E> {

	/**
	 * Constructeur de la deque. Crée une instance d'une deque </br></br>
	 * ***En réalité, construit un liste doublement chainée
	 * */
	public Deque_y() {
		super();
	}
		
	/**
	 * Renvoie la donnée stockée par le premier
	 * noeud du deque, sans supprimer ledit noeud
	 * 
	 * @return la donnée du dernier noeud
	 * */
	public E getFront() {
		
		try {
			return super.getHead().getElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Renvoie la donnée stockée par le dernier
	 * noeud du deque, sans supprimer ledit noeud
	 * 
	 * @return la donnée du dernier noeud
	 * */
	public E getRear() {
		
		try {
			return super.getTail().getElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Renvoie la taille du deque
	 * */
	public int getSize() {
		return super.getSize();
	}
	
	/**
	 * Ajoute un nouveau noeud au début du deque.  
	 * 
	 * @param e La donnée qui sera stockée par le noeud.
	 * */
	public void addFront(E e) {
		super.addFirst(e);
	}
	
	
	//============================================ Methodes pour les piles (LIFO) ===============

	/**
	 * Ajoute un noeud à la fin du deque. Cette methode
	 *  est pareil à la methode {@link #enqueue(Object)}.</br></br>
	 * 
	 * @param e la donnée qui sera stocké par le
	 * 			le noeud inséré. 
	 * */

	public void push(E e) {
		
		super.addLast(e);
		
	}
	
	/**
	 * Supprime le dernier noeud du deque 
	 *  @return la donnée stockée par le noeud supprimé.
	 * */
	public E pop() {
		
		return super.removeLast();
	}
	
	
	//============================================ Methodes pour les files (FIFO) ===============
	
	/**
	 * Ajoute un noeud à la fin du deque. Cette methode
	 *  est pareil à la methode {@link #push(Object)}.</br></br>
	 * 
	 * @param e la donnée qui sera stocké par le
	 * 			le noeud inséré. 
	 * */
	public void enqueue (E e) {
		super.addLast(e);
	}
	
	/**
	 * Supprime le premier noeud du deque
	 * 
	 * @return e : la donnée stocké par l'élément supprimé
	 * */
	public E dequeue() {
		return super.removeFirst();
	}
	
	/**
	 * Affichage de la liste
	 */
	@Override
	public String toString() {
		
		if(this.isEmpty())
			return "\n============= Double-ended-queue ================\n\n\t\t{"
					+ "-empty-"
					+ "}\n\n============= Double-ended-queue ================";
		
		//String str = "Liste doublement chainée : {";
		String str = "\n============= Double-ended-queue ================\n\n\t\t{";

		try {
			
			for(Noeud<E> current = this.getHead() ; current != null ; current = current.getNext()) {
				if(current.getPrevious() == null)
					str += "" ;
				
				str += current.getElement().toString();
				
				if(current == this.getTail()) {
					str += "" ;
				}else {
					str += " - ";
				}
					
			}
					
			str += "}\n\n" + "\tHead = "+this.getHead().getElement().toString()+"\t Tail = "+ this.getTail().getElement().toString()+"\tSize = " +this.getSize()+ 
							"\n\n============= Double-ended-queue ================\n";
			return str;
			
			
			
		} catch (Exception u) {
			
			return str;
		}
		
		
	}

	
	
}
