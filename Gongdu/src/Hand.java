import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Hand {

	Map<Integer, List<Card>> hand = new HashMap<Integer, List<Card>>(); 
	
	Hand(List<Card> cards) {
		Collections.sort(cards);
		for (Card card: cards) {
			if (hand.containsKey(card.suit)) {
				hand.get(card.suit).add(card);
			} else {
				List<Card> cardList = new ArrayList<Card>();
				cardList.add(card);
				hand.put(card.suit, cardList);
			}
		}
	}
	
	List<Card> getCardsOfSuit(int suit) {
		if (hand.containsKey(suit))
			return hand.get(suit);
		else return null;
	}
	
	void removeSuit(int suit) {
		hand.remove(suit);
	}
	
	List<Card> getCards() {
		List<Card> cards = new ArrayList<Card>();
		for (List<Card> cs: hand.values()) {
			cards.addAll(cs);
		}
		return cards;
	}
	
	List<Integer> getSuits() {
		return new ArrayList<Integer>(hand.keySet());
	}
	
	int getSuit(int index) {
		List<Integer> suits = new ArrayList<Integer>(hand.keySet());
		return suits.get(index);
	}
	
	@Override 
	public String toString() {
		return hand.toString();
	}
	
	public static void main(String[] args) {
		List<Card> hand = new ArrayList<Card>();
		Card c0 = new Card(0,0);
		Card c1 = new Card(1,0);
		Card c2 = new Card(2,4);
		Card c3 = new Card(0,11);
		Card c4 = new Card(0,10);
		hand.add(c0);
		hand.add(c1);
		hand.add(c2);
		hand.add(c3);
		hand.add(c4);		
		Hand newHand = new Hand(hand);
		System.out.println(newHand);		
	}
}
