import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class UInterface extends JFrame{
	
	private JLabel score;
	private JLabel battle;
	
	private int scoreH=0;
	private int scoreC=0;
	
	private Container contents;

	public UInterface() {
		super("Shifumi");
		
		contents=getContentPane();
		contents.setLayout(new GridLayout(0,1));
		
		score=new JLabel("You: "+scoreH+" - "+scoreC+" :Computer");
		battle=new JLabel("Ready when you are");
		
		contents.add(score);
		contents.add(battle);
		
		setSize(300,300);
		setVisible(true);
	}
	
	
	public void updateScore(int h, int c){
		scoreC=c;
		scoreH=h;
		score.setText("You: "+scoreH+" - "+scoreC+" :Computer");
	}
	
	public void updateBattle(String s){
		battle.setText(s);
	}

}
