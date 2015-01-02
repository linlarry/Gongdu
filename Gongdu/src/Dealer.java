import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Dealer {

	static int N = 4;
	static int NC = 52;
	public static Dealer getInstance() {
		return instance;
	}
	private static Dealer instance = new Dealer();
	
	List<Hand> dealHand() {
		Random rand = new Random();
		List<Card> cards = new ArrayList<Card>();

		for (int i = 0; i < NC; i++) {
			int suit = i*N/NC;
			int rank = i - suit * NC/N;  
			cards.add(new Card(suit, rank));
		}
		
		List<Hand> allHands = new ArrayList<Hand>();
		for (int i = 0; i < N; i++) {
			allHands.add(getHand(cards, NC/N, rand));
		}
		
		return allHands;
	}

	int evalCards(List<Card> cards) {
		int suit = cards.get(0).suit;
		int maxRank = cards.get(0).rank;
		int highPlayer = 0;
		for (int i = 1; i < cards.size(); i++) {
			Card c = cards.get(i);
			if (c.suit == suit && c.rank > maxRank) {
				highPlayer = i;
				maxRank = c.rank;
			}
		}
		return highPlayer;
	}
	
	private Hand getHand(List<Card> cards, int iter, Random rand) {
		List<Card> issuedCards = new ArrayList<Card>();
		int i = 0;
		int numCards = cards.size();
		while (iter > i) {
			int idx = rand.nextInt(numCards - i);
			Card issuedCard = cards.remove(idx);
			issuedCards.add(issuedCard);
			i++;
		}
		return new Hand(issuedCards);
	}
	
	public static void main(String[] args) {
		Dealer d = Dealer.getInstance();
		System.out.println(d.dealHand());
	}
}
