package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);

	}
	
	@Override
	public String toString() {
		return "D";
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
				
		//diagonalNoroeste
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() - 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//diagonalNordeste
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() - 1, p.getColuna() + 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() + 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
				while (getTabuleiro().posicaoExistente(p) && !getTabuleiro().temPeca(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() + 1, p.getColuna() - 1);
				}
				if (getTabuleiro().posicaoExistente(p) && temPecaAdversaria(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		return matriz;
	}
}