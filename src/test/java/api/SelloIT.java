package api;

import api.apiControllers.SelloApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.SelloDto;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SelloIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateSello() {
        this.createSello();
    }

    @Test
    void testSelloInvalidRequest() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSelloWithoutSelloDtoNombre() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).body(new SelloDto(null, "Memphis, Tennessee")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateSelloWithoutSelloDtoSede() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).body(new SelloDto("Stax Records", null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateSelloNotFoundException() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).path(SelloApiController.ID_ID)
                .expandPath("0196f6c4f97df3f48d570c23e46501ae").body(new SelloDto("Etiquette Records", "Washington, Tacoma")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testUpdateSello() {
        String id = this.createSello();
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).path(SelloApiController.ID_ID)
                .expandPath(id).body(new SelloDto("Stax Records", "Washington, Tacoma")).put();
        new Client().submit(request);
    }

    @Test
    void testUpdateSelloWithoutSelloDto() {
        String id = this.createSello();
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).path(SelloApiController.ID_ID)
                .expandPath(id).body(null).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    private String createSello() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).body(new SelloDto("Stax Records", "Memphis, Tennessee")).post();
        return (String) new Client().submit(request).getBody();
    }
}
