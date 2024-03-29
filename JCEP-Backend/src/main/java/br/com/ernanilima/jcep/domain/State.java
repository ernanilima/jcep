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
@Table(name = "state")
public class State implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id_state", length = 36, unique = true)
    private UUID idState;

    @Column(length = 50, nullable = false, unique = true)
    private String name; // nome

    @Column(length = 2, nullable = false, unique = true)
    private String acronym; // sigla

    @Column(length = 2, nullable = false, unique = true)
    private int code; // ibge

    @ManyToOne
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @JoinColumn(name = "country_id", referencedColumnName = "id_country", nullable = false)
    private Country country;

    @ManyToOne
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @JoinColumn(name = "region_id", referencedColumnName = "id_region", nullable = false)
    private Region region;

}
