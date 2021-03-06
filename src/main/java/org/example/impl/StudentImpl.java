package org.example.impl;

import org.example.tables.Course;
import org.example.tables.Education;
import org.example.tables.Student;
import org.example.dao.StudentDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentImpl implements StudentDao {
    EntityManagerFactory emf;
    EntityManager em;

    public StudentImpl() {
        emf = Persistence.createEntityManagerFactory("jpa");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Student student, Education education) {
        em.getTransaction().begin();
        student.setEducation(education);
        em.persist(student);
        em.getTransaction().commit();
    }

    @Override
    public Student getById(int id) {
        return em.find(Student.class, id);
    }

    @Override
    public void update(Student student) {
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Student student) {
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
    }

    @Override
    public void setCourseAndGrade(Student student, Course course) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();

    }

    @Override
    public List<Student> getAll() {
        return em.createQuery("SELECT student FROM Student student", Student.class).getResultList();
    }

    @Override
    public List<Student> getByEducation(int educationId) {
        TypedQuery<Student> query = em.createQuery("SELECT student FROM Student student WHERE education.id = :education", Student.class);
        query.setParameter("education", educationId);
        return query.getResultList();
    }

}
