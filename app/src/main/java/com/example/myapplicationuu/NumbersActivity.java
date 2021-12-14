package com.example.myapplicationuu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {
    private ListView BookListView;
    private  BookAdapter mAdapter;
    private static final String STATIC_BOOK_URL ="https://www.googleapis.com/books/v1/volumes?q=android&maxResults=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        BookListView = (ListView) findViewById(R.id.main);
        mAdapter = new BookAdapter(NumbersActivity.this, new ArrayList<Book>());
        BookListView.setAdapter(mAdapter);
        new BookTask().execute(STATIC_BOOK_URL);

    }




    public  class BookTask  extends AsyncTask<String, Void, List<Book>> {


        @Override
        protected List<Book> doInBackground(String... strings) {
            if (strings.length<1 ||strings[0] == null){
                return null;
            }
            List<Book> result = null;
            try {
                result = NetworkUtilies.fetchBookData(strings[0]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;

            }

        @Override
        protected void onPostExecute(List<Book> books) {
            mAdapter.clear();
            if (books!= null && !books.isEmpty()) {
                mAdapter.addAll(books);

            }
        }
    }
}

