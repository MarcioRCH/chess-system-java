package xadrez;

import boardgame.Posicao;
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
	
	private void iniciaPartida() {
		tabuleiro.colocaPeca(new Torre(tabuleiro, Cor.BRANCA), new Posicao(0,0));
		tabuleiro.colocaPeca(new Rei(tabuleiro, Cor.PRETA), new Posicao(0,4));
		tabuleiro.colocaPeca(new Rei(tabuleiro, Cor.BRANCA), new Posicao(7,4));
	}

}
