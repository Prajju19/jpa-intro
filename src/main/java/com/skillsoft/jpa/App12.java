package com.skillsoft.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App12 {

    public static void main( String[] args )
    {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("EmployeeDBUnit");
        EntityManager entityManager = factory.createEntityManager();

        try {

            entityManager.getTransaction().begin();

            Department1 techDept = new Department1("Tech", "Floor 3");
            Department1 researchDept = new Department1("Research", "Floor 5");

            EmployeeContactInfo eciOne = new EmployeeContactInfo("101 Main St", "555-101");
            EmployeeContactInfo eciTwo = new EmployeeContactInfo("51 Broadway", "555-155");
            EmployeeContactInfo eciThree = new EmployeeContactInfo("24 Walnut St", "555-222");

            Employee6 firstEmployee = new Employee6("Peter", "Holland",
                    "Manager", 120000d);

            Employee6 secondEmployee = new Employee6("Bruce", "Brenner",
                    "Analyst", 80000d);

            Employee6 thirdEmployee = new Employee6("Naomi", "Miller",
                    "Vice President", 160000d);

            firstEmployee.setContactInfo(eciOne);
            secondEmployee.setContactInfo(eciTwo);
            thirdEmployee.setContactInfo(eciThree);

            firstEmployee.setDepartment(techDept);
            secondEmployee.setDepartment(techDept);
            thirdEmployee.setDepartment(researchDept);

            entityManager.persist(techDept);
            entityManager.persist(researchDept);

            entityManager.persist(firstEmployee);
            entityManager.persist(secondEmployee);
            entityManager.persist(thirdEmployee);

            entityManager.persist(eciOne);
            entityManager.persist(eciTwo);
            entityManager.persist(eciThree);


            List<Employee6> employeeList = entityManager
                    .createQuery("SELECT ed FROM Employee6 ed")
                    .getResultList();

            System.out.println("\n****************************\n");
            System.out.println("The employees in the Employee table:");
            for(Employee6 emp: employeeList){
                System.out.println(emp);
            }

            System.out.println("\n****************************\n");

            List<EmployeeContactInfo> contactInfos = entityManager
                    .createQuery("SELECT eci FROM EmployeeContactInfo eci")
                    .getResultList();

            System.out.println("\nThe contact details for employees:\n");
            for(EmployeeContactInfo eci: contactInfos){
                System.out.println(eci);
            }

            System.out.println("\n****************************\n");

            List<Department1> departments = entityManager
                    .createQuery("SELECT dep FROM Department dep")
                    .getResultList();

            System.out.println("\nThe departments on record:\n");
            for(Department1 dep: departments){
                System.out.println(dep);
            }

            System.out.println("\n****************************\n");

        }
        catch (Exception exception){

            System.err.println("An exception occurred:" + exception);
        }
        finally {

            entityManager.getTransaction().commit();

            entityManager.close();
            factory.close();
        }

    }
}
