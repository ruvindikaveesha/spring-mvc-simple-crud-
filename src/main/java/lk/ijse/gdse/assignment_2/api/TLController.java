package lk.ijse.gdse.assignment_2.api;

import lk.ijse.gdse.assignment_2.dto.TLDto;
import lk.ijse.gdse.assignment_2.service.impl.TLServiceImpl;
import lk.ijse.gdse.assignment_2.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/tl")
public class TLController {

    private final TLServiceImpl tlService;

    @Autowired
    public TLController(TLServiceImpl tlService) {
        this.tlService = tlService;
    }

    @PostMapping
    public ResponseEntity<ResponseUtil> saveTL(@RequestBody TLDto tlDto){
        TLDto add = tlService.add(tlDto);
        return new ResponseEntity<>((
                new ResponseUtil(201,"TL Saved",add)),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ResponseUtil> searchTL(@RequestParam UUID id){
        TLDto search = tlService.search(id);
        return new ResponseEntity<>((
                new ResponseUtil(200,"TL Searched",search)),
                HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<ResponseUtil> updateTL(@RequestBody TLDto tlDto){
        TLDto update = tlService.update(tlDto);
        return new ResponseEntity<>((
                new ResponseUtil(200,"TL Updated",update)),
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping
    public ResponseEntity<ResponseUtil> deleteTL(@RequestParam UUID id){
        boolean delete = tlService.delete(id);
        return new ResponseEntity<>((
                new ResponseUtil(204,"TL Deleted",delete)),
                HttpStatus.NO_CONTENT
        );
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseUtil> getAllTL(){
        List<TLDto> all = tlService.getAll();
        return new ResponseEntity<>((
                new ResponseUtil(200,"All TLs",all)),
                HttpStatus.OK
        );
    }

}
