package com.example.sunshine;

public class AndroidFlavor {
    String versionName;
    String versionNumber;
    int image;
    int song;// drawable reference id
    String description;
String high;
String  low;
String day;
int date;
int month;
int year;
int pic;

    public AndroidFlavor(String vName, String vNumber, int image,int son)
    {
        this.versionName = vName;
        this.versionNumber = vNumber;
        this.image = image;
        this.song=son;
    }



    public AndroidFlavor(String high, String low, String day, int date, int month, int year, String description, int pic) {
        this.high=high;
        this.pic=pic;
        this.low=low;
        this.image=pic;
        this.day=day;
        this.date=date;
        this.month=month;
        this.year=year;
        this.description=description;
    }
public int getPic(){
        return pic;
}

    public String getVersionName(){
        return day;
    }
    public String getVersionNumber(){
        return description;
    }
    public int  getSong(){
        return song;
    }
    public int getImage(){
        return image;
    }

    public String getHigh(){
        return high;
    }
    public String getLow(){
        return low;
    }

    public String getmax() {
        return high;
    }
    public String getmin(){
        return low;
    }
}