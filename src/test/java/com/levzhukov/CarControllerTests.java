package com.levzhukov;

import com.levzhukov.car.Car;
import com.levzhukov.car.CarService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    CarService carService;

    @Test
    public void shouldReturnCarsList() throws Exception {
        when(carService.getAllCars()).thenReturn(List.of(new Car(1000, "GranTorino", LocalDate.of(1970, 01, 01))));

        mvc.perform(get("/api/car"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].cost").value(1000))
                .andExpect(jsonPath("$[0].model").value("GranTorino"))
                .andExpect(jsonPath( "$[0].issueDate").value(LocalDate.of(1970, 01, 01).toString()));
    }
    @Test
    public void shouldCreateCar() throws Exception{
        mvc.perform(post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cost\":1000, \"model\":\"GranTorino\", \"issueDate\":\"1970-01-01\"}"))
                .andExpect(status().isOk());
        verify(carService).addCar(any(Car.class));
    }
}
