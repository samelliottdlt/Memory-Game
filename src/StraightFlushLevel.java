import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class StraightFlushLevel extends EqualPairLevel {

	private static int score = 0;
	protected StraightFlushLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreManager validScore) {
		super(validTurnTime, mainFrame, validScore);
		super.turnsTakenCounter.setDifficultyModeLabel("Flush");
		super.scoreCounter.setDifficultyModeLabel("FL");
		cardsToTurnUp = 5;
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
			Card otherCard3 = (Card) this.turnedCardsBuffer.get(2);
			Card otherCard4 = (Card) this.turnedCardsBuffer.get(3);
			Card otherCard5 = (Card) this.turnedCardsBuffer.get(4);
			if(otherCard1.getSuit().equals(otherCard2.getSuit()) && otherCard1.getSuit().equals(otherCard3.getSuit()) && otherCard1.getSuit().equals(otherCard4.getSuit()) && otherCard1.getSuit().equals(otherCard5.getSuit()) ) {
				// Three cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();
				if(otherCard1.getRank().equalsIgnoreCase("K") || otherCard2.getRank().equalsIgnoreCase("K") || otherCard3.getRank().equalsIgnoreCase("K") || otherCard4.getRank().equalsIgnoreCase("K")||otherCard5.getRank().equalsIgnoreCase("K")){
					score = score + 65000 + 100* 13;
					scoreCounter.increment();
				}
				else if(otherCard1.getRank().equalsIgnoreCase("Q") || otherCard2.getRank().equalsIgnoreCase("Q") || otherCard3.getRank().equalsIgnoreCase("Q") || otherCard4.getRank().equalsIgnoreCase("Q")||otherCard5.getRank().equalsIgnoreCase("Q")){
					score = score + 65000 + 100 * 12;
					scoreCounter.increment();
				}
				else if(otherCard1.getRank().equalsIgnoreCase("J") || otherCard2.getRank().equalsIgnoreCase("J") || otherCard3.getRank().equalsIgnoreCase("J") || otherCard4.getRank().equalsIgnoreCase("J")||otherCard5.getRank().equalsIgnoreCase("J")){
					score = score + 65000 + 100 * 11;
					scoreCounter.increment();
				}
				else if (otherCard1.getRank().equalsIgnoreCase("T") || otherCard2.getRank().equalsIgnoreCase("T") || otherCard3.getRank().equalsIgnoreCase("T") || otherCard4.getRank().equalsIgnoreCase("T")||otherCard5.getRank().equalsIgnoreCase("T")){
					score = score + 65000 + 100 * 10;
					scoreCounter.increment();
				} 
				else {
					int[] cards = new int[4];

					cards[0] = Integer.parseInt(otherCard1.getRank());
					cards[1] = Integer.parseInt(otherCard2.getRank());
					cards[2] = Integer.parseInt(otherCard3.getRank());
					cards[3] = Integer.parseInt(otherCard4.getRank());
					cards[4] = Integer.parseInt(otherCard5.getRank());
					int greatestrank = cards[0];
					for(int i = 1 ; i<=4 ; i++){
						if(greatestrank < cards[i]){
							greatestrank = cards[i];
						}
					}
					score = score + 65000 + 100 * greatestrank;
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
