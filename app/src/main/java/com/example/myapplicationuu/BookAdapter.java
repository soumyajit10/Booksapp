package com.example.myapplicationuu;

import android.app.Activity;
import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {


    public BookAdapter(Activity context, ArrayList<Book> BooksInfo) {
        super(context,0,BooksInfo);
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book, parent, false);
        }

        Book currentEvent = (Book) getItem(position);


        TextView TitleView = (TextView) listItemView.findViewById(R.id.bookTitle);
        TitleView.setText( currentEvent.getTitle());

        TextView descriptionView = (TextView)listItemView.findViewById(R.id.description);
        descriptionView.setText(currentEvent.getDescription());

        TextView dateView = (TextView)listItemView.findViewById(R.id.publicationDate);
        dateView.setText("Date "+currentEvent.getPublishDate());

        TextView ratingView = (TextView)listItemView.findViewById(R.id.rating);
        ratingView.setText("Rating:" + currentEvent.getRating());

        TextView languageView = (TextView)listItemView.findViewById(R.id.languageBook);
        languageView.setText( "language: "+currentEvent.getLanguage());


        TextView PageCountView = (TextView)listItemView.findViewById(R.id.pageCount);
        PageCountView.setText( "MRP "+String.valueOf(currentEvent.getPageCount()));

        TextView PublicationView = (TextView)listItemView.findViewById(R.id.publicationName);
        PublicationView.setText(currentEvent.getPublication());

        TextView UrlView = (TextView)listItemView.findViewById(R.id.bookUrl);
        UrlView.setText("url  "+currentEvent.getUrl());




        return  listItemView;

    }
}
