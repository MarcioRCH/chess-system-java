package application;

import Xadrez.PartidaXadrez;

public class Program {

	public static void main(String[] args) {
		
		PartidaXadrez partidaXadrez = new PartidaXadrez();
		UI.mostraTabuleiro(partidaXadrez.getPieces());

	}

}
