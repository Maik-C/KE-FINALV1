package FilmAdvisor;

import java.util.Scanner;

public class Questions {
	
	Questions() {
		
	}
	
	Object askQuestions(Object o, Scanner input) {
		askForYesNo(input);
		askFor(input, o, "Theme", "What is the main theme of the film?\n For example: Love, Titanic or Growing Up.\n");
		askFor(input, o, "Country", "What is the country the film was made in?\n For example: America or India.\n");
		askFor(input, o, "Music", "What is the style of the music?\n For example: Tense, Lively or Pop.\n");
		askFor(input, o, "Director", "Who is the director of the film?\n For example: Stuart Sender or James Cameron.\n");
		askFor(input, o, "Plot", "What is the main plot of the film?\n For example: Loss of Memory, Murder or Fighting off evil.\n");
		return o;
	}
	
	void askForYesNo(Scanner input) {
		Program.out.printf("The film you provided is not recognized.\nWould you like to answer some questions about the film you provided?\n");
		Program.out.printf("1: yes\n2: no\n");
		String line = input.nextLine();
		Scanner intScanner = new Scanner(line);
		if(!intScanner.hasNext()) {
			throw new Error("\n\nYou decided to not answer some questions.\nProgram closed.\n");
		}
		else if(intScanner.nextInt() != 1) {
			throw new Error("\n\nYou decided to not answer some questions.\nProgram closed.\n");
		} else {
			Program.out.printf("Thank you for your cooperation.\nYou can leave questions unanswered.\n\n");
		}
	}
	
	void askFor(Scanner input, Object o, String attributeType, String question) {
		Program.out.printf(question);
		String line = input.nextLine();
		if(line.length()==0) {
			return;
		}
		o.add(new Attribute(attributeType,line));
	}
}
