package com.levzhukov;

import com.levzhukov.car.Car;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"../../reset-database-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CarControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private CarRepositoryTest carRepositoryTest;

    @Test
    public void setCarRepositoryTestLoads() throws Exception {
        assertThat(carRepositoryTest).isNotNull();
    }

    @Test
    public void shouldReturnCarsList() throws Exception {
        Car car = new Car(1000, "GranTorino", LocalDate.of(1970, 01, 01));
        carRepositoryTest.save(car);

        mvc.perform(get("/api/car"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].cost").value(1000))
                .andExpect(jsonPath("$[0].model").value("GranTorino"))
                .andExpect(jsonPath("$[0].issueDate").value(LocalDate.of(1970, 01, 01).toString()));
    }

    @Test
    public void shouldCreateCar() throws Exception {
        MvcResult result = mvc.perform(post("/api/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cost\":1000, \"model\":\"GranTorino\", \"issueDate\":\"1970-01-01\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String path = result.getResponse().getHeader("Location");

        mvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cost").value(1000))
                .andExpect(jsonPath("model").value("GranTorino"))
                .andExpect(jsonPath("issueDate").value(LocalDate.of(1970, 01, 01).toString()));

    }

    @Test
    public void shouldReturnCar() throws Exception {
        Car car = new Car(1000, "GranTorino", LocalDate.of(1970, 01, 01));
        carRepositoryTest.save(car);
        Long id = car.getId();

        this.mvc.perform(get("/api/car/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("cost").value(1000))
                .andExpect(jsonPath("model").value("GranTorino"))
                .andExpect(jsonPath("issueDate").value(LocalDate.of(1970, 01, 01).toString()));
    }

    @Test
    public void shouldUpdateCar() throws Exception {
        Car car = new Car(1000, "GranTorino", LocalDate.of(1970, 01, 01));
        carRepositoryTest.save(car);
        Long id = car.getId();

        mvc.perform(patch("/api/car/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"cost\":999,\"model\":\"Tesla\"}")
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cost").value(999))
                .andExpect(jsonPath("model").value("Tesla"))
                .andExpect(jsonPath("issueDate").value(LocalDate.of(1970, 01, 01).toString()));
    }

    @Test
    public void shouldDeleteCar() throws Exception {
        Car car = new Car(1000, "GranTorino", LocalDate.of(1970, 01, 01));
        carRepositoryTest.save(car);
        Long id = car.getId();

        mvc.perform(delete("/api/car/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cost").value(1000))
                .andExpect(jsonPath("model").value("GranTorino"))
                .andExpect(jsonPath("issueDate").value(LocalDate.of(1970, 01, 01).toString()));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        mvc.perform(delete("/api/car/1"))
                .andExpect(status().isNotFound());
    }
}
