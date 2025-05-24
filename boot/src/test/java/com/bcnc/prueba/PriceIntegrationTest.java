package com.bcnc.prueba;

import com.bcnc.prueba.application.Aplicacion;
import com.bcnc.prueba.domain.model.Price;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;



@SpringBootTest(
    classes = Aplicacion.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
class PriceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void setUpBeforeAll() {
        System.setProperty("file.encoding", StandardCharsets.UTF_8.name());
        System.setProperty("sun.jnu.encoding", StandardCharsets.UTF_8.name());
    }

    @BeforeEach
    void setUp() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new StdDeserializer<OffsetDateTime>(OffsetDateTime.class) {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");

            @Override
            public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                String date = p.getText();
                return OffsetDateTime.parse(date, formatter);
            }
        });
        objectMapper.registerModule(module);

        restTemplate.getRestTemplate().getMessageConverters().stream()
            .filter(converter -> converter instanceof org.springframework.http.converter.json.MappingJackson2HttpMessageConverter)
            .forEach(converter -> ((org.springframework.http.converter.json.MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper));
    }

    @Test
    @Sql("/data/insert_test_prices.sql")
    void shouldReturnPriceWhenValidParameters() {
        // Given
        String url = "/api/v1/price?dateTime=2023-06-14T10:00:00Z&productId=35455&brandId=1";

        // When
        ResponseEntity<Price> response = restTemplate.getForEntity(url, Price.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Expected HTTP 200 OK");
        Price price = response.getBody();
        assertNotNull(price, "Price should not be null");
        assertEquals(35455L, price.getProductId(), "Product ID should match");
        assertEquals(1L, price.getBrandId(), "Brand ID should match");
        assertEquals(1L, price.getPriceList(), "Price list should match");
    }

    @Test
    @Sql("/data/insert_test_prices.sql")
    void shouldReturn404WhenPriceNotFound() {
        // Given
        String notFoundUrl = "/api/v1/price?dateTime=2023-06-14T00:00:00Z&productId=99999&brandId=1";

        // When
        ResponseEntity<String> errorResponse = restTemplate.getForEntity(notFoundUrl, String.class);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode(), "Expected HTTP 404 Not Found");
    }


}
