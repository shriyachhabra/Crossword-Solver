import java.io.*;
import java.util.*;

public class Solution {
public static void main(String[] args) {
	
		Scanner scn = new Scanner(System.in);
		
		char[][] cross = new char[10][10];
		for (int i = 0; i < 10; i++) {
         String s=scn.next();
         cross[i]=s.toCharArray();

		}

		
		String str = scn.next();
		String[] words = str.split(";");
		crossword(cross, words, 0);

	}


	public static void crossword(char[][] matrix, String[] words, int idx) {

		if(idx==words.length) {
			for(int i=0;i<10;i++) {
				for(int j=0;j<10;j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			}
			return;
		}
		
		
		String word = words[idx];

		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix.length; c++) {

				if (matrix[r][c] == '+') {
					continue;
				}
				if (matrix[r][c] == '-' || matrix[r][c] == word.charAt(0)) {

					if (canPlaceHorizontally(matrix, r, c, word)) {
						boolean[] pos = placeHorizontally(matrix, r, c, word);
						crossword(matrix, words, idx + 1);
						unplace(matrix, pos, r, c);

					}
					if (canPlaceVert(matrix, r, c, word)) {
						boolean[] pos = placeVert(matrix, r, c, word);
						crossword(matrix, words, idx + 1);
						unplaceVert(matrix, pos, r, c);

					}
				}

			}
		}

	}

	private static void unplace(char[][] matrix, boolean[] pos, int r, int c) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pos.length; i++) {
			if (pos[i] == false) {
				matrix[r][i + c] = '-';
			}
		}

	}

	private static boolean[] placeHorizontally(char[][] matrix, int r, int c, String word) {
		// TODO Auto-generated method stub
		boolean[] tookHelp = new boolean[word.length()];
		for (int i = 0; i < word.length(); i++) {
			if (matrix[r][i + c] == '-') {
				matrix[r][i + c] = word.charAt(i);
			} else {
				tookHelp[i] = true;
			}
		}

		return tookHelp;
	}

	private static boolean canPlaceHorizontally(char[][] matrix, int r, int c, String word) {
		// TODO Auto-generated method stub
		for (int i = 0; i < word.length(); i++) {
			if (i + c == matrix.length) {
				return false;
			}
			if (matrix[r][c + i] == '+') {
				return false;
			} else if (matrix[r][c + i] != '-' && matrix[r][c + i] != word.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	private static void unplaceVert(char[][] matrix, boolean[] pos, int r, int c) {
		// TODO Auto-generated method stub
		for (int i = 0; i < pos.length; i++) {
			if (pos[i] == false) {
				matrix[r + i][c] = '-';
			}
		}

	}

	private static boolean[] placeVert(char[][] matrix, int r, int c, String word) {
		// TODO Auto-generated method stub
		boolean[] tookHelp = new boolean[word.length()];
		for (int i = 0; i < word.length(); i++) {
			if (matrix[r + i][c] == '-') {
				matrix[r + i][c] = word.charAt(i);
			} else {
				tookHelp[i] = true;
			}
		}

		return tookHelp;
	}

	private static boolean canPlaceVert(char[][] matrix, int r, int c, String word) {
		// TODO Auto-generated method stub
		for (int i = 0; i < word.length(); i++) {
			if (i + r == matrix.length) {
				return false;
			}
			if (matrix[r + i][c] == '+') {
				return false;
			} else if (matrix[r + i][c] != '-' && matrix[r + i][c] != word.charAt(i)) {
				return false;
			}
		}

		return true;
	}
}
