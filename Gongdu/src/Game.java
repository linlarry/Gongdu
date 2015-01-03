import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Game {
	public static Game getInstance() {
		return instance;
	}
	private static Game instance = new Game();
	
	private static Card inputCardFirst(Player player) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Select: " + player.hand);
		int suit = sc.nextInt();
		int pos = sc.nextInt();
		return player.drawCardFromHand(suit, pos);
	}
	
	private static Card inputCardFollow(Player player, int suit) {
		Scanner sc = new Scanner(System.in);
		int pos;
		List<Card> cards = player.hand.getCardsOfSuit(suit);
		if (cards == null) {
			System.out.println("Select: " + player.hand);
			suit = sc.nextInt();
			pos = sc.nextInt();
		} else		{
			System.out.println("Select: " + player.hand.getCardsOfSuit(suit));
			pos = sc.nextInt();
		}
		return player.drawCardFromHand(suit, pos);
	}
	
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
				Card c;
				if (curPlayer != 0) c = players.get(curPlayer).leadCard();
				else {
					c = inputCardFirst(players.get(curPlayer));
				}
				System.out.println("First card by player " + curPlayer + " " + c);
				cards.add(c);
			} else {
				Card c;
				if (curPlayer != 0) c = players.get(curPlayer).followCard(cards);
				else {
					c = inputCardFollow(players.get(curPlayer), cards.get(0).suit);
				}
				System.out.println("Follow card by player " + curPlayer + " " + c);
				cards.add(c);
			}
			curPlayer = (curPlayer+ 1)%players.size();
			if (cards.size() == players.size()) {
				List<Card> alignCards = new ArrayList<Card>();
				int offset = players.size() - curPlayer;
				for (int i = 0; i < players.size(); i++) {
					alignCards.add(cards.get((i + offset)%players.size()));
				}
				System.out.println("Cards this round " + alignCards);
				curPlayer = dealer.evalCards(alignCards);
				players.get(curPlayer).addScoreCard(alignCards);
				cards = new ArrayList<Card>();
			}
		}
		
		for (Player p : players) {
			
			System.out.println(p.getScore());
		}
	}
}
