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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration(value = "src/main/resources")
@DirtiesContext
@AutoConfigureMockMvc
public class UserControllerTest {
    private static String URL_BASE = "/api/user";
    private static String URL_BY_PARAM = "/list";
    private static String URL_UPDATE = "/update";
    private static String URL_SAVE = "/save";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testByOneParamPositive() throws Exception {
        String jsonRequest = "{\"officeId\": 1}";
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].id", is(10)))
                .andExpect(jsonPath("$.data[0].firstName", is("Микола")))
                .andExpect(jsonPath("$.data[0].secondName", nullValue()))
                .andExpect(jsonPath("$.data[0].middleName", nullValue()))
                .andExpect(jsonPath("$.data[0].position", is("Слесарь")))
                .andExpect(jsonPath("$.data[1].id", is(11)))
                .andExpect(jsonPath("$.data[1].firstName", is("Ангелина")))
                .andExpect(jsonPath("$.data[1].secondName", nullValue()))
                .andExpect(jsonPath("$.data[1].middleName", nullValue()))
                .andExpect(jsonPath("$.data[1].position", is("Офис-менеджер")))
                .andExpect(jsonPath("$.data[2].id", is(12)))
                .andExpect(jsonPath("$.data[2].firstName", is("Александр")))
                .andExpect(jsonPath("$.data[2].secondName", nullValue()))
                .andExpect(jsonPath("$.data[2].middleName", nullValue()))
                .andExpect(jsonPath("$.data[2].position", is("Курьер")));
    }

    @Test
    public void testByParamsPositive() throws Exception {
        String jsonRequest = "{\"officeId\":2, \"citizenshipCode\":976, \"docCode\":12}";
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].firstName", is("Рустам")))
                .andExpect(jsonPath("$.data[0].secondName", is("Казахов")))
                .andExpect(jsonPath("$.data[0].middleName", is("Рустамович")))
                .andExpect(jsonPath("$.data[0].position", is("Верстальщик")));
    }

    @Test
    public void testByParamsNegative() throws Exception {
        String jsonRequest = "{\"officeId\":2, \"citizenshipCode\":976, \"docCode\":13}";
        mockMvc.perform(post(URL_BASE + URL_BY_PARAM).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Пользователей по заданным параметрам не найдено"));
    }

    @Test
    public void testIdPositive() throws Exception {
        int id = 5;
        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", notNullValue()))
                .andExpect(jsonPath("$.data.id", is(5)))
                .andExpect(jsonPath("$.data.firstName", is("Сулико")))
                .andExpect(jsonPath("$.data.secondName", is("Тереберидзе")))
                .andExpect(jsonPath("$.data.middleName", is("Витальевна")))
                .andExpect(jsonPath("$.data.position", is("Управляющая")))
                .andExpect(jsonPath("$.data.phone", is("+7(922)491-45-34")))
                .andExpect(jsonPath("$.data.docName", is("Удостоверение беженца")))
                .andExpect(jsonPath("$.data.docNumber", is("6546 231531")))
                .andExpect(jsonPath("$.data.docDate", is("2017-12-25")))
                .andExpect(jsonPath("$.data.citizenshipName", is("Грузия")))
                .andExpect(jsonPath("$.data.citizenshipCode", is(319)))
                .andExpect(jsonPath("$.data.isIdentified", is(true)));
    }

    @Test
    public void testIdNegative() throws Exception {
        int id = 33;
        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Пользователь не найден id=" + id));
    }

    @Test
    public void testUpdatePositive() throws Exception {
        String jsonRequest = "{\"id\":2,\"firstName\":\"Сулико new\",\"secondName\": \"Тереберидзе new\"," +
                "\"middleName\": \"Витальевна new\",\"position\":\"Управляющая new\",\"citizenshipCode\":643," +
                "\"isIdentified\":false,\"docNumber\":\"newnewnew\",\"officeId\":5}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void testUpdateWrongId() throws Exception {
        int id = 4444;
        String jsonRequest = "{\"id\":"+id+",\"firstName\":\"Сулико new\",\"secondName\": \"Тереберидзе new\"," +
                "\"middleName\": \"Витальевна new\",\"position\":\"Управляющая new\",\"citizenshipCode\":643," +
                "\"isIdentified\":false,\"docNumber\":\"newnewnew\",\"officeId\":5}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Пользователь не найден id=" + id));
    }

    @Test
    public void testUpdateWrongOfficeId() throws Exception {
        int officeId = 4444;
        String jsonRequest = "{\"id\":2,\"firstName\":\"Сулико new\",\"secondName\": \"Тереберидзе new\"," +
                "\"middleName\": \"Витальевна new\",\"position\":\"Управляющая new\",\"citizenshipCode\":643," +
                "\"isIdentified\":false,\"docNumber\":\"newnewnew\",\"officeId\":"+officeId+"}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Офис с таким id не найден. id=" + officeId));
    }

    @Test
    public void testUpdateWrongCitizenship() throws Exception {
        int citizenship = 4444;
        String jsonRequest = "{\"id\":2,\"firstName\":\"Сулико new\",\"secondName\": \"Тереберидзе new\"," +
                "\"middleName\": \"Витальевна new\",\"position\":\"Управляющая new\",\"citizenshipCode\":"+citizenship+"," +
                "\"isIdentified\":false,\"docNumber\":\"newnewnew\",\"officeId\":5}";
        mockMvc.perform(post(URL_BASE + URL_UPDATE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Гражданство с таким кодом не найдено code=" + citizenship));
    }

    @Test
    public void testSavePositive() throws Exception {
        String jsonRequest = "{\"officeId\":7,\"firstName\":\"first name\",\"secondName\":\"second name\"," +
                "\"middleName\":\"middle name\",\"position\":\"position\",\"citizenshipCode\":319,\"docCode\":21}";
        mockMvc.perform(post(URL_BASE + URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void testSaveNegative() throws Exception {
        int docCode = 211;
        String jsonRequest = "{\"officeId\":7,\"firstName\":\"first name\",\"secondName\":\"second name\"," +
                "\"middleName\":\"middle name\",\"position\":\"position\",\"citizenshipCode\":319,\"docCode\":"+docCode+"}";
        mockMvc.perform(post(URL_BASE + URL_SAVE).contentType(MediaType.APPLICATION_JSON).content(jsonRequest))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.error")
                        .value("Запрошенная информация не найдена. Пожалуйста, попробуйте снова. Такой тип документа не найден. Код документа:"+docCode));
    }
}
