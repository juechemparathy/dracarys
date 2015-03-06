package com.yahoo.dracarys.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class AmazonFetcher {

	static public Map<String,String> searchAmazon(String mIsbn, String mAuthor, String mTitle) {

		String path = "http://theagiledirector.com/getRest_v3.php";
		if (mIsbn.equals("")) {
			path += "?author=" + mAuthor + "&title=" + mTitle;
		} else {
			path += "?isbn=" + mIsbn;
		}
		URL url;
        String stream=null;
		try {
			url = new URL(path);
            InputStream inputStream = getInputStream(url);
             stream = getStringFromInputStream(inputStream);
//            System.out.println(stream);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
		}
		return parseXMLInput(stream) ;
	}

    public static Map<String,String>  parseXMLInput(String  stream){
        Map<String,String> result= new HashMap<String,String>();
        String ean="";
        String title="";
        String author="";
        String smallImageUrl="";
        if(stream.indexOf("<EAN>")!=-1) {
             ean = stream.substring(stream.indexOf("<EAN>") + 5, stream.indexOf("</EAN>"));
        }
        if(stream.indexOf("<Title>")!=-1) {
            title = stream.substring(stream.indexOf("<Title>") + 7, stream.indexOf("</Title>"));
        }
        if(stream.indexOf("<Author>")!=-1) {
            author = stream.substring(stream.indexOf("<Author>") + 8, stream.indexOf("</Author>"));
        }
//        String content = stream.substring(stream.indexOf("<Content>")+9, stream.indexOf("</Content>"));
        int smallImageString = stream.indexOf("<MediumImage>");
        if(smallImageString>0) {
            smallImageUrl = stream.substring(stream.indexOf("<URL>", smallImageString) + 5, stream.indexOf("</URL>", smallImageString));
        }
        result.put("ean",ean);
        result.put("title",title);
        result.put("author",author);
//        result.put("content",content);
        result.put("smallimageurl",smallImageUrl);
        return result;
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
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    static public InputStream getInputStream(URL url) throws UnknownHostException {

        synchronized (url) {

            int retries = 3;
            while (true) {
                try {
                    java.net.URLConnection conn = url.openConnection();
                    conn.setConnectTimeout(30000);
                    return conn.getInputStream();
                } catch (UnknownHostException e) {
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
