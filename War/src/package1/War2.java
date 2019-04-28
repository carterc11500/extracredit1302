package package1;
//Carter Crouch
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
public class War2 extends War{
private JButton play, draw, again, rules, check;
private JLabel start, player, cpu, win, pScore, DHave, Q, result;
private String pval, cval, findme;
private int ps, cs, pX, cX;
private JScrollPane scrollpane;
private JTextField search;
private JPanel jp1, jp2, jmaster;

//	Creates 5 ArrayLists
// 1) Contains 4 copies of each type of card in a deck of cards for a total of 52, the suits of the cards don't matter for this game.
ArrayList<String> Deck = new ArrayList<String>(Arrays.asList("Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"));
// 2) The Player ArrayList will contain the cards within the user's deck
ArrayList<String> Player = new ArrayList<String>();
// 3) The Computer ArrayList will contain the cards within the computer's deck
ArrayList<String> Computer = new ArrayList<String>();
// 4) The inPlay ArrayList exists in the case of a tie where extra cards may be at stake and will be emptied into either the
// Player or Computer decks depending on who wins the next round
ArrayList<String> inPlay = new ArrayList<String>();
// 5) The Test ArrayList is used when searching for a card in your deck
ArrayList<String> Test = new ArrayList<String>();

// constructor
public War2() {
	
	jp1 = new JPanel();
	jp1.setBackground(Color.MAGENTA);
	BoxLayout layout = new BoxLayout(jp1, BoxLayout.Y_AXIS);
	jp1.setLayout(layout);
	
	jp2 = new JPanel();
	jp2.setBackground(Color.MAGENTA);
	
	jmaster = new JPanel();
	jmaster.setBackground(Color.MAGENTA);
	BoxLayout layout1 = new BoxLayout(jmaster, BoxLayout.Y_AXIS);
	jmaster.setLayout(layout1);
	jmaster.add(jp1);
	jmaster.add(jp2);
	add(jmaster);
	
	// The text area I have created holds the instructions for the card game War and can be accessed by pressing the
	// rules button before starting a game
	JTextArea textarea = new JTextArea(10,50);
	scrollpane = new JScrollPane(textarea);
	
	textarea.append("The objective of the game is to win all of the cards.\r\n\r\n"
			+ "The deck is divided evenly among the players, giving each 26 cards. In unison, \r\n"
			+ "each player reveals the top card of their deck and the player with the higher \r\n"
			+ "card takes both of the cards played and moves them to the bottom of their stack. Aces are low, \r\n"
			+ "and suits are ignored. If the two cards played are of equal value, then there is a \"war\". \r\n"
			+ "Both players place the next 3 cards of their pile face down, and then \r\n" + 
			"draw another card face up. The owner of the higher face-up card wins the war and \r\n" + 
			"adds all six cards on the table to the bottom of their deck. If the face-up \r\n" + 
			"cards are again equal then the battle repeats with another set of face-down/up \r\n" + 
			"cards. This repeats until one player's face-up card is higher than their \r\n" + 
			"opponent's. If a player runs out of cards during a war they immediately lose.\r\n\r\n" +
			"This game requires no skill so the outcome is entirely based on chance. \r\n" +
			"For my Java version, you simply have to press the draw button and one card will \r\n" +
			"be drawn and compared with the computer's card and the winner will automatically \r\n" + 
			"have the cards added to their deck. \r\n\r\n" +
			"I have also implemented a way to search your deck to find out if you have a \r\n" +
			"card or not, but you will not know its position.");
	
	textarea.setEditable(false);
	jp1.add(scrollpane);
	scrollpane.setVisible(false);
	
	search = new JTextField(5);
	
	rules = new JButton("Rules");
	rules.addActionListener(new ButtonListener());
	start = new JLabel("Start a game?");
	start.setFont((new Font("Helvetica", Font.BOLD,18)));
	jp1.add(start);
	jp1.add(rules);
	play = new JButton("Play!");
	play.addActionListener(new ButtonListener());
	jp1.add(play);
	draw = new JButton("Draw");
	draw.addActionListener(new ButtonListener());
	draw.setVisible(false);
	check = new JButton("Search");
	check.addActionListener(new ButtonListener());
	
	player = new JLabel();
	player.setFont((new Font("Helvetica", Font.PLAIN,18)));
	jp1.add(player);
	cpu = new JLabel();
	cpu.setFont((new Font("Helvetica", Font.PLAIN,18)));
	jp1.add(cpu);
	win = new JLabel();
	win.setFont((new Font("Helvetica", Font.BOLD,18)));
	jp1.add(win);
	pScore=new JLabel();
	pScore.setFont((new Font("Helvetica", Font.PLAIN,12)));
	jp1.add(pScore);
	again = new JButton("Play again");
	again.addActionListener(new ButtonListener());
	jp1.add(again);
	again.setVisible(false);
	jp1.add(draw);
	DHave = new JLabel("Do I have a(n) ");
	DHave.setFont((new Font("Helvetica", Font.PLAIN,12)));
	Q = new JLabel("?");
	Q.setFont((new Font("Helvetica", Font.PLAIN,12)));
	result = new JLabel();
	result.setFont((new Font("Helvetica", Font.PLAIN,12)));
	
	jp2.add(DHave);
	jp2.add(search);
	jp2.add(Q);
	jp2.add(check);
	jp2.add(result);
	jp2.setVisible(false);
	
	setBackground(Color.MAGENTA);
	setPreferredSize(new Dimension(600,400));
}
// What happens when each of the four buttons is pressed
private class ButtonListener implements ActionListener{
	public void actionPerformed(ActionEvent evt) {
// Shows the rules text area
		if(evt.getSource() == rules) {
			rules.setVisible(false);
			scrollpane.setVisible(true);
		}
// This will restart the game by re-shuffling the Deck ArrayList and emptying the other three as well as re-dealing each player 26 cards
		else if(evt.getSource() == again) {
			Player.removeAll(Player); Computer.removeAll(Computer); inPlay.removeAll(inPlay);
			
			again.setVisible(false);
			draw.setVisible(true);
			cpu.setText(""); win.setText(""); pScore.setText("");
			cpu.setVisible(true); win.setVisible(true); pScore.setVisible(true);
			player.setText("Draw a card!");
			
			Collections.shuffle(Deck);
			for(int i=0; i<Deck.size(); i++) {
			if(i%2 == 0) {
				Computer.add(Deck.get(i));
			}
			else {
				Player.add(Deck.get(i));
			}
			}	
		}
// This will start the game 
		else if(evt.getSource() == play) {
			
		play.setVisible(false);
		start.setVisible(false);
		scrollpane.setVisible(false);
		rules.setVisible(false);
		
		player.setVisible(true);
		cpu.setVisible(true);
		win.setVisible(true);
		draw.setVisible(true);
		pScore.setVisible(true);

	//Shuffle deck and deal cards
		Collections.shuffle(Deck);
		for(int i=0; i<Deck.size(); i++) {
		if(i%2 == 0) {
			Computer.add(Deck.get(i));
		}
		else {
			Player.add(Deck.get(i));
		}
		}
		
		player.setText("Draw a card!");
		
		}
		else if(evt.getSource() == check) {
			result.setVisible(true);
			{
			
			Test.addAll(Player);
		}
			findme = search.getText();
			findme = findme.substring(0, 1).toUpperCase() + findme.substring(1).toLowerCase();
			bubbleSort(Test);
			if(Collections.binarySearch(Test, findme) >= 0){
				result.setText(" Yes");
				Test.removeAll(Test);
			}
			else {
				result.setText(" No");
				Test.removeAll(Test);
			}
		}
	// Each player draws a card, the cards are compared and they are added to the bottom of the winner's deck
		else if (evt.getSource() == draw && Player.size() >0 && Computer.size() >0) {
		jp2.setVisible(true);
		result.setVisible(false);
		// Converts card names to integer values
			{
			pX = convert(Player);
			cX = convert(Computer);
		}
			
	// If the player draws the higher card
		if(pX>cX) {
		pval = Player.get(0); cval = Computer.get(0);	
			
		Player.add(Player.get(0)); Player.add(Computer.get(0));
		Player.remove(0); Computer.remove(0);
		
	//if the previous round was a tie, this for loop will add the cards from the inPlay deck to the winner's deck and then empty it
		for(int i=0; i<inPlay.size(); i++) {
			Player.add(inPlay.get(i));
		}
		inPlay.removeAll(inPlay);
		
		ps = Player.size();
		cs = Computer.size();
		
		player.setText("You draw: " + pval);
		cpu.setText("Computer draws: " + cval);
		win.setText("You win!");
		pScore.setText("Your cards: " + ps + " | Computer's cards: " + cs);
		
		}
	// if the computer draws the higher card
		else if(cX>pX) {
		pval = Player.get(0); cval = Computer.get(0);
		
		Computer.add(Computer.get(0)); Computer.add(Player.get(0)); 
		Player.remove(0); Computer.remove(0);
		
		for(int i=0; i<inPlay.size(); i++) {
			Computer.add(inPlay.get(i));
		}
		inPlay.removeAll(inPlay);
		
		ps = Player.size();
		cs = Computer.size();
		
		player.setText("You draw: " + pval);
		cpu.setText("Computer draws: " + cval);
		win.setText("Computer wins");
		pScore.setText("Your cards: " + ps + " | Computer's cards: " + cs);
		
		}
	// if there is a tie
		else if(cX == pX){
		// If someone runs out of cards during a war, they automatically lose
			if(Player.size()<4) {
				pval = Player.get(0); cval = Computer.get(0);
				Player.removeAll(Player);
				
				
				ps = Player.size();
				cs = Computer.size();
				
				player.setText("You draw: " + pval);
				cpu.setText("Computer draws: " + cval);
				win.setText("Tie! 6 more cards at stake for next round!");
				pScore.setText("Your cards: " + ps + " | Computer's cards: " + cs);
			}
			else if(Computer.size()<4) {
				pval = Player.get(0); cval = Computer.get(0);
				Computer.removeAll(Computer);
				
				
				ps = Player.size();
				cs = Computer.size();
				
				player.setText("You draw: " + pval);
				cpu.setText("Computer draws: " + cval);
				win.setText("Tie! 6 more cards at stake for next round!");
				pScore.setText("Your cards: " + ps + " | Computer's cards: " + cs);
			}
		// The cards in play as well as 3 additional cards from each player's deck are added to the inPlay ArrayList
			else {
			pval = Player.get(0); cval = Computer.get(0);
			
			inPlay.add(Computer.get(0)); inPlay.add(Computer.get(1)); inPlay.add(Computer.get(2)); inPlay.add(Computer.get(3));
			inPlay.add(Player.get(0)); inPlay.add(Player.get(1)); inPlay.add(Player.get(2)); inPlay.add(Player.get(3));
			Player.remove(0); Player.remove(0); Player.remove(0); Player.remove(0); 
			Computer.remove(0); Computer.remove(0); Computer.remove(0); Computer.remove(0); 
			
			ps = Player.size();
			cs = Computer.size();
			
			player.setText("You draw: " + pval);
			cpu.setText("Computer draws: " + cval);
			win.setText("Tie! 6 more cards at stake for next round!");
			pScore.setText("Your cards: " + ps + " | Computer's cards: " + cs);
			}
		}
			}
// when one player has no cards left
	else {
		jp2.setVisible(false);
		draw.setVisible(false);
		cpu.setVisible(false); win.setVisible(false); pScore.setVisible(false);
		
		if(ps ==0) {
			player.setText("You lost.");
		}
		else if(cs == 0) {
			player.setText("You won!");
		}
	// ask to play again
		again.setVisible(true);
		}
		}	
	}
}