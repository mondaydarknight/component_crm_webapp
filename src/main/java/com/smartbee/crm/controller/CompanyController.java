package com.smartbee.crm.controller;

import com.smargbee.openapi_generated.crm.model.ApiCompany;
import com.smartbee.crm.core.mappers.CompanyMapper;
import com.smartbee.crm.core.models.Company;
import com.smartbee.crm.core.security.Authorities;
import com.smartbee.crm.core.services.CompanyService;
import com.smartbee.openapi_generated.crm.api.CompaniesApi;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/crm/v1")
public class CompanyController implements CompaniesApi {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyMapper companyMapper;

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService =  companyService;
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.MANAGER, Authorities.OPERATOR })
    public ResponseEntity<List<ApiCompany>> getCompanies(@Valid @ApiParam(value = "") Pageable pageable) {
        List<Company> companies = companyService.getAllCompanies(pageable).getContent();
        return new ResponseEntity<>(companyMapper.toApiCompanies(companies), HttpStatus.OK);
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.OPERATOR })
    public ResponseEntity<ApiCompany> createCompany(@Valid @RequestBody ApiCompany apiCompany) {
        Company company = companyService.createCompany(companyMapper.toCompany(apiCompany));
        return new ResponseEntity<>(companyMapper.toApiCompany(company), HttpStatus.CREATED);
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.MANAGER})
    public ResponseEntity<Void> updateCompany(@PathVariable("id") Long id, @Valid @RequestBody ApiCompany apiCompany) {
        companyService.updateCompany(apiCompany, id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.MANAGER })
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
