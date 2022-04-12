package com.czerweny.tpfinal_dismov.backend.models;

import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseCourse;
import com.google.common.collect.Iterables;

import java.util.List;

public class Course {
    private String name;
    private String faculty;
    private String bachelor;
    private Double score;
    private Double acum_score;
    private Integer n_scores;
    private Integer n_students;
    private List<String> professors;
    private String hours;

    public Course(String name, String faculty, String bachelor, Double score, Double acum_score, Integer n_scores, Integer n_students, List<String> professors, String hours) {
        this.name = name;
        this.faculty = faculty;
        this.bachelor = bachelor;
        this.score = score;
        this.acum_score = acum_score;
        this.n_scores = n_scores;
        this.n_students = n_students;
        this.professors = professors;
        this.hours = hours;
    }

    public Course(String courseId, FirebaseCourse firebaseCourse) {
        this.name = courseId;
        this.faculty = firebaseCourse.getFaculty();
        this.bachelor = "Ingeniería Informática";
        this.score = firebaseCourse.getScore();
        this.acum_score = firebaseCourse.getAcum_score();
        this.n_scores = firebaseCourse.getN_scores();
        this.n_students = firebaseCourse.getN_students();
        this.professors = firebaseCourse.getProfessors();
        this.hours = firebaseCourse.getHours();
    }

    public Course() { }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public String getBachelor() { return bachelor; }
    public void setBachelor(String bachelor) { this.bachelor = bachelor; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public Double getAcum_score() { return acum_score; }
    public void setAcum_score(Double acum_score) { this.acum_score = acum_score; }

    public Integer getN_scores() { return n_scores; }
    public void setN_scores(int n_scores) { this.n_scores = n_scores; }

    public int getN_students() { return n_students; }
    public void setN_students(int n_students) { this.n_students = n_students; }

    public List<String> getProfessors() { return professors; }
    public void setProfessors(List<String> professors) { this.professors = professors; }

    public String getHours() { return hours; }
    public void setHours(String hours) { this.hours = hours; }

    public String getProfessorsAsString() {
        String result = "(Sin definir)";
        if (this.professors != null && this.professors.size() != 0) {
            result = this.professors.get(0);
            for (String proffesor : Iterables.skip(this.professors, 1)) {
                result = result + ", " + proffesor;
            }
        }
        return result;
    }

    public String getHoursAsString() {
        String result = "(Sin definir)";
        if (this.hours != null && !this.hours.equals("")) {
            result = hours;
        }
        return result;
    }
}
