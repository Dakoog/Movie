package movie.movieRents;

import movie.dao.MovieDao;
import movie.entity.Movie;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RecordingOfMovie {
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
                     * 5 - zakończyć program""");
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


                   movieDao.saveAll(title, productionYear, durationVideo_min);

                }
                case 2 ->movieDao.selectAll();
                case 3 -> {
                    System.out.println("Podaj tytuł szukanego filmu");
                    String title = input.nextLine();
                   movieDao.findName("title", title);
                }
                case 4 -> {
                    System.out.println("Podaj tytuł szukanego filmu");
                    String phraseOfTitle = input.nextLine();
                   List<Movie> movies =movieDao.containingPhrase("title", phraseOfTitle);

                    System.out.println("\n * * * * title zawierający frazę " + phraseOfTitle + " * * * *");
                    if (movies.isEmpty()) {
                        System.out.println("Niestety, nie ma filmu zawierającego daną '" + phraseOfTitle + "' frazę.");
                    }
                    System.out.println(movies);
                    System.out.println();
                }
                case 5 -> exitOfMoviesSaving();
                default -> System.out.println("Zły wpis. Taka komenda nie istnieje.");
            }

        } while (options != 5);

    }

    public static void exitOfMoviesSaving() {
        System.out.println("The End");
    }
}
