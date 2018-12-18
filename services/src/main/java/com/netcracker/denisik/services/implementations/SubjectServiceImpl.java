package com.netcracker.denisik.services.implementations;

import com.netcracker.denisik.converters.SubjectConverter;
import com.netcracker.denisik.dao.SubjectRepository;
import com.netcracker.denisik.dto.SubjectDTO;
import com.netcracker.denisik.entities.Subject;
import com.netcracker.denisik.exteption.ResourceAlreadyExistException;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class SubjectServiceImpl implements CrudService<SubjectDTO> {
    private SubjectRepository subjectRepository;
    private SubjectConverter subjectConverter;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectConverter subjectConverter){
        this.subjectRepository=subjectRepository;
        this.subjectConverter=subjectConverter;
    }


    @Override
    public long add(SubjectDTO subjectDTO) {
        Subject subject=subjectRepository.getByName(subjectDTO.getName());
        if(subject!=null){
            throw new ResourceAlreadyExistException("Subject exist with name : " + subjectDTO.getName());
        }
        return subjectRepository.save(subjectConverter.convert(subjectDTO)).getId();
    }

    @Override
    public void delete(long id) {
        if (subjectRepository.findOne(id) == null) {
            throw new ResourceNotFoundException("Deleting subject by id: " + id);
        }
        subjectRepository.delete(id);
    }

    @Override
    public List<SubjectDTO> getAll() {
        List<SubjectDTO> subjectDTOS = StreamSupport.stream(subjectRepository.findAll().spliterator(), false)
                .map(subject -> subjectConverter.convert(subject))
                .collect(Collectors.toList());
        log.debug("");
        return subjectDTOS;
    }

    @Override
    public SubjectDTO get(long id) {
        Subject subject=subjectRepository.findOne(id);
        return subjectConverter.convert(subject);
    }
}
