package dp;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	
	// TODO add additional methods as specified in the instructions PDF
	
	/**
	 * Inserts given actor and his/her movies into actor database.
	 * This method does not return anything.
	 * @param actor is the actor name as a string.
	 * @param movies is a String array of movie names that the actor has acted in.
	 * @param actorsInfo is the ArrayList that is to be inserted into/updated.
	*/
	public void insertActor (String actor, String [] movies, ArrayList <Actor> actorsInfo) {
		
		ArrayList<String> update_actors = new ArrayList<String>();//To store actor's names who need to be updated
		String a = actor.trim().toLowerCase(); //Storing actor in variable to make for easier use.
		
		//Adding new actor if actor is not in database.
		for (Actor Actor : actorsInfo) {
			//Creating a list of actors that just need movies to be updated.
			//Checking if the actor's name is already in list.
			if(a.equalsIgnoreCase(Actor.getName().trim().toLowerCase())) {
				update_actors.add(a);
			}
			else {;}
		}
		//Checking if current actor is already in database.
		if(update_actors.contains(a)) {
			//Iterating through each actor in actorsInfo then in update_actors to match their information.
			for(int j = 0; j < update_actors.size(); j++) {
				for(int i = 0; i < actorsInfo.size(); i++) {
					if((update_actors.get(j)).trim().equalsIgnoreCase(actorsInfo.get(i).getName().trim())) {
						//Iterating over each movie to add them to the actor's movie list in actorsInfo.
						for(int k = 0; k < movies.length; k++) {
							//checking if movie is already there.
							if (actorsInfo.get(i).getMoviesCast().contains(movies[k].trim()) == false) {
								//Adding new movies to the movies the actor was cast in.
								actorsInfo.get(i).getMoviesCast().add(movies[k]);
							}
							else {
								//Adding new movies to the movies the actor was cast in.
								;//actorsInfo.get(i).getMoviesCast().add(movies[k]);
							}
						}
					}else {;}
				}	
			}
		}
		//If actor is not already there, we will add the actor.
		else {
			//Adding the new actor
			actorsInfo.add(new Actor(actor));
			//Updating the new actor's movies.
			for(int j = 0; j < actorsInfo.size(); j++) {
				if((actorsInfo.get(j).getName().trim()).equalsIgnoreCase(a)) {
					for(int k = 0; k < movies.length; k++) {
						//Adding new movies to the movies the actor was cast in.
						actorsInfo.get(j).getMoviesCast().add(movies[k]);
							}
						}else {;}
					}
				}
	}	
	
	/**
	 * Inserts given ratings for given movie into movie database.
	 * This method does not return anything.
	 * @param movie is the movie name as a string
	 * @param ratings is an int array with 2 elements: the critics rating at index 0 and the audience rating at index 1
	 * @param The moviesInfo is the ArrayList that is to be inserted into/updated
	*/
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
	
		ArrayList<String> updated_movies = new ArrayList<String>();
		
		//Adding new movie if movie is not in database.
		for (Movie Movie : moviesInfo) {
			//Creating a list of know movie names.
			if(Movie.getName().trim().equalsIgnoreCase(movie)) {
				updated_movies.add(Movie.getName());
			}
		}
		
		
		//Checking if list has movie in it. Will add if it does not.
		if(updated_movies.contains(movie.trim())) {
			for(int i = 0; i < updated_movies.size(); i++) {
				//Updating ratings for each movie.
				if(updated_movies.get(i) == moviesInfo.get(i).getName()) {
					moviesInfo.get(i).setCriticRating(ratings[0]);
					moviesInfo.get(i).setAudienceRating(ratings[1]);
				}
			}
		}
		else {
			//Adding new movie with prescribed ratings.
			moviesInfo.add(new Movie(movie, ratings[0], ratings[1]));
		}
	}
	
	/**
	 * Given an actor, it returns the list of all movies they have acted in.
	 * @param actor string
	 * @param actorsInfo array list
	 * @returns list of movies that actor is in.
	*/
	public ArrayList<String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo) {
		//Defining an empty list for the movies.
		ArrayList<String> mov = new ArrayList<String>();
		//Iterating through to find in actor exists in database.
		for (Actor Actor : actorsInfo) {
			//Return value will be list of movies the actor is in if said actor exists.
			if(Actor.getName().trim().equalsIgnoreCase(actor.trim())) { //making sure to ignore case and whitespace.
				mov = Actor.getMoviesCast();
			}
			else {
			//The list is already empty and will stay and be returned that way.
				;
			}
		}
		return mov;
	}
	
	
	/**
	 * Given a movie, returns the list of all actors in that movie.
	 * @param movie string
	 * @param actorsInfo
	 * @returns list of all actors in that movie.
	*/
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo) {
		//Defining an empty list for actors.
		ArrayList<String> actors = new ArrayList<String>();
		//Iterating through to find in actor exists in database.
		for (Actor Actor : actorsInfo) {
			//Iterating through Actor's movie list to see if given movie is in it.
			for(String mov : Actor.getMoviesCast()) {
				if(mov.trim().equalsIgnoreCase(movie.trim())) { //making sure to ignore case and whitespace.
					//Actor is added to empty list if the given movie is in their list.
					actors.add(Actor.getName());
				}
				else {;}
			}
		}
		//Given, list is already empty when defined, it will return that way if condition is not met.
		return actors;
	}
	
	/**
	 * This method returns a list of movies that satisfy an inequality or equality, based on the
comparison argument and the targeted rating argument
	 * @param comparison is either ‘=’, ‘>’, or ‘< ‘ and is passed in as a char
	 * @param targetRating is an integer
	 * @param isCritic is a boolean that represents whether we are interested in the critics rating or the
audience rating. true = critic ratings, false = audience ratings.
	 * @param moviesInfo ArrayList
	 * @returns list of movies
	*/
	public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic, ArrayList <Movie> moviesInfo) {
		//Defining an empty list for actors.
		ArrayList<String> movies = new ArrayList<String>();
		
		//First check if inputs are valid.
		if ((comparison == '=' || comparison == '>' || comparison == '<') && (targetRating >= 0 && targetRating <= 100)) {
			//Next, check if ratings are critic ratings or audience ratings.
			if(isCritic == true) {
				//Next, check the comparison value and adding the movies whose rating match the value specified to the empty list.
				for(Movie mov : moviesInfo) {
					if(comparison == '=' && mov.getCriticRating() == targetRating) {
						movies.add(mov.getName());
					}
					else if(comparison == '>' && mov.getCriticRating() > targetRating) {
						movies.add(mov.getName());
					}
					else if(comparison == '<' && mov.getCriticRating() < targetRating) {
						movies.add(mov.getName());
					}
					else {
						;
					}
				}
			}
			else {
				//Repeating above steps if rating is Audience rating.
				for(Movie mov : moviesInfo) {
					if(comparison == '=' && mov.getAudienceRating() == targetRating) {
						movies.add(mov.getName());
					}
					else if(comparison == '>' && mov.getAudienceRating() > targetRating) {
						movies.add(mov.getName());
					}
					else if(comparison == '<' && mov.getAudienceRating() < targetRating) {
						movies.add(mov.getName());
					}
					else {
						;
					}
				}
			}
		}
		else {;}
		//List will remain unchanged and return empty if none of the conditions are not met.
		return movies;
	}
	
	/**
	 * Returns a list of all actors that the given actor has ever worked with in any movie.
	 * @param actor is the name of an actor as a String
	 * @param actorsInfo is the ArrayList to search through
	 * @returns list of actors
	*/
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo) {
		
    Set<String> costars_xduplicates = new LinkedHashSet<>(); 
		//Here, I am first iterating through the actor list to find if the actor given exists.
		for (Actor Actor : actorsInfo) {
			if(Actor.getName().trim().equalsIgnoreCase(actor)) {
				for (String movie : Actor.getMoviesCast()) {
					//If the actor exists, then I am iterating over the list of actors in each movie they've done.
					for(String actors : this.selectWhereMovieIs(movie, actorsInfo)) {
						//Checking to make sure we don't add the original actor but only the co-stars.
						if(Actor.getName().trim().equalsIgnoreCase(actors.trim())) {
							;
						}
						else {
							//Adding to a set to avoid duplicates.
							costars_xduplicates.add(actors);
						
						}
					}
				}
			}
			else {;}
		}
		ArrayList<String> costars = new ArrayList<String>(); //Creating empty list to store potential co-stars.
		costars.addAll(costars_xduplicates);
		return costars;
	}
	
	/**
	 * Returns a list of movie names where both actors were cast.
	 * @param actor1 is an actor name as a String
	 * @param actor2 is an actor name as a String
	 * @actorsInfo is the ArrayList to search through
	 * @returns list of movie names
	*/
	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor> actorsInfo) {
		ArrayList<String> movies = new ArrayList<String>(); //Creating empty list to store actors.
		
		//Iterating through the list of movies actor 1 has been in.
		for (String movie : this.selectWhereActorIs(actor1,actorsInfo)) {
			//Iterating through the list of movies actor 2 has been in.
			for (String movie1 : this.selectWhereActorIs(actor2,actorsInfo)) {
				//Checking for actors who are in both lists without considering whitespace and case.
				if(movie.trim().equalsIgnoreCase(movie1)) {
					movies.add(movie);
				}
			}
		}

		//List returns empty if nothing meets the given conditions.
		return movies;
	}
	
	/**
	 * Returns a list of movie names that both critics and the audience have rated above 85 (>= 85).
	 * @param moviesInfo list
	 * @returns a list of movie names.
	*/
	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
		ArrayList<String> movies = new ArrayList<String>(); //Creating empty list to store movies.
		
		//Iterating through list of movies
		for(Movie mov : moviesInfo) 
			//Adding movie name to empty list of it meets the conditions for the ratings
			if(mov.getCriticRating() >= 85 && mov.getAudienceRating() >= 85) {
				movies.add(mov.getName());
			}
			else {;
		}
		//List return empty if nothing meets the given conditions.
		return movies;
	}
	
	/**
	 * Given a pair of movies, this method returns a list of actors that acted in both movies.
	 * @param movie1 is a movie name as a String.
	 * @param movie2 is a movie name as a String.
	 * @param actorsInfo is the actor ArrayList.
	 * @returns a list of actors.
	*/
	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor> actorsInfo) {
		ArrayList<String> co_actors = new ArrayList<String>(); //Creating empty list to store actors.
		
		//Iterating through the list of actors in movie1.
		for (String actor : this.selectWhereMovieIs(movie1,actorsInfo)) {
			//Iterating through the list of actors in movie2.
			for (String actor1 : this.selectWhereMovieIs(movie2,actorsInfo)) {
				//Checking for actors who are in both lists without considering whitespace and case.
				if(actor.trim().equalsIgnoreCase(actor1)) {
					co_actors.add(actor);
				}
			}
		}

		//List returns empty if nothing meets the given conditions.
		return co_actors;
	}
	
	
	/**
	 * Given the moviesInfo DB, this static method returns the mean value of the critics ratings and the audience ratings.
	 * @returns mean values as a double array, where the 1st item (index 0) is the mean of all critics ratings and the 2nd 
	 * item (index 1) is the mean of all audience ratings.
	*/
	public static double [] getMean (ArrayList <Movie> moviesInfo) {
		double [] mean = {0.0, 0.0};
		double crs = 0.0; //critic rating sum
		double ars = 0.0; //audience rating sum
		
		for(Movie Movie : moviesInfo) {
			double dcr = Movie.getCriticRating(); //double critic rating
			double dar = Movie.getAudienceRating(); //double audience rating
			crs = crs + dcr;
			ars = ars + dar;
		}
		double mis = moviesInfo.size(); //movie info size casted to double
		
		//mean of all critic ratings at index 0.
		mean[0] = (crs/mis);
		//mean of all audience ratings at index 1.
		mean[1] = (ars/mis);
				
		return mean;
	}
}
