import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;


public class VenueMenu {


public VenueMenu(String venue) throws IOException {

    //LETS SEE THE MAGIC

    System.setProperty("webdriver.gecko.driver", "lib/geckodriver-v0.23.0-win64/geckodriver.exe");

    WebDriver webDriver = new FirefoxDriver();
    webDriver.get(venue);

    JavascriptExecutor js = (JavascriptExecutor)webDriver;
    js.executeScript("scrollBy(0,1500)");

    try{

        webDriver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div/div[3]/div/div[2]/div[7]/div/div/a")).click();



    } catch (Exception e){
        System.out.println(e);
    }


    //MAGIC ENDS HERE



    try {
        //SQL
        AccessToDB accessToDB = new AccessToDB();
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/venuemenu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",accessToDB.getUserName(),accessToDB.getPassword());
        //SQL
        Statement myStmt = myConn.createStatement();


        Document d = Jsoup.connect(venue).timeout(6000).get();

        //Magic
//        File input = new File("test.html");
//        Document d = Jsoup.parse(input, "UTF-8");
        //Magic ends

        String menuID = d.select("ul.menu-section-list").select("ul").attr("id");
        String venueName = d.select("div.venue-name").select("h1").text();
        Elements ele = d.select("ul#" + menuID);




        for (Element element : ele.select("li").select("div.beer-details")) {

            String regex = "(?<tap>\\d*). (?<beer>\\d?.*[A-z].*)";
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
            brewery = brewery.replace("'","''");
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
