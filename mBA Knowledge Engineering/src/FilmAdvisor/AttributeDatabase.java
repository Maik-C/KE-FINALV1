package FilmAdvisor;

public class AttributeDatabase {
	String[] attributeTypes;
	int[] times;
	int nrOfElements;
	
	AttributeDatabase(int size) {
		attributeTypes = new String[size];
		times = new int[size];
		nrOfElements = 0;
		init();		
	}
	
	void init() {
		add("Plot", 6);
		add("Theme", 5);
		add("Music", 4);
		add("Director", 3);
		add("Country", 2);
		add("Year", 1);
	}
	
	void add(String s, int i) {
		attributeTypes[nrOfElements] = s;
		times[nrOfElements] = i;
		nrOfElements++;
	}

	int select(Attribute a) {
		for(int i=0;i<nrOfElements;i++) {
			if(a.getType().equals(attributeTypes[i])) {
				return times[i];
			}
		}
		return 0;
	}
	
	void increaseScore(String t) {
		for(int i=0;i<nrOfElements;i++) {
			if(attributeTypes[i].equals("t")) {
				times[i]++;
				return;
			}
		}
	}
}
