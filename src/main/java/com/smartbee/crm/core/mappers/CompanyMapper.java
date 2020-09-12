package com.smartbee.crm.core.mappers;

import com.smargbee.openapi_generated.crm.model.ApiCompany;
import com.smartbee.crm.core.models.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    List<ApiCompany> toApiCompanies(List<Company> companies);

    ApiCompany toApiCompany(Company company);

    Company toCompany(ApiCompany apiCompany);
}
