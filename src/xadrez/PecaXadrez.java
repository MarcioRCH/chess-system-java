package xadrez;

import boardgame.Piece;
import boardgame.Tabuleiro;

public abstract class PecaXadrez extends Piece{
	
	private Cor cor;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

}
