/**
 *  This is a Hello World lab project from
 *  Portland Community College, CIS 233J
 *
 *  The main method says hi, then creates a new instance of HelloWorld, which connects to a url
 *  and prints what it receives back. Right now it's pointing at NOAA weather xml feed for Portland Airport.
 *
 *  @author Dorian Aites and Alvin Alexander
 *  Using HttpURLConnection code from a tutorial written by Alvin Alexander
 *  found here: https://alvinalexander.com/blog/post/java/how-open-url-read-contents-httpurl-connection-java
 *
 *  @version 2018.2.5
 */

package edu.pcc.cis.helloworld;

import java.util.Date;
import java.net.*;
import java.io.*;

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

    public HelloWorld() {
        try {
            String url = "http://w1.weather.gov/xml/current_obs/KPDX.xml";
            System.out.println("Connecting to " + url);
            System.out.println();
            System.out.println("Response:");
            System.out.println();

            String results = connectToSomething(url);
            System.out.println(results);
        }
        catch (Exception e)
        {
            //handled by controller
        }
    }

    private String connectToSomething(String cURL) throws Exception {
        URL icURL = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try {
            icURL = new URL(cURL);
            HttpURLConnection connection = (HttpURLConnection) icURL.openConnection();
            connection.setRequestMethod("GET");

            //uncomment to write output to icURL
            //connection.setDoOutput(true)

            // give it 15 seconds to respond
            connection.setReadTimeout(15*1000);
            connection.connect();

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
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
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
