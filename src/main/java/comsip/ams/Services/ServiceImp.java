package comsip.ams.Services;

import comsip.ams.Entities.Provider;
import comsip.ams.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImp implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public List<Provider> getAllProviders() {
        return (List<Provider>) this.providerRepository.findAll();
    }

    @Override
    public Provider saveProvider(Provider provider) {
        return this.providerRepository.save(provider);
    }

    @Override
    public Optional<Provider> getProviderById(int id) {
        return this.providerRepository.findById(id);
    }

    @Override
    public void deleteProviderById(int id) {
        this.providerRepository.deleteById(id);
    }

    // ✅ Nouvelle méthode update
    @Override
    public Provider updateProvider(Provider provider) {
        return this.providerRepository.save(provider);
    }
}
