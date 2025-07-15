package comsip.ams.Controllers;

import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import comsip.ams.Entities.Provider;
import comsip.ams.repositories.ProviderRepository;  // **Assure-toi que le package est correct**

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/providers")
@CrossOrigin("*")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @GetMapping
    @Operation(summary = "Récupération de tous les providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succès de get All Providers"),
            @ApiResponse(responseCode = "500", description = "Problème lors de la récupération")
    })
    public ResponseEntity<List<Provider>> getAllProviders() {
        List<Provider> providers = (List<Provider>) providerRepository.findAll();
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Ajout d'un nouvel provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Insertion avec succès"),
            @ApiResponse(responseCode = "500", description = "Problème lors de l'insertion")
    })
    public ResponseEntity<Provider> saveProvider(@RequestBody Provider provider) {
        Provider savedProvider = providerRepository.save(provider);
        return new ResponseEntity<>(savedProvider, HttpStatus.CREATED);
    }
    @GetMapping  ("/{id}")
    @Operation(summary = "Récupération d'un provider avec son id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Succès de get provider avec id"),
            @ApiResponse(responseCode = "500", description = "Problème lors de la récupération")
    })
    public ResponseEntity<Provider> getAProviderById(@PathVariable int id) {
       Optional<Provider>  opt= this.providerRepository.findById(id);
        if (opt.isEmpty())
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        else
        return new ResponseEntity<>( opt.get(),HttpStatus.FOUND);
    }
    @Operation(summary = "Suppression d'un provider par son id")
    @ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Si provider est trouvé puis supprimé"),
            @ApiResponse(responseCode = "404", description = "Provider inexistant") })
    @DeleteMapping ("/{id}")
    public ResponseEntity<Provider>deleteProviderById(@PathVariable int id){
        Optional <Provider>opt = this.providerRepository.findById(id);
        if (opt.isEmpty())
            return  ResponseEntity.notFound().build();
        else{
            this.providerRepository.deleteById( id);
            return ResponseEntity.noContent().build();
        }
    }
}
