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

    private static final String REQUEST_ERROR = "request error: ";

    private static final String METHOD_ERROR = "method error: ";

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
                    this.doPut(request);
                    break;
                case PATCH:
                    this.doPatch(request);
                    break;
                case DELETE:
                    throw new RequestInvalidException(REQUEST_ERROR + request.getMethod() + ' ' + request.getPath());
                default:
                    throw new RequestInvalidException(METHOD_ERROR + request.getMethod());
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
            throw new RequestInvalidException(METHOD_ERROR + request.getMethod());
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(SelloApiController.SELLOS + SelloApiController.ID_ID)) {
            this.selloApiController.update(request.getPath(1), (SelloDto) request.getBody());
        } else {
            throw new RequestInvalidException(METHOD_ERROR + request.getMethod());
        }
    }

    private void doPatch(HttpRequest request) {
        if (request.isEqualsPath(AlbumApiController.ALBUMES + AlbumApiController.ID_ID + AlbumApiController.GENERO)) {
            this.albumApiController.updateGenero(request.getPath(1), (String) request.getBody());
        } else {
            throw new RequestInvalidException(METHOD_ERROR + request.getMethod());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(SelloApiController.SELLOS)) {
            response.setBody(this.selloApiController.readAll());
        } else {
            throw new RequestInvalidException(METHOD_ERROR + request.getMethod() + ' ' + request.getPath());
        }
    }
}
