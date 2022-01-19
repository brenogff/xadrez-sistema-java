package xadrez.pe�as;

import tabuleiro.Posi��o;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Cavalo extends Pe�aXadrez {

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	private boolean podeMover(Posi��o posi��o) {
		Pe�aXadrez p = (Pe�aXadrez)getTabuleiro().pe�a(posi��o);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posi��o p = new Posi��o(0, 0);
		
		
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() - 2);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
				p.setValores(posi��o.getLinha() - 2, posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValores(posi��o.getLinha() - 2, posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
			
				p.setValores(posi��o.getLinha() - 1, posi��o.getColuna() + 2);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}	
				
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() + 2);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValores(posi��o.getLinha() + 2, posi��o.getColuna() + 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValores(posi��o.getLinha() + 2, posi��o.getColuna() - 1);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
				p.setValores(posi��o.getLinha() + 1, posi��o.getColuna() - 2);
				if (getTabuleiro().posi��oExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
			
		return matriz;
	}

}