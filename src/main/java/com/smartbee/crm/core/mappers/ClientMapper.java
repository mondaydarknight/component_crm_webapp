package com.smartbee.crm.core.mappers;

import com.smartbee.crm.core.models.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    List<com.smargbee.openapi_generated.crm.model.Client> toApiClients(List<Client> clients);

    Client toClient(com.smargbee.openapi_generated.crm.model.Client client);

    com.smargbee.openapi_generated.crm.model.Client toApiClient(Client client);
}
