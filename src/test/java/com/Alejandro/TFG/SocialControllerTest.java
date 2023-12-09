package com.Alejandro.TFG;

import com.Alejandro.TFG.controller.SocialController;
import com.Alejandro.TFG.model.Social;
import com.Alejandro.TFG.Service.SocialService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SocialControllerTest {

    @InjectMocks
    private SocialController socialController;

    @Mock
    private SocialService socialService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllSocials() throws Exception {
        // Configurar el comportamiento del servicio mock
        when(socialService.getAllSocials()).thenReturn(Collections.singletonList(/* Social simulado */));

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.get("/api/social/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetSocialById() throws Exception {
        Long socialId = 1L;

        // Configurar el comportamiento del servicio mock
        when(socialService.getSocialById(socialId)).thenReturn(/* Social simulado */);

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.get("/api/social/{id}", socialId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testSaveSocial() throws Exception {
        // Configurar el comportamiento del servicio mock
        when(socialService.saveSocial(any(Social.class))).thenReturn(/* Social simulado */);

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.post("/api/social/create")
                .content("{\"id\":1,\"hourAdvise\":\"12:00\",\"task\":{\"id\":1}}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteSocial() throws Exception {
        Long socialId = 1L;

        // Ejecutar la solicitud HTTP y realizar la prueba
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/social/delete/{id}", socialId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verificar que el servicio se llamó correctamente
        verify(socialService, times(1)).deleteSocial(socialId);
    }
}
