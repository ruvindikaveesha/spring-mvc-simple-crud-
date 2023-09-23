package lk.ijse.gdse.assignment_2.dto;

import lk.ijse.gdse.assignment_2.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TLDto {
    private UUID tlId;

    private String tlName;

    private String tlEmail;

    private List<Project> projects;
}
