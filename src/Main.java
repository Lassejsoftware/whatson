import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main{


    public static void main(String[] args) throws IOException {
        Document d = Jsoup.connect("https://untappd.com/v/himmeriget/4320261").timeout(6000).get();
        Elements ele = d.select("ul#section-menu-list-62571183");

        for(Element element : ele.select("li").select("div.beer-details").select("h5")){

            String title = element.select("h5").text();

            String url = element.select("a").attr("href");
            System.out.println(title + ": " + "https://untappd.com" + url);

        }


//
//     Document d = Jsoup.connect("https://untappd.com/v/brus/4733071").timeout(6000).get();
//     Elements ele = d.select("ul#section-menu-list-62270396");
//
//     for(Element element : ele.select("li").select("div.beer-details").select("h5")){
//
//         String title = element.select("h5").text();
//
//             String url = element.select("a").attr("href");
//             System.out.println(title + ": " + "https://untappd.com" + url);
//
//        }
    }
}