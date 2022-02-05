package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao ps = new Posicao(0, 0);
		
		//metodo que marca como verdadeiro as casas ACIMA da peça
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setLinha(ps.getLinha() - 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//metodo que marca como verdadeiro as casas a ESQUERDA da peça
		ps.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setColuna(ps.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//metodo que marca como verdadeiro as casas a DIREITA da peça
		ps.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setColuna(ps.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//metodo que marca como verdadeiro as casas ABAIXO da peça
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setLinha(ps.getLinha() + 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		return matriz;
	}

}
