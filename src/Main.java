import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main{


    public static void main(String[] args) throws IOException {

        Document d = Jsoup.connect("https://untappd.com/v/himmeriget/4320261").timeout(6000).get();
        String menuID = d.select("ul.menu-section-list").select("ul").attr("id");
        Elements ele = d.select("ul#" + menuID);

        for(Element element : ele.select("li").select("div.beer-details").select("h5")){

            String title = element.select("h5").text();
            String url = element.select("a").attr("href");
            System.out.println(title + ": " + "https://untappd.com" + url);

        }
    }
}