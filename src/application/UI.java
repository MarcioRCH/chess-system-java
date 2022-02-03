package application;

import xadrez.PecaXadrez;

public class UI {
	
	public static void mostraTabuleiro(PecaXadrez[][] pieces) {
		for(int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < pieces.length; j++) {
				mostraPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");

	}
	
	private static void mostraPiece(PecaXadrez piece) {
		if(piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
