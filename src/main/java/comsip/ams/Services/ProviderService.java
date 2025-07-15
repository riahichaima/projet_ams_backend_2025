package comsip.ams.Services;

import comsip.ams.Entities.Provider;

import java.util.List;
import java.util.Optional;

public interface ProviderService {
    List<Provider> getAllProviders();
    Provider saveProvider(Provider provider);
    Optional<Provider> getProviderById(int id);
    void deleteProviderById(int id);
    Provider updateProvider(Provider provider); // Ajout de update
}
