package movie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name="movie")
@Setter
@Getter

public class Movie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)// strategia generowania (Auto-Increment)
    private long id;
    @Column (length = 100,nullable = false)
    private String title;
    @Column (name="production_year",length = 4)
    private int productionYear;
    @Column (name="duration_video",length = 4)
    private int durationVideo_min;

    @Override
    public String toString() {

        return  ("* Film |"+title + "| * rok produkcji | " + productionYear +
                " | * czas trwania | " + durationVideo_min + " min.|\n");
    }

    public Movie(String title, int productionYear, int durationVideo) {
        this.title = title;
        this.productionYear = productionYear;
        this.durationVideo_min = durationVideo;


    }
}
