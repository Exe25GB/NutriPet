package com.nutripet.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.nutripet.usuario.DTO.UserResponseDTO;
import com.nutripet.usuario.model.User;
import com.nutripet.usuario.repository.UserRepository;
import com.nutripet.usuario.service.UserService;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    public void testObtenerTodos() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setNombre("Juan Perez");
        mockUser.setEmail("juan@nutripet.com");
        
        when(userRepository.findAll()).thenReturn(List.of(mockUser));

        List<UserResponseDTO> usuarios = userService.obtenerTodos();

        assertNotNull(usuarios); 
        assertEquals(1, usuarios.size()); 
        
        assertEquals("Juan Perez", usuarios.get(0).getNombre());
    }

}
