package com.yahoo.dracarys.helpers;

import android.net.ParseException;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AmazonManager {


    public static void main(String args[]) throws  Exception{
        Map<String,String> bundle = new HashMap<String,String>();
        searchAmazon("9781783983285","","",bundle,false);
    }



	/**
	 * 
	 * This searches the amazon REST site based on a specific isbn. It proxies through lgsolutions.com.au
	 * due to amazon not support mobile devices
	 * 
	 * @param mIsbn The ISBN to search for
	 * @return The book array
	 */
	static public void searchAmazon(String mIsbn, String mAuthor, String mTitle, Map<String,String> bookData, boolean fetchThumbnail) {

		//replace spaces with %20
		mAuthor = mAuthor.replace(" ", "%20");
		//try {
		//	mAuthor = URLEncoder.encode(mAuthor, "utf-8");
		//} catch (UnsupportedEncodingException e1) {
		//	// Just use raw author...
		//}

		mTitle = mTitle.replace(" ", "%20");
		//try {
		//	mTitle = URLEncoder.encode(mTitle, "utf-8");
		//} catch (UnsupportedEncodingException e1) {
		//	// Just use raw title...
		//} 
		
		String path = "http://theagiledirector.com/getRest_v3.php";
		if (mIsbn.equals("")) {
			path += "?author=" + mAuthor + "&title=" + mTitle;
		} else {
			path += "?isbn=" + mIsbn;
		}
		URL url;
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		SearchAmazonHandler handler = new SearchAmazonHandler(bookData, fetchThumbnail);

		try {
			url = new URL(path);
			parser = factory.newSAXParser();
			// We can't Toast anything here, so let exceptions fall through.
            InputStream inputStream = getInputStream(url);
            String stream = getStringFromInputStream(inputStream);
            System.out.println(stream);
			parser.parse(inputStream, handler);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
            e.printStackTrace();
		} catch (ParseException e) {
            e.printStackTrace();
		} catch (SAXException e) {
            e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
		}
		return;
	}

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        return sb.toString();

    }


    /**
     * Utility routine to get the data from a URL. Makes sure timeout is set to avoid application
     * stalling.
     *
     * @param url		URL to retrieve
     * @return
     * @throws java.net.UnknownHostException
     */
    static public InputStream getInputStream(URL url) throws UnknownHostException {

        synchronized (url) {

            int retries = 3;
            while (true) {
                try {
                    java.net.URLConnection conn = url.openConnection();
                    conn.setConnectTimeout(30000);
                    return conn.getInputStream();
                } catch (java.net.UnknownHostException e) {
                    e.printStackTrace();
                    retries--;
                    if (retries-- == 0)
                        throw e;
                    try { Thread.sleep(500); } catch(Exception junk) {};
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
