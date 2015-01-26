package FilmAdvisor;

public class ObjectDatabase {
	//add movies to the list
	private Objects objects;
	
	ObjectDatabase(int size) {
		objects = new Objects(size);
	}
	
	int getNrOfElements() {
		return objects.getNrOfElements();
	}
	
	void add(Object o) {
		objects.add(o);
	}
	
	boolean exists(Object o) {
		for(int i=0; i<objects.getNrOfElements(); i++) {
			if(o.hasSameNameAndYear(objects.getObject(i))) {
				o.addAttributes(objects.getObject(i).getAttributes());
				return true;
			}
		}
		return false;
	}

	Object getObject(int i) {
		return objects.getObject(i);
	}
	
	Objects getObjects() {
		return objects;
	}
}
