package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.model.Company;
import org.example.model.Contract;
import org.example.model.Person;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("many");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        Person p1 = new Person("Hrvoje Horvat");
        Person p2 = new Person("Ana Anić");
        Person p3 = new Person("Ivo Ivić");


        Company c1 = new Company("APIS d.o.o");
        Company c2 = new Company("Nexi");
        Company c3 = new Company("Siemens");

        Contract contract1 = new Contract(
                LocalDate.now(),
                12,
                new BigDecimal("3000.00")
        );


        povezivanjePersonAndContract(p1, contract1);
        povezivanjePersonAndContract(p2, contract1);

        povezivanjeCompanyAndContract(c1, contract1);
        povezivanjeCompanyAndContract(c2, contract1);


        // spremanje svih
        tx.begin();
        em.persist(contract1);
        em.persist(p1);
        em.persist(p2);
        em.persist(c1);
        em.persist(c2);
        tx.commit();

        // primjer ažuriranja
        tx.begin();
        Person osobaAzuriranje = em.find(Person.class, p1.getId());
        if (osobaAzuriranje != null) {
            osobaAzuriranje.setName("Hrvoje Horvat ažuriran");
            System.out.println("Ažurirano ime je:\t" + osobaAzuriranje.getName());
        }
        tx.commit();


        // brisanje
        tx.begin();
        Person osobaZaBrisanje = em.find(Person.class, p2.getId());
        if (osobaZaBrisanje != null) {
            System.out.println("Pronađena osoba za Brisanje  je:\t" + osobaZaBrisanje.getName());
            em.remove(osobaZaBrisanje);
        }

        tx.commit();
        em.close();
        emf.close();
    }


    private static void povezivanjePersonAndContract(Person person, Contract contract) {
        person.getContracts().add(contract);
        contract.getPersons().add(person);
    }

    private static void povezivanjeCompanyAndContract(Company company, Contract contract) {
        company.getContracts().add(contract);
        contract.getCompanies().add(company);
    }
}
