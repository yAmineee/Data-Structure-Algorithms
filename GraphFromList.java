package graph_implementation;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import double_linkedList.DoublyLinkedList;
import double_linkedList.Noeud;


/**
 * Cette classe représente un graphe non-dirigé pondéré utilisant une liste de successeurs. </br></br>
 * Elle contient des implémentations des algorithmes sur les Arbres Couvrants Minimum de PRIM et KRUSKAL : </br>
 *  {@link #Prim_AdjencyList()} : implémentation classique de l'algorithme de PRIM selon le pseudo-code
 * 	proposé dans la 4em édition du livre de H.Cormen et Al. "Introduction to Algorithms". </br>
 * 
 * {@link #Prim_AdjencyList_linkedlist()}, 
 * {@link #Kruskal_AdjencyList()}, 
 * {@link #Kruskal_AdjencyList_disjointSet()}.
 *  
 *  
 * @author Yamine Ibrahima
 * */
public class GraphFromList<V extends Comparable<V>> {

	private HashMap<Sommet<V>, ArrayList<Sommet<V>> > listeAdjacence;
	private ArrayList<Arete> listeAretes;
	private int nSommets;
	private int nAretes;

	/**
	 * Construit un graphe vide
	 * */
	public GraphFromList() {
		this.listeAdjacence = new HashMap<>();
		this.listeAretes = new ArrayList<Arete>();
		this.nSommets = 0;
		this.nAretes = 0;
	}

	/**
	 * @return the nSommets
	 */
	public int getnSommets() {	return nSommets; }

	/**
	 * @return the nAretes
	 */
	public int getnAretes() {	return nAretes; }

	/**
	 * @return the listeAretes
	 */
	public ArrayList<Arete> getListeAretes() {	return listeAretes; }

	/**
	 * Créer un sommet et l'ajoute au graphe
	 * */
	public void ajouterSommet(V vertexname)	{

		Sommet<V> vertex = new Sommet<V>(vertexname);
		listeAdjacence.put(vertex, vertex.successeurs);		//Ajouter le sommet au graphe
		this.nSommets++;									//Incrémenter le nombre de sommets

	}

	/**
	 * Supprimer un sommet du graphe
	 * @param vertexname nom du sommet à supprimer
	 * */
	public void supprimerSommet(V vertexname)	{

		Sommet<V> todelete = this.getSommet(vertexname);

		if(todelete == null) {
			System.out.println("Le noeud " + vertexname + " que vous essayer de supprimer n'existe pas");
			return;
		}

		//Supprimer les aretes comportant ce noeuds
		for(Arete edge : listeAretes) {
			if(edge.getDestination() == todelete || edge.getOrigine() == todelete) {
				this.listeAretes.set(listeAretes.indexOf(edge), null);
				this.nAretes--;
			}
		}

		ArrayList<Arete> temp = new ArrayList<Arete>();
		temp.add(null); 
		listeAretes.removeAll(temp);	//Pour pouvoir supprimer le null set dans le for plus haut


		//Enlever ce noeud des listes de successeurs des autres noeuds
		for(ArrayList<Sommet<V>> successeurliste : this.listeAdjacence.values()) {

			if(successeurliste.contains(todelete)) {
				successeurliste.remove(todelete);
			}

		}

		//Supprimer le noeud de la liste d'adjacence
		this.listeAdjacence.remove(todelete);

		//Décrementer le nombre de sommet
		this.nSommets--;
	}

	/**
	 * Vérifie si le graphe contient un sommet spécifier par le nom passé en param
	 * @return l'objet sommet qui encapsule le nom passé en param, pourvu qu'il existe.
	 * */
	private Sommet<V> getSommet(V vertexname) {

		for(Sommet<V> sommet : listeAdjacence.keySet()) {
			if(sommet.nom.compareTo(vertexname) == 0)
				return sommet;
		}

		return null;
	}

