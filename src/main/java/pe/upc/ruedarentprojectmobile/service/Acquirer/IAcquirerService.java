package pe.upc.ruedarentprojectmobile.service.Acquirer;

import pe.upc.ruedarentprojectmobile.model.Acquirer;

import java.util.List;

public interface IAcquirerService {
    Acquirer addAcquirer(Acquirer acquirer);
    Acquirer updateAcquirer(Acquirer acquirer, Long acquirerId);
    Acquirer getAcquirer(Long acquirerId);
    void deleteAcquirer(Long acquirerId);

    List<Acquirer> getAllAcquirers();

    Acquirer getAcquirerByDni(String dni);
}
