package api;

import api.apiControllers.AlbumApiController;
import api.apiControllers.ProgramaRadioApiController;
import api.apiControllers.SelloApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.AlbumDto;
import api.dtos.ProgramaRadioDto;
import api.dtos.SelloDto;
import api.entities.Genero;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProgramaRadioIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateProgramaRadio() {
        String albumId = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(ProgramaRadioApiController.PROGRAMAS_RADIO)
                .body(new ProgramaRadioDto("El Sotano", Boolean.FALSE, DayOfWeek.FRIDAY, albumId)).post();
        new Client().submit(request);
    }

    @Test
    void testProgramaRadioInvalidRequest() {
        HttpRequest request = HttpRequest.builder().path(ProgramaRadioApiController.PROGRAMAS_RADIO + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateProgramaRadioAlbumIdNotFoundException() {
        HttpRequest request = HttpRequest.builder().path(ProgramaRadioApiController.PROGRAMAS_RADIO)
                .body(new ProgramaRadioDto("El Sotano", Boolean.FALSE, DayOfWeek.FRIDAY, "0196f6c4f97df3f48d570c23e46501ae")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateProgramaRadioWithoutProgramaRadioDtoNombre() {
        String albumId = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(ProgramaRadioApiController.PROGRAMAS_RADIO)
                .body(new ProgramaRadioDto(null, Boolean.FALSE, DayOfWeek.FRIDAY, albumId)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateProgramaRadioWithoutProgramaRadioDtoNocturno() {
        String albumId = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(ProgramaRadioApiController.PROGRAMAS_RADIO)
                .body(new ProgramaRadioDto("El Sotano", null, DayOfWeek.FRIDAY, albumId)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateProgramaRadioWithoutProgramaRadioDtoDiaEmision() {
        String albumId = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(ProgramaRadioApiController.PROGRAMAS_RADIO)
                .body(new ProgramaRadioDto("El Sotano", Boolean.FALSE, null, albumId)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    private String createSello() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).body(new SelloDto("Jerden", "Washington, Seattle")).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createAlbum() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Introducing The Sonics", "The Sonics", LocalDateTime.of(1967, Month.MARCH, 10, 23, 12, 11), 12, Genero.GARAGE, selloId)).post();
        return (String) new Client().submit(request).getBody();
    }


}
