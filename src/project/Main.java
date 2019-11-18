package project;

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
				// Recupere le fichier txt du graphe à étudier
				String file = new File("").getAbsolutePath() + "/src/graphs/" + nb + ".txt";
				// On instancie le graphe
				Graph graph = new Graph(file);

				// Lecture du graphe
				readGraph(graph);
				
				
				// Lecture de la matrice d'adjacence
				readAdjacencyMatrix(graph);

				// Lecture de la matrice de valeurs
				readValuesMatrix(graph);
				
				// Detection de circuit				
				detectCycle(graph);

				// Calcul de rangs
				ranksCalc(graph);
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
		System.out.println("\n* Lecture du graphe \n");
		ArrayList<String[]> graphData = graph.getData();
		System.out.println(graph.getNumberVertices() + " sommets");
		System.out.println(graph.getNumberArcs() + " arcs");
		for (int i = 0; i < graphData.size(); i++) {
			System.out.println(graphData.get(i)[0] + " -> " + graphData.get(i)[1] + " = " + graphData.get(i)[2]);
		}
	}
	
	
	// Lecture de la matrice d'adjacence
	public static void readAdjacencyMatrix(Graph graph) {
		System.out.println("\n* Lecture de la matrice d'adjacence \n");
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
		System.out.println("\n* Lecture de la matrice de valeurs \n");
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
		System.out.println("\n* Detection de circuit \n");
		// On recupere tous les sommets
		Graph tempGraph = graph.deepClone();
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
		Graph tempGraph = graph.deepClone();
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
		}
		// Si il existe toujours des sommets, alors le graphe contient au moins un circuit sinon etc...
		if (pr.length != 0) {
			ic = true;
		}
		return ic;
	}
	
	
	// Calcule les rangs de chaque sommet du graphe.
	public static void ranksCalc(Graph graph) {
		System.out.println("\n* Calcul des rangs par élimination des points d'entrée \n");
		if (!isCyclic(graph.deepClone())) {
			ArrayList<String[]> ranks = graph.deepClone().getAllVerticesRanks();
			for (int i = 0; i < ranks.size(); i++) {
				System.out.println("Rang courant = " + i);
				System.out.println("Points d'entrée:");
				System.out.println(Arrays.toString(ranks.get(i)));
			}
			System.out.println("Graphe vide");
			System.out.println("Rangs calculés");
			for (int i = 0; i < graph.getNumberVertices()+1; i++) {
				if (i == 0) {
					System.out.printf("%6s", "Sommet");
				} else {
					System.out.printf("%6s", i-1);
				}
			}
			System.out.println();
			for (int i = 0; i < graph.getNumberVertices()+1; i++) {
				if (i == 0) {
					System.out.printf("%6s", "Rang");
				} else {
					System.out.printf("%6s", graph.deepClone().getVertexRankByName(Integer.toString(i-1)));
				}
			}
		} else {
			System.out.println("On ne peut pas étudier les rangs des sommets car le graphe contient au moins un circuit.");
		}
	}
	
	
	// Supprime tous les doublons dans une liste de type Integer
	public static String[] removeDuplicates(String[] list) {
		return Arrays.stream(list).distinct().toArray(String[]::new);
	}
}
