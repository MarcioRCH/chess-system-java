package pecas.xadrez;

import boardgame.Posicao;
import boardgame.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez{

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao ps = new Posicao(0, 0);
		
		//metodo que marca como verdadeiro as casas NOROESTE da peça
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setValor(ps.getLinha() - 1, ps.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//metodo que marca como verdadeiro as casas a NORDESTE da peça
		ps.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setValor(ps.getLinha() - 1, ps.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//metodo que marca como verdadeiro as casas a SUDESTE da peça
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setValor(ps.getLinha() + 1, ps.getColuna() + 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		
		//metodo que marca como verdadeiro as casas SUDOESTE da peça
		ps.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(ps) && !getTabuleiro().haUmaPeca(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
			ps.setValor(ps.getLinha() + 1, ps.getColuna() - 1);
		}
		if(getTabuleiro().posicaoExistente(ps) && existeUmaPecaOponente(ps)) {
			matriz[ps.getLinha()][ps.getColuna()] = true;
		}
		return matriz;
	}

}
