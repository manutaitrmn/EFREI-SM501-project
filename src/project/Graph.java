package project;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph implements Cloneable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String[]> data;

	
	//CONSTRUCTEUR
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
		this.data = converted;
	}
	//FIN CONTRUCTEUR
	
	

	//METHODES POUR LES MATRICES
	// Recupere la MATRICE D'ADJACENCE sous forme de tableau d'entiers a 2 dimensions
	public String[][] getAdjacencyMatrix() {
		int nbv = getNumberVertices();
		String[][] m = new String[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = "0";
            }
        }
		for (int i = 0; i < data.size(); i++) {
			if (!data.get(i)[0].equals("*")) {
				m[Integer.parseInt(data.get(i)[0])][Integer.parseInt(data.get(i)[1])] = "1";
			}
		}
		return m;
	}
	// Recupere la MATRICE DE VALEURS sous formes de tableau d'entiers a 2 dimensions
	public String[][] getValuesMatrix() {
		int nbv = getNumberVertices();
		String[][] m = new String[nbv][nbv];
		for (int row = 0; row < nbv; row++) {
            for (int col = 0; col < nbv; col++) {
                m[row][col] = null;
            }
        }
		for (int i = 0; i < data.size(); i++) {
			if (!data.get(i)[0].equals("*")) {
				m[Integer.parseInt(data.get(i)[0])][Integer.parseInt(data.get(i)[1])] = data.get(i)[2];
			}
		}
		return m;
	}
	//FIN METHODES POUR LES MATRICES

	
	
	
	//METHODES POUR LES SOMMETS
	// Recupere le nombre de sommets total du graphe
	public int getNumberVertices() {
		return getAllVertices().length;
	}
	// Recupere tous les sommets du graphe dans une liste de string
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
	// Recupere tous les sommets possedant des fleches sortantes dans une liste de string (les sommets de gauche dans le txt)
	public String[] getLeftVertices() {
		ArrayList<String> lv = new ArrayList<String>();
		int i;
		for ( i = 0; i < data.size(); i++) {
			lv.add(data.get(i)[0]);
		}
		String[] flv = new String[lv.size()];
		for (i = 0; i < lv.size(); i++) {
			flv[i] = lv.get(i);
		}
		return flv;
	}
	// Recupere tous les sommets possedant des fleches entrantes dans une liste de string (les sommets de droite dans le txt)
	public String[] getRightVertices() {
		ArrayList<String> rv = new ArrayList<String>();
		int i;
		for ( i = 0; i < data.size(); i++) {
			rv.add(data.get(i)[1]);
		}
		String[] frv = new String[rv.size()];
		for (i = 0; i < rv.size(); i++) {
			frv[i] = rv.get(i);
		}
		return frv;
	}
	// Recupere les points d'entrée sous forme de liste de string
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
	// Recupere les points de sortie sous forme de liste de string
	public String[] getSinkVertices() {
		String[] lv = getLeftVertices();
		String[] rv = getRightVertices();
		ArrayList<String> sv = new ArrayList<String>();
		for (int i = 0; i < rv.length; i++) {
			if (!Arrays.asList(lv).contains(rv[i])) {
				sv.add(rv[i]);
			}
		}
		String[] result = new String[sv.size()];
		for (int i = 0; i < sv.size(); i++) {
			result[i] = sv.get(i);
		}
		return removeDuplicates(result);	
	}
	// Supprime un sommet
	public void removeVertexByName(String v) {
		ArrayList<String[]> toBeRemoved = new ArrayList<String[]>();
		String[] lv = getLeftVertices();
		String[] rv = getRightVertices();
		for (int i = 0; i < data.size(); i++) {
			if (rv[i].equals(v)) {
				toBeRemoved.add(data.get(i));
			} else if (lv[i].equals(v)) {
				data.set(i, new String[] {"*",data.get(i)[1],"*"});
			}
		}
		data.removeAll(toBeRemoved);
		checkVertices();
	}
	// Vérifie les liaisons inutiles et les supprime
	public void checkVertices() {
		ArrayList<String[]> toBeRemoved = new ArrayList<String[]>();
		String[] lv = getLeftVertices();
		String[] rv = getRightVertices();
		for (int i = 0; i < data.size(); i++) {
			if (lv[i].equals("*") && (Arrays.asList(lv).contains(rv[i]) || Arrays.asList(removeValueById(rv, i)).contains(rv[i]))) {
				toBeRemoved.add(data.get(i));
			}
		}
		data.removeAll(toBeRemoved);
	}
	// Recupere les sommets classés par rangs dans un tableau 2d
	public ArrayList<String[]> getAllVerticesRanks() {
		// On recupere tous les sommets
		ArrayList<String[]> ranks = new ArrayList<String[]>();
		//Tant qu'il y a toujours des points d'entre
		while (getSourceVertices().length != 0) {
			// On recupere les points d'entree
			String[] sv = getSourceVertices();
			ranks.add(sv);
			// On supprime les points d'entree
			for (int i = 0; i < sv.length; i++) {
				removeVertexByName(sv[i]);
			}
		}
		return ranks;
	}
	// Recupere le rang d'un sommet en donnant le nom du sommet
	public String getVertexRankByName(String vertexName) {
		ArrayList<String[]> ranks = getAllVerticesRanks();
		String rank = null;
		int i = 0;
		while (rank == null && i < ranks.size()) {
			for (int j = 0; j < ranks.get(i).length; j++) {
				if (ranks.get(i)[j].equals(vertexName)) {
					rank = Integer.toString(i);
				}
			}
			i++;
		}
		return rank;
	}
	// FIN METHODES POUR LES SOMMETS

	
	
	
	//METHODES POUR LES ARCS
	// Recupere le nombre d'arcs
	public int getNumberArcs() {
		String[] lv = getLeftVertices();
		int count = 0;
		for (int i = 0; i < lv.length; i++) {
			if (!lv[i].equals("*")) {
				count++;
			}
		}
		return count;
	}
	public Integer[] getAllArrowsValues() {
		ArrayList<Integer> arrows = new ArrayList<Integer>();
		int i;
		for (i = 0; i < data.size(); i++) {
			if (!data.get(i)[2].equals("*")) {
				arrows.add(Integer.parseInt(data.get(i)[2]));
			}
		}
		Integer[] farrows = new Integer[arrows.size()];
		for (i = 0; i < arrows.size(); i++) {
			farrows[i] = arrows.get(i);
		}
		return farrows;
	}
	// Recupere les valeurs des fleches sortantes d'un sommet dans une liste d'entiers
	public Integer[] getOutgoingArrowsValuesOf(String vertex) {
		ArrayList<Integer> arrows = new ArrayList<Integer>();
		int i;
		for (i =0; i < data.size(); i++) {
			if (data.get(i)[0].equals(vertex)) {
				arrows.add(Integer.parseInt(data.get(i)[2]));
			}
		}
		Integer[] farrows = new Integer[arrows.size()];
		for (i = 0; i < arrows.size(); i++) {
			farrows[i] = arrows.get(i);
		}
		return farrows;
	}
	//FIN METHODES POUR LES ARCS
	
	

	//GETTERS & SETTERS
	// Recupere les donnees du graphe sous forme d'un tableau 2D
	public ArrayList<String[]> getData() {
		return data;
	}
	// Set la valeur de la variable privee data ***
	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}
	
	
	
	
	
	//AUTRES METHODES UTILES
	// Supprime tous les doublons dans une liste de type Integer
	private static String[] removeDuplicates(String[] list) {
		return Arrays.stream(list).distinct().toArray(String[]::new);
	}
	// Retire les caracteres "*" d'une liste de string
	private String[] removeStars(String[] list) {
		ArrayList<String> av = new ArrayList<String>();
        int i;
        for (i = 0; i < list.length; i++) {
        	if (!list[i].equals("*")) {
        		av.add(list[i]);
        	}
        }
        String[] finalResult = new String[av.size()];
        for (i = 0; i < av.size(); i++) {
        	finalResult[i] = av.get(i);
        }
        return finalResult;
	}
	// Retire une valeur d'une liste de string en donnant son index
	private String[] removeValueById(String[] List, int index) {
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
	// Cloner un objet graph
	public Graph deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Graph) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
