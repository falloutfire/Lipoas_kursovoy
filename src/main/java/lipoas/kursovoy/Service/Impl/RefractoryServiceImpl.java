package lipoas.kursovoy.Service.Impl;

import lipoas.kursovoy.Entity.Refractory;
import lipoas.kursovoy.Repository.RefractoryRepository;
import lipoas.kursovoy.Service.RefractoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefractoryServiceImpl implements RefractoryService {

    @Autowired
    private RefractoryRepository refractoryRepository;

    @Override
    public List<Refractory> findAll() {
        return refractoryRepository.findAll();
    }
}
