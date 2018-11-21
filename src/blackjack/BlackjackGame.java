package blackjack;

public class BlackjackGame {
	
	private Deck deck;
	private Hand playerHand, dealerHand;
	private Card holeCard;
	private int cardValue, playerHandValue, dealerHandValue, startingHandSize, holeCardValue; // hole card is the dealer's card this face down and not visible to the player.

	public BlackjackGame() {
		deck = new Deck();
		playerHand = new Hand();
		dealerHand = new Hand();
		cardValue = 1;
		playerHandValue = 0;
		dealerHandValue = 0;
		holeCardValue = 0;
		startingHandSize = 2;
	}
	
	// Accessor Methods
	public Deck getDeck() { return deck; }
	
	public Hand getPlayerHand() { return playerHand; }
	
	public Hand getDealerHand() { return dealerHand; }
	
	public boolean isHoleCard(Card c) { return holeCard == c; }
	
	public int getPlayerHandValue() { return playerHandValue; }
	
	public int getDealerHandValue() { return dealerHandValue; }
	
	public int getStartingHandSize() { return startingHandSize; }
	
	public int getHoleCardValue() { return holeCardValue; }
	
	// Makes the value of the Hole Card visible to the player.
	public void returnHoleCardValue() { dealerHandValue += holeCardValue; }
	
	public void generateGame() throws Exception {
		deck.generate();
		
		for (int i = 0; i < deck.getSize(); i++) {
			if (i % 4 == 0 && cardValue < 10) { cardValue++; }
			deck.getStack().get(i).setValue(cardValue);
		}
		
		deck.shuffle();
		
		// Give out starting cards to the player and the dealer
		addToPlayerHand(deck.draw() );
		addToDealerHand(deck.draw() );
		addToPlayerHand(deck.draw() );
		addToDealerHand(deck.draw() );
		
		holeCard = dealerHand.getCardAtIndex(1);
		
		holeCardValue = dealerHand.getCardAtIndex(1).getValue();
		dealerHandValue -= holeCardValue;
	}
	
	public void addToPlayerHand(Card c) { 
		playerHand.addToHand(c);
		playerHandValue += c.getValue();
	}
	
	public void addToDealerHand(Card c) { 
		dealerHand.addToHand(c); 
		dealerHandValue += c.getValue();
	}
	
	public boolean playerWon() {
		if (playerHandValue <= 21 && (playerHandValue > dealerHandValue) ) return true;
		
		else return false;
	}
}
