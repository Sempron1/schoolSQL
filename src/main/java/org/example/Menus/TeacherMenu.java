package org.example.Menus;

import org.example.Command;
import org.example.Course;
import org.example.Education;
import org.example.Teacher;
import org.example.dao.CourseDao;
import org.example.dao.EducationDao;
import org.example.dao.TeacherDao;
import org.example.impl.CourseImpl;
import org.example.impl.EducationImpl;
import org.example.impl.TeacherImpl;

import java.util.*;

public class TeacherMenu implements Command {
    TeacherDao teacherDao = new TeacherImpl();
    EducationDao educationDao = new EducationImpl();
    CourseDao courseDao = new CourseImpl();
    Teacher teacher;
    Education education;
    Course course;
    static List<Course> courseSet = new ArrayList<>();


    private void searchMethod(){
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().toLowerCase();
        command = command.replaceAll("\\s+", "");
        switch (command) {
            case "add" -> {
                System.out.println("Write the name of the teacher, then the education id you want to him/her to be added to");
                System.out.println("For example: John Doe 1");
                teacherDao.create(new Teacher(name(sc), name(sc)), getEducationId(sc));
            }
            case "update" -> {
                System.out.println("Write the teacher id then the teacher updated name");
                System.out.println("For example: 1 John Doe");
                getTeacherId(sc).setName(name(sc), name(sc));
                teacherDao.update(teacher);
            }
            case "id" -> {
                System.out.println("To search for specific teacher write the teacher id here: ");
                System.out.println(getTeacherId(sc));
            }
            case "delete" -> {
                System.out.println("Write the id of the teacher you want to delete");
                getTeacherId(sc);
                teacherDao.delete(teacher);
            }
            case "addtocourse" -> {

                System.out.println("Write the course id then the teacher id please");
                courseSet.add(getCourseId(sc));
                teacherDao.setCourse(courseSet, getTeacherId(sc));
            }
            case "deletefromcourse" -> {
                System.out.println("Please write the id of the course then then id of the teacher");
                teacherDao.deleteFromCourse(sc.nextInt(), sc.nextInt());
            }

            case "all" -> teacherDao.getAll().forEach(System.out::println);
            case "commands" -> {
                System.out.println("Commands: add, update, id, delete, all, delete from course, add to course");
                searchMethod();
            }
            default -> {
                System.out.println("Försök igen!");
                execute();
            }
        }

    }

    private String name(Scanner sc){
        return sc.next();
    }
    private Teacher getTeacherId(Scanner sc){
        return teacher = teacherDao.getById(sc.nextInt());
    }
    private Course getCourseId(Scanner sc){
        return course = courseDao.getById(sc.nextInt());
    }
    private Education getEducationId(Scanner sc){
        return education = educationDao.getById(sc.nextInt());
    }

    private void printMenuOption(){
        System.out.println("Here you can search, add, remove, update anything related to the eduation table");
        System.out.println("write \"commands\" to get all the available");
    }

    @Override
    public void execute() {
        printMenuOption();
        searchMethod();
    }
}