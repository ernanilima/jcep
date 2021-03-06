package br.com.ernanilima.jcep.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true)
    private UUID idAddress;

    @Column(length = 8, unique = true, nullable = false)
    private Integer zipCode; // cep

    @ManyToOne
    @JoinColumn(name = "idCountry")
    private Country country; // pais

    @ManyToOne
    @JoinColumn(name = "idRegion")
    private Region region; // regiao

    @ManyToOne
    @JoinColumn(name = "idState")
    private State state; // unidade federativa

    @ManyToOne
    @JoinColumn(name = "idCity")
    private City city; // cidade

    @Column(length = 50, nullable = false)
    private String district; // bairro

    @Column(length = 50, nullable = false)
    private String street; // rua

    @Column(length = 50)
    private String complement; // complemento

    @Column(length = 10, nullable = false, unique = true)
    private Integer code; // ibge

}
