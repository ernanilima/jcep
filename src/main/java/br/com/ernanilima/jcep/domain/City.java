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
@Table(name = "city")
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true)
    private UUID idCity;

    @Column(length = 50, nullable = false, unique = true)
    private String name; // nome

    @Column(length = 10, nullable = false, unique = true)
    private int code; // ibge

    @Column(length = 2, nullable = false)
    private Integer areaCode; // ddd

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id_country", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "region_id", referencedColumnName = "idRegion", nullable = false)
    private Region region;

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "idState", nullable = false)
    private State state;

}
