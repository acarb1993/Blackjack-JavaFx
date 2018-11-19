package blackjack;

public class BlackjackGame {
	
	private Deck deck;
	private Hand playerHand, dealerHand;
	private int cardValue;

	public BlackjackGame() {
		deck = new Deck();
		playerHand = new Hand();
		dealerHand = new Hand();
		cardValue = 1;
	}
	
	// Accessor Methods
	public Deck getDeck() { return deck; }
	
	public Hand getPlayerHand() { return playerHand; }
	
	public Hand getDealerHand() { return dealerHand; }
	
	//Mutator Methods
	
	public void generateCardValues() {
		deck.generate();
		for (int i = 0; i < deck.getSize(); i++) {
			if (i % 4 == 0 && cardValue < 10) { cardValue++; }
			deck.getStack().get(i).setValue(cardValue);
		}
		deck.shuffle();
		deck.showStack();
	}
	
	public void addToPlayerHand(Card c) { playerHand.addToHand(c); }
	
	public void addToDealerHand(Card c) { dealerHand.addToHand(c); }
}
