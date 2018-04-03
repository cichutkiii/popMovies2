package pl.preclaw.popmovies.Utilities;

import java.util.List;

public class ReviewResults {



    private int id;
    private List<Reviews> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reviews> getReviews() {
        return results;
    }

    public void setReviews(List<Reviews> results) {
        this.results = results;
    }

    public static class Reviews {


        private String author;
        private String content;
        private String url;

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
    }
}
