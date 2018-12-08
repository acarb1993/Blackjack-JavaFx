// This class will control the rules and the logic of the game.

package blackjack;

public class BlackjackGame {
	
	private Deck deck;
	private Player player, dealer;
	private Card holeCard;
	private int cardValue, startingHandSize, holeCardValue; // hole card is the dealer's card this face down and not visible to the player.

	public BlackjackGame() {
		deck = new Deck();
		player = new Player();
		dealer = new Player();
		
		cardValue = 1;
        holeCardValue = 0;
		startingHandSize = 2;
	}
	
	// Accessor Methods
	public Deck getDeck() { return deck; }
	
	public Player getPlayer() { return player; }
	
	public Player getDealer() { return dealer; }
	
	public Hand getPlayerHand() { return player.getHand(); }
	
	public Hand getDealerHand() { return dealer.getHand(); }
	
	public Card getHoleCard() { return holeCard; }
	
	public boolean isHoleCard(Card c) { return holeCard == c; }
	
	public int getPlayerHandValue() { return player.getTotalCardValue(); }
	
	public int getDealerHandValue() { return dealer.getTotalCardValue(); }
	
	public int getStartingHandSize() { return startingHandSize; }
	
	public int getHoleCardValue() { return holeCardValue; }
	
	// Makes the value of the Hole Card visible to the player.
	public void returnHoleCardValue() { dealer.setTotalCardValue(dealer.getTotalCardValue() + holeCardValue); }
	
	// Generates the values of all the cards
	public void generateGame() {
		deck.generate();
		
		for (int i = 0; i < deck.getSize(); i++) {
			if (i % 4 == 0 && cardValue < 10) // Makes sure the value of the cards in the deck does not exceed 10
				cardValue++; 
			
			deck.getStack().get(i).setValue(cardValue);
		}
		
		deck.shuffle();
		
		// Give out starting cards to the player and the dealer
		addToPlayerHand(deck.draw() );
		addToDealerHand(deck.draw() );
		addToPlayerHand(deck.draw() );
		addToDealerHand(deck.draw() );
		
		holeCard = dealer.getHand().getCardAtIndex(1);
		
		holeCardValue = dealer.getHand().getCardAtIndex(1).getValue();
		dealer.setTotalCardValue(dealer.getTotalCardValue() - holeCardValue);
	}
	
	public void addToPlayerHand(Card c) { 
		player.addToPlayerHand(c); 
	}
	
	public void addToDealerHand(Card c) {  
		dealer.addToPlayerHand(c); 
	}
	
	public boolean playerWon() {
		if (dealer.getTotalCardValue() > 21) return true;
		
		if ( (player.getTotalCardValue() <= 21) && (player.getTotalCardValue() > dealer.getTotalCardValue()) ) return true;
		
		else return false;
	}
}
