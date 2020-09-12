package com.smartbee.crm.controller;

import com.smargbee.openapi_generated.crm.model.Client;
import com.smartbee.crm.core.mappers.ClientMapperImpl;
import com.smartbee.crm.core.repositories.ClientRepository;
import com.smartbee.crm.core.security.Authorities;
import com.smartbee.crm.core.services.ClientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(clientController, "clientMapper", new ClientMapperImpl());
    }

    @Test
    public void getClients() {
        Pageable page = PageRequest.of(0, 5);
        LocalDateTime now = LocalDateTime.now();
        com.smartbee.crm.core.models.Client client = new com.smartbee.crm.core.models.Client();
        client.setId(123);
        client.setName("foo");
        client.setEmail("foo@test.com");
        client.setPhone("0900000000");
        client.setCreatedBy(Authorities.OPERATOR);
        client.setCreatedAt(now);
        client.setUpdatedBy(Authorities.MANAGER);
        client.setUpdatedAt(now);
        when(clientService.getAllClients(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(client)));
        ResponseEntity<List<Client>> response = clientController.getClients(page);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get(0).getId()).isEqualTo(123);
        assertThat(response.getBody().get(0).getName()).isEqualTo("foo");
        assertThat(response.getBody().get(0).getEmail()).isEqualTo("foo@test.com");
        assertThat(response.getBody().get(0).getPhone()).isEqualTo("0900000000");
        assertThat(response.getBody().get(0).getCreatedBy()).isEqualTo(Authorities.OPERATOR);
        assertThat(response.getBody().get(0).getCreatedAt()).isEqualTo(now);
        assertThat(response.getBody().get(0).getUpdatedBy()).isEqualTo(Authorities.MANAGER);
        assertThat(response.getBody().get(0).getUpdatedAt()).isEqualTo(now);
    }

    @Test
    public void createClient() {
        Client apiClient = new Client();
        apiClient.setName("foo");
        apiClient.setEmail("foo@test.com");
        apiClient.setPhone("0000000000");
        apiClient.setCompanyId(1);
        com.smartbee.crm.core.models.Client newClient = new com.smartbee.crm.core.models.Client();
        newClient.setId(123);
        newClient.setName("foo");
        newClient.setEmail("foo@test.com");
        newClient.setPhone("0000000000");
        newClient.setCompanyId(1);
        HttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(clientService.createClient(any(com.smartbee.crm.core.models.Client.class))).thenReturn(newClient);
        ResponseEntity<Client> response = clientController.createClient(apiClient);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(123);
        assertThat(response.getBody().getName()).isEqualTo("foo");
        assertThat(response.getBody().getEmail()).isEqualTo("foo@test.com");
        assertThat(response.getBody().getPhone()).isEqualTo("0000000000");
        assertThat(response.getBody().getCompanyId()).isEqualTo(1);
    }

    @Test
    public void updateClient() {
        Long id = 123L;
        Client apiClient = new Client();
        apiClient.setName("foo");
        apiClient.setEmail("foo@test.com");
        apiClient.setPhone("0900000000");
        apiClient.setCompanyId(1);
        HttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        doNothing().when(clientService).updateClient(any(Client.class), anyLong());
        ResponseEntity<Void> response = clientController.updateClient(id, apiClient);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void deleteClient() {
        Long id = 123L;
        doNothing().when(clientService).deleteClient(id);
        HttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ResponseEntity<Void> response = clientController.deleteClient(anyLong());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }
}
