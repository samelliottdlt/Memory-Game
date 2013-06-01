import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CustomLevel extends EqualPairLevel {
	/* In this level, the player will flip 5 cards and the program will verify if it is a "Four of a Kind" according to poker rules.
A four of a kind is when 5 cards are drawn and contains the 4 cards of the same rank.
Ex. ( 9♣ 9♠ 9♦ 9♥ J♥)
Score will be tallied by multiplying by 4 the value of the cards of the same rank 
plus the value of the card that appears once. Thus it is beneficial for the
player to pass if the last card is of little value or to use a card of little
value as the last card and create 4 of a kinds with high value cards.
The final card will not be shown before being asked to pass. If the user chooses
to pass, the final card will be shown and a point will be deducted from the score.
*/
	private static int score = 0;
	protected CustomLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreManager validScore) {
		super(validTurnTime, mainFrame, validScore);
		super.turnsTakenCounter.setDifficultyModeLabel("Custom");
		super.scoreCounter.setDifficultyModeLabel("CL");
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
		if(this.turnedCardsBuffer.size() == 5)
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

			int[] cardrank = new int[5];
			cardrank[0]= Integer.parseInt((getRank2(otherCard1)));
			cardrank[1]= Integer.parseInt((getRank2(otherCard2)));
			cardrank[2]= Integer.parseInt((getRank2(otherCard3)));
			cardrank[3]= Integer.parseInt((getRank2(otherCard4)));
			cardrank[4]= Integer.parseInt((getRank2(otherCard5)));
			Arrays.sort(cardrank);


			if((cardrank[0]==cardrank[1] && cardrank[0]==cardrank[2] && cardrank[0]==cardrank[3] )|| (cardrank[1]==cardrank[2] && cardrank[1]==cardrank[3] && cardrank[1]==cardrank[4])){
				
				String input = JOptionPane.showInputDialog("Would you like to pass? (Type yes if you would like to pass or no if you don't want to pass.)");
				if(input.equalsIgnoreCase("no")){
					if(cardrank[0]==cardrank[1]){
						score = score + (4*cardrank[0]) + cardrank[4];
						scoreCounter.increment();
						this.turnedCardsBuffer.clear();
						this.turnDownTimer.start();
						
					}

					else{
						score = score + (4*cardrank[4]) + cardrank[0];
						scoreCounter.increment();
						this.turnedCardsBuffer.clear();
						this.turnDownTimer.start();
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

	public String getRank2(Card CardY){
		//String cardX= CardY.getRank();
		if (CardY.getRank().equals("t")){		
			return "10";}
		else if (CardY.getRank().equals("j")){
			return "11";}
		else if (CardY.getRank().equals("q")){
			return "12";}
		else if (CardY.getRank().equals("k")){
			return "13";}
		else if (CardY.getRank().equals("a")){
			return "1";}
		else return CardY.getRank();
	}

	static int getScore(){
		return score;
	}
	static int resetScore(){
		return score=0;
	}
}
