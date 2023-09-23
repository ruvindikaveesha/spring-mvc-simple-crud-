package lk.ijse.gdse.assignment_2.api;

import lk.ijse.gdse.assignment_2.dto.ProjectDto;
import lk.ijse.gdse.assignment_2.service.ProjectService;
import lk.ijse.gdse.assignment_2.service.impl.ProjectServiceImpl;
import lk.ijse.gdse.assignment_2.util.ResponseUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ResponseUtil> saveProject(@RequestBody ProjectDto projectDto){
        ProjectDto add = projectService.add(projectDto);
        return new ResponseEntity<>((
                new ResponseUtil(201,"Project Saved",add)),
                HttpStatus.CREATED
        );
    }
    @PutMapping
    public ResponseEntity<ResponseUtil> updateProject(@RequestBody ProjectDto projectDto){
        ProjectDto update = projectService.update(projectDto);
        return new ResponseEntity<>((
                new ResponseUtil(200,"Project Updated",update)),
                HttpStatus.ACCEPTED
        );
    }
    @DeleteMapping
    public ResponseEntity<ResponseUtil> deleteProject(@RequestParam UUID id){
        boolean delete = projectService.delete(id);
        return new ResponseEntity<>((
                new ResponseUtil(204,"Project Deleted",delete)),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping
    public ResponseEntity<ResponseUtil> searchProject(@RequestParam UUID id){
        ProjectDto search = projectService.search(id);
        return new ResponseEntity<>((
                new ResponseUtil(200,"Project Searched",search)),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseUtil> getAllProjects(){
        List<ProjectDto> all = projectService.getAll();
        return new ResponseEntity<>((
                new ResponseUtil(200,"All Projects",all)),
                HttpStatus.OK
        );
    }

}
