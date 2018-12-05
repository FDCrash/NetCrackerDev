package com.netcracker.denisik.services.servicesimpl;

import com.netcracker.denisik.converters.AdminConverter;
import com.netcracker.denisik.dao.AdminRepository;
import com.netcracker.denisik.dto.AdminDTO;
import com.netcracker.denisik.services.CRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements CRUDService<AdminDTO> {
    private final AdminRepository adminRepository;
    private AdminConverter adminConverter;

    public AdminServiceImpl(AdminRepository adminRepository) {
        adminConverter = new AdminConverter();
        this.adminRepository=adminRepository;
    }

    @Override
    public void add(AdminDTO adminDTO) {
        adminRepository.save(adminConverter.convert(adminDTO));
    }

    @Override
    public void delete(int id) {
        adminRepository.delete(id);
    }

    @Override
    public void update(AdminDTO adminDTO) {
        adminRepository.save(adminConverter.convert(adminDTO));
    }

    @Override
    public List<AdminDTO> getAll() {
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false)
                .map(admin -> adminConverter.convert(admin)).collect(Collectors.toList());
    }

    @Override
    public AdminDTO get(int id) {
        return adminConverter.convert(adminRepository.findOne(id));
    }

    public void changeStatusAdmin(String login) {
        adminRepository.changeStatus(login);
    }
}
