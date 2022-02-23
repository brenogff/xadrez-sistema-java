package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		//acima
				p.setValores(posicao.getLinha() - 1, posicao.getColuna());
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() - 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//esquerda
				p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() - 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				//direita
				p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setColuna(p.getColuna() + 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				//abaixo
				p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setLinha(p.getLinha() + 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		return matriz;
	}
}