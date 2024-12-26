package com.test.xpress.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse<T> {

    private List<String> mensajes;
    private T data;
    private String estado;

    public ApiResponse(List<String> mensajes, String estado, T data) {
        this.mensajes = mensajes;
        this.estado = estado;
        this.data = data;
    }

    public List<String> getMensaje() {
        return mensajes;
    }

    public void setMensaje(List<String> mensajes) {
        this.mensajes = mensajes;
    }


}
