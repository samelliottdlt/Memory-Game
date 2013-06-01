import javax.swing.JLabel;


public class ScoreManager extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int score = 0;
	private String DESCRIPTION;
	private String DIFFICULTY;

	public ScoreManager()
	{
		super();
		reset();
	}
	public void setDifficultyModeLabel(String difficultyMode){
		DESCRIPTION = "Score: ";
		setHorizontalTextPosition(JLabel.RIGHT);
		this.DIFFICULTY = difficultyMode;
	}

	public int getNumOfTurns(){
		return this.score;
	}

	public void update()
	{
		setText(DESCRIPTION + Integer.toString(this.score));
		setHorizontalTextPosition(JLabel.LEFT);
	}

	public void reset()
	{
		this.score = 0;

		EqualPairLevel.resetScore();		
		RankTrioLevel.resetScore();		
		StraightFlushLevel.resetScore();		
		FullHouseLevel.resetScore();
		CustomLevel.resetScore();

		update();
	}
	public void increment(){
		if(DIFFICULTY.equalsIgnoreCase("EPL")){
			score = EqualPairLevel.getScore();
		}
		else if(DIFFICULTY.equalsIgnoreCase("TL")){
			score = RankTrioLevel.getScore();
		}
		else if (DIFFICULTY.equalsIgnoreCase("FL")){
			score = StraightFlushLevel.getScore();
		}
		else if (DIFFICULTY.equalsIgnoreCase("FHL")){
			score = FullHouseLevel.getScore();
		}
		else if (DIFFICULTY.equalsIgnoreCase("CL")){
			score = CustomLevel.getScore();
		}
		update();
	}
}
