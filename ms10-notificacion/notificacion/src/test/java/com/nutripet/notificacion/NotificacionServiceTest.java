package com.nutripet.notificacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.nutripet.notificacion.DTO.NotificacionResponseDTO;
import com.nutripet.notificacion.model.Notificacion;
import com.nutripet.notificacion.repository.NotificacionRepository;
import com.nutripet.notificacion.service.NotificacionService;

@SpringBootTest
public class NotificacionServiceTest {

    @Autowired
    private NotificacionService notificacionService;

    @MockitoBean
    private NotificacionRepository notificacionRepository;

    @Test
    public void testObtenerTodas() {
        Notificacion mockNoti = new Notificacion();
        mockNoti.setId(1L);
        mockNoti.setDestinatario("mascota@nutripet.com");
        mockNoti.setMensaje("Su pedido de alimento premium ha sido enviado.");
        mockNoti.setFechaEnvio(LocalDateTime.now());
        
        when(notificacionRepository.findAll()).thenReturn(List.of(mockNoti));

        
        List<NotificacionResponseDTO> notificaciones = notificacionService.obtenerTodas();

        
        assertNotNull(notificaciones); 
        assertEquals(1, notificaciones.size()); 
        
        assertEquals("mascota@nutripet.com", notificaciones.get(0).getDestinatario());
        assertEquals("Su pedido de alimento premium ha sido enviado.", notificaciones.get(0).getMensaje());
    }

}
