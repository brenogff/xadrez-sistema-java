package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Pe�a[][] pe�as;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar tabuleiro: � necess�rio que tenha pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pe�as = new Pe�a[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	
	public Pe�a pe�a(int linhas, int colunas) {
		if(!posi��oExistente(linhas, colunas)) {
			throw new TabuleiroException("Posi��o n�o existe no tabuleiro");
		}
		
		return pe�as[linhas][colunas];
	}

	public Pe�a pe�a(Posi��o posi��o) {
		if (!posi��oExistente(posi��o)) {
			throw new TabuleiroException("Posi��o n�o existe no tabuleiro");
		}
		return pe�as[posi��o.getLinha()][posi��o.getColuna()];
	}
	
	public void colocarPe�a(Pe�a pe�a, Posi��o posi��o) {
		if (temPe�a(posi��o)) {
			throw new TabuleiroException("J� existe uma pe�a na posi��o: " + posi��o);
		}
		pe�as[posi��o.getLinha()][posi��o.getColuna()] = pe�a;
		pe�a.posi��o = posi��o;
	}
	
	public Pe�a removerPe�a(Posi��o posi��o) {
		if(!posi��oExistente(posi��o)) {
			throw new TabuleiroException("Posi��o n�o existe no tabuleiro");
		}
		if (pe�a(posi��o) == null) {
			return null;
		}
		
		Pe�a auxiliar = pe�a(posi��o);
		auxiliar.posi��o = null;
		pe�as[posi��o.getLinha()][posi��o.getColuna()] = null;
		return auxiliar;
	}
	
	private boolean posi��oExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posi��oExistente(Posi��o posi��o) {
		return posi��oExistente(posi��o.getLinha(),posi��o.getColuna()); 
	}
	
	public boolean temPe�a(Posi��o posi��o) {
		if (!posi��oExistente(posi��o)) {
			throw new TabuleiroException("Posi��o n�o existe no tabuleiro");
		} 
		return pe�a(posi��o) != null;
	}
}
