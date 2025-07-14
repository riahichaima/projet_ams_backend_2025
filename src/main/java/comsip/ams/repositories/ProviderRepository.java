package comsip.ams.repositories;

import comsip.ams.Entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProviderRepository  extends CrudRepository<Provider, Integer> {
}
