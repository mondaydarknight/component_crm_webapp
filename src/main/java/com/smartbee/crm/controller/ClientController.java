package com.smartbee.crm.controller;

import com.smargbee.openapi_generated.crm.model.ApiUser;
import com.smargbee.openapi_generated.crm.model.Client;
import com.smartbee.crm.core.mappers.ClientMapper;
import com.smartbee.crm.core.security.Authorities;
import com.smartbee.crm.core.services.ClientService;
import com.smartbee.openapi_generated.crm.api.ClientsApi;
import io.swagger.annotations.ApiParam;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crm/v1")
public class ClientController implements ClientsApi {

    private static final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientMapper clientMapper;

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.MANAGER, Authorities.OPERATOR })
    public ResponseEntity<List<Client>> getClients(@Valid @ApiParam(value = "") Pageable pageable) {
        List<com.smartbee.crm.core.models.Client> clients = clientService.getAllClients(pageable).getContent();
        return new ResponseEntity<>(clientMapper.toApiClients(clients), HttpStatus.OK);
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.OPERATOR })
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client apiClient) {
        com.smartbee.crm.core.models.Client newClient = clientService.createClient(clientMapper.toClient(apiClient));
        return new ResponseEntity<>(clientMapper.toApiClient(newClient), HttpStatus.CREATED);
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.MANAGER})
    public ResponseEntity<Void> updateClient(@PathVariable("id") Long id, @Valid @RequestBody Client apiClient) {
        clientService.updateClient(apiClient, id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @Secured({ Authorities.ADMIN, Authorities.MANAGER })
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
