import java.io.IOException;
import java.util.ArrayList;

public class Venues {

ArrayList<VenueMenu> venues = new ArrayList<>();

public Venues() throws IOException{


    VenueMenu Himmeriget = new VenueMenu("https://untappd.com/v/himmeriget/4320261");
    VenueMenu Brus = new VenueMenu("https://untappd.com/v/brus/4733071");
    VenueMenu Dispensary = new VenueMenu("https://untappd.com/v/dispensary-whiskey-beer/6363074");
    VenueMenu CafeLangeBro = new VenueMenu("https://untappd.com/v/cafe-langebro/29882");
    venues.add(Himmeriget);
    venues.add(Brus);
    venues.add(Dispensary);
    venues.add(CafeLangeBro);


}
}
