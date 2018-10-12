package api;

import api.apiControllers.SelloApiController;
import api.dtos.SelloDto;
import api.exceptions.RequestInvalidException;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpStatus;

public class Dispatcher {

    private SelloApiController selloApiController = new SelloApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                case GET:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                case PUT:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                case PATCH:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                case DELETE:
                    throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
                default:
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        //TODO. Crear primero SelloApiController
        if (request.isEqualsPath(SelloApiController.SELLOS)) {
            response.setBody(this.selloApiController.create((SelloDto) request.getBody()));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

}
