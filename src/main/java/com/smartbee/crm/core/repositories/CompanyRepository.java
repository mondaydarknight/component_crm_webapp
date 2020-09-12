package com.smartbee.crm.core.repositories;

import com.smartbee.crm.core.models.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}
