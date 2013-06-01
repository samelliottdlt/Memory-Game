import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/*FULL HOUSE TRIO LEVEL
The game consists of a grid of distinct cards. At the start of the game, ever card is face down. The object is to
find a combination of three same rank cards and two same rank cards for a total of five cards. The score is determined by
getting awarded 700 points plus three times the amount of the trio times three. Every turn deducts a penalty point.*/

public class FullHouseLevel extends RankTrioLevel {
	// TRIO LEVEL: The goal is to find, on each turn, three cards with the same rank
	static int score= 0;
	protected FullHouseLevel(TurnsTakenCounterLabel validTurnTime, JFrame mainFrame, ScoreManager validScore) {

		super(validTurnTime, mainFrame, validScore);
		super.turnsTakenCounter.setDifficultyModeLabel("Trio Level");
		super.scoreCounter.setDifficultyModeLabel("FHL");
		cardsToTurnUp = 5;
		cardsPerRow = 10;
		rowsPerGrid = 5;
	}
	protected void makeDeck() {

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
			int cardNum[] = new int[5];
			cardNum[0]= Integer.parseInt((getRank2(otherCard1)));
			cardNum[1]= Integer.parseInt((getRank2(otherCard2)));
			cardNum[2]= Integer.parseInt((getRank2(otherCard3)));
			cardNum[3]= Integer.parseInt((getRank2(otherCard4)));
			cardNum[4]= Integer.parseInt((getRank2(otherCard5)));
			Arrays.sort(cardNum);
			int cardNum1= cardNum[0];
			int cardNum2= cardNum[1];
			int cardNum3= cardNum[2];
			int cardNum4= cardNum[3];
			int cardNum5= cardNum[4];

			if(((((cardNum1==cardNum2)&&(cardNum2==cardNum3)) && (cardNum4==cardNum5))||((cardNum1==cardNum2)&&(((cardNum3==cardNum4)&&(cardNum4==cardNum5)))))) 
			{
				// Three cards match, so remove them from the list (they will remain face up)
				this.turnedCardsBuffer.clear();
				if(((cardNum1==cardNum2)&&(cardNum2==cardNum3))&&(cardNum4==cardNum5)){
					score = score + 700 + cardNum1*3;
					scoreCounter.increment();
					this.turnDownTimer.start();
				}
				else{
					score = score + 700 +3* cardNum4;
					this.turnDownTimer.start();
					scoreCounter.increment();}}

			else {

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
