package com.smartbee.crm.core.repositories;

import com.smartbee.crm.core.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Optional<Client> findOneById(Long id);
}
