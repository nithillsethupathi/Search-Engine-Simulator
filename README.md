<h1>Search-Engine-Simulator</h1>
<h3>Design and Implementation</h3>
Google Search Engine Simulator was implemented using HeapSort and priority queue. I used four classes to implement the Google Search Engine Simulator.

<h5>List of classes and its functions:</h5>
<ol>
<li>First class being MaxHeap, which implements all the HeapSort methods, and Heap Priority Queue methods.</li>
<li>Second class is WebCrawler, given by the professor himself. Implemented a new method called URL() in that class. URL() method gets the HashSet URLs and puts it all in a PageRank ArrayList. An ArrayList is used to manipulate the URL into a list with its score.</li>
<li>Third class is PageRank, which gives a score to all the URLs based on frequency, money, history and links. The values for all four are randomly generated and it is totalled as one score. The total sum will not exceed 100.</li>
<li>Fourth class is main, which is used to test and print out the programming requirements.</li>
<ol>
