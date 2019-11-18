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
		this.data = converted;
	}
	
	
	//@Override
	//public Graph clone() throws CloneNotSupportedException {
	//	Graph newGraph = (Graph) super.clone();
	//	newGraph.setData(null);
	//	newGraph.data = this.data;
	//	return newGraph;
	//}
	
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
			if (!data.get(i)[0].contains("*")) {
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
			if (!data.get(i)[0].contains("*")) {
				m[Integer.parseInt(data.get(i)[0])][Integer.parseInt(data.get(i)[1])] = data.get(i)[2];
			}
		}
		return m;
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
	
	
	//METHODES POUR LES SOMMETS
	
	// Recupere tous les sommets possedant des fleches sortantes dans une liste de string (les sommets de gauche dans le txt)
	public String[] getLeftVertices() {
		String[] lv = new String[getNumberArcs()];
		for (int i = 0; i < getNumberArcs(); i++) {
			lv[i] = data.get(i)[0];
		}
		return lv;
	}
	
	// Recupere tous les sommets possedant des fleches entrantes dans une liste de string (les sommets de droite dans le txt)
	public String[] getRightVertices() {
		String[] lr = new String[getNumberArcs()];
		for (int i = 0; i < getNumberArcs(); i++) {
			lr[i] = data.get(i)[1];
		}
		return lr;
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
	
	// Recupere le nombre de sommets total du graphe
	public int getNumberVertices() {
		return getAllVertices().length;
	}
	
	// Supprime un sommet
	public void removeVertex(String v) {
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
			if (lv[i].equals("*") && (Arrays.asList(lv).contains(rv[i]) || Arrays.asList(getValueById(rv, i)).contains(rv[i]))) {
				toBeRemoved.add(data.get(i));
			}
		}
		data.removeAll(toBeRemoved);
	}
	
	// Retire une valeur d'une liste de string en donnant son index
	public String[] getValueById(String[] List, int index) {
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
		return data.size();
	}
	
	// RECUPERER LE GRAPHE SOUS FORME DE TABLEAU A 2D
	public ArrayList<String[]> getData() {
		return data;
	}
	
	public void setData(ArrayList<String[]> data) {
		this.data = data;
	}
	
	
	
	
	
	//AUTRES METHODES UTILES
	// Supprime tous les doublons dans une liste de type Integer
	public static String[] removeDuplicates(String[] list) {
		return Arrays.stream(list).distinct().toArray(String[]::new);
	}
	
	// Retire les caracteres "*" d'une liste de string
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
	
	
}
