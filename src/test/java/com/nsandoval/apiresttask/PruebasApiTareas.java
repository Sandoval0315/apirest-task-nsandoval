package com.nsandoval.apiresttask;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.nsandoval.apiresttask.repository.TaskRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PruebasApiTareas {

    @Autowired
    private MockMvc cliente;

    @Autowired
    private TaskRepository repositorio;

    @BeforeEach
    void limpiarBaseDeDatos() {
        repositorio.deleteAll();
    }

    @Test
    void crearTareaDevuelve201() throws Exception {
        cliente.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Comprar comida\",\"description\":\"Ir al supermercado\",\"isCompleted\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Comprar comida"));
    }

    @Test
    void tituloVacioDevuelve400() throws Exception {
        cliente.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.estado").value(400));
    }

    @Test
    void tareaInexistenteDevuelve404() throws Exception {
        cliente.perform(get("/api/tasks/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.estado").value(404));
    }
}
