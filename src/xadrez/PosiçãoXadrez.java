package xadrez;

import tabuleiro.Posi��o;

public class Posi��oXadrez {
	
	private char coluna;
	private int linha;
	
	
	public Posi��oXadrez(char coluna, int linha) {
		if(coluna < 'A' || coluna > 'H' || linha < 1 || linha > 8) {
			throw new XadrezException("Erro ao instaciar Posi��oXadrez. Valores v�lidos s�o de A1 at� H8");
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
	
	protected Posi��o toPosi��o() {
		return new Posi��o(8 - linha, coluna - 'A');
	}
	
	protected static Posi��oXadrez dePosi��o(Posi��o posi��o) {
		return new Posi��oXadrez((char) ('A' - posi��o.getColuna()), 8 - posi��o.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha; // usa o "" como macete para concatena��o automaticamente
	}
}
