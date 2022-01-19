package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peça;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
import xadrez.peças.Bispo;
import xadrez.peças.Cavalo;
import xadrez.peças.Peão;
import xadrez.peças.Rainha;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peça> PeçasNoTabuleiro = new ArrayList<>();
	private List<Peça> CapturaPeças = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual =  Cor.BRANCO;
		inicioJogo();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PeçaXadrez[][] getPeças() {
		PeçaXadrez[][] matriz = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()]; 
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PeçaXadrez) tabuleiro.peça(i, j);
			}
		}
		
		return matriz;
	}
	
	public boolean[][] movimentosPossiveis(PosiçãoXadrez inicioPosição) {
		Posição posição = inicioPosição.toPosição();
		validarInicioPosição(posição);
		return tabuleiro.peça(posição).movimentosPossiveis();
	}
	
	public PeçaXadrez executarMovimentoPeça(PosiçãoXadrez inicioPosição, PosiçãoXadrez destinoPosição) {
		Posição inicio = inicioPosição.toPosição();
		Posição destino = destinoPosição.toPosição();
		validarInicioPosição(inicio);
		validarDestinoPosição(inicio, destino);
		Peça capturaPeça = façaMovimento (inicio, destino);
		
		if (testeCheck(jogadorAtual)) {
			desfaçaMovimento(inicio, destino, capturaPeça);
			throw new XadrezException("Você não pode se colocar em xeque");
		}
		
		check = (testeCheck(adversario(jogadorAtual))) ? true : false;
		
		if (testeCheckMate(adversario(jogadorAtual))) {
			checkMate = true;
		}
		
		else {
			proximoTurno();
		}
		
		return (PeçaXadrez) capturaPeça;

	}
		
	private Peça façaMovimento(Posição inicio, Posição destino) {
		PeçaXadrez p = (PeçaXadrez)tabuleiro.removerPeça(inicio);
		p.aumentarContarMovimento();
		Peça capturaPeça = tabuleiro.removerPeça(destino);
		tabuleiro.colocarPeça(p, destino);
		
		if (capturaPeça != null) {
			PeçasNoTabuleiro.remove(capturaPeça);
			CapturaPeças.add(capturaPeça);
		}
			
		return capturaPeça;
	}
	
	private void desfaçaMovimento(Posição inicio, Posição destino, Peça capturaPeça) {
		PeçaXadrez p = (PeçaXadrez)tabuleiro.removerPeça(destino);
		p.diminuirContarMovimento();
		tabuleiro.colocarPeça(p, inicio);
		
		if (capturaPeça != null) {
			tabuleiro.colocarPeça(capturaPeça, destino);
			CapturaPeças.remove(capturaPeça);
			PeçasNoTabuleiro.add(capturaPeça);
		}
	}
	
	private void validarInicioPosição(Posição posição) {
		if (!tabuleiro.temPeça(posição)) {
			throw new XadrezException("Não há peça na posição inicial");
		}
		
		if (jogadorAtual != ((PeçaXadrez)tabuleiro.peça(posição)).getCor()) {
			throw new XadrezException("A peça escolhida não é sua");
		}
		
		if (!tabuleiro.peça(posição).temAlgumMovimentoPossivel() ) {
			throw new XadrezException("Não existe movimentos possíveis para a peça escolhida");
			
		}
	}
	
	private void validarDestinoPosição(Posição inicio, Posição destino) {
		if (!tabuleiro.peça(inicio).movimentosPossiveis(destino)) {
			throw new XadrezException("A peça não pode se mover para a posição de destino");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor adversario(Cor cor) {
		return (cor == Cor.BRANCO) ?Cor.PRETO : Cor.BRANCO;
	}
	
	private PeçaXadrez Rei(Cor cor) {
		List<Peça> list = PeçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p : list) {
			if (p instanceof Rei) {
				return (PeçaXadrez)p;
			}
		}
		
		throw new IllegalStateException("Não existe Rei " + cor + " no tabuleiro");
	}
	
	private boolean testeCheck(Cor cor) {
		Posição posiçãoRei = Rei(cor).getPosiçãoXadrez().toPosição();
		List<Peça>peçasAdversarias = PeçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == adversario(cor)).collect(Collectors.toList());
		for (Peça p : peçasAdversarias) {
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posiçãoRei.getLinha()][posiçãoRei.getColuna()]) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean testeCheckMate(Cor cor) {
		if (!testeCheck(cor)) {
			return false;
		}
		
		List<Peça> list = PeçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peça p : list) {
			boolean[][] matriz = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posição inicio = ((PeçaXadrez)p).getPosiçãoXadrez().toPosição();
						Posição destino =  new Posição(i, j);
						Peça capturaPeça = façaMovimento(inicio, destino);
						boolean testeCheck = testeCheck(cor);
						desfaçaMovimento(inicio, destino, capturaPeça);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private void colocarNovaPeça(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.colocarPeça(peça, new PosiçãoXadrez(coluna, linha).toPosição());
		PeçasNoTabuleiro.add(peça);
	}
	
	
	private void inicioJogo() {
		
		
		colocarNovaPeça('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('a', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('b', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('c', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('d', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('e', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('f', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('g', 2, new Peão(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('h', 2, new Peão(tabuleiro, Cor.BRANCO));
		
		colocarNovaPeça('a', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('h', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeça('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		colocarNovaPeça('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeça('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		colocarNovaPeça('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		colocarNovaPeça('e', 8, new Rei(tabuleiro, Cor.PRETO));
		colocarNovaPeça('a', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('b', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('c', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('d', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('e', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('f', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('g', 7, new Peão(tabuleiro, Cor.PRETO));
		colocarNovaPeça('h', 7, new Peão(tabuleiro, Cor.PRETO));
		
	}
}
