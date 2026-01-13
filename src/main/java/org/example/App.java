package org.example;

import org.example.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Student getStudent = session.get(Student.class, 10); // null
        System.out.println("Student Name::" + getStudent.getName()); // NullPointerException
        // Proxy Object will be returned (Lazy Loading)
        Student loadStudent = session.load(Student.class, 10);
        //if not found, then throws ObjectNotFoundException
        System.out.println("Student Name::" + loadStudent.getName());

        session.close();
        System.out.println("Student is created with Id::" + student.getId());
    }
}
