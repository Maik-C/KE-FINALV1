package FilmAdvisor;

public class Object {
	private String name;
	private int year;
	private Attributes attributes;
	private int score;
	
	Object() {
		//empty object
	}
	
	Object(int i) {
		attributes = new Attributes(i);
	}
	
	Object(String s, int j, int size) {
		name = s;
		year = j;
		attributes = new Attributes(size);
		score = 0;
	}

	void setName(String n) {
		name = n;
	}
	
	void setYear(int i) {
		year = i;
	}
	
	String getName() {
		return name;
	}
	
	Integer getYear() {
		return year;
	}
	
	Integer getScore() {
		return score;
	}
	
	Attributes getAttributes() {
		return attributes;
	}
	
	void add(Attribute a) {
		attributes.add(a);
	}
	
	void addAttributes(Attributes a) {
		for(int i=0;i<a.getNrOfElements();i++) {
			add(a.getAttribute(i));
		}
	}
	
	boolean hasSameNameAndYear(Object o) {
		return (name.toLowerCase().equals(o.getName().toLowerCase()) & year == o.getYear());
	}

	boolean hasAttribute(Attribute a) {
		//contains this object the same attribute as attribute a?
		for(int i=0;i<attributes.getNrOfElements();i++) {
			Attribute b = attributes.getAttribute(i);
			if(b.hasEqualType(a) & b.hasEqualValue(a)) {
				return true;
			}
		}
		return false;
	}
	
	void increaseScore() {
		//increase the score of the movie
		score++;
	}

	String getAttributeValue(String t) {
		//return the attribute value of this object, if there is an attribute of type t
		for(int i=0;i<attributes.getNrOfElements();i++) {
			if(attributes.getAttribute(i).getType().equals(t)) {
				return attributes.getAttribute(i).getValue();
			}
		}
		return null;
	}
}
