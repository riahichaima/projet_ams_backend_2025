package comsip.ams.Controllers;

import comsip.ams.Entities.Provider;
import comsip.ams.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/providers")
@RestController
public class ProviderController {
    @Autowired
    ProviderRepository providerRepository;
    @GetMapping ("/")
    @Operation(summary = "Récupération de tous les providers")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Succès de get All Providers"),
            @ApiResponse(responseCode = "500", description = "Problème lors de la récupération") })
    public List<Provider> getAllProviders() {
        return (List<Provider>) providerRepository.findAll();
    }
    @PostMapping ("/")
    public Provider saveProvider(@RequestBody Provider provider) {
      //Provider p = new Provider("samsung","rue street","samsung@gmail.com");
        return providerRepository.save(provider);
    }

}
