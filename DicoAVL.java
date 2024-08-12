/** 
 * DicoBST.java
 * 27 juin 2024
 * @author Yamine Ibrahima
 */
package arbres;

import java.util.List;
import java.util.ArrayList;
import deque.Deque_y;

/**
 * <i>Cette classe représente un dictionnaire implémenté avec un AVL.</i>
 * 		
 * @implNote Cette classe n'implémente pas de fonction de suppression. </br>
 * 			 Cette classe a été développé pour les besoins d'un Travail Dirigé
 * 			 d'un cours de structure de donnée de deuxème année de Baccalauréat 
 * 			 en informatique.</br> Elle intègre le strict minimum pour les besoins 
 * 			 du TD. Elle peut donc paraître incomplète à bien des égards.
 *  
 * @author Yamine Ibrahima
 */
public class DicoAVL<K extends Comparable<K>, V> {

	protected DicoAVLNoeud <K,V> racine;
	protected List<K> keys ;
	protected List<V> values;
	protected List<DicoAVLNoeud <K,V>> nodelist;
	
	/**
	 *	Construit un nouveau dictionnaire ayant une racine. 
	 * 
	 * @param rootkey : La clef de la racine de l'arbre
	 * @param rootValue : la valeur associé à la clef de la racine
	 * */	
	public DicoAVL(K rootkey, V rootValue) {
		this.racine = new DicoAVLNoeud<K,V>(rootkey, rootValue, null);
		this.nodelist = new ArrayList<DicoAVLNoeud <K,V>> ();
		this.keys = new ArrayList<K> ();
		this.values = new ArrayList<V>();
		
		this.nodelist.add(racine);
		this.keys.add(racine.getKey());
		this.values.add(racine.getValue());
	}
	
	/**
	 * Construit un dictionnaire vide
	 * */
	public DicoAVL() {
		this(null, null);
	}
	
	 /**
	  * Set la racine uniquement si l'arbre est vide
	  * */
	protected void setRacine(K rootkey, V rootValue) {
		this.racine = (this.racine.key == null)? new DicoAVLNoeud<K,V>(rootkey, rootValue, null)  : this.racine ;
		this.nodelist.set(0, racine);
		this.keys.set(0, racine.key);
		this.values.set(0, racine.value);
	}
	
		
	/**
	 * @return the racine
	 */
	public K getRacineKey() {
		return racine.getKey();
	}

	/**
	 * @return the hauteurTree
	 */
	public int getHauteurTree() {
		return this.racine.hauteur;
	}

	/**
	 * @return the nodelist
	 */
	public List<DicoAVLNoeud<K, V>> getNodelist() {	return nodelist; }

