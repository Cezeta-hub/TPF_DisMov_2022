package com.czerweny.tpfinal_dismov.backend.databases.firebase;

import com.czerweny.tpfinal_dismov.backend.models.Course;

import java.util.HashMap;
import java.util.List;

public class FirebaseCourse {
    private String faculty;
    private Integer n_students;
    private Integer n_scores;
    private Double acum_score;
    private Double score;
    private List<String> professors;
    private String hours;

    public FirebaseCourse(String faculty, Integer n_students, Integer n_scores, Double acum_score, Double score, List<String> professors, String hours) {
        this.faculty = faculty;
        this.n_students = n_students;
        this.n_scores = n_scores;
        this.acum_score = acum_score;
        this.score = score;
        this.professors = professors;
        this.hours = hours;
    }

    public FirebaseCourse(Course course) {
        this.faculty = course.getFaculty();
        this.n_students = course.getN_students();
        this.n_scores = course.getN_scores();
        this.acum_score = course.getAcum_score();
        this.score = course.getScore();
        this.professors = course.getProfessors();
        this.hours = course.getHours();
    }

    public FirebaseCourse() { }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }

    public Integer getN_students() { return n_students; }
    public void setN_students(Integer n_students) { this.n_students = n_students; }

    public Integer getN_scores() { return n_scores; }
    public void setN_scores(Integer n_scores) { this.n_scores = n_scores; }

    public Double getAcum_score() { return acum_score; }
    public void setAcum_score(Double acum_score) { this.acum_score = acum_score; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public List<String> getProfessors() { return this.professors; }
    public void setProfessors(List<String> professors) { this.professors = professors; }

    public String getHours() { return this.hours; }
    public void setHours(String hours) { this.hours = hours; }


    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("faculty", faculty);
        result.put("n_students", n_students);
        result.put("n_scores", n_scores);
        result.put("acum_score", acum_score);
        result.put("score", score);
        result.put("professors", professors);
        result.put("hours", hours);

        return result;
    }

    public HashMap<String, Object> toMapUpdateScore() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("n_scores", n_scores);
        result.put("acum_score", acum_score);
        result.put("score", score);
        return result;
    }

    public HashMap<String, Object> toMapUpdateStudents() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("n_students", n_students);
        return result;
    }
}
