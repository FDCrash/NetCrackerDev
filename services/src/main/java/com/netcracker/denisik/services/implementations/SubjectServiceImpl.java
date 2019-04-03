package com.netcracker.denisik.services.implementations;

import com.google.gson.Gson;
import com.netcracker.denisik.converters.SubjectConverter;
import com.netcracker.denisik.dao.SubjectRepository;
import com.netcracker.denisik.dto.SubjectDTO;
import com.netcracker.denisik.dto.dtoxml.Subjects;
import com.netcracker.denisik.entities.Subject;
import com.netcracker.denisik.exteption.ResourceAlreadyExistException;
import com.netcracker.denisik.exteption.ResourceNotFoundException;
import com.netcracker.denisik.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Transactional
@Service
public class SubjectServiceImpl implements CrudService<SubjectDTO> {
    private SubjectRepository subjectRepository;
    private SubjectConverter subjectConverter;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectConverter subjectConverter) {
        this.subjectRepository = subjectRepository;
        this.subjectConverter = subjectConverter;
    }


    @Override
    public long add(SubjectDTO subjectDTO) {
        log.debug("Check free name for subject");
        if (subjectRepository.existsByName(subjectDTO.getName())) {
            log.error("Subject already exist with name: " + subjectDTO.getName());
            throw new ResourceAlreadyExistException("Subject exist with name : " + subjectDTO.getName());
        }
        log.debug("Add/update subject :" + subjectDTO.getName());
        return subjectRepository.save(subjectConverter.convert(subjectDTO)).getId();
    }

    @Override
    public void delete(long id) {
        log.debug("Deleting subject");
        if (!subjectRepository.existsById(id)) {
            log.error("Not found subject for delete by id: " + id);
            throw new ResourceNotFoundException("Deleting subject by id: " + id);
        }
        subjectRepository.delete(id);
    }

    @Override
    public List<SubjectDTO> getAll() {
        List<SubjectDTO> subjectDTOS = StreamSupport.stream(subjectRepository.findAll().spliterator(), false)
                .map(subject -> subjectConverter.convert(subject))
                .collect(Collectors.toList());
        log.debug("To Json operation subjects");
        convertToJson(subjectDTOS);
        log.debug("To XML operation subjects");
        convertToXml(subjectDTOS);
        log.debug("Getting subjects from DB");
        return subjectDTOS;
    }

    public void convertToJson(List<SubjectDTO> subjectDTOS) {
        try(FileWriter writer = new FileWriter("services/src/main/resources/json/jsonformatsubject.json")) {
            new Gson().toJson(subjectDTOS, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void convertToXml(List<SubjectDTO> subjectDTOS) {
        try (FileWriter writer = new FileWriter("services/src/main/resources/xml/xmlformatsubject.xml")) {
            Subjects users = new Subjects(subjectDTOS);
            JAXBContext context = JAXBContext.newInstance(Subjects.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(users, writer);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public SubjectDTO get(long id) {
        log.debug("Start getting subject by id: " + id);
        Subject subject = subjectRepository.findOne(id);
        return subjectConverter.convert(subject);
    }
}
