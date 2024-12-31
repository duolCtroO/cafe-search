package oort.cloud.cafe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@ToString
@Table(name = "daily stat")
public class DailyStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "query")
    private String query;

    @Column(name = "eventDateTime")
    private LocalDateTime eventDateTime;

    public DailyStats(String query, LocalDateTime eventDateTime) {
        this.id = id;
        this.query = query;
        this.eventDateTime = eventDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyStats that = (DailyStats) o;
        return Objects.equals(id, that.id) && Objects.equals(query, that.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, query);
    }
}
