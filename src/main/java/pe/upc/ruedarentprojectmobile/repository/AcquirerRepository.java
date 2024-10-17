package pe.upc.ruedarentprojectmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.upc.ruedarentprojectmobile.model.Acquirer;

public interface AcquirerRepository extends JpaRepository<Acquirer, Long> {
    Acquirer findByDni(String dni);

    boolean existsAcquirerByDni(String dni);
}
