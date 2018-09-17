import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class VenueMenu {

    ArrayList<Beer> beerMenu = new ArrayList<>();


public VenueMenu(String venue) throws IOException{

    Document d = Jsoup.connect(venue).timeout(6000).get();
    String menuID = d.select("ul.menu-section-list").select("ul").attr("id");
    Elements ele = d.select("ul#" + menuID);


    for(Element element : ele.select("li").select("div.beer-details")){

        String beer = element.select("h5").select("a").text();
        String brewery = element.select("h6").select("span").select("a").text();
        String url = "https://untappd.com" + element.select("a").attr("href");
        Beer newBeer = new Beer(beer,brewery,url);
        beerMenu.add(newBeer);

    }
}

}
