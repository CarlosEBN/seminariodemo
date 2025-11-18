package co.edu.uniajc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "st_id")
    private long id;

    @Column(name = "st_name")
    private String name;

    @Column(name = "st_last_name")
    private String lastName;

    @Column(name = "st_age")
    private Integer age;

    @Column(name = "st_state")
    private Boolean state;
}
