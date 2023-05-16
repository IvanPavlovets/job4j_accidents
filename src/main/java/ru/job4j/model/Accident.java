package ru.job4j.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель данных описывающих правонарушение.
 *
 * @author Ivan Pavlovets
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @Column(name = "a_name")
    private String name;
    @Column(name = "text")
    private String text;
    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "TYPE_ID_FK"))
    private AccidentType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accident_rule", joinColumns = {
            @JoinColumn(name = "accident_id", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "rule_id", nullable = false, updatable = false)})
    private Set<Rule> rules = new HashSet<>();

    public void addRule(Rule rule) {
        rules.add(rule);
    }
}
