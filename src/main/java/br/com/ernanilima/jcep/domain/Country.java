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
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true)
    private UUID idCountry;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 2, nullable = false)
    private String acronym;

    @Column(length = 4, nullable = false)
    private int code;

    @JsonIgnore
    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private List<Region> regions;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<State> states;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<City> cities;

}
