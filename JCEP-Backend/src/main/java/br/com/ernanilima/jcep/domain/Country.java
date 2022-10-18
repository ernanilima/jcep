package br.com.ernanilima.jcep.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id_country", length = 36, unique = true)
    private UUID idCountry;

    @Column(length = 50, nullable = false, unique = true)
    private String name; // nome

    @Column(length = 2, nullable = false, unique = true)
    private String acronym; // sigla

    @Column(length = 4, nullable = false, unique = true)
    private int code; // ibge

}
