package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}
	
	//metodo que verifica se o Cavalo pode se mover para uma determinada posição
	private boolean podeMover(Posicao posicao) {
		PecaXadrez peca = (PecaXadrez)getTabuleiro().piece(posicao); //pega a peça (Cavalo) que está em uma determinada posição
		return peca == null || peca.getCor() != getCor();
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao ps = new Posicao(0, 0);
		
		//testando cada uma das posições que o Cavalo pode se mover
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().posicaoExistente(ps) && podeMover(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		return matriz;
	}

}
