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
@Table(name = "state")
public class State implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true)
    private UUID idState;

    @Column(length = 50, nullable = false, unique = true)
    private String name; // nome

    @Column(length = 2, nullable = false, unique = true)
    private String acronym; // sigla

    @Column(length = 2, nullable = false, unique = true)
    private int code; // ibge

    @ManyToOne
    @JoinColumn(name = "idCountry")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "idRegion")
    private Region region;

}
