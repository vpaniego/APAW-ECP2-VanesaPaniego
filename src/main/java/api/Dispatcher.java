package api;

import api.apiControllers.AlbumApiController;
import api.apiControllers.SelloApiController;
import api.dtos.AlbumDto;
import api.dtos.SelloDto;
import api.exceptions.ArgumentNotValidException;
import api.exceptions.NotFoundException;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    private SelloApiController selloApiController = new SelloApiController();

    private AlbumApiController albumApiController = new AlbumApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request, response);
                    break;
                case PATCH:
                    this.doPatch(request, response);
                    break;
                case DELETE:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                default:
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(SelloApiController.SELLOS)) {
            response.setBody(this.selloApiController.create((SelloDto) request.getBody()));
        } else if (request.isEqualsPath(AlbumApiController.ALBUMES)) {
            response.setBody(this.albumApiController.create((AlbumDto) request.getBody()));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

    private void doPut(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(SelloApiController.SELLOS + SelloApiController.ID_ID)) {
            this.selloApiController.update(request.getPath(1), (SelloDto) request.getBody());
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(SelloApiController.SELLOS)) {
            response.setBody(this.selloApiController.readAll());
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPatch(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(AlbumApiController.ALBUMES + AlbumApiController.ID_ID + AlbumApiController.GENERO)) {
            this.albumApiController.updateGenero(request.getPath(1), (String) request.getBody());
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

}
