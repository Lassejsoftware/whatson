import java.io.IOException;
import java.util.Scanner;

public class Main{

    VenueLoader venueLoader = new VenueLoader();
    private int chosenVenue;


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        System.out.println("*** Welcome to What's on? pre-alpha version ***");

        main.commandLine();

    }


    public void commandLine() throws IOException {
        System.out.println("You can see what these bar, has on menu");
        System.out.println("Please enter the number in front of the bar you want to explore");
        venueLoader.getVenues();

        System.out.print("> ");

        Scanner sc = new Scanner(System.in);

        try {
            chosenVenue = Integer.parseInt(sc.next());

            VenueMenu venueMenu = new VenueMenu(venueLoader.venues.get(venueLoader.venueNumber.get(chosenVenue-1)));


        } catch (IndexOutOfBoundsException e){
            System.out.println("Nope there is only " + venueLoader.venues.size() + " options!");

        } catch (IllegalArgumentException e) {
            System.out.println("HEY!!! That was not a number?!");
        }


    }



}


