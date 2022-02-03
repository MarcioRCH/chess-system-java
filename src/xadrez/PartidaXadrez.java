package xadrez;

import boardgame.Tabuleiro;
import pecas.xadrez.Rei;
import pecas.xadrez.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		iniciaPartida();
	}
	
	public PecaXadrez[][] getPieces(){
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaXadrez) tabuleiro.piece(i, j);
			}
		}
		return matriz;
	}
	
	private void colocaNovaPeca(char coluna, int linha, PecaXadrez piece) {
		tabuleiro.colocaPeca(piece, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	private void iniciaPartida() {
		colocaNovaPeca('a', 8, new Torre(tabuleiro, Cor.BRANCA));
		colocaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA));
		colocaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
	}

}
