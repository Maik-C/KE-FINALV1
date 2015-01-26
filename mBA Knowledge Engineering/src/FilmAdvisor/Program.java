package FilmAdvisor;

import java.io.*;
import java.util.Scanner;

public class Program {
	
	static PrintStream out = new PrintStream(System.out);

	int RETURN_NUMBER_OF_OBJECTS = 5;
	int NR_OF_ATTRIBUTES = 6;
	int NR_OF_OBJECTS = 46;

	Object getUserInputFilm(Scanner input) {
		//ask user for film
		out.printf("Please input a film and the year it came out.\nFormat: film;year\n");
		if(!input.hasNextLine()) {
			out.printf("\n");
			return new Object();
		}
		String line = input.nextLine();
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(";");
		if(!lineScanner.hasNext()) {
			throw new Error("\n\nA name and year of making ar needed to run the program.\n");
		}
		return new Object(lineScanner.next(),lineScanner.nextInt(),NR_OF_ATTRIBUTES);
	}
	
	Attributes generate(Object o) {
		return o.getAttributes();
	}
	
	Attributes specifyOne(Scanner input, Attributes a, AttributeDatabase adb) {
		//ask user for most important attribute
		Attributes resultaat = new Attributes(NR_OF_ATTRIBUTES);
		printList("What is the most important attribute of this film?",a);
		if(!input.hasNextLine()) {
			out.printf("\n");
			return new Attributes(NR_OF_ATTRIBUTES);
		}
		String line = input.nextLine();
		Scanner intScanner = new Scanner(line);
		intScanner.useDelimiter("");
		int index = intScanner.nextInt();
		resultaat.add(a.getAttribute(index));
		a.remove(index);
		adb.increaseScore(a.getAttribute(index).getType());
		return resultaat;
	}
	
	Objects cover(Attribute a, ObjectDatabase odb) {
		Objects resultaat = new Objects(odb.getNrOfElements());
		for(int i = 0;i<odb.getNrOfElements();i++) {
			Object object = odb.getObject(i);
			if(object.hasAttribute(a)) {
				object.increaseScore();
				resultaat.add(object);
			}
		}
		return resultaat;
	}
	
	int select(AttributeDatabase db, Attributes a) {
		//get the attribute number of the attributes in 'a' that was most chosen by the program users. 
		//from the AttributeDatabase.
		int max = -1;
		int index = -1;
		for(int i=0;i<a.getNrOfElements();i++) {
			if(max < db.select(a.getAttribute(i))) {
				max = db.select(a.getAttribute(i));
				index = i;
			}
		}
		return index;
	}
	
	int[] specifyTwo(Objects objects, Attribute a) {
		//which objects have the same attribute as Attribute a
		int[] resultaat = new int[objects.getNrOfElements()];
		for(int j=0;j<resultaat.length;j++) {
			resultaat[j] = -1;
		}
		int nrOfElements = 0;
		String type = a.getType();
		String value = a.getValue();
		Object o;
		for(int i=0;i<objects.getNrOfElements();i++) {
			o = objects.getObject(i);
			String tnew = o.getAttributeValue(type);
			if(tnew.equals(value)) {
				resultaat[nrOfElements] = i;
				nrOfElements++;
			}
		}
		return resultaat;
	}
	
	void obtain(Objects o, int[] observables) {
		for(int i=0;i<observables.length;i++) {
			if(observables[i]!=-1) {
				o.getObject(observables[i]).increaseScore();
			}
		}
	}
	
	boolean verify(Objects o, Attributes a, Attributes b_a) {
		//return true if all attributes have been used
		if(a.getNrOfElements() == 0) {
			return true;
		}
		int count = 0;
		for(int i=0;i<o.getNrOfElements();i++) {
			if(o.getObject(i).getScore() >= Math.ceil(b_a.getNrOfElements()/2.0)) {
				count++;
			}
		}
		//return true if there are less films with at least a 50% score then the return number of films
		return (count <= RETURN_NUMBER_OF_OBJECTS);
	}
	
	Objects match(Objects os, Object o) {
		return os.getHighestScores(o);
	}
	
	void start() {
		Scanner input = new Scanner(System.in);
		
		//open objectdatabase
		ObjectDatabase filmDatabase = new ReadDatabase().ReadObjectDatabase(NR_OF_OBJECTS, NR_OF_ATTRIBUTES);
		//open attributedatabase
		AttributeDatabase attributeDatabase = new AttributeDatabase(NR_OF_ATTRIBUTES);
		
		//ask user for movie	
		Object film = getUserInputFilm(input);
		//exit if movie not in database
		if(!filmDatabase.exists(film)) {
			film = new Questions().askQuestions(film, input);
		}
		//film attributes
		Attributes attributes = generate(film);
		//user chosen attribute
		Attributes b_attributes = specifyOne(input,attributes, attributeDatabase);
		//get films with attribute name+value
		Objects films = cover(b_attributes.getAttribute(0), filmDatabase); 
		
		boolean endloop = false;
		int index = -1;
		int[] observables;
		while(!endloop) {
			//return next best attribute
			index = select(attributeDatabase, attributes);
			//add attribute to used attributes
			b_attributes.add(attributes.getAttribute(index));
			//list of movie indexes with the selected attribute
			observables = specifyTwo(films,attributes.getAttribute(index));
			//remove attribute from list with available attributes
			attributes.remove(index);
			//update film score based on the observables
			obtain(films, observables);
			//end the loop?
			endloop = verify(films, attributes, b_attributes);
		}
		//get the films with the highest scores
		Objects filmlist = match(films, film);
		//print the recommended films list
		printList("Recommended films (score out of "+b_attributes.getNrOfElements()+"):",filmlist,true,RETURN_NUMBER_OF_OBJECTS);
	}

	void printList(String t, Objects os, boolean backwards, int nr) {
		out.printf(""+t+"\n");
		if(backwards) {
			Object o;
			int x = os.getNrOfElements();
			for(int i=0;i<Math.min(x,nr);i++) {
				o = os.getObject(x-1-i);
				out.printf(""+i+": "+o.getName()+", "+o.getYear()+" ("+o.getScore()+")\n");
			}
		} else {
			printList(os, nr);
		}
	}
	
	void printList(Objects os, int nr) {
		Object o;
		for(int i=0;i<Math.min(os.getNrOfElements(),nr);i++) {
			o = os.getObject(i);
			out.printf(""+i+": "+o.getName()+", "+o.getYear()+" ("+o.getScore()+")\n");
		}
	}
	
	void printList(String t, Attributes a) {
		out.printf(""+t+"\n");
		for(int i=0;i<a.getNrOfElements();i++) {
			out.printf(""+i+": "+a.getAttribute(i).getType()+", "+a.getAttribute(i).getValue()+"\n");
		}
	}

	public static void main(String[] args) {
		new Program().start();

	}

}
