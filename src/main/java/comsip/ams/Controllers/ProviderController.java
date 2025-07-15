package comsip.ams.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
