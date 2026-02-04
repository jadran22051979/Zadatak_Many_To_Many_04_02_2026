package org.example.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private int durationMonths;

    private BigDecimal salary;

    @ManyToMany(mappedBy = "contracts")
    private Set<Person> persons = new HashSet<>();

    @ManyToMany(mappedBy = "contracts")
    private Set<Company> companies = new HashSet<>();

    public Contract() {
    }

    public Contract(LocalDate startDate, int durationMonths, BigDecimal salary) {
        this.startDate = startDate;
        this.durationMonths = durationMonths;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public Set<Person> getPersons() {
        return persons;
    }
    public BigDecimal getSalary() {
        return salary;
    }


    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

}
