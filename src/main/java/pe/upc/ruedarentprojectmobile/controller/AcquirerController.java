package pe.upc.ruedarentprojectmobile.controller;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.ruedarentprojectmobile.model.Acquirer;
import pe.upc.ruedarentprojectmobile.response.ApiResponse;
import pe.upc.ruedarentprojectmobile.service.Acquirer.IAcquirerService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/Acquirer")
public class AcquirerController {
    private final IAcquirerService acquirerService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAcquirers(){
        try {
            List<Acquirer> acquirers = acquirerService.getAllAcquirers();
            return ResponseEntity.ok(new ApiResponse("Found", acquirers));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/id/{acquirerId}/acquirer")
    public ResponseEntity<ApiResponse> getAcquirer(@PathVariable Long acquirerId){
        try {
            Acquirer acquirer = acquirerService.getAcquirer(acquirerId);
            return ResponseEntity.ok(new ApiResponse("Found", acquirer));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/dni/{dni}/acquirer")
    public ResponseEntity<ApiResponse> getAcquirerByDni(@PathVariable String dni){
        try {
            Acquirer acquirer = acquirerService.getAcquirerByDni(dni);
            return ResponseEntity.ok(new ApiResponse("Found", acquirer));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @DeleteMapping("/delete/{acquirerId}/acquirer")
    public ResponseEntity<ApiResponse> deleteAcquirer(@PathVariable Long acquirerId){
        try {
            acquirerService.deleteAcquirer(acquirerId);
            return ResponseEntity.ok(new ApiResponse("Deleted", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PutMapping("/update/{acquirerId}/acquirer")
    public ResponseEntity<ApiResponse> updateAcquirer(@PathVariable Long acquirerId,@RequestBody Acquirer acquirer){
        try {
            Acquirer updatedAcquirer = acquirerService.updateAcquirer(acquirer, acquirerId);
            return ResponseEntity.ok(new ApiResponse("Updated", updatedAcquirer));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAcquirer(@RequestBody Acquirer acquirer){
        try {
            Acquirer newAcquirer = acquirerService.addAcquirer(acquirer);
            return ResponseEntity.ok(new ApiResponse("Added", newAcquirer));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("error", INTERNAL_SERVER_ERROR));
        }
    }

}
