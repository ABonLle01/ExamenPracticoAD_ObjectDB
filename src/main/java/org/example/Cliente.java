package org.example;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
public class Cliente {
    private Long id;
    private String nombre;
    private Long total;
    private String estado;

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}
