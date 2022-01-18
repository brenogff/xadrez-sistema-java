package tabuleiro;

public abstract class Peça {
	
	protected Posição posição;
	private Tabuleiro tabuleiro;
	
	
	public Peça(Tabuleiro tabuleiro) {
		super();
		this.tabuleiro = tabuleiro;
		posição = null;
	}

	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentosPossiveis(Posição posição) {
		return movimentosPossiveis()[posição.getLinha()][posição.getColuna()];
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
