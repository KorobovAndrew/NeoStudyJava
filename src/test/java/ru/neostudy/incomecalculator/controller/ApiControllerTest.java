package ru.neostudy.incomecalculator.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.neostudy.incomecalculator.service.CalculationService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CalculationService calculationService;

    @DisplayName("должен возвращать корректный результат для входных данных")
    @Test
    void shouldReturnExpectedHolidayPay() throws Exception {

        double averageSalaryTest = 120;
        int vacationDaysTest = 10;

        double correctAnswer = 40;


        given(calculationService.calculateSalary(averageSalaryTest, vacationDaysTest)
        ).willReturn(correctAnswer);


        Gson gson = new GsonBuilder().create();
        mvc.perform(get("/api/v1/calculate")
                        .param("averageSalary", String.valueOf(averageSalaryTest))
                        .param("vacationDays", String.valueOf(vacationDaysTest))
                        .accept("application/json; charset=utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(gson.toJson(correctAnswer)));
    }

}