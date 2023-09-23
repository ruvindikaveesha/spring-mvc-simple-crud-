package lk.ijse.gdse.assignment_2.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TL {
    @Id
    private UUID tlId;

    private String tlName;

    private String tlEmail;

    @OneToMany(mappedBy = "tl",cascade = CascadeType.DETACH)
    private List<Project> projects;
}
