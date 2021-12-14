package com.example.myapplicationuu;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NetworkUtilies {
    private static final String STATIC_BOOK_URL ="https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";


    public static URL buildUrl(String requestUrl) {
       // Uri buildUri = Uri.parse(STATIC_BOOK_URL).buildUpon().build();
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream= null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(150000);
            urlConnection.setRequestMethod("GET");
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse =readFromStream(inputStream);

            }

        }catch (IOException e){
           e.printStackTrace();

        } finally{
            if (urlConnection==null){
                urlConnection.disconnect();
            }
            if (inputStream == null){
                inputStream.close();
            }

        }
        return jsonResponse;
    }


    private static String readFromStream (InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }


        return output.toString();

    }





    public static List<Book> getSimpleWeatherStringFromJson(String BookJsonStr) throws JSONException {

        if (TextUtils.isEmpty(BookJsonStr)) {
            return null;
        }
        List<Book> MyBooks = new ArrayList<>();

        JSONObject baseJsonResponse = new JSONObject(BookJsonStr);
        JSONArray jsonArray = baseJsonResponse.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currentBook = jsonArray.getJSONObject(i);
            JSONObject insideBook = currentBook.getJSONObject("volumeInfo");
            String title = insideBook.getString("title");
            String description = insideBook.getString("description");
            String publisher = insideBook.getString("publisher");
            String url = insideBook.getString("previewLink");
            String date = insideBook.getString("publishedDate");
            String language = insideBook.getString("language");
            String rating = insideBook.getString("maturityRating");
            Integer pageCount = insideBook.getInt("pageCount");


            Book ReadBook = new Book(publisher,pageCount,title,url,date,description,language,rating);
            MyBooks.add(ReadBook);


        }
        return MyBooks;
    }

    public static List<Book> fetchBookData(String requestUrl) throws JSONException {
        URL url = buildUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Book> earthquakes = getSimpleWeatherStringFromJson(jsonResponse);
        return earthquakes;

    }
}
