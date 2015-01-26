package FilmAdvisor;

public class Attribute {
	private String type;
	private String value;
	
	Attribute(String t, String v) {
		type = t;
		value = v;
	}
	
	String getType() {
		return type;
	}
	
	String getValue() {
		return value;
	}
	
	boolean hasEqualType(Attribute a) {
		return type.equals(a.getType());
	}
	
	boolean hasEqualValue(Attribute a) {
		return value.equals(a.getValue());
	}
}
