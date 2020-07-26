# scrapert
A universal web content scraper to provide text data for ticker tape scrollers.

This is a content aggregator to supply input to a LED-matrix scrolling ticker tape hardware project I'm planning to build.
The code is provided as-is and without warranty and liability. The code, stack and architecture may change without notice as I go along
building the ticker tape project, so if you like stability, better fork it.
Also, the RSS and web content sources mentioned in the code are purely for demonstration purposes.
Please respect copyright and user agreements when accessing someone's content. Get permission before you start using it.

## running and hosting

This is a Spring Boot application with its own embedded Tomcat server, and it can be started by building the artifacts (mvn clean install),
fishing the executable JAR file out of the /target directory (scrapert-1.0.0.jar), and running it using: java -jar scrapert-1.0.0.jar

This manner of packaging the application, makes it very easy to host on AWS Elastic Beanstalk (Amamzon Web Services).
They have a 1 year free program and reasonable pricing afterwards, so that's my initial hosting set up.
But technically this is just a neat, self-contained Spring Boot app that will run just about anywhere and can be hosted on all
kinds of VMs or hardware servers, as long as there is JVM and an internet connection available.

## content

Currently the content is gathered by means of loading RSS feeds and by scraping a plain HTML page.
RSS feeds are processed by the Rome library through the FeedReader class, while HTML scraping is done by the TurboScraper class.

RSS sources can be set up in the beans.xml config file. The URL from where the RSS can be loaded, as well as the bean name can
both be set there.

Scraping sources also supply an array of String values (cuttingPoints) to indicate where the HTML source needs to be snipped in order
to get the values desired. The cuttingPoints always come in pairs (start and end), and the encapsulated values in between these
points will automatically become available in another array of Strings, by the TurboScraper.scrape() method.

As some sources detect automated loading of content, there may be a need to 'masquerade' as an ordinary web brower in order to
access the HTML. This can usually be done by adding some typical HTML request headers. This feature is not yet part of the
TurboScraper load() method, but can easily be added if loading content proves to be difficult. Other extensions are possible
as well, i.e. HTTPS connections using username/password or certificate access etc.
The code is simple enough to add your own functionality there.

## content updates

Originally I had planned to build in a buffer/cache for content to limit to number of RSS/HTML requests, but this kind of goes
against the whole idea of being updated regularly of the latest developments in the news. Provdied the refresh frequency of
a ticker tape app isn't too crazy high (~1/min or so), direct reloads actually are preferable.

This means that the request to the endpoints ('/mix' etc. - see the Controller) directly provoke a RSS/HTML reload.
This content is then smashed into a flat String and returned as the Response value.

## future additions

Unfortunately Twitter and Instagram are reducing their APIs to scripted access, in order to combat bots and hackers.
But it is technically possible to use libraries like Twitter4J to load Twitter posts and potentially include them in the aggergator as well.
Another interesting source is Google News. They have also reduced their RSS functionality, buit there may still be ways of accessing content.

It looks like the days of Web 2.0 are really over. This is why old fashioned HTML scraping is still relevant, because the technology
to do it properly (e.g. RSS and APIs) seems to have come and gone. It's not a technology issue, but a content ownership and
control issue.

There is still a Quartz dependency in the project that can be removed, as the backend updates are no longer part of the plan.
