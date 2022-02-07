package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
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
		
		return matriz;
	}

}
