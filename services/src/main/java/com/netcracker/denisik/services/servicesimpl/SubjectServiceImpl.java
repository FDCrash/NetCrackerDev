package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.SubjectConverter;
import com.netcracker.denisik.dao.SubjectRepository;
import com.netcracker.denisik.dto.SubjectDTO;
import com.netcracker.denisik.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectServiceImpl implements CRUDService<SubjectDTO> {
    private SubjectRepository subjectRepository;
    private SubjectConverter subjectConverter;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectConverter subjectConverter){
        this.subjectRepository=subjectRepository;
        this.subjectConverter=subjectConverter;
    }


    @Override
    public long add(SubjectDTO subjectDTO) {
        return subjectRepository.save(subjectConverter.convert(subjectDTO)).getId();
    }

    @Override
    public void delete(long id) {
        subjectRepository.delete(id);
    }

    @Override
    public List<SubjectDTO> getAll() {
        return StreamSupport.stream(subjectRepository.findAll().spliterator(), false)
                .map(subject -> subjectConverter.convert(subject))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO get(long id) {
        return subjectConverter.convert(subjectRepository.findOne(id));
    }
}
