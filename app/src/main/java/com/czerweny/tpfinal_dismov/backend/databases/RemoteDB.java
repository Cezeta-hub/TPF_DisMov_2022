package com.czerweny.tpfinal_dismov.backend.databases;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;

import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseComment;
import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseCourse;
import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseFile;
import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.Course;
import com.czerweny.tpfinal_dismov.backend.models.File;
import com.czerweny.tpfinal_dismov.backend.models.User;
import com.czerweny.tpfinal_dismov.backend.databases.firebase.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RemoteDB {
    @SuppressLint("StaticFieldLeak")
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String usersNode = "Users";
    private static final String coursesNode = "Courses";
    private static final String filesNode = "Files";
    private static final String commentsNode = "Reviews";

    /**-------------------------------------------------**/
    /**---                   USERS                   ---**/
    /**-------------------------------------------------**/
    public static Task<User> getUser(String userId) {
        TaskCompletionSource<User> taskCompletionSource = new TaskCompletionSource<>();

        db.collection(usersNode).document(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user = new User(document.getId(),document.toObject(FirebaseUser.class));
                                taskCompletionSource.setResult(user);
                            } else {
                                taskCompletionSource.setException(new Exception("No existe un usuario con ese Id."));
                            }
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener el usuario."));
                        }
                    }
                });

        return taskCompletionSource.getTask();
    }

    public static Task<Void> postUser(User user) {
        return db.collection(usersNode).document(user.getEmail())
                .set(new FirebaseUser(user).toMap());
    }

    public static Task<Void> updateUser(User user) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(usersNode).document(user.getEmail())
                .update(new FirebaseUser(user).toMapUpdateLogin())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudo actualizar el usuario"));
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Void> updateUserCourses(User user) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(usersNode).document(user.getEmail())
                .update(new FirebaseUser(user).toMapUpdateCourses())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudo actualizar la lista de materias del usuario"));
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Void> updateUserData(User user) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(usersNode).document(user.getEmail())
                .update(new FirebaseUser(user).toMapUpdateData())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudieron actualizar los datos del usuario"));
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Void> deleteUser(String userId) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(usersNode).document(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                db.collection(usersNode).document(document.getId()).delete()
                                        .addOnSuccessListener(aVoid -> taskCompletionSource.setResult(null));
                            } else {
                                taskCompletionSource.setException(new Exception("No existe un usuario con ese Id."));
                            }
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener el usuario."));
                        }
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Boolean> userExists(String userId) {
        TaskCompletionSource<Boolean> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(usersNode).document(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                taskCompletionSource.setResult(true);
                            } else {
                                taskCompletionSource.setResult(false);
                                taskCompletionSource.setException(new Exception("No existe un usuario con ese Id."));
                            }
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener el usuario"));
                        }
                    }
                });
        return taskCompletionSource.getTask();
    }

    /**-------------------------------------------------**/
    /**---                  COURSES                  ---**/
    /**-------------------------------------------------**/

    public static Task<List<Course>> getCourses() {
        TaskCompletionSource<List<Course>> taskCompletionSource = new TaskCompletionSource<>();

        db.collection(coursesNode).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Course> courses = new ArrayList<Course>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Course course = new Course(document.getId(),document.toObject(FirebaseCourse.class));
                                courses.add(course);
                            }
                            taskCompletionSource.setResult(courses);
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener la lista de materias."));
                        }
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Course> getCourse(String courseId) {
        TaskCompletionSource<Course> taskCompletionSource = new TaskCompletionSource<>();

        db.collection(coursesNode).document(courseId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Course materia = new Course(document.getId(),document.toObject(FirebaseCourse.class));
                                taskCompletionSource.setResult(materia);
                            } else {
                                taskCompletionSource.setException(new Exception("No existe una materia con ese ID."));
                            }
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener la materia."));
                        }
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<List<Comment>> getCourseComments(String courseId) {
        TaskCompletionSource<List<Comment>> taskCompletionSource = new TaskCompletionSource<>();

        db.collection(coursesNode).document(courseId).collection(commentsNode).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Comment> comments = new ArrayList<Comment>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Comment comment = new Comment(courseId, document.toObject(FirebaseComment.class));
                                comments.add(comment);
                            }
                            taskCompletionSource.setResult(comments);
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener la lista de comentarios de la materia."));
                        }
                    }
                });

        return taskCompletionSource.getTask();
    }

    public static Task<List<File>> getCourseFiles(String courseId) {
        TaskCompletionSource<List<File>> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(coursesNode).document(courseId).collection(filesNode).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<File> files = new ArrayList<File>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                File file = new File(courseId, document.toObject(FirebaseFile.class));
                                files.add(file);
                            }
                            taskCompletionSource.setResult(files);
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener la lista de archivos de la materia."));
                        }
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<List<Course>> getCoursesForProfile(List<String> courseIds) {
        TaskCompletionSource<List<Course>> taskCompletionSource = new TaskCompletionSource<>();

        db.collection(coursesNode).whereIn(FieldPath.documentId(), courseIds).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Course> courses = new ArrayList<Course>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Course course = new Course(document.getId(),document.toObject(FirebaseCourse.class));
                                courses.add(course);
                            }
                            taskCompletionSource.setResult(courses);
                        } else {
                            taskCompletionSource.setException(new Exception("No se pudo obtener la lista de materias"));
                        }
                    }
                });

        return taskCompletionSource.getTask();
    }

    public static Task<Void> updateCourseScore(Course course) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(coursesNode).document(course.getName())
                .update(new FirebaseCourse(course).toMapUpdateScore())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudo actualizar la calificación de la materia"));
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Void> updateCourseStudents(Course course) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(coursesNode).document(course.getName())
                .update(new FirebaseCourse(course).toMapUpdateStudents())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudo actualizar la lista de alumnos de la materia"));
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Void> postCourseComment(Comment comment) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(coursesNode).document(comment.getCourseId()).collection(commentsNode)
                .add(new FirebaseComment(comment).toMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudo guardar el comentario en la materia"));
                    }
                });
        return taskCompletionSource.getTask();
    }

    public static Task<Void> postCourseFile(File file) {
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        db.collection(filesNode).document(file.getCourseId()).collection(filesNode)
                .add(new FirebaseFile(file).toMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) { }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        taskCompletionSource.setException(new Exception("No se pudo guardar el archivo en la materia"));
                    }
                });
        return taskCompletionSource.getTask();
    }
}
