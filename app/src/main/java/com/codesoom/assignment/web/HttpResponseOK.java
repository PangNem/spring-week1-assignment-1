package com.codesoom.assignment.web;

import com.sun.net.httpserver.HttpExchange;

public class HttpResponseOK extends HttpResponse{

    public HttpResponseOK(HttpExchange exchange) {
        super(exchange);
    }

    @Override
    protected int httpStatusCode() {
        return 200;
    }
}