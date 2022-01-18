package tabuleiro;

public abstract class Pe�a {
	
	protected Posi��o posi��o;
	private Tabuleiro tabuleiro;
	
	
	public Pe�a(Tabuleiro tabuleiro) {
		super();
		this.tabuleiro = tabuleiro;
		posi��o = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentosPossiveis(Posi��o posi��o) {
		return movimentosPossiveis()[posi��o.getLinha()][posi��o.getColuna()];
	}
	
	public boolean temAlgumMovimentoPossivel() {
		boolean [][] matriz = movimentosPossiveis();
		for (int i=0; i<matriz.length; i++) {
			for (int j=0; j<matriz.length; j++) {
				if (matriz[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

}
