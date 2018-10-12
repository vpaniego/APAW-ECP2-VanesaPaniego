package api;

import api.apiControllers.AlbumApiController;
import api.apiControllers.SelloApiController;
import api.daos.DaoFactory;
import api.daos.memory.DaoMemoryFactory;
import api.dtos.AlbumDto;
import api.dtos.SelloDto;
import api.entities.Genero;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlbumIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private String createSello() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).body(new SelloDto("Sun Studio", "Memphis, Tennessee")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void createAlbum() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, selloId)).post();
        new Client().submit(request);
    }

    @Test
    void testAlbumInvalidRequest() {
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES + "/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createAlbumSelloIdNotFound() {
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, "c4ca4238a0b923820dcc509a6f75849b")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void createAlbumWithoutGenero() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, null, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createAlbumWithoutNombre() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto(null, "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createAlbumWithoutArtista() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", null, LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createAlbumWithoutFechaEdicion() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", null, 12, null, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createAlbumWithoutNumPistas() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), null, Genero.GARAGE, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }



}
