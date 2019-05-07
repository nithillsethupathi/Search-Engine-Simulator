package HeapSort;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class that tests the program and its required functions
 */
public class main {
    public static void main(String[] args) {


        /**
         *Prints out 30 URLS sorted with their PageRank.
         */
        System.out.println("Please search: ");
        Scanner scan = new Scanner(System.in);                        //Scanner object for the user to insert the search item
        String keyword = scan.nextLine();
        WebCrawler crawl = new WebCrawler(keyword);                   //Initializing WebCrawler object to access the methods
        crawl.search();                                              //Crawls through the search method to get the links and the search details
        ArrayList<PageRank> rank = crawl.URL();                      //A PageRank ArrayList to store the URL that you get through crawling the URL method.
        MaxHeap heap = new MaxHeap(rank, rank.size(), 30);      //Initializing MaxHeap object
        heap.HeapSort();                                            //HeapSort is called to sort the URLS based on their PageRank.

        //Prints out the Sorted List in the descending order of the PageRank score
        int count = 1;
        for (int i = rank.size() - 1; i >= 0; i--) {
            System.out.println(count + ". " + rank.get(i).URL + " , PageRank Score: " + rank.get(i).getScore() + " [" + "Frequency: " + rank.get(i).getFrequency()
                    + " History: " + rank.get(i).getHistory() + " Linked: " + rank.get(i).getLinked() + " Money: " + rank.get(i).getMoney() + "]");
            count++;
        }


        /**
         *Prints out top 20 URLS using priority queue.
         */
        System.out.println("Top 20 websites using priority queue: ");
        heap.setHeapSize(20);                      //Sets the heap size to 20
        ArrayList<PageRank> priorityRank = new ArrayList<>();

        //Adds top 20 URLS out of 30
        for (int i = rank.size() - 1; i >= 10; i--) {
            priorityRank.add(rank.get(i));
        }

        heap.setHeap(priorityRank);               //Sets the heap with priority
        heap.BuildMaxHeap();                       //BuildMaxHeap method is called to build the MaxHeap for the list
        //Prints out the list
        int list = 1;
        for (int i = 0; i < priorityRank.size(); i++) {
            System.out.println(list + ". " + priorityRank.get(i).URL + " , PageRank Score: " + priorityRank.get(i).getScore() + " [" + "Frequency: " + priorityRank.get(i).getFrequency()
                    + " History: " + priorityRank.get(i).getHistory() + " Linked: " + priorityRank.get(i).getLinked() + " Money: " + priorityRank.get(i).getMoney() + "]");
            list++;
        }

        /**
         *Lets user insert the URL, and that URL will be added to the list.
         *That Updated List will be printed out.
         *This prints out the MaxHeap, and not the sorted list.
         */
        System.out.println("Please Insert the URL");
        Scanner insert = new Scanner(System.in);             //Scanner function for the user to insert the URL
        String type = insert.nextLine();
        PageRank URL = new PageRank(type);                   //PageRank object that will make the URL compatible for MaxHeapInsert
        heap.MaxHeapInsert(URL);                             //MaxHeapInsert method from MaxHeap.java that we use to let the user insert the URL they want to insert.
        //Prints out the list
        int list1 = 1;
        for (int i = 0; i < priorityRank.size(); i++) {
            System.out.println(list1 + ". " + priorityRank.get(i).URL + " , PageRank Score: " + priorityRank.get(i).getScore() + " [" + "Frequency: " + priorityRank.get(i).getFrequency()
                    + " History: " + priorityRank.get(i).getHistory() + " Linked: " + priorityRank.get(i).getLinked() + " Money: " + priorityRank.get(i).getMoney() + "]");
            list1++;
        }

        /**
         *Extract Max function
         *This function extracts the URL with the highest PageRank
         */
        PageRank extMax = heap.HeapExtractMax();        //PageRank object that will extract the URL with highest PageRank.
        //Prints out the URL
        System.out.println("URL with highest PageRank score is: " + extMax.URL + " , PageRank Score: " + extMax.getScore() + " [" + "Frequency: " + extMax.getFrequency()
                + " History: " + extMax.getHistory() + " Linked: " + extMax.getLinked() + " Money: " + extMax.getMoney() + "]");


        /**
         *Increasing the PageRank score of a URL using the HeapIncreaseKey method.
         *This prints out the MaxHeap, and not the sorted list
         */
        System.out.println("Enter the URL number from the list for which you want to increase the PageRank for: ");
        int x = insert.nextInt();               //Lets the user insert the list number assigned to the URL.
        PageRank url = priorityRank.get(x - 1);   //PageRank object get the URL based on the list number given by the user.
        System.out.println("Enter the PageRank score for " + url.URL + ":");
        int y = insert.nextInt();               //User enters the PageRank score for the URL
        url.setScore(y);                        //This function sets that score to the URL
        heap.HeapIncreaseKey(x - 1, url);    //Increases or decreases the URL position in the list based on the PageRank score
        //Prints out the list
        int list2 = 1;
        for (int i = 0; i < priorityRank.size(); i++) {
            System.out.println(list2 + ". " + priorityRank.get(i).URL + " , PageRank Score: " + priorityRank.get(i).getScore());
            list2++;
        }
    }

}