	/**
	 * Ajouter une arete ayant un poids donné entre 2 sommets
	 * 
	 * @param source : Le sommet source (doit préalablement exister)
	 * @param destination : Le sommet destination (doit préalablement exister)
	 * @param poids : Le poids de cet arete
	 * */
	public void ajouterArete(V source, V destination, int poids) {

		Sommet<V> src = this.getSommet(source);				//Source de l'arete
		Sommet<V> dst = this.getSommet(destination);		//Destination de l'arete

		//Vérifier que la source et la destination existe bien
		if(src == null) {
			System.out.println("Impossible de créer cet arete "
					+ "car le sommet source " + source.toString() + " n'existe pas !");
			return;
		} else if(dst == null) {
			System.out.println("Impossible de créer cet arete "
					+ "car le sommet destination " + destination.toString() + " n'existe pas !");
			return;
		}

		//Création de l'arete
		Arete edge = new Arete(src,dst,poids) ;				// Créer l'objet arete
		this.listeAretes.add(edge);							// Ajouter l'objet créer à la liste des aretes
		this.nAretes++;										// Incrémenter le nombre d'aretes
	}

	/**
	 * Supprimer une arete du graphe.
	 * @param source : origine de l'arete à supprimer
	 * @param destination : destination de l'arete à supprimer
	 * */
	public void supprimerArete(V source, V destination) {

		Sommet<V> src = this.getSommet(source);				//Source de l'arete
		Sommet<V> dst = this.getSommet(destination);		//Destination de l'arete

		//Vérifier que la source et la destination existe bien
		if(src == null) {
			System.out.println("Impossible de supprimer cet arete "
					+ "car le sommet source " + source.toString() + " n'existe pas !");
			return;
		} else if(dst == null) {
			System.out.println("Impossible de supprimer cet arete "
					+ "car le sommet destination " + destination.toString() + " n'existe pas !");
			return;
		}

		//Supprimer de la liste d'arête
		for(Arete edge : this.listeAretes) {
			if(edge.getOrigine() == src && edge.getDestination() == dst) {
				this.listeAretes.remove(edge);
				this.nAretes--;
				break;
			}
		}
		//Supprimer les liens entre la source et la destination
		src.successeurs.remove(dst);
		dst.successeurs.remove(src);


	}

	/**
	 * Comparer le poids de deux aretes
	 * */
	public int comparerArete(Arete a1, Arete a2) {
		return a1.comparerPoids(a2);
	}

	private Arete getArete(Sommet<V> source, Sommet<V> dest) {

		for(Arete edge : this.listeAretes) {

			if((edge.origine.equals(source) && edge.destination.equals(dest)) || 
					(edge.origine.equals(dest) && edge.destination.equals(source)))
				return edge;

		}

		return null;

	}

	/**
	 * Afficher le contenu du graphe
	 * */
	public void toprint() {

		System.out.println("Sommets\t \tSuccesseurs");
		for (Map.Entry<Sommet<V>, ArrayList<Sommet<V>> > element : listeAdjacence.entrySet()) {
			System.out.print(element.getKey().getNom() + " \t<-> \t");
			for (Sommet<V> successeur : element.getValue()) {
				System.out.print(successeur.getNom() + " ");
			}
			System.out.println();
		}
	}

	/** -----------------------------------------------Algo ACM----------------------------------------------------------------------------------* */

