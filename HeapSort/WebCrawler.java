package HeapSort;

import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebCrawler class that crawls through the web and gets URLs for the user
 */

class WebCrawler {

    private String url;
    private String keyword;
    private HashSet<String> urls = new HashSet<String>();
    private static final String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    private Document htmlDocument;
    private static Pattern patternDomainName;
    private Matcher matcher;
    private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";

    static {
        patternDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    // Constructor for the HeapSort.WebCrawler object
    // It saved the keyword that user entered and put it into a google search link

    WebCrawler(String aKeyword) {
        keyword = aKeyword;
        url = "https://google.com/search?q=" + aKeyword + "&num=80";

    }

    // This method start the search
    public ArrayList<PageRank> search() {
        String currentUrl = url;
        crawl(currentUrl);
        boolean success = searchForWord(keyword);

        if (success) {
            System.out.println(String.format("**Success** Word %s found at %s", keyword, currentUrl));
        }

        System.out.println(String.format("**Done** Visited %s web page(s)", urls.size()));


        System.out.println(" \nHere are the first 30 URL linked: \n");
        return URL();
    }

    // The method will use pattern and matcher to extract the domain
    public String getDomainName(String url) {
        matcher = patternDomainName.matcher(url);
        if (url.startsWith("/url?q=https://")) {
            return url.substring(15, 42);
        }
        return url.substring(14, 42);
    }

    // get the set of urls result
    public HashSet<String> getUrls() {
        System.out.println(urls);
        return this.urls;

    }

    /**
     * PageRank URL method to get the HashSet URL and change it to ArrayList
     *
     * @return
     */
    public ArrayList<PageRank> URL() {
        ArrayList<PageRank> url = new ArrayList<>();
        Iterator iter = urls.iterator();      //An iterator that iterates through the HashSet urls
        //while loop is used to take the urls from HashSet and make a PageRank object for each of the urls
        while (iter.hasNext()) {
            String element = (String) iter.next();  //Gets the next url in the HashSet
            PageRank myURL = new PageRank(element); //Puts those urls into a PageRank object myURL
            url.add(myURL);                         //The object url is added to the PageRank ArrayList
            myURL.getScore();                       //Gets the PageRank score for the URL stored
        }
        return url;                                 //Returns the ArrayList of URLs
    }

    // This method will crawl the linked and put them in to a set to keep
    // Give it a URL and it makes an HTTP request for a web page
    public boolean crawl(String url) {
        try {

            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            final Document htmlDocument = connection.timeout(5000).get();
            this.htmlDocument = htmlDocument;

            if (connection.response().statusCode() == 200) {
                System.out.println("\n**Visiting** Received web page at " + url);
            }
            if (!connection.response().contentType().contains("text/html")) {
                System.out.println("**Failure** Retrieved something other than HTML");
                return false;
            }

            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") linked");

            for (Element link : linksOnPage) {
                String temp = link.attr("href");
                if (temp.startsWith("/url?q=https://www")) {
                    this.urls.add(getDomainName(temp));
                }
                //Gets only 30 URLS
                if (urls.size() > 29) {
                    break;
                }
            }
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }


    // This method will check if the website contains keyword
    public boolean searchForWord(String searchWord) {
        if (this.htmlDocument == null) {
            System.out.println("Error!");
            return false;
        }
        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

}
