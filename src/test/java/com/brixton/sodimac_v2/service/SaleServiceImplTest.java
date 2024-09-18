package com.brixton.sodimac_v2.service;

import com.brixton.sodimac_v2.controller.GenericNotFoundException;
import com.brixton.sodimac_v2.data.model.*;
import com.brixton.sodimac_v2.data.model.enums.RegistryProformaType;
import com.brixton.sodimac_v2.data.model.enums.StatusGroupType;
import com.brixton.sodimac_v2.data.repository.*;
import com.brixton.sodimac_v2.dto.request.NaturalClientRequestDTO;
import com.brixton.sodimac_v2.dto.request.ProformaRequestDTO;
import com.brixton.sodimac_v2.dto.request.SaleDetailRequestDTO;
import com.brixton.sodimac_v2.dto.request.TicketRequestDTO;
import com.brixton.sodimac_v2.dto.response.ProformaResponseDTO;
import com.brixton.sodimac_v2.dto.response.SaleDetailResponseDTO;
import com.brixton.sodimac_v2.dto.response.TicketResponseDTO;
import com.brixton.sodimac_v2.service.mapper.SaleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SaleServiceImplTest {

    @Mock
    ProformaRepository proformaRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    StatusSaleRepository statusSaleRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    SaleDetailRepository saleDetailRepository;
    @Mock
    NaturalClientRepository naturalClientRepository;
    @Mock
    LegalClientRepository legalClientRepository;
    @Mock
    TicketRepository ticketRepository;

    @InjectMocks
    SaleServiceImpl saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Dado que se recibe un ProformaRequestDTO con datos válidos y el producto no esta en stock, se debe retornar un ProformaResponseDTO con stado temporal")
    void createProforma_withValidInput_shouldReturnProformaResponseDTO() {
        // Arrange
        ProformaRequestDTO requestDTO = new ProformaRequestDTO();
        requestDTO.setStatusSale(1);
        List<SaleDetailRequestDTO> details = new ArrayList<>();
        SaleDetailRequestDTO detail1 = new SaleDetailRequestDTO();
        detail1.setProduct(1L);
        detail1.setQuantity(1);
        details.add(detail1);
        requestDTO.setDetails(details);
        Employee authorizedEmployee = new Employee();
        Area areaAuthorized = new Area();
        areaAuthorized.setAreaName("SHELLING");
        authorizedEmployee.setArea(areaAuthorized);

        List<StatusSale> statusProformas = new ArrayList<>();
        StatusSale statusSaleConfirmado = new StatusSale();
        statusSaleConfirmado.setDescription("CONFIRMED");
        statusSaleConfirmado.setId(2);
        StatusSale statusSaleTemporal = new StatusSale();
        statusSaleTemporal.setDescription("TEMPORAL");
        statusSaleTemporal.setId(1);
        statusProformas.add(statusSaleConfirmado);
        statusProformas.add(statusSaleTemporal);

        List<StatusSale> statusDetails = new ArrayList<>();
        StatusSale statusSaleDetailAvailable = new StatusSale();
        statusSaleDetailAvailable.setDescription("AVAILABLE");
        statusSaleDetailAvailable.setStatusGroup(StatusGroupType.DETAIL);
        StatusSale statusSaleDetailOutOfStock = new StatusSale();
        statusSaleDetailOutOfStock.setDescription("OUT_OF_STOCK");
        statusSaleDetailOutOfStock.setStatusGroup(StatusGroupType.DETAIL);
        statusDetails.add(statusSaleDetailAvailable);
        statusDetails.add(statusSaleDetailOutOfStock);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        List<SaleDetailResponseDTO> details2 = new ArrayList<>();
        SaleDetailResponseDTO venta = new SaleDetailResponseDTO();
        venta.setStatusDetail("OUT_OF_STOCK");
        details2.add(venta);
        expectedResponseDTO.setDetails(details2);

        // Set up requestDTO with valid data

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(authorizedEmployee));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.DETAIL))).thenReturn(statusDetails);
        when(statusSaleRepository.findByIdAndStatusGroup(anyInt(), eq(StatusGroupType.PROFORMA))).thenReturn(Optional.of(statusProformas.get(0)));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.PROFORMA))).thenReturn(statusProformas);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        when(proformaRepository.save(any())).thenReturn(new Proforma());

        // Act

        ProformaResponseDTO responseDTO = saleService.createProforma(requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(expectedResponseDTO.getDetails().get(0).getStatusDetail(), responseDTO.getDetails().get(0).getStatusDetail());
        // Add more assertions as needed
    }

    @Test
    @DisplayName("Dado que se recibe un ProformaRequestDTO con datos válidos y el producto esta en stock, se debe retornar un ProformaResponseDTO con estado temporal")
    void createProforma_withValidInputAndAvailable_shouldReturnProformaResponseDTO() {
        // Arrange
        ProformaRequestDTO requestDTO = new ProformaRequestDTO();
        requestDTO.setStatusSale(1);
        List<SaleDetailRequestDTO> details = new ArrayList<>();
        SaleDetailRequestDTO detail1 = new SaleDetailRequestDTO();
        detail1.setProduct(1L);
        detail1.setQuantity(1);
        details.add(detail1);
        requestDTO.setDetails(details);
        Employee authorizedEmployee = new Employee();
        Area areaAuthorized = new Area();
        areaAuthorized.setAreaName("SHELLING");
        authorizedEmployee.setArea(areaAuthorized);
        List<StatusSale> statusProformas = new ArrayList<>();
        StatusSale statusSaleConfirmado = new StatusSale();
        statusSaleConfirmado.setDescription("CONFIRMED");
        statusSaleConfirmado.setId(2);
        StatusSale statusSaleTemporal = new StatusSale();
        statusSaleTemporal.setDescription("TEMPORAL");
        statusSaleTemporal.setId(1);
        statusProformas.add(statusSaleConfirmado);
        statusProformas.add(statusSaleTemporal);

        List<StatusSale> statusDetails = new ArrayList<>();
        StatusSale statusSaleDetailAvailable = new StatusSale();
        statusSaleDetailAvailable.setDescription("AVAILABLE");
        statusSaleDetailAvailable.setStatusGroup(StatusGroupType.DETAIL);
        StatusSale statusSaleDetailOutOfStock = new StatusSale();
        statusSaleDetailOutOfStock.setDescription("OUT_OF_STOCK");
        statusSaleDetailOutOfStock.setStatusGroup(StatusGroupType.DETAIL);
        statusDetails.add(statusSaleDetailAvailable);
        statusDetails.add(statusSaleDetailOutOfStock);
        Product product = new Product();
        product.setQuantity(2);
        product.setId(1L);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        List<SaleDetailResponseDTO> details2 = new ArrayList<>();
        SaleDetailResponseDTO venta = new SaleDetailResponseDTO();
        venta.setStatusDetail("AVAILABLE");
        details2.add(venta);
        expectedResponseDTO.setDetails(details2);

        // Set up requestDTO with valid data

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(authorizedEmployee));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.DETAIL))).thenReturn(statusDetails);
        when(statusSaleRepository.findByIdAndStatusGroup(anyInt(), eq(StatusGroupType.PROFORMA))).thenReturn(Optional.of(statusProformas.get(0)));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.PROFORMA))).thenReturn(statusProformas);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(proformaRepository.save(any())).thenReturn(new Proforma());

        // Act

        ProformaResponseDTO responseDTO = saleService.createProforma(requestDTO);

        // Assert

        assertEquals(expectedResponseDTO.getDetails().get(0).getStatusDetail(), responseDTO.getDetails().get(0).getStatusDetail());
        assertNotNull(responseDTO);
        // Add more assertions as needed
    }

    @Test
    @DisplayName("Dado que se recibe un ProformaRequestDTO con datos válidos y el producto no esta en stock, se debe retornar un ProformaResponseDTO confirmada")
    void createProforma_withValidInput_shouldReturnProformaResponseDTOconfirmed() {
        // Arrange
        ProformaRequestDTO requestDTO = new ProformaRequestDTO();
        requestDTO.setStatusSale(2);
        List<SaleDetailRequestDTO> details = new ArrayList<>();
        SaleDetailRequestDTO detail1 = new SaleDetailRequestDTO();
        detail1.setProduct(1L);
        detail1.setQuantity(1);
        details.add(detail1);
        requestDTO.setDetails(details);
        Employee authorizedEmployee = new Employee();
        Area areaAuthorized = new Area();
        areaAuthorized.setAreaName("SHELLING");
        authorizedEmployee.setArea(areaAuthorized);

        List<StatusSale> statusProformas = new ArrayList<>();
        StatusSale statusSaleConfirmado = new StatusSale();
        statusSaleConfirmado.setDescription("CONFIRMED");
        statusSaleConfirmado.setId(2);
        StatusSale statusSaleTemporal = new StatusSale();
        statusSaleTemporal.setDescription("TEMPORAL");
        statusSaleTemporal.setId(1);
        statusProformas.add(statusSaleConfirmado);
        statusProformas.add(statusSaleTemporal);

        List<StatusSale> statusDetails = new ArrayList<>();
        StatusSale statusSaleDetailAvailable = new StatusSale();
        statusSaleDetailAvailable.setDescription("AVAILABLE");
        statusSaleDetailAvailable.setStatusGroup(StatusGroupType.DETAIL);
        StatusSale statusSaleDetailOutOfStock = new StatusSale();
        statusSaleDetailOutOfStock.setDescription("OUT_OF_STOCK");
        statusSaleDetailOutOfStock.setStatusGroup(StatusGroupType.DETAIL);
        statusDetails.add(statusSaleDetailAvailable);
        statusDetails.add(statusSaleDetailOutOfStock);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        List<SaleDetailResponseDTO> details2 = new ArrayList<>();
        SaleDetailResponseDTO venta = new SaleDetailResponseDTO();
        venta.setStatusDetail("OUT_OF_STOCK");
        details2.add(venta);
        expectedResponseDTO.setDetails(details2);

        // Set up requestDTO with valid data

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(authorizedEmployee));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.DETAIL))).thenReturn(statusDetails);
        when(statusSaleRepository.findByIdAndStatusGroup(anyInt(), eq(StatusGroupType.PROFORMA))).thenReturn(Optional.of(statusSaleConfirmado));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.PROFORMA))).thenReturn(statusProformas);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        when(proformaRepository.save(any())).thenReturn(new Proforma());

        // Act

        ProformaResponseDTO responseDTO = saleService.createProforma(requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(expectedResponseDTO.getDetails().get(0).getStatusDetail(), responseDTO.getDetails().get(0).getStatusDetail());
        // Add more assertions as needed
    }

    @Test
    @DisplayName("Dado que se recibe un ProformaRequestDTO con datos válidos y el producto esta en stock, se debe retornar un ProformaResponseDTO confirmada")
    void createProforma_withValidInputAndAvailable_shouldReturnProformaResponseDTOconfirmed() {
        // Arrange
        ProformaRequestDTO requestDTO = new ProformaRequestDTO();
        requestDTO.setStatusSale(2);
        List<SaleDetailRequestDTO> details = new ArrayList<>();
        SaleDetailRequestDTO detail1 = new SaleDetailRequestDTO();
        detail1.setProduct(1L);
        detail1.setQuantity(1);
        details.add(detail1);
        requestDTO.setDetails(details);
        Employee authorizedEmployee = new Employee();
        Area areaAuthorized = new Area();
        areaAuthorized.setAreaName("SHELLING");
        authorizedEmployee.setArea(areaAuthorized);
        List<StatusSale> statusProformas = new ArrayList<>();
        StatusSale statusSaleConfirmado = new StatusSale();
        statusSaleConfirmado.setDescription("CONFIRMED");
        statusSaleConfirmado.setId(2);
        StatusSale statusSaleTemporal = new StatusSale();
        statusSaleTemporal.setDescription("TEMPORAL");
        statusSaleTemporal.setId(1);
        statusProformas.add(statusSaleConfirmado);
        statusProformas.add(statusSaleTemporal);

        List<StatusSale> statusDetails = new ArrayList<>();
        StatusSale statusSaleDetailAvailable = new StatusSale();
        statusSaleDetailAvailable.setDescription("AVAILABLE");
        statusSaleDetailAvailable.setStatusGroup(StatusGroupType.DETAIL);
        StatusSale statusSaleDetailOutOfStock = new StatusSale();
        statusSaleDetailOutOfStock.setDescription("OUT_OF_STOCK");
        statusSaleDetailOutOfStock.setStatusGroup(StatusGroupType.DETAIL);
        statusDetails.add(statusSaleDetailAvailable);
        statusDetails.add(statusSaleDetailOutOfStock);
        Product product = new Product();
        product.setQuantity(2);
        product.setId(1L);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        List<SaleDetailResponseDTO> details2 = new ArrayList<>();
        SaleDetailResponseDTO venta = new SaleDetailResponseDTO();
        venta.setStatusDetail("AVAILABLE");
        details2.add(venta);
        expectedResponseDTO.setDetails(details2);

        // Set up requestDTO with valid data

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(authorizedEmployee));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.DETAIL))).thenReturn(statusDetails);
        when(statusSaleRepository.findByIdAndStatusGroup(anyInt(), eq(StatusGroupType.PROFORMA))).thenReturn(Optional.of(statusSaleConfirmado));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.PROFORMA))).thenReturn(statusProformas);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(proformaRepository.save(any())).thenReturn(new Proforma());

        // Act

        ProformaResponseDTO responseDTO = saleService.createProforma(requestDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(expectedResponseDTO.getDetails().get(0).getStatusDetail(), responseDTO.getDetails().get(0).getStatusDetail());
        // Add more assertions as needed
    }

    @Test
    void createProforma_withNonExistingEmployee_shouldThrowGenericNotFoundException() {
        // Arrange
        ProformaRequestDTO requestDTO = new ProformaRequestDTO();
        requestDTO.setStatusSale(2);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        List<SaleDetailResponseDTO> details2 = new ArrayList<>();
        SaleDetailResponseDTO venta = new SaleDetailResponseDTO();
        venta.setStatusDetail("AVAILABLE");
        details2.add(venta);
        expectedResponseDTO.setDetails(details2);

        // Set up requestDTO with valid data

        when(employeeRepository.findById(anyLong())).thenThrow(new GenericNotFoundException("Employee not found"));

        // Act y Assert
        assertThrows(GenericNotFoundException.class, () -> saleService.createProforma(requestDTO));

    }

    @Test
    void createProforma_withNonExistingProduct_shouldHandleNonExistingProduct() {
        // Arrange
        ProformaRequestDTO requestDTO = new ProformaRequestDTO();
        requestDTO.setStatusSale(2);

        List<SaleDetailRequestDTO> details = new ArrayList<>();
        SaleDetailRequestDTO detail1 = new SaleDetailRequestDTO();
        detail1.setProduct(1L);
        detail1.setQuantity(1);
        details.add(detail1);
        requestDTO.setDetails(details);

        Employee authorizedEmployee = new Employee();
        Area areaAuthorized = new Area();
        areaAuthorized.setAreaName("SHELLING");
        authorizedEmployee.setArea(areaAuthorized);

        List<StatusSale> statusProformas = new ArrayList<>();
        StatusSale statusSaleConfirmado = new StatusSale();
        statusSaleConfirmado.setDescription("CONFIRMED");
        statusSaleConfirmado.setId(2);
        StatusSale statusSaleTemporal = new StatusSale();
        statusSaleTemporal.setDescription("TEMPORAL");
        statusSaleTemporal.setId(1);
        statusProformas.add(statusSaleConfirmado);
        statusProformas.add(statusSaleTemporal);

        List<StatusSale> statusDetails = new ArrayList<>();
        StatusSale statusSaleDetailAvailable = new StatusSale();
        statusSaleDetailAvailable.setDescription("AVAILABLE");
        statusSaleDetailAvailable.setStatusGroup(StatusGroupType.DETAIL);
        StatusSale statusSaleDetailOutOfStock = new StatusSale();
        statusSaleDetailOutOfStock.setDescription("OUT_OF_STOCK");
        statusSaleDetailOutOfStock.setStatusGroup(StatusGroupType.DETAIL);
        statusDetails.add(statusSaleDetailAvailable);
        statusDetails.add(statusSaleDetailOutOfStock);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        List<SaleDetailResponseDTO> details2 = new ArrayList<>();
        SaleDetailResponseDTO venta = new SaleDetailResponseDTO();
        venta.setStatusDetail("AVAILABLE");
        details2.add(venta);
        expectedResponseDTO.setDetails(details2);

        // Set up requestDTO with valid data

        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(authorizedEmployee));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.DETAIL))).thenReturn(statusDetails);
        when(statusSaleRepository.findByIdAndStatusGroup(anyInt(), eq(StatusGroupType.PROFORMA))).thenReturn(Optional.of(statusSaleConfirmado));
        when(statusSaleRepository.findByStatusGroup(eq(StatusGroupType.PROFORMA))).thenReturn(statusProformas);
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        ProformaResponseDTO response = saleService.createProforma(requestDTO);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.getDetails().size());

        // Verifica que el producto "nulo" se manejó correctamente (sin lanzar excepción)
        verify(productRepository).findById(1L);  // Se llamó para encontrar el producto con ID
    }

    @Test
    void getProforma_withExistingProforma_shouldReturnProformaResponseDTO() {
        // Arrange
        long proformaId = 1L;
        Proforma proforma = new Proforma();
        proforma.setId(proformaId);

        ProformaResponseDTO expectedResponseDTO = new ProformaResponseDTO();
        expectedResponseDTO.setId(proformaId);
        // Configura el mapper para que devuelva el DTO esperado
        when(proformaRepository.findByIdWithDetails(proformaId)).thenReturn(Optional.of(proforma));

        // Act
        ProformaResponseDTO responseDTO = saleService.getProforma(proformaId);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(proformaId, responseDTO.getId());
    }

    @Test
    void getProforma_withNonExistingProforma_shouldThrowGenericNotFoundException() {
        // Arrange
        long proformaId = 1L;

        // Configura el repositorio para que devuelva un Optional vacío
        when(proformaRepository.findByIdWithDetails(proformaId)).thenThrow(new GenericNotFoundException("Proforma not found"));

        // Act & Assert
        assertThrows(GenericNotFoundException.class, () -> saleService.getProforma(proformaId));
    }

    @Test
    @DisplayName("confirmSaleTicket with valid input should return TicketResponseDTO")
    void confirmSaleTicket_withValidInput_shouldReturnTicketResponseDTO() {
        // Arrange
        TicketRequestDTO requestDTO = new TicketRequestDTO();
        requestDTO.setProformaId(1L);
        NaturalClientRequestDTO clientDTO = new NaturalClientRequestDTO();
        clientDTO.setNumber("123456");
        requestDTO.setClient(clientDTO);

        Proforma proforma = new Proforma();
        proforma.setRegistryProforma(RegistryProformaType.UNUSED);
        proforma.setDetails(new ArrayList<>());

        when(proformaRepository.findByIdWithDetails(anyLong())).thenReturn(Optional.of(proforma));
        when(naturalClientRepository.findById(anyString())).thenReturn(Optional.of(new NaturalClient()));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(new Ticket());

        // Act
        TicketResponseDTO responseDTO = saleService.confirmSaleTicket(requestDTO);

        // Assert
        assertNotNull(responseDTO);
    }

    @Test
    @DisplayName("confirmSaleTicket with non-existing Proforma should throw GenericNotFoundException")
    void confirmSaleTicket_withNonExistingProforma_shouldThrowGenericNotFoundException() {
        // Arrange
        TicketRequestDTO requestDTO = new TicketRequestDTO();
        requestDTO.setProformaId(1L);

        when(proformaRepository.findByIdWithDetails(anyLong())).thenThrow(new GenericNotFoundException("Proforma not found"));

        // Act & Assert
        assertThrows(GenericNotFoundException.class, () -> saleService.confirmSaleTicket(requestDTO));
    }

    @Test
    @DisplayName("confirmSaleTicket with used Proforma should throw IllegalStateException")
    void confirmSaleTicket_withUsedProforma_shouldThrowIllegalStateException() {
        // Arrange
        TicketRequestDTO requestDTO = new TicketRequestDTO();
        requestDTO.setProformaId(1L);

        Proforma proforma = new Proforma();
        proforma.setRegistryProforma(RegistryProformaType.USED);

        when(proformaRepository.findByIdWithDetails(anyLong())).thenReturn(Optional.of(proforma));

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> saleService.confirmSaleTicket(requestDTO));
    }

    @Test
    void getTicket() {
    }

    @Test
    void confirmSaleBill() {
    }

    @Test
    void getBill() {
    }

    @Test
    void deleteTicket() {
    }

    @Test
    void deleteBill() {
    }
}