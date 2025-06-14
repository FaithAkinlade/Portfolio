package dp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.MovieDB;

class MovieTriviaTest {

	// instance of movie trivia object to test
	MovieTrivia mt;
	// instance of movieDB object
	MovieDB movieDB;

	@BeforeEach
	void setUp() throws Exception {
		// initialize movie trivia object
		mt = new MovieTrivia();

		// set up movie trivia object
		mt.setUp("moviedata.txt", "movieratings.csv");

		// get instance of movieDB object from movie trivia object
		movieDB = mt.movieDB;
	}

	@Test
	void testSetUp() {
		assertEquals(6, movieDB.getActorsInfo().size(),
				"actorsInfo should contain 6 actors after reading moviedata.txt.");
		assertEquals(7, movieDB.getMoviesInfo().size(),
				"moviesInfo should contain 7 movies after reading movieratings.csv.");

		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName(),
				"\"meryl streep\" should be the name of the first actor in actorsInfo.");
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size(),
				"The first actor listed in actorsInfo should have 3 movies in their moviesCasted list.");
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0),
				"\"doubt\" should be the name of the first movie in the moviesCasted list of the first actor listed in actorsInfo.");

		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName(),
				"\"doubt\" should be the name of the first movie in moviesInfo.");
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating(),
				"The critics rating for the first movie in moviesInfo is incorrect.");
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating(),
				"The audience rating for the first movie in moviesInfo is incorrect.");
	}

	@Test
	void testInsertActor() {

		// try to insert new actor with new movies
		mt.insertActor("test1", new String[] { "testmovie1", "testmovie2" }, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"After inserting an actor, the size of actorsInfo should have increased by 1.");
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(),
				"After inserting actor \"test1\", the name of the last actor in actorsInfo should be \"test1\".");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(),
				"Actor \"test1\" should have 2 movies in their moviesCasted list.");
		assertEquals("testmovie1",
				movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0),
				"\"testmovie1\" should be the first movie in test1's moviesCasted list.");

		// try to insert existing actor with new movies
		mt.insertActor("   Meryl STReep      ", new String[] { "   DOUBT      ", "     Something New     " },
				movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"Since \"meryl streep\" is already in actorsInfo, inserting \"   Meryl STReep      \" again should not increase the size of actorsInfo.");

		// look up and inspect movies for existing actor
		// note, this requires the use of properly implemented selectWhereActorIs method
		// you can comment out these two lines until you have a selectWhereActorIs
		// method
		//assertEquals(4, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
		//		"After inserting Meryl Streep again with 2 movies, only one of which is not on the list yet, the number of movies \"meryl streep\" appeared in should be 4.");
		assertTrue(mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).contains("something new"),
				"After inserting Meryl Streep again with a new Movie \"     Something New     \", \"somenthing new\" should appear as one of the movies she has appeared in.");

		// TODO add additional test case scenarios
    //try to insert existing actor with no new movies and different case letters.
		mt.insertActor(" Brad PITT      ", new String[] {}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"Since \"Brad PITT\" is already in actorsInfo, inserting \" Brad PITT      \" again should not increase the size of actorsInfo.");
		assertEquals(2, mt.selectWhereActorIs("brad pitt", movieDB.getActorsInfo()).size(),
				"After inserting Brad Pitt again with no movies, the number of movies \" Brad PITT      \" appeared in should be 2.");		
		mt.insertActor(" KARL      ", new String[] {"Karl 1", "Karl 2: Karl's Return"}, movieDB.getActorsInfo());
		assertEquals(8, movieDB.getActorsInfo().size(),
				"Since \"Karl\" is not in actorsInfo, inserting \" KARL      \" should increase the size of actorsInfo by 1.");
		assertEquals(2, mt.selectWhereActorIs("KARL", movieDB.getActorsInfo()).size(),
				"After inserting Karl, the number of movies \"KARL\" appeared in should be 2.");
	}

	@Test
	void testInsertRating() {

		// try to insert new ratings for new movie
		mt.insertRating("testmovie", new int[] { 79, 80 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"After inserting ratings for a movie that is not in moviesInfo yet, the size of moviesInfo should increase by 1.");
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName(),
				"After inserting a rating for \"testmovie\", the name of the last movie in moviessInfo should be \"testmovie\".");
		assertEquals(79, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating(),
				"The critics rating for \"testmovie\" is incorrect.");
		assertEquals(80, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating(),
				"The audience rating for \"testmovie\" is incorrect.");

		// try to insert new ratings for existing movie
		mt.insertRating("doubt", new int[] { 100, 100 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Since \"doubt\" is already in moviesInfo, inserting ratings for it should not increase the size of moviesInfo.");

		// look up and inspect movies based on newly inserted ratings
		// note, this requires the use of properly implemented selectWhereRatingIs
		// method
		// you can comment out these two lines until you have a selectWhereRatingIs
		// method
		assertEquals(1, mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).size(),
				"After inserting a critic rating of 100 for \"doubt\", there should be 1 movie in moviesInfo with a critic rating greater than 99.");
		assertTrue(mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).contains("doubt"),
				"After inserting the rating for \"doubt\", \"doubt\" should appear as a movie with critic rating greater than 99.");

		// TODO add additional test case scenarios
		mt.insertRating("Jersey Mikes", new int[] { 20, 22 }, movieDB.getMoviesInfo());
		assertEquals(9, movieDB.getMoviesInfo().size(),
				"Since \"Jersey Mikes\" is not in moviesInfo, inserting ratings for it should increase the size of moviesInfo by 1.");
		mt.insertRating("Jersey Mikes", new int[] { 101, 101 }, movieDB.getMoviesInfo());
    assertEquals(0, mt.selectWhereRatingIs('>', 100, true, movieDB.getMoviesInfo()).size(),
				"Since \"Jersey Mikes\" has ratings above 100, this method should do nothing.");
  }

	@Test
	void testSelectWhereActorIs() {
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"The number of movies \"meryl streep\" has appeared in should be 3.");
		assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0),
				"\"doubt\" should show up as first in the list of movies \"meryl streep\" has appeared in.");

		// TODO add additional test case scenarios
    assertEquals(2, mt.selectWhereActorIs("Brad Pitt", movieDB.getActorsInfo()).size(),
				"The number of movies \"Brad Pitt\" has appeared in should be 2.");
    assertEquals(true, mt.selectWhereActorIs("Tom Hanks", movieDB.getActorsInfo()).contains("the post"),
				"The list of movies for Tom Hanks should include \"The Post\".");
	}

	@Test
	void testSelectWhereMovieIs() {
		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" should be an actor who appeared in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" should be an actor who appeared in \"doubt\".");

		// TODO add additional test case scenarios
		assertEquals(1, mt.selectWhereMovieIs("catch me if you can", movieDB.getActorsInfo()).size(),
				"There should be 1 actors in \"catch me if you can\".");
    assertEquals(2, mt.selectWhereMovieIs("the post", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"the post\".");
	}

	@Test
	void testSelectWhereRatingIs() {
		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size(),
				"There should be 6 movies where critics rating is greater than 0.");
		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size(),
				"There should be no movie where audience rating is equal to 65.");
		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size(),
				"There should be 2 movies where critics rating is less than 30.");

		// TODO add additional test case scenarios
		assertEquals(0, mt.selectWhereRatingIs('*', 79, true, movieDB.getMoviesInfo()).size(),
				"* is not a valid operator so list should return empty.");
		assertEquals(0, mt.selectWhereRatingIs('>', 100, true, movieDB.getMoviesInfo()).size(),
				"There should be 0 movies where critics rating above 100."); 
	}

	@Test
	void testGetCoActors() {
		assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size(),
				"\"meryl streep\" should have 2 co-actors.");
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"),
				"\"tom hanks\" was a co-actor of \"meryl streep\".");
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" was a co-actor of \"meryl streep\".");

		// TODO add additional test case scenarios
		assertEquals(0, mt.getCoActors("brandon krakowsky", movieDB.getActorsInfo()).size(),
				"\"brandon krakowsky\" should have no co-actors.");
		assertEquals(0, mt.getCoActors("brad pitt", movieDB.getActorsInfo()).size(),
				"\"brad pitt\" should have no co-actors.");   
	}

	@Test
	void testGetCommonMovie() {
		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size(),
				"\"tom hanks\" and \"meryl streep\" should have 1 movie in common.");
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"),
				"\"the post\" should be a common movie between \"tom hanks\" and \"meryl streep\".");

		// TODO add additional test case scenarios
		assertEquals(1, mt.getCommonMovie("meryl streep", "amy adams", movieDB.getActorsInfo()).size(),
				"\"amy adams\" and \"meryl streep\" should have 1 movie in common.");
		assertEquals(0, mt.getCommonMovie("brad pitt", "robin williams", movieDB.getActorsInfo()).size(),
				"\"brad pitt\" and \"robin williams\" should have no movie in common.");  
	}

	@Test
	void testGoodMovies() {
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies, movies with both critics and audience rating that are greater than or equal to 85.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"),
				"\"jaws\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");

		// TODO add additional test case scenarios
    //Testing if the method if a new movie is inserted.
    mt.insertRating("Crazy Birds", new int[] {19, 20}, movieDB.getMoviesInfo());
		assertEquals(false, mt.goodMovies(movieDB.getMoviesInfo()).contains("crazy birds"),
				"\"Crazy Birds\" should now not be considered a good movie, since it's critics and audience ratings are below 85.");
		assertEquals(false, mt.goodMovies(movieDB.getMoviesInfo()).contains("popeye"),
				"\"Popeye\" should not be considered a good movie, since it's critics and audience ratings are not greater than or equal to 85.");
  }

	@Test
	void testGetCommonActors() {
		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in both \"doubt\" and \"the post\".");
		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"),
				"The actor that appeared in both \"doubt\" and \"the post\" should be \"meryl streep\".");

		// TODO add additional test case scenarios
		assertEquals(1, mt.getCommonActors("cast away", "the post", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in both \"cast away\" and \"the post\".");
		assertEquals(0, mt.getCommonActors("cast away", "fight club", movieDB.getActorsInfo()).size(),
				"There should be no actor that appeared in both \"seven\" and \"fight club\".");
	}

	@Test
	void testGetMean() {
		assertEquals(67.9, mt.getMean(movieDB.getMoviesInfo())[0], 0.1, "The mean of all critics ratings is incorrect.");

		// TODO add additional test case scenarios!
    //Testing that mean of audience rating in accurate.
    assertEquals(65.7, mt.getMean(movieDB.getMoviesInfo())[1], 0.1, "The mean of all audience ratings is accurate.");
    //Testing that mean would change if ratings changed.
    mt.insertRating("doubt", new int[] { 100, 100 }, movieDB.getMoviesInfo());
    assertEquals(68.8, mt.getMean(movieDB.getMoviesInfo())[1], 0.1, 
        "The mean of all audience ratings changed and is accurate.");
    assertEquals(70.8, mt.getMean(movieDB.getMoviesInfo())[0], 0.1, 
        "The mean of all critic ratings changed and is accurate.");

	}
}
