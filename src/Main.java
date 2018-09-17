import java.io.IOException;

public class Main{


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("*** Welcome to What's on? pre-alpha version ***");

        //Write under here what bar you want to see: Brus, Dispensary, CafeLangeBro or Himmeriget
        main.getMenu("CafeLangeBro");

    }


    public void getMenu(String venue) throws IOException{
        Venues venues = new Venues();

        VenueMenu venueMenu = new VenueMenu(venues.venues.get(venue));
        System.out.println(venue);
        for(int i = 0; i<venueMenu.beerMenu.size();i++){

            System.out.println(venueMenu.beerMenu.get(i).getBeer() + " from " + venueMenu.beerMenu.get(i).getBrewery());
            System.out.println(venueMenu.beerMenu.get(i).getUrl());

        }


    }



}




//    VenueMenu himmeriget = new VenueMenu("https://untappd.com/v/himmeriget/4320261");