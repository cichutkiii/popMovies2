package pl.preclaw.popmovies.Utilities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class TrailersResults {


    private int id;
    private ArrayList<ResultsList> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ResultsList> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsList> results) {
        this.results = results;
    }

    public static class ResultsList implements Parcelable {

        private String id;
        private String key;
        private String name;


        protected ResultsList(Parcel in) {
            id = in.readString();
            key = in.readString();
            name = in.readString();
        }

        public static final Creator<ResultsList> CREATOR = new Creator<ResultsList>() {
            @Override
            public ResultsList createFromParcel(Parcel in) {
                return new ResultsList(in);
            }

            @Override
            public ResultsList[] newArray(int size) {
                return new ResultsList[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public String getYoutubeImageUrl() {

            return ParsingUtilities.buildYoutubeUrl(key);
        }
        public String getKey() {

            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(key);
            dest.writeString(name);
        }
    }
}
