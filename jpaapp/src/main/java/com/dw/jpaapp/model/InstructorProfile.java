package com.dw.jpaapp.model;


import com.dw.jpaapp.dto.InstructorProfileDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "Instructor_Profile")
public class InstructorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bio",length =  3000) //length 는 글자수(바이트 수 아님)
    private String bio;

    @Column(name = "github_url")
    private String githubUrl;

    @OneToOne
    @JoinColumn(name = "instructor_id")// 단방향 참조
    private Instructor instructor;

    public InstructorProfileDTO toDTO() {
        return new InstructorProfileDTO(this.id, this.bio,
                this.githubUrl, this.instructor.getId());
    }
}

