package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peça[][] peças;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar tabuleiro: é necessário que tenha pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		peças = new Peça[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	
	public Peça peça(int linhas, int colunas) {
		if(!posiçãoExistente(linhas, colunas)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		
		return peças[linhas][colunas];
	}

	public Peça peça(Posição posição) {
		if (!posiçãoExistente(posição)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		return peças[posição.getLinha()][posição.getColuna()];
	}
	
	public void colocarPeça(Peça peça, Posição posição) {
		if (temPeça(posição)) {
			throw new TabuleiroException("Já existe uma peça na posição: " + posição);
		}
		peças[posição.getLinha()][posição.getColuna()] = peça;
		peça.posição = posição;
	}
	
	public Peça removerPeça(Posição posição) {
		if(!posiçãoExistente(posição)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		if (peça(posição) == null) {
			return null;
		}
		
		Peça auxiliar = peça(posição);
		auxiliar.posição = null;
		peças[posição.getLinha()][posição.getColuna()] = null;
		return auxiliar;
	}
	
	private boolean posiçãoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posiçãoExistente(Posição posição) {
		return posiçãoExistente(posição.getLinha(),posição.getColuna()); 
	}
	
	public boolean temPeça(Posição posição) {
		if (!posiçãoExistente(posição)) {
			throw new TabuleiroException("Posição não existe no tabuleiro");
		} 
		return peça(posição) != null;
	}
}
