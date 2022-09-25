package br.com.ernanilima.jcep.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = "id_country", length = 36, unique = true)
    private UUID idCountry;

    @Column(length = 50, nullable = false, unique = true)
    private String name; // nome

    @Column(length = 2, nullable = false, unique = true)
    private String acronym; // sigla

    @Column(length = 4, nullable = false, unique = true)
    private int code; // ibge

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<Region> regions;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<State> states;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<City> cities;

}
