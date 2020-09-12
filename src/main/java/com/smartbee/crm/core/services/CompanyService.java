package com.smartbee.crm.core.services;

import com.smargbee.openapi_generated.crm.model.ApiCompany;
import com.smartbee.crm.core.models.Company;
import com.smartbee.crm.core.repositories.CompanyRepository;
import com.smartbee.crm.core.security.Authorities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyService {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public Company createCompany(Company company) {
        company.setCreatedBy(Authorities.getRoleAuthority());
        return companyRepository.save(company);
    }

    public void updateCompany(ApiCompany apiCompany, Long id) {
        companyRepository.findById(id).ifPresent(company -> {
            company.setName(apiCompany.getName());
            company.setAddress(apiCompany.getAddress());
            company.setUpdatedBy(Authorities.getRoleAuthority());
            company.setUpdatedAt(LocalDateTime.now());
            companyRepository.save(company);
            LOG.debug("Updated company information: {}", company);
        });
    }

    public void deleteCompany(Long id) {
        companyRepository.findById(id).ifPresent(company -> {
            companyRepository.delete(company);
            LOG.debug("Removed company information: {}", company);
        });
    }
}
