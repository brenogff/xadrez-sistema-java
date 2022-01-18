package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Rei extends Pe�aXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posi��o posi��o) {
		Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o(0, 0);
		
		//acima
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna());
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//abaixo
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna());
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//esquerda
				p.setValores(posi��o.getLinha(), posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//direita
				p.setValores(posi��o.getLinha(), posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}	
				
		//diagonalNoroeste
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalNordeste
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
			
		return matriz;
	}

}
