import java.util.Random;


public class Computer {
	
	private int lastHumanPlay;
	private int scoreC=0;
	private int scoreH=0;
	private String display;
	
	private String[] choices={"Rock","Paper","Scissors"};
	
	private UInterface ui;
	
	public Computer(SampleListener l){
		super();
		ui=new UInterface();
		l.setComputer(this);
		l.setUI(ui);
	}

	public void playComputer(int humanPlay){
		display=choices[humanPlay]+" vs ";
		int play;
		Random randomGenerator = new Random();
		play=randomGenerator.nextInt(3);
		
		switch(play){
			case 0: display+="Rock";break;
			case 1: display+="Paper";break;
			case 2: display+="Scissors";break;
			default: display+="Unknown...";break;
		}
		
		determineWinner(play,humanPlay);
		lastHumanPlay=humanPlay;
		
		ui.updateBattle(display);
		ui.updateScore(scoreH,scoreC);
		System.out.println(display);
	}
	
	
	public void determineWinner(int c, int h){
		if(h-c==1 || h==0&&c==2){
			scoreH++;
			display+=" --> You win!!!";		
		}else{
			if(c==h){
			}else{
				scoreC++;
				display+=" --> You loose...";
			}
		}
		
	}
	
}



