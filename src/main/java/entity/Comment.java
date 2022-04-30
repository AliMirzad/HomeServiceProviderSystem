package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double point;
    private String comment;
    @ManyToOne
    private ExpertUser expertUser;

    @Builder
    public Comment(Integer id, Double point, String comment, ExpertUser expertUser) {
        this.id = id;
        this.point = point;
        this.comment = comment;
        this.expertUser = expertUser;
    }
}
