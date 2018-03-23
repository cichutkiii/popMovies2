# Popular movies, stage 2

Todos:

- <span style="color:red">**Todo -** </span>Change gridView to RecyclerView
- <span style="color:red">**Todo -** </span>POC, implementation and use of BottomNavigationBar
- <span style="color:red">**Todo -** </span> POC, implementation Using Retrofit library
- <span style="color:red">**Todo -** </span> POC, implementation Using GSON library
- <span style="color:red">**Todo -** </span>use gradle.properties to hide API_KEY
- <span style="color:red">**Todo -** </span> using placeholder() and error() methods with picasso -> https://www.simplifiedcoding.net/picasso-android-tutorial-picasso-image-loader-library/
- <span style="color:red">**Todo -** </span> changing Serializable into parcelable
- <span style="color:green">**Done -**</span> App is written solely in the Java Programming Language.
- <span style="color:green">**Done -**</span>UI contains an element (e.g., a spinner or settings menu) to toggle the sort order of the movies by: most popular, highest rated.
- <span style="color:green">**Done -**</span>Movies are displayed in the main layout via a grid of their corresponding movie poster thumbnails.
- <span style="color:green">**Done -**</span>UI contains a screen for displaying the details for a selected movie.
- <span style="color:green">**Done -**</span>Movie Details layout contains title, release date, movie poster, vote average, and plot synopsis.
- <span style="color:red">**Todo -** </span> Movie Details layout contains a section for displaying trailer videos and user reviews.
- <span style="color:red">**Todo -** </span>When a user changes the sort criteria (most popular, highest rated, and favorites) the main view gets updated correctly.
- <span style="color:red">**Todo -** </span>When a movie poster thumbnail is selected, the movie details screen is launched.
- <span style="color:red">**Todo -** </span>When a trailer is selected, app uses an Intent to launch the trailer.
- <span style="color:red">**Todo -** </span>In the movies detail screen, a user can tap a button(for example, a star) to mark it as a Favorite.
- <span style="color:green">**Done -**</span>In a background thread, app queries the /movie/popular or /movie/top_rated API for the sort criteria specified in the settings menu.
- <span style="color:red">**Todo -** </span> App requests for related videos for a selected movie via the /movie/{id}/videos endpoint in a background thread and displays those details when the user selects a movie.
- <span style="color:red">**Todo -** </span>App requests for user reviews for a selected movie via the /movie/{id}/reviews endpoint in a background thread and displays those details when the user selects a movie.
- <span style="color:red">**Todo -** </span>The titles and IDs of the user’s favorite movies are stored in a native SQLite database and are exposed via a ContentProvider. This ContentProvider is updated whenever the user favorites or unfavorites a movie. No other persistence libraries are used.
- <span style="color:red">**Todo -** </span>When the "favorites" setting option is selected, the main view displays the entire favorites collection based on movie ids stored in the ContentProvider.
- <span style="color:red">**Todo -** </span> Extend the favorites ContentProvider to store the movie poster, synopsis, user rating, and release date, and display them even when offline.
- <span style="color:red">**Todo -** </span>Implement sharing functionality to allow the user to share the first trailer’s YouTube URL from the movie details screen.
