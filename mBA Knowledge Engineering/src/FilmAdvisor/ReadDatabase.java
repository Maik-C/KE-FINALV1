package FilmAdvisor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadDatabase {

    private static int NR_OF_FILMS = 46;
    
    ReadDatabase() {
    	
    }
    
    ObjectDatabase ReadObjectDatabase(int nrOfObjects, int nrOfAttributes) {
    	ObjectDatabase resultaat = new ObjectDatabase(nrOfObjects);
	    try {
	        FileReader fileReader = new FileReader(new File("C:\\Users\\Maik\\IdeaProjects\\KE-Demo\\mBA Knowledge Engineering\\src\\FilmAdvisor\\filmDB.txt"));
	        BufferedReader br = new BufferedReader(fileReader);
	        Object newObject;
	        String line = "";
	        for(int i = 0; i < Math.min(NR_OF_FILMS,nrOfObjects); i++)
	        {
	        	newObject = new Object(nrOfAttributes);
	            //the following is not pretty but there is no efficient way of turning this into a loop.
	            //read data per line
	            //assumes text file list n types of info:
	            //1 Film name
	            //2 Location
	            //3 Music
	            //4 Theme
	            //5 plot
	            //6 Director
	            //7 year

	            //reading name
	            newObject.setName(br.readLine());
	            //reading location
	            newObject.add(new Attribute("Country",br.readLine().toLowerCase()));
	            //reading Music
	            newObject.add(new Attribute("Music",br.readLine().toLowerCase()));
	            //reading Theme
	            newObject.add(new Attribute("Theme",br.readLine().toLowerCase()));
	            //Reading Plot
	            newObject.add(new Attribute("Plot",br.readLine().toLowerCase()));
	            //Reading Director
	            newObject.add(new Attribute("Director",br.readLine().toLowerCase()));
	            //reading Year
	            line = br.readLine();
	            newObject.add(new Attribute("Year",line));
	            newObject.setYear(Integer.parseInt(line));
	            
	            //add to database
	            resultaat.add(newObject);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return resultaat;
    }
}