	/**
	 * Implementation (sans disjoint set) de l'algorithme de Kruskal sur un graphe
	 * représenté par une liste d'adjacence. </br> </br>
	 * 
	 * L'algorithme utilise les listes chainées.
	 * 
	 * @throws Exception 
	 * */
	public void Kruskal_AdjencyList() throws Exception{

		ArrayList<String> ACMPath = new ArrayList<String>(this.nSommets);	//Liste pour sauvegarder le path de l'ACM
		int sommeACMPath = 0;

		//ArrayList pour stocker chacune des linkedList
		ArrayList<DoublyLinkedList<Sommet<V>>> linkedlistset = new ArrayList<DoublyLinkedList<Sommet<V>>>(this.nSommets);

		//Initialisation des ensembles de bases
		DoublyLinkedList<Sommet<V>> lkdlst = null ;
		for(Sommet<V> sommet : this.listeAdjacence.keySet()) {
			lkdlst = new DoublyLinkedList<Sommet<V>>();				//	Création d'une liste chainée vide
			lkdlst.addFirst(sommet);								//	Ajout d'un sommet à l'ensemble représenté par la liste chainée crée
			linkedlistset.add(lkdlst);								//	Ajout de l'ensemble formé par le sommet à l'ensemble des sets
		}

		//System.out.println(linkedlistset.toString());				// 	Pour avoir un aperçu des différents ensemble (liste chainée) créé

		//Trier les Aretes par ordre de poids croissant
		List<Arete> sorted_Edge_List = new ArrayList<>(this.listeAretes);
		Collections.sort(sorted_Edge_List);
		//System.out.println(sorted_Edge_List);

		/*
		 * * * * * * * * * * * * * * * * * * * * * * * * Traitement KRUSKAL
		 * Pour chaque arete Si --> Sj :
		 * 		
		 * 		+ Déterminer le head de la liste chainée contenant Si
		 * 		+ Déterminer le head de la liste chainée contenant Sj
		 * 
		 * 		+ Si Head_Si != Head_Sj (les 2 endpoints d'une arete sont contenu dans 2 différents ensembles)
		 * 			- Ajouter l'arete à l'arbre couvrant minimum
		 * 			- Fusionner les ensembles contenant Si et Sj ( c-a-d ajouter la linkedlist contenant
		 * 				Sj à la linkedlist contenant Si )
		 * 			- Supprimer l'ensemble (la linkedlist) qui contenait Sj
		 * 
		 * 		+ Réinitailiser les références des variables utiliser pour les traitements
		 * 	
		 * Si : Origine d'une arete
		 * Sj : Destination de l'arete
		 * 	
		 * */

		//Initialiser les variables à utiliser pour sauvegarder les références
		Arete edge = null ;

		Sommet<V> edge_origine = null;				// Si : Origine de l'arete
		Sommet<V> edge_destination = null;			// Sj : Destination de l'arete

		Noeud<Sommet<V>> head_Si = null;			// Référence vers le head de la liste chainée contenant Si
		Noeud<Sommet<V>> head_Sj = null;			// Référence vers le head de la liste chainée contenant Sj

		Noeud<Sommet<V>> current = null;			// Variable pour le parcours des listes chainées

		while(!sorted_Edge_List.isEmpty()) {

			edge = sorted_Edge_List.get(0);					// get l'arete ayant le plus petit poids

			edge_origine = edge.origine;					// Si : Origine de l'arete
			edge_destination = edge.destination;			// Sj : Destination de l'arete


			//Déterminer head_Si : le head de la liste chainée contenant le sommet Si (origine de l'arete)
			for( DoublyLinkedList<Sommet<V>> lst : linkedlistset) {		//Parcourir chaque liste chainée contenu dans la liste des ensembles (la linkedlistset)

				current = lst.getHead();								// Référence vers le head de la liste chainée courrante

				while (current != null) {								// Parcourir la liste chainée

					/* Si un élément de la liste chainée courante correspond à l'origine de l'edge 
					 * alors on a trouvé l'ensemble auquel appartient l'origine de l'edge.
					 * 
					 *  Sauvegarder une référence vers le head de la liste chainée et arrêter le parcours
					 * */
					if(current.getElement().equals(edge_origine)) {		
						head_Si = lst.getHead();
						break;								// Sortir du while
					}

					current = current.getNext();
				}

				if(head_Si == lst.getHead())
					break;									// Sortir du for

			}

			//Déterminer head_Sj : le head de la liste chainée contenant le sommet Sj (destination de l'arete)
			for( DoublyLinkedList<Sommet<V>> lst : linkedlistset) {		//Parcourir chaque liste chainée contenu dans la linkedlistset

				current = lst.getHead();					// Référence vers le head de la liste chainée courrante

				while (current != null) {					// Parcourir la liste chainée

					if(current.getElement().equals(edge_destination)) {		
						head_Sj = lst.getHead();
						break;								// Sortir du while
					}

					current = current.getNext();
				}

				if(head_Sj == lst.getHead())
					break;									// Sortir du for

			}

			//Verifier si une arete est safe pour le path ACM
			if(head_Si != head_Sj) {

				ACMPath.add(edge.toString());
				sommeACMPath += edge.getPoids();

				//Fusionner les liste chainées contenant Si et Sj
				for( DoublyLinkedList<Sommet<V>> lst : linkedlistset) {		//Reparcourir l'ensemble des liste chainées :

					if(lst.getHead().equals(head_Si)) { 					//Trouver la liste dont le head est égal à head_Si
						lst.append(head_Sj)	;								//Ajouter la liste chainée représenté par head_Sj à la liste chainée représentée par head_Si		
						break;
					}

				}

				/*
				 * Après avoir ajouté la liste chainée contenant Sj à la liste chainée contenant Si
				 * il faut supprimer la liste chainée qui contenait Sj
				 * */
				DoublyLinkedList<Sommet<V>> toremove = null;
				for( DoublyLinkedList<Sommet<V>> lst : linkedlistset) {	

					if(lst.getHead().equals(head_Sj)) {
						toremove = lst;
					}

				}
				linkedlistset.remove(toremove);

			}


			// A la fin de chaque itération, reinitialiser les références des variables utilisé
			edge = null ;
			edge_origine = null;				// Si : Origine de l'arete
			edge_destination = null;			// Sj : Destination de l'arete
			head_Si = null;		// Référence vers le head de la liste chainée contenant Si
			head_Sj = null;		// Référence vers le head de la liste chainée contenant Sj
			current = null;

			sorted_Edge_List.remove(0); 		//Supprimer l'arete qui vient d'être traité

		}


		//System.out.println(linkedlistset.toString());

		System.out.println("\n\nACMPath (poids total = " + sommeACMPath + " ) :\n" + ACMPath.toString());

	}


