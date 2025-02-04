package com.example.crud.domain.product;

import jakarta.persistence.*;
import lombok.*;

@Table(name="product")
@Entity(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Integer price_in_cents;

    private Boolean active;

    //Construtor da classe-> quando instancia passa parametros default para salvar os atributos
    public ProductEntity(RequestProductDTO requestProductDTO){
        this.name = requestProductDTO.name();
        this.price_in_cents = requestProductDTO.price_in_cents();
        this.active = true;
    }

}
