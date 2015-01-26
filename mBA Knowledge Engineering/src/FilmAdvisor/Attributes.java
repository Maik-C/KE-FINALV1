package FilmAdvisor;

public class Attributes {
	private Attribute[] attributes;
	private int nrOfElements;
	
	Attributes(int size) {
		attributes = new Attribute[size];
		nrOfElements = 0;
	}
	
	int getNrOfElements() {
		return nrOfElements;
	}
	
	Attribute[] getAttributes() {
		return attributes;
	}
	
	void add(Attribute a) {
		attributes[nrOfElements] = a;
		nrOfElements++;
	}

	Attribute getAttribute(int i) {
		return attributes[i];
	}

	void remove(int attr) {
		//might have to change based on how the method Program.select(Attributes a) works
		for(int i = attr;i<nrOfElements-1;i++) {
			attributes[i] = attributes[i+1];
		}
		nrOfElements -= 1;
	}
}
