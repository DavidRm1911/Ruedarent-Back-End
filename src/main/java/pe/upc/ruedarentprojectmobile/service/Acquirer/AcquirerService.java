package pe.upc.ruedarentprojectmobile.service.Acquirer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.upc.ruedarentprojectmobile.exceptions.AlreadyExistsException;
import pe.upc.ruedarentprojectmobile.exceptions.ResourceNotFoundException;
import pe.upc.ruedarentprojectmobile.model.Acquirer;
import pe.upc.ruedarentprojectmobile.repository.AcquirerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcquirerService  implements IAcquirerService{
    private final AcquirerRepository acquirerRepository;

    @Override
    public Acquirer addAcquirer(Acquirer acquirer) {
        return Optional.ofNullable(acquirer).filter(c -> !acquirerRepository.existsAcquirerByDni(c.getDni()))
                .map(acquirerRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(acquirer.getDni()+ "already exists"));
    }

    @Override
    public Acquirer updateAcquirer(Acquirer acquirer, Long acquirerId) {
        return Optional.ofNullable(getAcquirer(acquirerId))
                .map(oldAcquirer -> {
                    oldAcquirer.setDni(acquirer.getDni());
                    oldAcquirer.setName(acquirer.getName());
                    oldAcquirer.setLastName(acquirer.getLastName());
                    oldAcquirer.setPhone(acquirer.getPhone());
                    oldAcquirer.setEmail(acquirer.getEmail());
                    return acquirerRepository.save(oldAcquirer);
                }).orElseThrow(() -> new ResourceNotFoundException("Acquirer not found"));
    }

    @Override
    public Acquirer getAcquirer(Long acquirerId) {
        return acquirerRepository.findById(acquirerId)
                .orElseThrow(()-> new ResourceNotFoundException("Acquirer not found"));

    }

    @Override
    public void deleteAcquirer(Long acquirerId) {
        acquirerRepository.findById(acquirerId).ifPresentOrElse(acquirerRepository::delete,() -> {throw new ResourceNotFoundException("Acquirer not found");});
    }

    @Override
    public List<Acquirer> getAllAcquirers() {
        return acquirerRepository.findAll();
    }

    @Override
    public Acquirer getAcquirerByDni(String dni) {
        return acquirerRepository.findByDni(dni);
    }
}
