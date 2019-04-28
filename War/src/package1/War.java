package package1;
//Carter Crouch
import java.util.ArrayList;
import javax.swing.JPanel;
public class War extends JPanel{
public static int convert(ArrayList<String> list) {
	if(list.get(0).equals("Ace")) {
		return 1;
	}
	else if(list.get(0).equals("Two")) {
		return 2;
	}
	else if(list.get(0).equals("Three")) {
		return 3;
	}
	else if(list.get(0).equals("Four")) {
		return 4;
	}
	else if(list.get(0).equals("Five")) {
		return 5;
	}
	else if(list.get(0).equals("Six")) {
		return 6;
	}
	else if(list.get(0).equals("Seven")) {
		return 7;
	}
	else if(list.get(0).equals("Eight")) {
		return 8;
	}
	else if(list.get(0).equals("Nine")) {
		return 9;
	}
	else if(list.get(0).equals("Ten")) {
		return 10;
	}
	else if(list.get(0).equals("Jack")) {
		return 11;
	}
	else if(list.get(0).equals("Queen")) {
		return 12;
	}
	else{
		return 13;
	}
}
public static void bubbleSort(ArrayList<String> list) {
	for(int i=0; i<list.size()-1; i++) {
		for(int j=0; j<list.size()-i-1;j++) {
			if(list.get(j).compareTo(list.get(j+1)) > 0) {
				String temp = "";
				temp = list.get(j);
				list.set(j, list.get(j+1));
				list.set(j+1, temp);
			}
		}
	}
}
} 


