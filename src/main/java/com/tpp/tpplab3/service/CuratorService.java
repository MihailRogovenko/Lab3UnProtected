package com.tpp.tpplab3.service;

import com.tpp.tpplab3.models.Curators;
import com.tpp.tpplab3.repository.CuratorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuratorService {

    @Autowired
    private CuratorsRepository curatorsRepository;

    public List<Curators> getAllCurators() {
        return curatorsRepository.findAll();
    }

    public Optional<Curators> findCuratorById(Integer id) {
        return curatorsRepository.findById(id);
    }

    public void saveCurator(Curators curator) {
        curatorsRepository.save(curator);
    }

    public void updateCurator(Curators updatedCurator) {
        Optional<Curators> existingCuratorOpt = curatorsRepository.findById(updatedCurator.getCuratorId());

        if (existingCuratorOpt.isPresent()) {
            Curators existingCurator = existingCuratorOpt.get();
            existingCurator.setName(updatedCurator.getName());
            existingCurator.setAge(updatedCurator.getAge());
            existingCurator.setDepartment(updatedCurator.getDepartment());
            curatorsRepository.save(existingCurator);
        }
    }

    public void deleteCuratorById(Integer id) {
        curatorsRepository.deleteById(id);
    }
}
