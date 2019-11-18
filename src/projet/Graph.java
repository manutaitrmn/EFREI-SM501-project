package projet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
	
	private ArrayList<String[]> graph;
	
	
	// Le constructeur
	public Graph(String file) {
		ArrayList<String[]> converted = new ArrayList<String[]>();
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] l = line.split(" ");
				String[] ints = new String[l.length];
				for (int i = 0 ; i < l.length ; i++) {
					ints[i] = l[i];
				}
				converted.add(ints);
			}
			br.close();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		graph = converted;
	}
	
	
	
	
	// Lecture du graphe
	public void read() {
		System.out.println("* Lecture du graphe");
		System.out.println(getNumberVertices() + " sommets");
		System.out.println(getNumberArcs() + " arcs");
		for (int i = 0; i < graph.size(); i++) {
			System.out.println(graph.get(i)[0] + " -> " + graph.get(i)[1] + " = " + graph.get(i)[2]);
		}
	}
	
	
	
	
	
	
	// METHODES POUR LA MATRICE D'ADJACENCE
	// Lecture de la matrice d'adjacence
	public void readAdjacencyMatrix() {
		System.out.println("Lecture de la matrice d'adjacence");
		String[][] m = getAdjacencyMatrix();
		for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[row].length; col++) {
                System.out.printf("%6s", m[row][col]);
            }
            System.out.println();
        }
	}
	// Recupere la matrice d'adjacence sous forme de tableau d'entiers a 2 dimensions
	public String[][] getAdjacencyMatrix() {
		int nbv = getNumberVertices();
		String[][] m = new String[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = "0";
            }
        }
		for (int i = 0; i < graph.size(); i++) {
			m[Integer.parseInt(graph.get(i)[0])][Integer.parseInt(graph.get(i)[1])] = "1";
		}
		return m;
	}
	// FIN MATRICE D'ADJACENCE
	
	
	
	
	
	
	// METHODE POUR LA MATRICE DE VALEURS
	// Lecture de la matrice de valeurs
	public void readValuesMatrix() {
		System.out.println("Lecture de la matrice de valeurs");
		String[][] m = getValuesMatrix();
		for (int row = 0; row < m.length; row++) {
            for (int col = 0; col < m[row].length; col++) {
            	if (m[row][col] == null) {
            		System.out.printf("%6s", "*");
            	} else {
            		System.out.printf("%6s", m[row][col]);
            	}
                
            }
            System.out.println();
        }
	}
	// Recupere la matrice de valeurs sous formes de tableau d'entiers a 2 dimensions
	public String[][] getValuesMatrix() {
		int nbv = getNumberVertices();
		String[][] m = new String[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = null;
            }
        }
		for (int i = 0; i < graph.size(); i++) {
			m[Integer.parseInt(graph.get(i)[0])][Integer.parseInt(graph.get(i)[1])] = graph.get(i)[2];
		}
		return m;
	}
	// FIN MATRICE DE VALEURS
	

	
	
	// Recupere les points d'entrée sous forme de liste d'entiers
	public String[] getSourceVertices() {
		String[] lv = getLeftVertices();
		String[] rv = getRightVertices();
		ArrayList<String> sv = new ArrayList<String>();
		for (int i = 0; i < lv.length; i++) {
			if (!Arrays.asList(rv).contains(lv[i]) && !lv[i].contains("*")) {
				sv.add(lv[i]);
			} else if (lv[i].contains("*")) {
				sv.add(rv[i]);
			}
		}
		String[] result = new String[sv.size()];
		for (int i = 0; i < sv.size(); i++) {
			result[i] = sv.get(i);
		}
		return removeDuplicates(result);	
	}
	
	public String[] removeStars(String[] list) {
		ArrayList<String> av = new ArrayList<String>();
        int i;
        for (i = 0; i < list.length; i++) {
        	if (!list[i].contains("*")) {
        		av.add(list[i]);
        	}
        }
        String[] finalResult = new String[av.size()];
        for (i = 0; i < av.size(); i++) {
        	finalResult[i] = av.get(i);
        }
        return finalResult;
	}
	
	
	//METHODES POUR LES SOMMETS
	// Recupere tous les sommets possedant des fleches sortantes dans une liste d'entiers (les sommets de gauche dans le txt)
	public String[] getLeftVertices() {
		String[] lv = new String[getNumberArcs()];
		for (int i = 0; i < getNumberArcs(); i++) {
			lv[i] = graph.get(i)[0];
		}
		return lv;
	}
	// Recupere tous les sommets possedant des fleches entrantes dans une liste d'entiers (les sommets de droite dans le txt)
	public String[] getRightVertices() {
		String[] lr = new String[getNumberArcs()];
		for (int i = 0; i < getNumberArcs(); i++) {
			lr[i] = graph.get(i)[1];
		}
		return lr;
	}
	// Recupere tous les sommets du graphe dans une liste d'entiers
	public String[] getAllVertices() {
        String[] array1 = getLeftVertices();
        String[] array2 = getRightVertices();
        int aLen = array1.length;
        int bLen = array2.length;
        String[] result = new String[aLen + bLen];
        System.arraycopy(array1, 0, result, 0, aLen);
        System.arraycopy(array2, 0, result, aLen, bLen);
        result = removeDuplicates(result);
        return removeStars(result);
        
    }
	// Recupere le nombre de sommets total du graphe
	public int getNumberVertices() {
		return getAllVertices().length;
	}
	// Supprime un sommet
	public void removeVertex(String v) {
		ArrayList<String[]> toBeRemoved = new ArrayList<String[]>();
		String[] lv = getLeftVertices();
		String[] rv = getRightVertices();
		for (int i = 0; i < graph.size(); i++) {
			if (rv[i].equals(v)) {
				toBeRemoved.add(graph.get(i));
			} else if (lv[i].equals(v)) {
				graph.set(i, new String[] {"*",graph.get(i)[1],"*"});
			}
		}
		graph.removeAll(toBeRemoved);
		checkVertices();
	}
	public void checkVertices() {
		ArrayList<String[]> toBeRemoved = new ArrayList<String[]>();
		String[] lv = getLeftVertices();
		String[] rv = getRightVertices();
		for (int i = 0; i < graph.size(); i++) {
			if (lv[i].equals("*") && (Arrays.asList(lv).contains(rv[i]) || Arrays.asList(getVerticesExceptOneByIndex(rv, i)).contains(rv[i]))) {
				toBeRemoved.add(graph.get(i));
			}
		}
		graph.removeAll(toBeRemoved);
	}
	// Filtre une liste de String
	public String[] getVerticesExceptOneByIndex(String[] List, int index) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = 0; i < List.length; i++) {
			if (i != index) {
				newList.add(List[i]);
			}
		}
		String[] result = new String[newList.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = newList.get(i);
		}
		return result;
	}
	// FIN METHODES POUR LES SOMMETS

	
	
	
	// RECUPERER LE NOMBRE D'ARCS
	public int getNumberArcs() {
		return graph.size();
	}
	
	// RECUPERER LE GRAPHE SOUS FORME DE TABLEAU A 2D
	public ArrayList<String[]> getValues() {
		return graph;
	}
	
	
	
	
	
	//AUTRES METHODES UTILES
	// Supprime tous les doublons dans une liste de type Integer
	public static String[] removeDuplicates(String[] list) {
		return Arrays.stream(list).distinct().toArray(String[]::new);
	}
	
	
}
