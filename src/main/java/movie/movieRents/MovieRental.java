package movie.movieRents;

import movie.dao.MovieDao;
import movie.entity.Movie;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieRental {
    public static void main(String[] args) {

        MovieDao movieDao = new MovieDao();
        Scanner input = new Scanner(System.in);

        int options = 0;
        do {
            System.out.println("""
                    Podaj co chcesz zrobić:
                     * 1 - dodać film
                     * 2 - wyświetlić dostępne filmy
                     * 3 - wyszukać dany film po tytule
                     * 4 - wyszukać dany film po frazie zawartej w tytule
                     * 5 - wyszukać filmy powstałe w danym okresie czasu
                     * 6 - wyszukać filmy trwające mniej niż ... min
                     * 7 - wyszukać filmy trwające więcej niż ... min
                     * 8 - zakończyć program""");


            try {
                options = input.nextInt();

            } catch (InputMismatchException ime) {
                options = 0;

            }
            input.nextLine();

            switch (options) {
                case 1 -> {
                    System.out.println("Wpisz tytuł , rok produkcji, oraz czas trwania filmu (min) ");
                    String title = input.nextLine();
                    int productionYear = input.nextInt();
                    int durationVideo_min = input.nextInt();

                 movieDao.save(title, productionYear, durationVideo_min);


                }
                case 2 -> {
                    List<Movie> movies = movieDao.selectAll();
                    System.out.println("\n   * * * * * * * * * * * *  M O V I E * * * * * * * * * * * *\n");
                    System.out.println(movies);
                    System.out.println();
                }
                case 3 -> {
                    System.out.println("Podaj tytuł szukanego filmu");
                    String title = input.nextLine();
                    List<Movie> movies = movieDao.findName("title", title);
                    System.out.println("\n * * * * " + title.toUpperCase() + ": " + title.toLowerCase() + " * * * *");
                    if (movies.isEmpty()) {
                        System.out.println("Niestety, nie ma filmu o podanym ( " + title + " ) tytule.");
                    }
                    System.out.println(movies);
                    System.out.println();

                }
                case 4 -> {
                    System.out.println("Podaj frazę z tytułu szukanego filmu");
                    String phraseOfTitle = input.nextLine();
                    List<Movie> movies = movieDao.containingPhrase("title", phraseOfTitle);

                    System.out.println("\n * * * * title zawierający frazę " + phraseOfTitle + " * * * *");
                    if (movies.isEmpty()) {
                        System.out.println("Niestety, nie ma filmu zawierającego daną ( " + phraseOfTitle + " ) frazę.");
                    }
                    System.out.println(movies);
                    System.out.println();
                }
                case 5 -> {
                    System.out.println("Podaj interesujący Cię okres produkcji filmu ");
                    int afterYear = input.nextInt();
                    int beforeYear = input.nextInt();
                    List<Movie> movies = movieDao.yearsInBetween(afterYear, beforeYear);

                    System.out.println("\n * * * * " + "FILMY WYPRODUKOWANE POMIĘDZY " + afterYear + "  " + beforeYear + " * * * *");
                    System.out.println(movies);
                    System.out.println();
                }
                case 6 -> {
                    System.out.println("Podaj określ czas (w min) trwania filmu krótszy niż ...");
                    int shorterThan = input.nextInt();
                    List<Movie> movies = movieDao.movieDurationShorterThan(shorterThan);
                    System.out.println("\n * * * * " + "FILMY KTÓRYCH CZAS TRWANIA JEST KRÓTSZY NIŻ" + shorterThan + " * * * *");

                    System.out.println(movies);
                    System.out.println();
                }
                case 7 -> {
                    System.out.println("Podaj określ czas (w min) trwania filmu dłuższy niż ...");
                    int longerThan = input.nextInt();
                    List<Movie> movies = movieDao.movieDurationLongerThan(longerThan);
                    System.out.println("\n * * * * " + "FILMY KTÓRYCH CZAS TRWANIA JEST DŁUŻSZY NIŻ" + longerThan + " * * * *");
                    System.out.println("");
                    System.out.println(movies);
                    System.out.println();
                }
                case 8 -> exitOfMoviesSaving();
                default -> System.out.println("Zły wpis. Taka komenda nie istnieje.");
            }

        } while (options != 8);

    }

    public static void exitOfMoviesSaving() {
        System.out.println("The End");
    }
}
