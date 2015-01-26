package FilmAdvisor;

public class Objects {
	private Object[] objects;
	private int nrOfElements;
	
	Objects(int size) {
		objects = new Object[size];
		nrOfElements = 0;
	}
	
	int getNrOfElements() {
		return nrOfElements;
	}
	
	Object[] getObjects() {
		return objects;
	}
	
	Object getObject(int i) {
		return objects[i];
	}
	
	boolean isEmpty() {
		//are there no objects in the object list?
		return (nrOfElements==0);
	}
	
	boolean isFull() {
		//is the object list full?
		return (nrOfElements==objects.length);
	}
	
	void add(Object o) {
		//Add an element to the objects list
		objects[nrOfElements] = o;
		nrOfElements++;
	}

	void addInOrder(Object o) {
		//Add elements according to score. Low score to high score.
		if(isEmpty()) {
			add(o);
			return;
		}
		int score = o.getScore();
		for(int i=0;i<nrOfElements;i++) {
		 	if(score < objects[i].getScore()) {
		  		for(int j=nrOfElements;j>i;j--) {
		  			objects[j] = objects[j-1];
		  		}
		  		objects[i] = o;
		  		nrOfElements++;
		  		return;
		 	}
		}
		add(o);
	}
	
	Objects getHighestScores(Object o) {
		//Return the 'nr' highest scores.
		Objects resultaat = new Objects(nrOfElements);
		for(int i = 0;i<nrOfElements;i++) {
			if(!objects[i].hasSameNameAndYear(o)) {
				resultaat.addInOrder(objects[i]);
			}
		}
		return resultaat;
	}
}
