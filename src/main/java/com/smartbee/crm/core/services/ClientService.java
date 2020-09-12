package com.smartbee.crm.core.services;

import com.smartbee.crm.core.models.Client;
import com.smartbee.crm.core.repositories.ClientRepository;
import com.smartbee.crm.core.security.Authorities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    public Client createClient(Client newClient) {
        newClient.setCreatedBy(Authorities.getRoleAuthority());
        return clientRepository.save(newClient);
    }

    public void updateClient(com.smargbee.openapi_generated.crm.model.Client apiClient, Long id) {
        clientRepository.findById(id).ifPresent(client -> {
            client.setName(apiClient.getName());
            client.setEmail(apiClient.getEmail());
            client.setPhone(apiClient.getPhone());
            client.setUpdatedBy(Authorities.getRoleAuthority());
            client.setUpdatedAt(LocalDateTime.now());
            clientRepository.save(client);
            LOG.debug("Updated client information: {}", client);
        });
    }

    public void deleteClient(Long id) {
        clientRepository.findOneById(id).ifPresent(client -> {
            clientRepository.delete(client);
            LOG.debug("Delete CRM client information: {}", client);
        });
    }
}
