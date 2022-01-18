package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peça;
import tabuleiro.Posição;
import tabuleiro.Tabuleiro;
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
		Peça p = tabuleiro.removerPeça(inicio);
		Peça capturaPeça = tabuleiro.removerPeça(destino);
		tabuleiro.colocarPeça(p, destino);
		
		if (capturaPeça != null) {
			PeçasNoTabuleiro.remove(capturaPeça);
			CapturaPeças.add(capturaPeça);
		}
			
		return capturaPeça;
	}
	
	private void desfaçaMovimento(Posição inicio, Posição destino, Peça capturaPeça) {
		Peça p = tabuleiro.removerPeça(destino);
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
		
		
		colocarNovaPeça('H', 7, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('D', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeça('E', 1, new Rei(tabuleiro, Cor.BRANCO));
		
		colocarNovaPeça('B', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeça('A', 8, new Rei(tabuleiro, Cor.PRETO));
		
		
	}
}
