package projet;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		
		try {
			
			// Demande a l'utilisateur de choisir le graphe
			System.out.println("Quel graphe voulez vous etudier? (-1 pour quitter)");
			
			// Récupération de la saisie de l'utilisateur
			Scanner action = new Scanner(System.in);
			int nb = action.nextInt();

			while (nb != -1) {
				
				// Déclaration des types de variables
				String file;
				Graph graph;
				
				// Récupération du fichier txt du graphe à étudier
				file = new File("").getAbsolutePath() + "/src/projet/Graphe" + nb + ".txt";
				
				// Conversion du graphe en tableau 2D à partir du fichier txt
				// graph = convert(file);
				graph = new Graph(file);
				
				// Lecture du graphe
				System.out.println();
				readGraph(graph);
				
				// Lecture de la matrice d'adjacence
				System.out.println();
				readAdjacencyMatrix(graph);

				// Lecture de la matrice de valeurs
				System.out.println();
				readValuesMatrix(graph);
				
				// Detection de circuit
				System.out.println();
				detectCycle(graph);
				
				// Calcul de rangs
				System.out.println();
				System.out.println("Calcul de rangs");
				if (!isCyclic(graph)) {
					
				} else {
					System.out.println("On ne peut pas étudier les rangs du graphe car il contient au moins un circuit.");
				} 
				nb = -1;				
			}
			
			System.out.println();
			System.out.println("Fin");
			action.close();
			
		} catch (Exception e) {
			
			System.err.println(e + "Un problème est survenu");
			
		}
	}
	
	
	// Lecture du graphe
	public static void readGraph(Graph graph) {
		System.out.println("* Lecture du graphe \n");
		ArrayList<String[]> graphData = graph.getData();
		System.out.println(graph.getNumberVertices() + " sommets");
		System.out.println(graph.getNumberArcs() + " arcs");
		for (int i = 0; i < graphData.size(); i++) {
			System.out.println(graphData.get(i)[0] + " -> " + graphData.get(i)[1] + " = " + graphData.get(i)[2]);
		}
	}
	
	
	// Lecture de la matrice d'adjacence
	public static void readAdjacencyMatrix(Graph graph) {
		System.out.println("* Lecture de la matrice d'adjacence \n");
		String[][] m = graph.getAdjacencyMatrix();
		for (int row = 0; row < m.length+1; row++) {
            for (int col = 0; col < m.length+1; col++) {
            	if (row == 0) {
            		if (col == 0) {
            			System.out.printf("%6s", " ");
            		} else {
            			System.out.printf("%6s", col-1);
            		}
            		
            	} else {
            		if (col == 0) {
            			System.out.printf("%6s", row-1);
            		} else {
            			System.out.printf("%6s", m[row-1][col-1]);
            		}
            	}
                
            }
            System.out.println();
        }
	}
	
	// Lecture de la matrice de valeurs
	public static void readValuesMatrix(Graph graph) {
		System.out.println("* Lecture de la matrice de valeurs \n");
		String[][] m = graph.getValuesMatrix();
		for (int row = 0; row < m.length+1; row++) {
            for (int col = 0; col < m.length+1; col++) {
            	if (row == 0) {
            		if (col == 0) {
            			System.out.printf("%6s", " ");
            		} else {
            			System.out.printf("%6s", col-1);
            		}	
            	} else {
            		if (col == 0) {
            			System.out.printf("%6s", row-1);
            		} else {
            			if (m[row-1][col-1] == null) {
                    		System.out.printf("%6s", "*");
                    	} else {
                    		System.out.printf("%6s", m[row-1][col-1]);
                    	}
            		}
            	}
            }
            System.out.println();
        }
	}
	
	
	// Detection de cycle d'un graphe
	public static void detectCycle(Graph graph) {
		System.out.println("* Detection de circuit \n");
		Graph tempGraph = graph;
		// On recupere tous les sommets
		String[] pr = tempGraph.getAllVertices();
		//Tant qu'il y a toujours des points d'entre
		while (tempGraph.getSourceVertices().length != 0) {
			// On recupere les points d'entree
			String[] sv = tempGraph.getSourceVertices();
			System.out.println("Points d'entrée");
			System.out.println(Arrays.deepToString(sv));
			// On supprime les points d'entree
			System.out.println("Suppression des points d'entrée");
			for (int i = 0; i < sv.length; i++) {
				tempGraph.removeVertex(sv[i]);
			}
			// On recupere les points restants
			System.out.println("Points restants");
			pr = tempGraph.getAllVertices();
			if (pr.length != 0) {
				System.out.println(Arrays.deepToString(pr));
			} else {
				System.out.println("Aucun");
			}
			// graph.checkVertices();
		}
		System.out.println("Points d'entrée \nAucun");
		// Si il existe toujours des sommets, alors le graphe contient au moins un circuit sinon etc...
		if (pr.length == 0) {
			System.out.println("Le graphe contient aucun circuit.");
		} else {
			System.out.println("Le graphe contient au moins un circuit.");
		}
	}
	
	
	// Renvoie un booleen (true si le graphe contient un cycle, sinon false)
	public static boolean isCyclic(Graph graph) {
		boolean ic = false;
		Graph tempGraph = graph;
		// On recupere tous les sommets
		String[] pr = tempGraph.getAllVertices();
		//Tant qu'il y a toujours des points d'entre
		while (tempGraph.getSourceVertices().length != 0) {
			// On recupere les points d'entree
			String[] sv = tempGraph.getSourceVertices();
			// On supprime les points d'entree
			for (int i = 0; i < sv.length; i++) {
				tempGraph.removeVertex(sv[i]);
			}
			// On recupere les points restants
			pr = tempGraph.getAllVertices();
			if (pr.length != 0) {
				System.out.println(Arrays.deepToString(pr));
			} else {
				System.out.println("Aucun");
			}
		}
		// Si il existe toujours des sommets, alors le graphe contient au moins un circuit sinon etc...
		if (pr.length != 0) {
			ic = true;
		}
		return ic;
	}
	
	
	// Supprime tous les doublons dans une liste de type Integer
	public static String[] removeDuplicates(String[] list) {
		return Arrays.stream(list).distinct().toArray(String[]::new);
	}
}