	/**
	 * Cette fonction permet d'ajouter un élément
	 * au dictionnaire.
	 * @param nodeKey La clef du noeud
	 * @param nodeValue la valeur associé à la clef key
	 * */
	public void ajouter(K nodeKey, V nodeValue) {
		
		/*
		 * Si l'arbre est vide, set la racine.
		 * */
		if (this.isEmpty())
			setRacine(nodeKey, nodeValue);
		
		
		//Si le noeud existe déjà
		
		if (rechercher(nodeKey) != null) {
			return;
		}
		
		
		/*
		 * Dans le cas où l'arbre n'est pas vide, le parcourir en level-order.
		 * 
		 * L'insertion sera performer de la même façon qu'un BST classique. La
		 * seule différence est que dans le cas du dictionnaire, les comparaisons
		 * s'effectueront uniquement sur les keys.
		 *
		 * 
		 * */
		
		Deque_y<DicoAVLNoeud<K, V>> file = new Deque_y<DicoAVLNoeud<K, V>>();
		DicoAVLNoeud<K, V> temp = null;
		
		file.enqueue(this.racine);
		
		while(!file.isEmpty()) {
			
			temp = file.dequeue();
			
			if (temp == null)
				break;
			
			if (nodeKey.compareTo(temp.key) < 0 ) {			// La clef du nouveau noeud est inférieur à celle du noeud courrant
				
				/*
				 * Performer l'insertion dans le sous-arbres gauche
				 * */
				if(temp.getFilsgauche() == null) {

					temp.setFilsgauche(nodeKey);
					temp.filsgauche.value = nodeValue;
					
					this.keys.add(temp.getFilsgauche().getKey());		// Ajouter la clef du noeud créé à l'ensemble des clefs du dictionnaire
					this.values.add(temp.getFilsgauche().getValue());	// Ajouter la valeur du noeud créé à l'ensemble des clefs du dictionnaire
					this.nodelist.add(temp.filsgauche);					// Ajouter le noeud insérer à la liste de noeud
					
					verificationAVL(temp.filsgauche);					// Après insertion du noeud, vérifier les propriétés AVL
					return;
				}else {
					
					//Si le noeud courrant à un fils gauche, l'enfiler
					file.enqueue(temp.getFilsgauche());
					continue;
					
				}
				
				
				
			}else if (nodeKey.compareTo(temp.key) > 0) {	// La clef du nouveau noeud est supérieur à celle du noeud courrant
				
				/*
				 * Performer l'insertion dans le sous-arbres droit
				 * */
				if(temp.getFilsdroit() == null) {

					temp.setFilsdroit(nodeKey);
					temp.filsdroit.value = nodeValue;
					
					this.keys.add(temp.getFilsdroit().getKey());		// Ajouter la clef du noeud créé à l'ensemble des clefs du dictionnaire
					this.values.add(temp.getFilsdroit().getValue());	// Ajouter la valeur du noeud créé à l'ensemble des clefs du dictionnaire
					this.nodelist.add(temp.filsdroit);					// Ajouter le noeud insérer à la liste de noeud
					
					verificationAVL(temp.filsdroit);					// Après insertion du noeud, vérifier les propriétés AVL
					return;
				}else {
					
					//Si le noeud courrant à un fils gauche, l'enfiler
					file.enqueue(temp.getFilsdroit());
					
				}
				
				
			}
			
		}
				
		
		
	
	}
	
	/**
	 * Cette fonction permet de supprimer un élément
	 * du dictionnaire, pourvu qu'il existe.
	 * @param key La clef du noeud
	 * @return l'élément supprimer.
	 * */
	public DicoAVLNoeud <K,V> supprimer(K key){
		
		//Pas implémenté car inutile pour les besoins du TD
		System.err.println("\t\t\tSuppression de noeud non-implémenter "
				+ "car inutile pour les besoins du TDs");
		
		return null;
	}
	
	/**
	 * Recherche le noeud spécifié par la clef
	 * @return le Noeud référencé qui encapsule
	 * la clef passé en paramètre
	 * @param clef la key à rechercher
	 * */
	public DicoAVLNoeud<K, V> rechercher(K clef){
		
		if(this.isEmpty())
			return null;
		
		Deque_y <DicoAVLNoeud <K,V>> file = new Deque_y <DicoAVLNoeud <K,V>>();
		
		file.enqueue(this.racine);
		DicoAVLNoeud <K,V> temp = null;
		
		while(!file.isEmpty()) {
			
			temp = file.dequeue();
			
			if (temp == null)
				break;
			
			//Vérifier si le data du noeud actuel correspond à l'élément recherché
			if (temp.getKey().compareTo(clef) == 0) {
				return temp;
			} else {				//Sinon enfiler ses enfants
				
				if(temp.getFilsgauche() != null) // temp.getFilsgauche().key 
					file.enqueue(temp.filsgauche);
				
				if(temp.getFilsdroit() != null) // temp.getFilsdroit().key
					file.enqueue(temp.filsdroit);				
				
			}
			
		}
			
		return null;
	}
	
	public boolean contains(K clef) {
				
		return containsKey(this.racine, clef);
	}
	
