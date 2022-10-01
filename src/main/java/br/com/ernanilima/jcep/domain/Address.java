package br.com.ernanilima.jcep.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id_address", length = 36, unique = true)
    private UUID idAddress;

    @Column(name = "zip_code", length = 8, unique = true, nullable = false)
    private String zipCode; // cep

    @ManyToOne
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @JoinColumn(name = "country_id", referencedColumnName = "id_country", nullable = false)
    private Country country; // pais

    @ManyToOne
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @JoinColumn(name = "region_id", referencedColumnName = "id_region", nullable = false)
    private Region region; // regiao

    @ManyToOne
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @JoinColumn(name = "state_id", referencedColumnName = "id_state", nullable = false)
    private State state; // unidade federativa

    @ManyToOne
    @Setter
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @JoinColumn(name = "city_id", referencedColumnName = "id_city", nullable = false)
    private City city; // cidade

    @Column(length = 50, nullable = false)
    private String district; // bairro

    @Column(length = 50, nullable = false)
    private String street; // rua

    @Column(length = 50)
    private String complement; // complemento

    @Column(length = 10, nullable = false)
    private Integer code; // ibge

}
