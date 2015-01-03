import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

	Hand hand;
	List<Card> scoreCards = new ArrayList<Card>();
	static int[] scoreTable = {0,0,0,-10,-10,-10,-10,-10,-10,-20,-30,-40,-50};
	static int pigScore = -100;
	static int yangScore = 100;
	
	Random rand = new Random();
	
	Player(Hand hand) {
		this.hand = hand;
	}
	
	boolean isDone() {
		return hand.getCards().size() == 0;
	}
	
	Card leadCard() {
		int suitIdx = rand.nextInt(hand.getSuits().size());
		int suit = hand.getSuit(suitIdx);
		int pos = rand.nextInt(hand.getCardsOfSuit(suit).size());
		return drawCardFromHand(suit, pos);
	}
	
	Card drawCardFromHand(int suit, int pos) {
		List<Card> cards = hand.getCardsOfSuit(suit);
		Card drewCard = cards.remove(pos);
		if (cards.size() == 0) hand.removeSuit(suit);
		return drewCard;
	}

	Card followCard(List<Card> cards) {
		int suit = cards.get(0).suit;
		int pos;
		List<Card> myCards = hand.getCardsOfSuit(suit);
		if (myCards != null) {
			pos = rand.nextInt(myCards.size());
		}
		else {			
			suit = hand.getSuits().get(rand.nextInt(hand.getSuits().size()));
			pos = rand.nextInt(hand.getCardsOfSuit(suit).size());
		}
		return drawCardFromHand(suit, pos);
	}
	
	void addScoreCard(List<Card> cards) {
		scoreCards.addAll(cards);
	}
	
	int getScore() {
		int score = 0;
		boolean doubler = false;
		
		for (Card c : scoreCards) {
			if (c.suit == 0) {
				score += scoreTable[c.rank];
			} else if (c.suit ==3 && c.rank == 10) {
				score += pigScore;
			} else if (c.suit == 1 && c.rank == 9) {
				score += yangScore;
			} else if (c.suit == 2 && c.rank == 8) {
				doubler = true;
			} else continue;
			System.out.print(c + " ");
		}
		if (doubler) score = score *2;
		System.out.print("\n");
		return score;
	}
	
	List<Card> getScoreCards() {
		return scoreCards;
	}
	
	@Override 
	public String toString() {
		return hand.toString();
	}
}
