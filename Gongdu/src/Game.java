import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {
	public static Game getInstance() {
		return instance;
	}
	private static Game instance = new Game();
	
	public static void main(String[] args) {
		Dealer dealer = Dealer.getInstance();
		List<Hand> hands = dealer.dealHand();
		Random rand = new Random();
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < hands.size(); i++) {
			players.add(new Player(hands.get(i)));
		}
		
		System.out.println(players);
		
		int curPlayer = rand.nextInt(hands.size());		
		List<Card> cards = new ArrayList<Card>();
		
		while (!players.get(curPlayer).isDone()) {
			if (cards.size() == 0) {
				Card c = players.get(curPlayer).leadCard();
				System.out.println("First card by player " + curPlayer + " " + c);
				cards.add(c);
			} else {
				Card c = players.get(curPlayer).followCard(cards);
				System.out.println("Follow card by player " + curPlayer + " " + c);
				cards.add(c);
			}
			curPlayer = (curPlayer+ 1)%players.size();
			if (cards.size() == players.size()) {	
				System.out.println("Cards this round " + cards);
				curPlayer = dealer.evalCards(cards);
				players.get(curPlayer).addScoreCard(cards);
				cards = new ArrayList<Card>();
			}
		}
		
		for (Player p : players) {
			
			System.out.println(p.getScore());
		}
	}
}
