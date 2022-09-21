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
@Table(name = "region")
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true)
    private UUID idRegion;

    @Column(length = 50, nullable = false, unique = true)
    private String name; // nome

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "idCountry", nullable = false)
    private Country country;

    @JsonIgnore
    @OneToMany(mappedBy = "region")
    private List<State> states;

}
