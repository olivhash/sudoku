
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