	/**
	 * Vérifie si le dictionnaire est vide
	 * */
	public boolean isEmpty() {	return this.racine.key == null ; }
	
	/**
	 * Cette fonction permet de déterminer si le 
	 * dictionnaire contient un noeud identifier
	 * par la clef passé en paramètre
	 * @param key Clef dont il faut vérifier l'existence
	 * dans le dictionnaire
	 * */
	protected boolean containsKey(DicoAVLNoeud <K,V> source, K clef) {
		
		//Cas ou le dictionnaire est vide
		if(this.isEmpty())
			return false;
		
		/**
		 * On pourrait également simplement vérifier si l'attribut
		 * keys du dictionnaire possède une entrée == key
		 * 
		 *		- return keys.contain(key);
		 * 
		 * On n'aurait donc plus besoin d'effectuer un parcours de l'arbre
		 * et donc on n'aurait plus besoin du paramètre source
		 * 
		 *  
		 * */	
		
		Deque_y <DicoAVLNoeud <K,V>> file = new Deque_y <DicoAVLNoeud <K,V>>();
		
		file.enqueue(source);
		DicoAVLNoeud <K,V> temp = null;
		
		while(!file.isEmpty()) {
			
			temp = file.dequeue();
			
			if (temp == null)
				break;
			
			//Vérifier si le data du noeud actuel correspond à l'élément recherché
			if (temp.getKey().compareTo(clef) == 0) {
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
	 * Cette fonction permet de set la valeur associé a une clef,
	 * pourvue que cette dernière existe dans le dictionnaire
	 * */
	public void setNodeValue(K key, V value) {
		
		if(this.keys.contains(key)) {
			
			DicoAVLNoeud <K ,V> temp = rechercher(key);
			temp.setValue(value);
			
		} else {
			throw new NullPointerException("Aucun noeud n'encapsule la clef mentionnée");
		}
		
	}
	
	/**
	 * Cette fonction permet d'accéder à la data associé
	 * à la clef passé en paramètre, pourvu que cette clef
	 * existe.
	 * @return la valeur associé à clef key ou null
	 * */
 	public V get(K clef) {
		
		if (!containsKey(racine, clef))
			return null;
		
		return rechercher(clef).getValue();
	}
	
	/**
	 * 
	 * @return Une représentation textuelle  (en level-order)  de l'arbre
	 * 
	 * */
	public String toprint() {
		
		String prompt = "";
		
		Deque_y <DicoAVLNoeud <K ,V>> file = new Deque_y <DicoAVLNoeud <K ,V>>();
		
		file.enqueue(this.racine);
		
		DicoAVLNoeud <K ,V> temp = null;
		
		//Faire un for qui va jusqu'à la profondeur de l'arbre
		
		while(!file.isEmpty()) {
			
			temp = file.dequeue(); //Noeud current
			
			if(temp != null) {
				if (temp == this.racine) {
					
					prompt += "Noeud : " + temp.getKey().toString()  + " \n"
							+ "\tValeur :" + ((temp.getValue() != null)? temp.getValue().toString() : "none") + "\n"
							+ "\tParent :" + " none " + " \n"
							+ "\tFilsgauche :"+ ((temp.getFilsgauche() != null)? temp.getFilsgauche().getKey().toString() : "none") + "\n"
							+ "\tFilsdroit :"+ ((temp.getFilsdroit() != null)? temp.getFilsdroit().getKey().toString() : "none") + "\n"
							+ "\tHauteur : " + temp.getHauteur()  + " \n----------------------";	
					
				}else {

					prompt += "\nNoeud : " + temp.getKey().toString()  + " \n"
							+ "\tValeur :" + ((temp.getValue() != null)? temp.getValue().toString() : "none") + "\n"
							+ "\tParent :" + temp.parent.toString() + "\n"
							+ "\tFilsgauche :"+ ((temp.getFilsgauche() != null)? temp.getFilsgauche().getKey().toString() : "none") + "\n"
							+ "\tFilsdroit :"+ ((temp.getFilsdroit() != null)? temp.getFilsdroit().getKey().toString() : "none") + "\n"
							+ "\tHauteur : " + temp.getHauteur() + " \n----------------------" ;
				}
				
				
				if(temp.getFilsgauche()!=null) {	
					
					file.enqueue(temp.filsgauche); 
				}
				
				if(temp.getFilsdroit() !=null) {
					
					file.enqueue(temp.filsdroit); 
				}
				
			}
		}
		
		prompt += " ";
			
		return prompt;
	}
	
	/**
	 * @return La liste de tous les noeuds de l'arbre avec leurs relations
	 * */
	@Override
	public String toString() {
		return "DicoBST [racine=" + racine + ", nodelist=" + nodelist + "]";
	}

	
	//========================================================Fonctions AVL===========================================================
	
	/**
	 * <i>Performe une rotation simple à gauche.</i></br></br>
	 * 
	 * <strong>Précondition :</strong> Le noeud nd viole la propriété AVL à cause d'une insertion
	 * dans son sous-arbre gauche. La différence de hauteur entre son sous-arbre
	 * gauche et son sous-arbre droit est de 2.</br></br>
	 * 
	 * <strong>Rotation simple à gauche :</strong> Le fils gauche (fgnd) du noeud nd devient le parent
	 * du noeud nd et le noeud nd devient le fils droit du noeud fgnd. Ensuite le fils
	 * droit de fgnd devient le fils gauche de nd. </br>
	 * @param nd : Noeud débalancé sur lequel appliqué la rotation
	 * */
 	private void rotationSG(DicoAVLNoeud<K,V> nd) {
		
		/*
		 * 1. Sauvegarder les informations du fils gauche de nd (fgnd)
		 * 2. Set nd en tant que fils droit de son fils gauche (fgnd)
		 * 3. Set fgnd en tant que parent de nd
		 * 4. Set le fils gauche de nd avec le fils droit de fgnd
		 * 5. Set nd en tant que parent du fils droit de fgnd
		 * 
		 * 6.Set les hauteurs
		 * 
		 * */
		
		DicoAVLNoeud<K,V> nd_fg = nd.filsgauche;
		
		if(nd.filsgauche !=null)
			nd.filsgauche.parent = nd.parent;	//Le parent de nd devient le parent de fgnd
		
		//Faire pointer le parent de nd vers le fils de gauche de nd (fgnd)
		if(nd.parent != null && nd.equals(nd.parent.filsgauche)) {
			nd.parent.filsgauche = nd_fg;
		}else if(nd.parent != null && nd.equals(nd.parent.filsdroit)) {
			nd.parent.filsdroit = nd_fg;
		}
		
		nd.parent = nd_fg;
		nd.filsgauche = (nd_fg != null) ? nd_fg.filsdroit : null ;
		
		if(nd_fg != null)
			nd_fg.filsdroit = nd;
			
		if (nd.equals(this.racine)){
			this.racine = (nd_fg != null)? nd_fg : this.racine;
		}
		
		
		nd.setHeight(Math.max((nd.filsgauche != null) ? nd.filsgauche.hauteur : 0,
								(nd.filsdroit!=null)? nd.filsdroit.hauteur : 0) + 1);
		
		if (nd_fg != null)
			nd_fg.setHeight(Math.max((nd_fg.filsgauche != null) ? nd_fg.filsgauche.hauteur : 0,
				(nd_fg.filsdroit!=null)? nd_fg.filsdroit.hauteur : 0) + 1);
		
		
		
	}
	
	/**
	 * <i>Performe une rotation simple à droite.</i></br></br>
	 * 
	 * <strong> Précondition :</strong>  Le noeud nd viole la propriété AVL à cause d'une insertion
	 * dans son sous-arbre droit. La différence de hauteur entre son sous-arbre
	 * gauche et son sous-arbre droit est de 2.</br></br>
	 * 
	 * <strong> Rotation simple à droite :</strong>  Le fils droit (fdnd) du noeud nd devient le parent
	 * du noeud nd et le noeud nd devient le fils gauche du noeud fdnd. Ensuite le fils
	 * gauche de fdnd devient le fils droit de nd.</br>
	 * @param nd : Noeud débalancé sur lequel appliqué la rotation
	 * */
	private void rotationSD(DicoAVLNoeud<K,V> nd) {
		
		DicoAVLNoeud<K,V> nd_fd = nd.filsdroit;
		
		if(nd.filsdroit !=null)
			nd.filsdroit.parent = nd.parent;	//Le parent de nd devient le parent de fdnd
		
		//Faire pointer le parent de nd vers le fils de droit de nd (fdnd)
		if(nd.parent != null && nd.equals(nd.parent.filsdroit)) {
			nd.parent.filsdroit = nd_fd;
		}else if(nd.parent != null && nd.equals(nd.parent.filsgauche)) {
			nd.parent.filsgauche = nd_fd;
		}
		
		nd.parent = nd_fd;
		nd.filsdroit = (nd_fd != null) ? nd_fd.filsgauche : null ;
		
		if(nd_fd != null)
			nd_fd.filsgauche = nd;
			
		if (nd.equals(this.racine)){
			this.racine = (nd_fd != null)? nd_fd : this.racine;
		}
		
		
		nd.setHeight(Math.max((nd.filsgauche != null) ? nd.filsgauche.hauteur : 0,
								(nd.filsdroit!=null)? nd.filsdroit.hauteur : 0) + 1);
		
		if (nd_fd != null)
			nd_fd.setHeight(Math.max((nd_fd.filsgauche != null) ? nd_fd.filsgauche.hauteur : 0,
				(nd_fd.filsdroit!=null)? nd_fd.filsdroit.hauteur : 0) + 1);
		
		
	}
	
	/**
	 * <i>Performe une rotation double à gauche.</i></br></br>
	 * 
	 * <strong>Précondition :</strong> Le noeud nd viole la propriété AVL à cause d'une insertion
	 * dans le sous-arbre droit de son fils de gauche. La différence de hauteur entre son sous-arbre
	 * gauche et son sous-arbre droit est de 2.</br></br>
	 * 
	 * <strong>Rotation double à gauche :</strong> Une rotation simple à droite du
	 * fils de gauche de nd suivi d'une rotation simple à gauche de nd.</br>
	 * 
	 * @param nd : noeud violant la propriété AVL et sur lequel effectué la rotation.
	 * */
	private void rotationDG(DicoAVLNoeud<K,V> nd) {
		this.rotationSD(nd.filsgauche);
		this.rotationSG(nd); 	
	}
	
	/**
	 * <i>Performe une rotation double à droite.</i></br></br>
	 * 
	 * <strong>Précondition :</strong> Le noeud nd viole la propriété AVL à cause d'une insertion
	 * dans le sous-arbre gauche de son fils de droit. La différence de hauteur entre son sous-arbre
	 * gauche et son sous-arbre droit est de 2
	 * 
	 * <strong>Rotation double à gauche :</strong> Une rotation simple à gauche du
	 * fils de droite de nd suivi d'une rotation simple à droite de nd.</br>
	 * 
	 * @param nd : noeud violant la propriété AVL et sur lequel effectué la rotation.
	 * 
	 * */
	private void rotationDD(DicoAVLNoeud<K,V> nd) {
		this.rotationSG(nd.filsdroit);
		this.rotationSD(nd);
	}
	
	/**
	 * <i>Détermine le premier noeud (ascendant au noeud passé en paramètre) qui ne 
	 * respecte pas la propriété AVL, et lui applique la rotation adéquate afin de
	 * rétablir la propriété AVL.</i></br></br>
	 * 
	 * @param node : Noeud récemment inséré dans l'arbre
	 * */
	private void verificationAVL( DicoAVLNoeud<K,V> node){
		
		DicoAVLNoeud<K,V> node_current = node.parent ;
		int hauteur_filsgauche = -1;
		int hauteur_filsdroit = -1;
		
		/**
		 * Déterminer le 1er noeud ascendant au noeud inséré (le paramètre node)
		 * qui ne respecte pas les propriétés AVL et le sauvegar
		 * */
		while (node_current != null) {
			//Stocker la hauteur des fils du noeud courrant. Si un fils n'existe pas,sa hauteur = 0
			hauteur_filsgauche = (node_current.filsgauche != null) ? node_current.filsgauche.hauteur : 0 ;
			hauteur_filsdroit = (node_current.filsdroit != null) ? node_current.filsdroit.hauteur : 0;
			
			//Si la différence entre les hauteurs des fils du noeud courrant est = 2 alors sortir de la boucle
			if ((hauteur_filsgauche - hauteur_filsdroit == 2) || (hauteur_filsdroit - hauteur_filsgauche == 2) )
				break;
			
			//Sinon passer au prochain ancêtre
			node_current = node_current.parent; //Passer au prochain parent
		}
		
		//Aucun noeud non-conforme n'a été trouvé
		if(node_current == null)
			return;
		
		/*
		System.out.println("\n\n==============================================================================================================\n"
				+ "---------------Noeud parent non-conforme = " + node_current.getKey() + "\n"
						+ "---------------Noeud inséré = " + node.key + "\n"
								+ "==============================================================================================================\n"); */
		
		//Le débalancement est causé par le sous arbre gauche du noeud non-conforme
		if(hauteur_filsgauche > hauteur_filsdroit) {
			int hauteurfgfg = (node_current.filsgauche.filsgauche != null) ? node_current.filsgauche.filsgauche.hauteur : 0 ;
			int hauteurfgfd = (node_current.filsgauche.filsdroit != null) ? node_current.filsgauche.filsdroit.hauteur : 0 ;
			
			if(hauteurfgfg > hauteurfgfd ) {
				
				this.rotationSG(node_current);
				return;
			}else{
				this.rotationDG(node_current);
				return;
			}
			
		} 
		
		//Le débalancement est causé par le sous arbre droit du noeud non-conforme
		if (hauteur_filsdroit > hauteur_filsgauche) {
			int hauteurfdfd = (node_current.filsdroit.filsdroit != null) ? node_current.filsdroit.filsdroit.hauteur : 0 ;
			int hauteurfdfg = (node_current.filsdroit.filsgauche != null) ? node_current.filsdroit.filsgauche.hauteur : 0 ;
			
			if(hauteurfdfd > hauteurfdfg ) {
				
				this.rotationSD(node_current);
				return;
			}else{
				this.rotationDD(node_current);
				return;
			}
			
		}
		
		
		//System.err.println("------------------------------------------- Aucune rotation effectuée ");
		
	}
	
	//============================================================================================================================
	
	/**
	 * Cette classe représente un noeud du dictionnaire
	 * 
	 * @author Yamine Ibrahima
	 * */
	@SuppressWarnings("hiding")
	protected class DicoAVLNoeud <K ,V> {
		
		//Attribut
		protected K key;
		protected V value;
		/**
		 * La hauteur d'un noeud, représente la distance entre la feuille la plus profonde
		 * de l'arbre et le noeud courrant. 
		 * A ne pas confondre avec la profondeur qui représente la distance de la racine
		 * de l'arbre au le noeud courrant.
		 * 
		 * La hauteur d'une feuille est de 1
		 * */
		private int hauteur;
		private DicoAVLNoeud <K ,V> parent;
		protected DicoAVLNoeud <K ,V> filsgauche;
		protected DicoAVLNoeud <K ,V> filsdroit ;
		
		//Constructeurs
		/**
		 * 
		 */
		public DicoAVLNoeud(K clef) {
			
			this.key = clef ;
			this.value = null;
			this.parent = null;
			this.filsgauche = null;
			this.filsdroit = null;
		
		}
		
		/**
		 * Construit un noeud.
		 * @param clef : La key
		 * @param value : La valeur associé à la clef
		 * @param p : le noeud parent au noeud construit 
		 * */
		public DicoAVLNoeud(K clef, V value, DicoAVLNoeud<K, V> p) {
			this(clef);
			this.value = value;		
			this.parent = p ;
			//this.profondeur= (p!=null)? p.getProfondeur() + 1 : 0;
			/*
			 * Chaque noeud nouvellement inséré est une feuille donc sa hauteur = 1 ; 
			 * */
			this.hauteur = 1 ;
			
			/*
			 * Une fois un noeud inséré, mettre à jour la hauteur de ses ancêtres
			 * */
			updateAncestorHeight(); 
			
		}
		
		/**
		 * @param filsgauche the filsgauche to set
		 */
		public void setFilsgauche(K clef) {
			this.filsgauche = new DicoAVLNoeud<K, V>(clef,null,this);
		}

		/**
		 * @param clef : La clef du noeudfils droit
		 */
		public void setFilsdroit(K clef) {
			this.filsdroit = new DicoAVLNoeud<K, V>(clef,null,this);
		}
		
		/**
		 * @param value the value to set
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		/**
		 * @return the filsgauche
		 */
		public DicoAVLNoeud<K, V> getFilsgauche() {		return filsgauche; }

		/**
		 * @return the filsdroit
		 */
		public DicoAVLNoeud<K, V> getFilsdroit() {	return filsdroit; }

		/**
		 * @return the hauteur
		 */
		public int getHauteur() {	return hauteur; }

		/**
		 * @return the key
		 */
		public K getKey() { 	return key; }

		/**
		 * @return the value
		 */
		public V getValue() {	 return value; }
		
		/**
		 * Cette methode permet à chaque fois qu'on insère un noeud,
		 * 	de mettre à jour la hauteur de tous ces noeuds parents.
		 * */
		private void updateAncestorHeight() {
			
			//Si le noeud courrant n'a pas d'ancêtre arrêter = cas du root
			if( this.parent == null)
				return ;
		
	
			//DicoAVLNoeud<K, V> current = this;
			DicoAVLNoeud<K, V> ancestor = this.parent;
			while(ancestor != null) {
				
				//Update la hauteur du parent direct
				ancestor.hauteur = Math.max(ancestor.hauteur, this.hauteur+1) ;
				
				//System.out.println("Dans le while =========------------------------- Ancestor = " + ancestor.getKey() + " Current = " + current.getKey());
				
				//Itérer sur le prochain ancêtre
				//current = ancestor;
				ancestor = ancestor.parent;
			} 
			
			/*
			 * Cette facon de procéder cause un java.lang.StackOverflowError avec un nombre élever d'entrer
			 * car pour chaque feuille, les appels se font en O(hauteur de l'arbre) 
			//Update la hauteur du parent direct
			this.parent.hauteur = Math.max(this.parent.hauteur, this.hauteur+1) ;
			
			//Appel recursif sur les ancêtres
			this.parent.updateAncestorHeight(); 
			*/
		}
		
		private void setHeight(int h) {	
			this.hauteur = h;
		}
		
		@Override
		public String toString() {
			return "[key=" + key + ""
					+ ", value=" + value + ""
							+ ", parent=" +((this.parent != null )? parent.getKey() : "null") + ""
									+ ", filsgauche=" + ((filsgauche != null )? filsgauche.getKey() : "null") + ""
											+ ", filsdroit=" +((filsdroit != null )? filsdroit.getKey() : "null") + ""
													+ ", hauteur=" + this.hauteur +"]\n\t\t"; 
		}
		
		
		
		
		
	}
}
