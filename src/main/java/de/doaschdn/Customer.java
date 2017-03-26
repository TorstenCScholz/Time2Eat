package de.doaschdn;

import lombok.*;

import javax.persistence.*;

@Table(
        name = "Customer",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"firstName", "lastName"})}
)
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
