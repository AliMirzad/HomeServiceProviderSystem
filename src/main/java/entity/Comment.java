package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer point;
    private String comment;

    @Builder
    public Comment(Integer id, Integer point, String comment) {
        this.id = id;
        this.point = point;
        this.comment = comment;
    }
}
