package xadrez;

import tabuleiro.Posição;

public class PosiçãoXadrez {
	
	private char coluna;
	private int linha;
	
	
	public PosiçãoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadrezException("Erro ao instaciar PosiçãoXadrez. Valores válidos são de a1 até h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}


	public char getColuna() {
		return coluna;
	}


	public int getLinha() {
		return linha;
	}
	
	protected Posição toPosição() {
		return new Posição(8 - linha, coluna - 'a');
	}
	
	protected static PosiçãoXadrez dePosição(Posição posição) {
		return new PosiçãoXadrez((char) ('a' + posição.getColuna()), 8 - posição.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha; // usa o "" como macete para concatenação automaticamente
	}
}
