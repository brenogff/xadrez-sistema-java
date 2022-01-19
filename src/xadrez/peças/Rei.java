package xadrez.peças;

import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posição posição) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posição);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testePossivelRoque(Posição posição) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posição);
		return p == null || p instanceof Torre && p.getCor() == getCor() && p.getContarMovimento() == 0;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posição p = new Posição(0, 0);
		
		//acima
				p.setValores(posição.getLinha() - 1, posição.getColuna());
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
		
		//abaixo
				p.setValores(posição.getLinha() + 1, posição.getColuna());
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//esquerda
				p.setValores(posição.getLinha(), posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//direita
				p.setValores(posição.getLinha(), posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}	
				
		//diagonalNoroeste
				p.setValores(posição.getLinha() - 1, posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalNordeste
				p.setValores(posição.getLinha() - 1, posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudoeste
				p.setValores(posição.getLinha() + 1, posição.getColuna() + 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//diagonalSudeste
				p.setValores(posição.getLinha() + 1, posição.getColuna() - 1);
				if (getTabuleiro().posiçãoExistente(p) && podeMover(p)) {
					matriz[p.getLinha()][p.getColuna()] = true;
				}
				
		//movimentoEspecialRoque
				
				if (getContarMovimento() == 0 && !partidaXadrez.getCheck()) {
					//testando posição para Roque pequeno
					Posição posiçãoT1 = new Posição(posição.getLinha(), posição.getColuna() + 3);
					if (testePossivelRoque(posiçãoT1)) {
						Posição p1 = new Posição(posição.getLinha(), posição.getColuna() + 1);
						Posição p2 = new Posição(posição.getLinha(), posição.getColuna() + 2);
						if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null) {
							matriz[posição.getLinha()][posição.getColuna() + 2] = true;
						}
					}
					
					//testando posição para Roque grande
					Posição posiçãoT2 = new Posição(posição.getLinha(), posição.getColuna() - 4);
					if (testePossivelRoque(posiçãoT1)) {
						Posição p1 = new Posição(posição.getLinha(), posição.getColuna() - 1);
						Posição p2 = new Posição(posição.getLinha(), posição.getColuna() - 2);
						Posição p3 = new Posição(posição.getLinha(), posição.getColuna() - 3);
						if (getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null && getTabuleiro().peça(p3) == null) {
							matriz[posição.getLinha()][posição.getColuna() - 2] = true;
						}
					}
				}
			
		return matriz;
	}

}
