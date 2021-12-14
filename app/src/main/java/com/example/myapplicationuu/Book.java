package com.example.myapplicationuu;

public class Book {

    private  String mPublication;
    private Integer mPageCount;
    private String mTitle;
    private String mUrl;
    private  String mPublishDate;
    private String mDescription;
    private  String mLanguage;
    private  String mRating;



    public Book( String publicationName, int pageName, String title, String url ,String PublishDate ,String description, String language,String rating){
        mPublication = publicationName;
        mPageCount = pageName;
        mTitle= title;
        mUrl = url;
        mPublishDate = PublishDate;
        mDescription = description;
        mLanguage = language;
        mRating = rating;
    }


    public String getPublication(){
        return mPublication;
    }
    public Integer getPageCount(){ return mPageCount; }
    public String getTitle(){
        return mTitle;
    }
    public String getUrl(){ return mUrl;}
    public String getPublishDate(){ return mPublishDate;}
    public  String getDescription(){ return  mDescription;}
    public String getLanguage(){return  mLanguage;}
    public  String getRating(){return mRating;}


}

