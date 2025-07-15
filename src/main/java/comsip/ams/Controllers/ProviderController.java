package comsip.ams.Controllers;

import comsip.ams.Entities.Provider;
import comsip.ams.Services.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/providers")
@CrossOrigin("*")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    // ✔️ Get All
    @GetMapping
    @Operation(summary = "Récupération de tous les providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succès de get All Providers"),
            @ApiResponse(responseCode = "500", description = "Problème lors de la récupération")
    })
    public ResponseEntity<List<Provider>> getAllProviders() {
        return new ResponseEntity<>(providerService.getAllProviders(), HttpStatus.OK);
    }

    // ✔️ Get by ID
    @GetMapping("/{id}")
    @Operation(summary = "Recherche d'un provider par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provider trouvé"),
            @ApiResponse(responseCode = "404", description = "Provider inexistant")
    })
    public ResponseEntity<Provider> getProviderById(@PathVariable int id) {
        Optional<Provider> opt = providerService.getProviderById(id);
        return opt.map(provider -> new ResponseEntity<>(provider, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✔️ Create
    @PostMapping
    @Operation(summary = "Ajout d'un nouveau provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Insertion réussie"),
            @ApiResponse(responseCode = "500", description = "Erreur lors de l'insertion")
    })
    public ResponseEntity<Provider> saveProvider(@RequestBody Provider provider) {
        return new ResponseEntity<>(providerService.saveProvider(provider), HttpStatus.CREATED);
    }

    // ✔️ Update
    @PutMapping
    @Operation(summary = "Mise à jour d'un provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mise à jour réussie"),
            @ApiResponse(responseCode = "404", description = "Provider inexistant")
    })
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider) {
        Optional<Provider> opt = providerService.getProviderById(provider.getId());
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Provider updated = opt.get();
            updated.setName(provider.getName());
            updated.setEmail(provider.getEmail());
            updated.setAddress(provider.getAddress());
            return new ResponseEntity<>(providerService.updateProvider(updated), HttpStatus.OK);
        }
    }

    // ✔️ Delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Suppression d'un provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Suppression réussie"),
            @ApiResponse(responseCode = "404", description = "Provider inexistant")
    })
    public ResponseEntity<Void> deleteProviderById(@PathVariable int id) {
        Optional<Provider> opt = providerService.getProviderById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            providerService.deleteProviderById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
