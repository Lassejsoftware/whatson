import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class VenueMenu {

    String barName;
    String beer;
    String brewery;
    String url;


public VenueMenu(String venue) throws IOException{

    Document d = Jsoup.connect(venue).timeout(6000).get();
    barName = d.select("div.venue-name").select("h1").text();
    String menuID = d.select("ul.menu-section-list").select("ul").attr("id");
    Elements ele = d.select("ul#" + menuID);

    System.out.println(barName);
    for(Element element : ele.select("li").select("div.beer-details")){

        beer = element.select("h5").select("a").text();
        brewery = element.select("h6").select("span").select("a").text();
        url = element.select("a").attr("href");
        System.out.println(beer + " from " + brewery + ": \n" + "https://untappd.com" + url);

    }
}

    public String getBarName() {
        return barName;
    }

    public String getBeer() {
        return beer;
    }

    public String getUrl() {
        return url;
    }
}
