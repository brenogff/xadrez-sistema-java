package tabuleiro;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar tabuleiro: é necessário que tenha pelo menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	
	public Peca peca(int linhas, int colunas) {
		if(!posicaoExistente(linhas, colunas)) {
			throw new TabuleiroException("Posicao não existe no tabuleiro");
		}
		
		return pecas[linhas][colunas];
	}

	public Peca peca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posicao não existe no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if (temPeca(posicao)) {
			throw new TabuleiroException("Já existe uma peca na posicao: " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posicao não existe no tabuleiro");
		}
		if (peca(posicao) == null) {
			return null;
		}
		
		Peca auxiliar = peca(posicao);
		auxiliar.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return auxiliar;
	}
	
	private boolean posicaoExistente(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(),posicao.getColuna()); 
	}
	
	public boolean temPeca(Posicao posicao) {
		if (!posicaoExistente(posicao)) {
			throw new TabuleiroException("Posicao não existe no tabuleiro");
		} 
		return peca(posicao) != null;
	}
}
