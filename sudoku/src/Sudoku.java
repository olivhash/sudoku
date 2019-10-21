
class Case {
	private int ligne;
	private int colonne;

	public int getLigne() {
		return ligne;
	}

	public int getColonne() {
		return colonne;
	}

	Case(int l, int c) {
		this.ligne = l;
		this.colonne = c;
	}

	Case() {
		this(0, 0);
	}

	/* Suivante(8,8) est (9,0) */
	Case suivante() {
		Case c = new Case();
		if (this.colonne == 8) {
			c.colonne = 0;
			c.ligne = this.ligne + 1;
		} else {
			c.ligne = this.ligne;
			c.colonne = this.colonne + 1;
		}
		return c;
	}

}

class Grille {
	private int[][] grille;
	private long appels;

	Grille(int[][] grille) {
		this.grille = grille;
		appels = 0;
	}

	public void afficheGrille() {
		int i, j;
		for (i = 0; i < 9; i++) {
			if (i % 3 == 0)
				System.out.print("+---+---+---+\n");
			for (j = 0; j < 9; j++) {
				if (j % 3 == 0)
					System.out.print("|");
				System.out.print(grille[i][j]);
			}
			System.out.print("|\n");
		}
		System.out.print("+---+---+---+\n");
	}

	public int inserableLigne(Case c, int n) {
		int j;
		for (j = 0; j < 9; j++)
			if (grille[c.getLigne()][j] == n)
				return 0;
		return 1;
	}

	public int inserableColonne(Case c, int n) {
		int i;
		for (i = 0; i < 9; i++)
			if (grille[i][c.getColonne()] == n)
				return 0;
		return 1;
	}

	public int inserableCarre(Case c, int n) {
		/* le coin haut, gauche du sous-carre */
		int haut = c.getLigne() - c.getLigne() % 3;
		int gauche = c.getColonne() - c.getColonne() % 3;
		int hh, gg;
		/* parcours du sous-carre */
		for (hh = 0; hh < 3; hh++)
			for (gg = 0; gg < 3; gg++)
				if (grille[haut + hh][gauche + gg] == n)
					return 0;
		return 1;
	}

	/*
	 * on remplit recursivement a partir de la case cCour on retourne true si ça
	 * s'est bien passé, false sinon
	 */

	public boolean resolution(Case cCour) {
		appels++;
		if (cCour.getLigne() == 9) {// test d'arret: tout est rempli
			return true;
		}
		if (grille[cCour.getLigne()][cCour.getColonne()] == 0) { // case vide
			int n;
			for (n = 1; n < 10; n++) { // on essaie tout
				if ((inserableLigne(cCour, n) == 1) 
						&& (inserableColonne(cCour, n) == 1)
						&& (inserableCarre(cCour, n) == 1)) {
					grille[cCour.getLigne()][cCour.getColonne()] = n;
					// afficheGrille();
					if (resolution(cCour.suivante())) // gagné !
						return true;
				}
			}
			/* si on sort de la boucle: aucun n n'a convenu */
			grille[cCour.getLigne()][cCour.getColonne()] = 0;
			return false;
		} else { // on saute les case déjà remplies (elles ont été données au début du problème)
			return (resolution(cCour.suivante()));
		}
	}

	public void jouer() {
		afficheGrille();
		Case init = new Case(0, 0);
		appels = 0;
		if (resolution(init)) {
			System.out.print("Voici une solution possible:\n");
			afficheGrille();
		} else {
			System.out.print("Grille insoluble !\n");
			afficheGrille();
		}
		System.out.println("Nombre d'appels récursifs: " + appels);
	}

}

public class Sudoku {

	public static void main(String[] args) {
		int[][] grille = { 
				{ 1, 0, 0, 4, 0, 0, 3, 7, 0 }, 
				{ 2, 0, 0, 6, 0, 0, 0, 0, 9 }, 
				{ 0, 0, 0, 0, 8, 0, 0, 0, 0 },
				{ 0, 4, 0, 0, 9, 0, 0, 3, 0 },
				{ 0, 2, 1, 0, 0, 0, 5, 8, 0 },
				{ 0, 6, 0, 0, 5, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 4, 0, 0, 0, 0 },
				{ 6, 0, 0, 0, 0, 7, 0, 0, 5 }, 
				{ 0, 3, 9, 0, 0, 6, 0, 0, 8 } };
		
		Grille s = new Grille(grille);
		s.jouer();
	}
}
