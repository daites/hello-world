package edu.pcc.cis.helloworld;

import java.util.Date;
import java.net.*;
import java.io.*;

/**
 *  This is a Hello World lab project from
 *  Portland Community College, CIS 233J
 *
 *  This started as a simple hello world, then I added a HttpURLConnection request
 *  because I wanted to see how that works.
 *
 *  The HelloWorld class says hi, then creates a new instance of HelloWorld.
 *
 *  Modifications:
 *  2018.2.4 Added HttpURLConnection code
 *  2018.2.5 Formatting, more comments
 *
 *  @author Dorian Aites and Alvin Alexander
 *  Using HttpURLConnection code from a tutorial written by Alvin Alexander
 *  found here: https://alvinalexander.com/blog/post/java/how-open-url-read-contents-httpurl-connection-java
 *  @version 2018.2.5
 */
public class HelloWorld {
    public static void main(String[] args) throws Exception {
        System.out.println("Howdy world!");
        System.out.println(new Date());
        System.out.println();
        System.out.println("Let's try connecting to something");
        System.out.println("---------------------------------");
        System.out.println();
        new HelloWorld();
    }

    /**
     * Declare a String url to connect to, try to connect to the url by calling connectToSomething.
     */
    private HelloWorld() {
        try {
            //Get the url to connect to
            String url = "http://w1.weather.gov/xml/current_obs/KPDX.xml";
            System.out.println("Connecting to " + url);
            System.out.println();
            System.out.println("Response:");
            System.out.println();
            String results = connectToSomething(url);
            System.out.println(results);
        }
        catch (Exception e) {
            //handled by controller
        }
    }

    /**
     * Creeate a HttpURLConnection, store the response received as a string, then print out.
     *
     * @param cURL the url to connect to
     * @return the response received back, as a string
     * @throws Exception
     */
    private String connectToSomething(String cURL) throws Exception {
        URL icURL;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            //transform input url (cURL) into a new URL object (icURL)
            icURL = new URL(cURL);

            //make a connection
            HttpURLConnection connection = (HttpURLConnection) icURL.openConnection();

            //set connection request method to GET
            connection.setRequestMethod("GET");

            //uncomment to write output to icURL
            //connection.setDoOutput(true)

            // give it 15 seconds to respond
            connection.setReadTimeout(15*1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            //read the response line by line, appending to stringBuilder
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        finally {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
