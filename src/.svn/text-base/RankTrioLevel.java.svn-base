import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RankTrioLevel extends EqualPairLevel {

	// TRIO LEVEL: The goal is to find, on each turn, three cards with the same rank
	protected static int score = 0;
	protected RankTrioLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreManager validScore) {
		super(validTurnTime, mainFrame, validScore);
		super.turnsTakenCounter.setDifficultyModeLabel("Trio Level");
		super.scoreCounter.setDifficultyModeLabel("TL");
		cardsToTurnUp = 3;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}

	@Override
	protected void makeDeck() {
		// In Trio level the grid consists of distinct cards, no repetitions
		ImageIcon cardIcon[] = this.loadCardIcons();

		//back card
		ImageIcon backIcon = cardIcon[TotalCardsPerDeck];

		int cardsToAdd[] = new int[getRowsPerGrid() * getCardsPerRow()];
		for(int i = 0; i < (getRowsPerGrid() * getCardsPerRow()); i++)
		{
			cardsToAdd[i] = i;
		}

		// randomize the order of the deck
		this.randomizeIntArray(cardsToAdd);

		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, randomized
			int num = cardsToAdd[i];
			// make the card object and add it to the panel
			String rank = cardNames[num].substring(0, 1);
			String suit = cardNames[num].substring(1, 2);
			this.grid.add( new Card(this, cardIcon[num], backIcon, num, rank, suit));
		}
	}

	@Override
	protected boolean addToTurnedCardsBuffer(Card card) {
		// add the card to the list
		this.turnedCardsBuffer.add(card);
		if(this.turnedCardsBuffer.size() == getCardsToTurnUp())
		{
			// We are uncovering the last card in this turn
			// Record the player's turn
			this.turnsTakenCounter.increment();
			// get the other card (which was already turned up)
			Card otherCard1 = (Card) this.turnedCardsBuffer.get(0);
			Card otherCard2 = (Card) this.turnedCardsBuffer.get(1);
			if((card.getRank().equals(otherCard1.getRank())) && (card.getRank().equals(otherCard2.getRank()))) {
				// Three cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();
				String rank = new String(card.getRank());
				if(Character.isDigit(rank.charAt(0))){
					int i = Integer.parseInt(rank);
					score = score + 46 + 3 * i;
					scoreCounter.increment();
				}
				if(card.getRank().equalsIgnoreCase("t")){
					score = score + 46 + 3 * 10;
					scoreCounter.increment();
				}
				if(card.getRank().equalsIgnoreCase("j")){
					score = score + 46 + 3 * 11;
					scoreCounter.increment();
				}
				if(card.getRank().equalsIgnoreCase("q")){
					score = score + 46 + 3 * 12;
					scoreCounter.increment();
				}
				if(card.getRank().equalsIgnoreCase("k")){
					score = score + 46 + 3 * 13;
					scoreCounter.increment();
				}
				if(card.getRank().equalsIgnoreCase("a")){
					score = score + 46 + 3 * 14;
					scoreCounter.increment();
				}
			}
			else 
			{
				// The cards do not match, so start the timer to turn them down
				this.turnDownTimer.start();
				score = score -1;
				scoreCounter.increment();
			}
		}
		return true;
	}
	static int getScore(){
		return score;
	}
	static int resetScore(){
		return score=0;
	}
}
