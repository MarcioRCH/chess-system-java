package boardgame;

public class Piece {
	
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	
	public Piece (Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		posicao = null;
	}
	
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
}