	/**
	 * Implementation de l'algorithme (en utilisant les liste 
	 * chainée au lieu des priority queue) de Prim sur un graphe
	 * représenté par une Liste d'adjacence.
	 * */
	public void Prim_AdjencyList() {

		int coutACM = 0;

		//Set les attributs key et predec de chaque sommet du graphe
		for(Sommet<V> vtx : this.listeAdjacence.keySet()) {
			vtx.key = Integer.MAX_VALUE;
			vtx.predec = null;
		}

		//Initialisation de la racine de l'ACM
		@SuppressWarnings("unchecked")
		Sommet<V> temp = (Sommet<V>) this.listeAdjacence.keySet().toArray()[0];
		temp.key = 0;


		//Liste chainée
		DoublyLinkedList<Sommet<V>> linkedlist = new DoublyLinkedList<>();

		//Ajouter un élément à listechainée
		linkedlist.addFirst(temp);

		//Variable pour le parcours de la liste chainée
		Noeud<Sommet<V>> current = null;

		try {

			//Ajouter chaque noeud à la liste chainée dépendamment de la valeur de son attribut key
			for(Sommet<V> vtx : this.listeAdjacence.keySet()) {

				if(vtx == linkedlist.getHead().getElement())
					continue;

				linkedlist.addLast(vtx);

			}

			//System.out.println("Liste chainée PRIM après initialisation : " + linkedlist);

			//ACM Path
			ArrayList<Arete> path_ACM = new ArrayList<Arete>();

			while (!linkedlist.isEmpty()) {

				temp = linkedlist.removeFirst();

				coutACM += temp.key;
				path_ACM.add(this.getArete(temp.predec, temp));

				for(Sommet<V> vtx : temp.successeurs) {

					//Vérifier si la liste chainée contient déjà ce sommet
					current = linkedlist.getHead();
					while (current != null) {

						if(current.getElement().equals(vtx) && this.getArete(temp, vtx).getPoids() < vtx.key) {

							linkedlist.remove(current);
							current.getElement().predec = temp;
							current.getElement().key = this.getArete(temp, vtx).getPoids();

							//Si l'attribut key du sommet courrant est < au head, set un nouveau head
							if(current.getElement().key < linkedlist.getHead().getElement().key) {
								linkedlist.addFirst(current.getElement());
								continue;

							} else if(current.getElement().key >= linkedlist.getHead().getElement().key ) {

								/*
								 * Si la key du sommet courrant est supérieur au head,
								 *  insérer le sommet de sorte que pred.key < current.key < succ.key
								 * */

								Noeud<Sommet<V>> current_parcours = linkedlist.getTail();

								while(current_parcours != null){

									if(current.getElement().key >= current_parcours.getElement().key) {
										linkedlist.addBetween(current.getElement(), current_parcours, current_parcours.getNext());
										break; 	//Sortir du while
									}

									current_parcours = current_parcours.getPrevious();
								}
								current_parcours = null;

							}
							break;
						}

						current = current.getNext();
					}

				}

			}

			System.out.println("\nListe des aretes constituant l'ACM :");
			for(Arete edge : path_ACM) {
				System.out.println((edge != null ) ? edge.toString() : " - ");
			}

			System.out.println("Poids total de l'ACM = " + coutACM);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	/** -----------------------------------------------Algo ACM----------------------------------------------------------------------------------* */


	/**
	 * Cette classe représente un objet Sommet
	 * @author Yamine Ibrahima
	 * */
	private class Sommet <S> implements Comparable<Sommet <S>>{

		/**/
		private S nom;

		/**
		 * Attribut utile pour l'implémentation de l'ago de prim 
		 * */
		private int key;
		private Sommet<S> predec ;

		private ArrayList<Sommet<S>> successeurs;

		public Sommet(S name) {

			this.nom = name;
			this.successeurs = new ArrayList<Sommet<S>>();

		}

		/**
		 * @return the nom
		 */
		public String getNom() {
			return nom.toString();
		}


		@SuppressWarnings("unused")
		public Comparator <Sommet<S>>  getComparateur(){

			Comparator <Sommet<S>> cmptr = new Comparator <Sommet<S>> () {

				@Override
				public int compare(Sommet<S> o1, Sommet<S> o2) {

					return Integer.compare(o1.key, o2.key);
				}

			};

			return cmptr;

		}





		@Override
		public String toString() {
			//return "Sommet [nom=" + nom.toString() + "]";
			return "[ " + nom.toString() + "]";
		}

		@Override
		public int compareTo(Sommet<S> o) {

			return Integer.compare(this.key, o.key);
		}


	}

	/**
	 * Cette classe représente un objet Arete
	 * @author Yamine Ibrahima
	 * */
	private class Arete  implements Comparable<Arete>{

		private Sommet<V> origine;
		private Sommet<V> destination;
		private int poids ;

		/**
		 * Construit une arete ayant le poids "weight" entre les sommets "from" et "to".
		 * 
		 * @param from : Source de l'arete
		 * @param destination : Destination de l'arete
		 * @param weight : poids de l'arete
		 * */
		public Arete(Sommet<V> from, Sommet<V> to, int weight) {

			this.origine = from;
			this.destination = to;
			this.poids = weight;

			from.successeurs.add(to);
			to.successeurs.add(from);

		}

		/**
		 * @return the origine
		 */
		public Sommet<V> getOrigine() {	return origine;	}

		/**
		 * @return the destination
		 */
		public Sommet<V> getDestination() {	return destination;	}

		/**
		 * @return the poids
		 */
		public int getPoids() {		return poids; }

		/**
		 * Permet de comparer le poids de deux aretes
		 * */
		private int comparerPoids(Arete arete) {

			return Integer.compare(this.poids, arete.poids);
		}

		@SuppressWarnings("unused")
		//Utile pour utiliser les disjoint set avec kruskal
		public static Comparator <Arete> getComparateur (){

			Comparator <Arete> cmptr = new Comparator <Arete> () {

				@Override
				public int compare(Arete o1, Arete o2) {

					return Integer.compare(o1.poids, o2.poids);
					//return o1.comparerPoids(o2);
				}

			};

			return cmptr;

		}

		@Override
		public String toString() {
			return "\t[origine = " + origine.getNom() + ", destination = " + destination.getNom() + ", poids = " + this.getPoids() + "]\n";
		}

		@Override
		public int compareTo(Arete o) {
			return Integer.compare(this.poids, o.poids);
		}


	}



}