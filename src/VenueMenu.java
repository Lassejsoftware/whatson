import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.*;


public class VenueMenu {


public VenueMenu(String venue) throws IOException{



    try {
        //SQL
        AccessToDB accessToDB = new AccessToDB();
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/venuemenu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",accessToDB.getUserName(),accessToDB.getPassword());
        //SQL
        Statement myStmt = myConn.createStatement();


        Document d = Jsoup.connect(venue).timeout(6000).get();
        String menuID = d.select("ul.menu-section-list").select("ul").attr("id");
        String venueName = d.select("div.venue-name").select("h1").text();
        Elements ele = d.select("ul#" + menuID);



        for (Element element : ele.select("li").select("div.beer-details")) {

            String regex = "(?<tap>\\d*). (?<beer>[A-z].*)";
            String beerPlusTap = element.select("h5").select("a").text();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(beerPlusTap);
            String beer = null;
            String tap = null;

            while (matcher.find()) {
                for (int i = 1; i == 1; i++) {
                    tap = matcher.group("tap");
                    beer = matcher.group("beer");
                    beer = beer.replace("'","''");
                }
            }

            String type = element.select("h5").select("em").text();
            String brewery = element.select("h6").select("span").select("a").text();
            String url = "https://untappd.com" + element.select("a").attr("href");

            String sql = "insert into venuemenu "
                    + " (venue, tap, beer, typ, brewery, url)"
                    + " values ('" + venueName + "','" + tap + "','" + beer + "','" + type + "','" + brewery + "','" + url +"')";

            myStmt.executeUpdate(sql);

            System.out.println("Transfer to DB complete");

        }
    }
    catch (Exception exc){
        exc.printStackTrace();
    }


}
}
