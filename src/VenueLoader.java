import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VenueLoader {

HashMap<String,String> venues = new HashMap<>();
ArrayList<String> venueNumber = new ArrayList<>();

public VenueLoader(){


    try (BufferedReader br = new BufferedReader(new FileReader("resources/venues.txt"))) {
        String line;

        while ((line = br.readLine()) != null) {

            String regex = "(?<barName>[A-z]*\\d*),(?<url>(?:http|https)(?::\\/{2}[\\w]+)(?:[\\/|\\.]?)(?:[^\\s\\\"]*))";
            String string = line;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                for (int i = 1; i == 1 ; i++) {
                    String barName = matcher.group("barName");
                    String url = matcher.group("url");
                    venues.put(barName, url);

                }
            }

        }
    } catch (FileNotFoundException e) {
        System.out.println("Hmmm, something went wrong? \nAre you sure, that you have the folder resources, with the file \"venues.txt\" in it?");
    } catch (IOException e) {
        System.out.println("Hmmm, something went wrong?");
    }

}


public void getVenues(){

    int i = 1;


    for(String key : venues.keySet()){

        System.out.println(i++ + ". " + key);
        venueNumber.add(key);
    }
}








}
