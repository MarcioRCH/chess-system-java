package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{
	
	private PartidaXadrez partidaXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean testeTorreCastling(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().piece(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimento() == 0;
	}
	
	//metodo que verifica se o Rei pode se mover para uma determinada posição
	private boolean podeMover(Posicao posicao) {
		PecaXadrez peca = (PecaXadrez)getTabuleiro().piece(posicao); //pega a peça (Rei) que está em uma determinada posição
		return peca == null || peca.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao ps = new Posicao(0, 0);
		
		/*testando cada uma das posições que o Rei pode se mover
		 * Acima
		 */
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//abaixo
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//esquerda
		ps.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//direita
		ps.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//noroeste
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//nordeste
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//sudoeste
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//sudeste
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//movimento especial Castling
		if(getContadorDeMovimento() == 0 && !partidaXadrez.getCheck()) {
			//movimento especial Castling lado do Rei
			Posicao posicaoTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if(testeTorreCastling(posicaoTorre1)) {
				Posicao ps1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao ps2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if(getTabuleiro().piece(ps1) == null && getTabuleiro().piece(ps2) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			//movimento especial Castling lado da Rainha
			Posicao posicaoTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if(testeTorreCastling(posicaoTorre2)) {
				Posicao ps1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao ps2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao ps3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if(getTabuleiro().piece(ps1) == null && getTabuleiro().piece(ps2) == null && getTabuleiro().piece(ps3) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return matriz;
	}

}
