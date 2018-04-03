package pl.preclaw.popmovies.Utilities;

import java.util.List;

public class TrailersResults {


    private int id;
    private List<ResultsList> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsList> getResults() {
        return results;
    }

    public void setResults(List<ResultsList> results) {
        this.results = results;
    }

    public static class ResultsList {

        private String id;
        private String key;
        private String name;



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


    }
}
