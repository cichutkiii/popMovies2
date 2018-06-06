package pl.preclaw.popmovies.Utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReviewResults {



    private int id;
    private ArrayList<Reviews> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Reviews> getReviews() {
        return results;
    }

    public void setReviews(ArrayList<Reviews> results) {
        this.results = results;
    }

    public static class Reviews implements Parcelable {

        public Reviews(){
            super();
        }
        private String author;
        private String content;
        private String url;

        public Reviews(Parcel in) {
            author = in.readString();
            content = in.readString();
            url = in.readString();
        }

        public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
            @Override
            public Reviews createFromParcel(Parcel in) {
                return new Reviews(in);
            }

            @Override
            public Reviews[] newArray(int size) {
                return new Reviews[size];
            }
        };

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(author);
            dest.writeString(content);
            dest.writeString(url);
        }
    }
}
