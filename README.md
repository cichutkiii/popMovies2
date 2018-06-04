# Popular movies, stage 2

Todos:


- **WIP -** Implement sharing functionality to allow the user to share the first trailer’s YouTube URL from the movie details screen.
- **Todo -** use gradle.properties to hide API_KEY
- **Todo -** using placeholder() and error() methods with picasso -> https://www.simplifiedcoding.net/picasso-android-tutorial-picasso-image-loader-library/
- **Implemented -** When a user changes the sort criteria (most popular, highest rated, and favorites) the main view gets updated correctly.
- **Implemented -** In the movies detail screen, a user can tap a button(for example, a star) to mark it as a Favorite.
- **Implemented -** The titles and IDs of the user’s favorite movies are stored in a native SQLite database and are exposed via a ContentProvider. This ContentProvider is updated whenever the user favorites or unfavorites a movie. No other persistence libraries are used.
- **Implemented -** When the "favorites" setting option is selected, the main view displays the entire favorites collection based on movie ids stored in the ContentProvider.
- **Implemented -** Extend the favorites ContentProvider to store the movie poster, synopsis, user rating, and release date, and display them even when offline.
- **Implemented -** Movie Details layout contains a section for displaying trailer videos and user reviews.
- **Implemented -** When a trailer is selected, app uses an Intent to launch the trailer.
- **Implemented -** App requests for related videos for a selected movie via the /movie/{id}/videos endpoint in a background thread and displays those details when the user selects a movie.
- **Implemented -** App requests for user reviews for a selected movie via the /movie/{id}/reviews endpoint in a background thread and displays those details when the user selects a movie.
- **Implemented -** changing Serializable into parcelable
- **Implemented -** Change gridView to RecyclerView
- **Implemented -** POC, implementation Using Retrofit library
- **Implemented -** POC, implementation Using GSON library
- **Implemented -** When a movie poster thumbnail is selected, the movie details screen is launched.
- **Implemented -** In a background thread, app queries the /movie/popular or /movie/top_rated API for the sort criteria specified in the settings menu.
- **Implemented -** App is written solely in the Java Programming Language.
- **Implemented -** UI contains an element (e.g., a spinner or settings menu) to toggle the sort order of the movies by: most popular, highest rated.
- **Implemented -** Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails.
- **Implemented -** UI contains a screen for displaying the details for a selected movie.
- **Implemented -** Movie Details layout contains title, release date, movie poster, vote average, and plot synopsis.
