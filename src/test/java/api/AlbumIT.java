package api;

import api.apiControllers.AlbumApiController;
import api.apiControllers.SelloApiController;
import api.dtos.AlbumDto;
import api.dtos.AlbumQueryDto;
import api.dtos.SelloDto;
import api.entities.Genero;
import http.Client;
import http.HttpException;
import http.HttpRequest;
import http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlbumIT {

    @Test
    void testCreateAlbum() {
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
    void testCreateAlbumSelloIdNotFound() {
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, "c4ca4238a0b923820dcc509a6f75849b")).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutGenero() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, null, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutNombre() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto(null, "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutArtista() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", null, LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutFechaEdicion() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", null, 12, null, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateAlbumWithoutNumPistas() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), null, Genero.GARAGE, selloId)).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateAlbumGenero() {
        String albumId = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES).path(AlbumApiController.ID_ID)
                .expandPath(albumId).path(AlbumApiController.GENERO).body(Genero.ROCK.name()).patch();
        new Client().submit(request);
    }

    @Test
    void testUpdateAlbumGeneroWithoutGenero() {
        String albumId = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES).path(AlbumApiController.ID_ID)
                .expandPath(albumId).path(AlbumApiController.GENERO).body(null).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdateAlbumGeneroIdNotFound() {
        String albumId = "c4ca4238a0b923820dcc509a6f75849b";
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES).path(AlbumApiController.ID_ID)
                .expandPath(albumId).path(AlbumApiController.GENERO).body(Genero.ROCK.name()).patch();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void testSearchAlbumNumPistas() {
        String id = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES).path(AlbumApiController.SEARCH)
                .param("q", "numPistas:>=7").get();
        List<AlbumQueryDto> albumes = (List<AlbumQueryDto>) new Client().submit(request).getBody();
        assertTrue(!albumes.isEmpty());
    }

    @Test
    void testSearchAlbumNumPistasWithoutParamQ() {
        String id = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES).path(AlbumApiController.SEARCH)
                .param(null, "numPistas:>=7").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testSearchAlbumNumPistasParamError() {
        String id = this.createAlbum();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES).path(AlbumApiController.SEARCH)
                .param("q", "error:>=7").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    private String createSello() {
        HttpRequest request = HttpRequest.builder().path(SelloApiController.SELLOS).body(new SelloDto("Sun Studio", "Memphis, Tennessee")).post();
        return (String) new Client().submit(request).getBody();
    }

    private String createAlbum() {
        String selloId = this.createSello();
        HttpRequest request = HttpRequest.builder().path(AlbumApiController.ALBUMES)
                .body(new AlbumDto("Here are The Sonics", "The Sonics", LocalDateTime.of(1965, Month.MARCH, 5, 12, 30, 57), 12, Genero.GARAGE, selloId)).post();
        return (String) new Client().submit(request).getBody();
    }
}
