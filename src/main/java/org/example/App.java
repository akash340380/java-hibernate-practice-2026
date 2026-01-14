package org.example;

import org.example.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class App {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student student = new Student();
        student.setName("Kunal");
        student.setEmail("kunal@test.hibernate.com");
        session.save(student);

        tx.commit();
        //Getting the student
        //Student getStudent = session.get(Student.class, 10); // null
        //System.out.println("Student Name::" + getStudent.getName()); // NullPointerException
        // Proxy Object will be returned (Lazy Loading)
        Student loadStudent = session.load(Student.class, 10);
        //if not found, then throws ObjectNotFoundException
        //System.out.println("Student Name::" + loadStudent.getName());
        //System.out.println("Student is created with Id::" + student.getId());

        //Getting the student using the setParameter

        Query query = session.createQuery("from Student s where s.id = : studentId");
        query.setParameter("studentId", 1);
        Student getStud1 = (Student) query.getSingleResult();

        System.out.println("Student Name ::" + getStud1.getName());
        System.out.println("Student Email ::" + getStud1.getEmail());

        // Getting the complete student list parameter

        Query query1 = session.createQuery("from Student");

        for (Object empObj : query1.getResultList()) {

            Student student1 = (Student) empObj;
            System.out.println("Student Name ::" + student1.getName());
            System.out.println("Student Email ::" + student1.getEmail());
        }

        session.close();
    }
}
