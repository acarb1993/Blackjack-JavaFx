package blackjack;

public class Main {
	public static void main(String args[]) {
		Deck deck = new Deck();
		deck.generate();
		deck.shuffle();
		System.out.println();
		System.out.println(deck.draw().toString());
	}
}
