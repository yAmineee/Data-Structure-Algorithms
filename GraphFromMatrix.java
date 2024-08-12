package graph_implementation;

import java.util.Arrays;

/**
 * Simple Java implementation for Kruskal's algorithm
 * @author <a href = "https://www.geeksforgeeks.org/kruskals-algorithm-simple-implementation-for-adjacency-matrix/" > www.geeksforgeeks.org : Kruskal</a>
 * @author <a href = "https://www.geeksforgeeks.org/prims-algorithm-simple-implementation-for-adjacency-matrix-representation/" > www.geeksforgeeks.org : Prim</a>
 * @author Yamine Ibrahima : Merge et modifications mineures
 * */
public class GraphFromMatrix {

	private int V;
	private int[] parent;
	private int[][] edges;
	private int INF = Integer.MAX_VALUE;

	/**
	 * Crée un graphe avec V sommet
	 * @param nombreSommet : Nombre de sommets
	 * */
	public GraphFromMatrix(int nombreSommet) {
		this.V = nombreSommet;
		this.parent = new int[nombreSommet];
		
	}
	
	/**
	 * Génère les arêtes dépendamment de la matrice d'adjacence
	 * passé en paramètre.
	 * @param adjacencyMatrix Matrice d'adjacence du graphe
	 * */
	public void setEdges(int[][] adjacencyMatrix) {
		
		if(adjacencyMatrix.length != this.V) {
			System.out.println("La taille de la matrice d'adjacence est différente de " + this.V);
			return;
		}else {
			//Vérifier le length de chaque ligne de la matrice
			for(int i = 0; i < adjacencyMatrix.length ; i++) {
				
				if(adjacencyMatrix[i].length != this.V) {
					System.out.println("La taile de la " + (i+1) + " ème ligne de la matrice est différente de "+ this.V);
					return;
				}
				
			}
		}
		
		
		this.edges = adjacencyMatrix ;
	}
	
	/**
	 * Affiche les arêtes du graphes
	 * */
	public void affichageEdges() {
		for(int i = 0; i < this.edges.length; i++)
			System.out.println(Arrays.toString(this.edges[i]) );
	}
	
	
	/**----------------------------------------------------------------------- Methodes pour Kruskal -----------------*/
	//Find set of vertex i
	private int find(int i)
	{
		while (parent[i] != i)
			i = parent[i];
		return i;
	}

	//Does union of i and j. It returns
	//false if i and j are already in same
	//set.
	private void union1(int i, int j)
	{
		int a = find(i);
		int b = find(j);
		parent[a] = b;
	}

	//Finds MST using Kruskal's algorithm
	public void kruskalMST() {
		
		int cost[][] = this.edges ;
		
		int mincost = 0; // Cost of min MST.

		// Initialize sets of disjoint sets.
		for (int i = 0; i < V; i++)
			parent[i] = i;

		// Include minimum weight edges one by one
		int edge_count = 0;
		while (edge_count < V - 1)
		{
			int min = INF, a = -1, b = -1;
			for (int i = 0; i < V; i++)
			{
				for (int j = 0; j < V; j++) 
				{
					if (find(i) != find(j) && cost[i][j] < min) 
					{
						min = cost[i][j];
						a = i;
						b = j;
					}
				}
			}

			union1(a, b);
			System.out.printf("Edge %d:(%d, %d) cost:%d \n",
					edge_count++, a, b, min);
			mincost += min;
		}
		System.out.printf("\n Minimum cost= %d \n", mincost);
	}
	
	
	/**----------------------------------------------------------------------- Methodes pour PRIM -----------------*/
	
	// Returns true if edge u-v is a valid edge to be
		// include in MST. An edge is valid if one end is
		// already included in MST and other is not in MST.
		static boolean isValidEdge(int u, int v, boolean[] inMST) {
			if (u == v)
				return false;
			if (inMST[u] == false && inMST[v] == false)
				return false;
			else if (inMST[u] == true && inMST[v] == true)
				return false;
			return true;
		}
		

		public void primMST() {
			
			int cost[][] = this.edges;
			
			boolean []inMST = new boolean[V];

			// Include first vertex in MST
			inMST[0] = true;

			// Keep adding edges while number of included
			// edges does not become V-1.
			int edge_count = 0, mincost = 0;
			while (edge_count < V - 1)
			{

				// Find minimum weight valid edge. 
				int min = INF, a = -1, b = -1;
				for (int i = 0; i < V; i++) 
				{
					for (int j = 0; j < V; j++) 
					{			 
						if (cost[i][j] < min) 
						{
							if (isValidEdge(i, j, inMST)) 
							{
								min = cost[i][j];
								a = i;
								b = j;
							}
						}
					}
				}
				
				if (a != -1 && b != -1) 
				{
					System.out.printf("Edge %d:(%d, %d) cost: %d \n", 
											edge_count++, a, b, min);
					mincost = mincost + min;
					inMST[b] = inMST[a] = true;
				}
			}
			System.out.printf("\n Minimum cost = %d \n", mincost);
		}
}

//This code contributed by Rajput-Ji
