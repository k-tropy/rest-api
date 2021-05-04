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

import java.util.StringJoiner;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
@AutoConfigureMockMvc
public class OrganizationControllerTest {
    private static String URL_BASE = "/api/organization";
    private static String URL_BY_PARAM = "/list";
    private static String URL_UPDATE = "/update";
    private static String URL_SAVE = "/save";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testByParamPositive() throws Exception {
        String jsonName = "\"name\": \"Рога\"";
        String jsonInn = "\"inn\": \"778944556611\"";
        String jsonIsAcive = "\"isActive\": \"true\"";

        testByParamPositiveExecutor(jsonJoiner(jsonName));
        testByParamPositiveExecutor(jsonJoiner(jsonName, jsonInn));
        testByParamPositiveExecutor(jsonJoiner(jsonName, jsonIsAcive));
        testByParamPositiveExecutor(jsonJoiner(jsonName, jsonInn, jsonIsAcive));
    }

    @Test
    public void testByParamNegative() throws Exception {
        String jsonName = "\"name\": \"Копыта\"";
        String jsonWrongName = "\"name\": \"wrong Копыта\"";
        String jsonWrongInn = "\"inn\": \"488944556612\"";
        String jsonNotValidInn = "\"inn\": \"48894455661122222222\"";
        String jsonWrongIsAcive = "\"isActive\": \"false\"";

        testByParamNegativeExecutor(jsonJoiner(jsonWrongName));
        testByParamNegativeExecutor(jsonJoiner(jsonName, jsonWrongInn));
        testByParamNegativeExecutor(jsonJoiner(jsonName, jsonWrongIsAcive));
        testByParamNegativeExecutor(jsonJoiner(jsonName, jsonNotValidInn));
    }

    @Test
    public void testIdPositive() throws Exception {
        int id = 3;
        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.name", is("Гольфстрим")))
                .andExpect(jsonPath("$.data.fullName", is("Общество с ограниченной ответственностью Гольфстрим")))
                .andExpect(jsonPath("$.data.inn", is("518944556611")))
                .andExpect(jsonPath("$.data.kpp", is("111111211")))
                .andExpect(jsonPath("$.data.address", is("ул.Героев, 107")))
                .andExpect(jsonPath("$.data.phone", nullValue()))
                .andExpect(jsonPath("$.data.isActive", is(true)));
    }

    @Test
    public void testIdNegative() throws Exception {
        int id = 33;
        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Нет результатов для id=" + id));
    }

    @Test
    public void testUpdatePositive() throws Exception {
        String jsonRequest = "{\"id\": 4,\"name\": \"Достаток\",\"fullName\": \"Общество с ограниченной ответственностью Достаток\"," +
                "\"inn\": \"238944556611\",\"kpp\": \"111113111\",\"address\": \"ул.Базарная, 1\",\"phone\": null,\"isActive\": true}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void testUpdateNegative() throws Exception {
        int id = 4444;
        String jsonRequest = "{\"id\": " + id + ", \"name\": \"Достаток\",\"fullName\": \"Общество с ограниченной ответственностью Достаток\"," +
                "\"inn\": \"238944556611\",\"kpp\": \"111113111\",\"address\": \"ул.Базарная, 1\",\"phone\": null,\"isActive\": true}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Организация id=" + id + " не найдена"));
    }

    @Test
    public void testSavePositive() throws Exception {
        String jsonRequest = "{\"name\": \"name\",\"fullName\": \"fullName\"," +
                "\"inn\": \"112233445566\",\"kpp\": \"111113111\",\"address\": \"address\",\"phone\": \"phone\",\"isActive\": true}";
        mockMvc.perform(post(URL_BASE + URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void testSaveNegative() throws Exception {
        String jsonRequest = "{\"name\": \"name\",\"fullName\": \"fullName\"," +
                "\"inn\": \"1122334455661\",\"kpp\": \"111113111\",\"address\": \"address\",\"phone\": \"phone\",\"isActive\": true}";
        mockMvc.perform(post(URL_BASE + URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Ошибка валидации. Неверный формат вводимых данных.  Пожалуйста, попробуйте снова. " +
                                "saveOrganization.organizationIn.inn: Inn must be between 10 and 12 characters"));
    }

    private void testByParamPositiveExecutor(String json) throws Exception {
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Рога")))
                .andExpect(jsonPath("$.data[0].isActive", is(true)));
    }

    private void testByParamNegativeExecutor(String json) throws Exception {
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON_UTF8).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.error", notNullValue()));
    }

    private String jsonJoiner(String... array) {
        StringJoiner sj = new StringJoiner(",", "{", "}");
        for (String s : array) {
            sj.add(s);
        }
        return sj.toString();
    }

}
