package ru.bolgov.bell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
@AutoConfigureMockMvc
public class OfficeControllerTest {
    private static String URL_BASE = "/api/office";
    private static String URL_BY_PARAM = "/list";
    private static String URL_UPDATE = "/update";
    private static String URL_SAVE = "/save";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testByParamPositive() throws Exception {
        String jsonRequest = "{\"orgId\": 1}";
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("102")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)));
    }

    @Test
    public void testByParamNegative() throws Exception {
        String jsonRequest = "{\"orgId\": 1," + "\"name\": \"wrong name\"}";
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Офисы по заданным параметрам не найдены."));
    }

    @Test
    public void testIdPositive() throws Exception {
        int id = 2;
        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(2)))
                .andExpect(jsonPath("$.data.name", is("103")))
                .andExpect(jsonPath("$.data.address", is("ул.Лунина, 7")))
                .andExpect(jsonPath("$.data.phone", nullValue()))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testIdNegative() throws Exception {
        int id = 33;
        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Не удалось найти офис id=" + id));
    }

    @Test
    public void testUpdatePositive() throws Exception {
        String jsonRequest = "{\"id\": 4,\"name\": \"New name\",\"address\": \"New address\"}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void testUpdateNegative() throws Exception {
        int id = 4444;
        String jsonRequest = "{\"id\": " + id + ", \"name\": \"оф.208000\",\"address\": \"ул.Базарная, 1\"}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Не удалось найти офис id=" + id));
    }

    @Test
    public void testSavePositive() throws Exception {
        String jsonRequest = "{\"orgId\": 2, \"name\": \"оф.208000\", \"address\": \"ул.New, 9999\"}";
        mockMvc.perform(post(URL_BASE + URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void testSaveNegative() throws Exception {
        String jsonRequest = "{\"orgId\": 222, \"name\": \"оф.208000\", \"address\": \"ул.New, 9999\"}";
        mockMvc.perform(post(URL_BASE + URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("К сожалению во время работы произошла ошибка. Код ошибки: e0001 Пожалуйста, попробуйте снова. "));
    }
}
