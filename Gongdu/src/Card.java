
public class Card implements Comparable<Card>{

	int suit;
	int rank;
	
	static char[] suits = {'H','D','S','C'};
	static String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	
	Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return suits[suit] + ranks[rank];
	}
	
	public static void main(String[] args) {
		Card c0 = new Card(1,8);
		System.out.println(c0);
	}

	@Override
	public int compareTo(Card o) {
		if (suit == o.suit) return Integer.compare(rank, o.rank);
		else return Integer.compare(suit, o.suit);
	}
}
