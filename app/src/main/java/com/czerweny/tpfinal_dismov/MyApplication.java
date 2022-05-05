package com.czerweny.tpfinal_dismov;

import android.app.Application;

import androidx.room.Room;

import com.czerweny.tpfinal_dismov.backend.databases.AppDatabase;
import com.czerweny.tpfinal_dismov.backend.models.Location;
import com.czerweny.tpfinal_dismov.backend.models.Notification;
import com.czerweny.tpfinal_dismov.backend.repositories.LocationsRepository;
import com.czerweny.tpfinal_dismov.backend.repositories.NotificationsRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyApplication  extends Application {

    static private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,
                "local-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        prePopulateDataBase();
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    private void prePopulateDataBase() {
        List<Location> locations = new ArrayList<>();

        locations.add(new Location("0", "Aula 1", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 1076, 867));
        locations.add(new Location("1", "Aula 2", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 1090, 990));
        locations.add(new Location("2", "Aula 3", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 1090, 1192));
        locations.add(new Location("3", "Aula 4", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 1090, 1292));
        locations.add(new Location("4", "Aula 5", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 1086, 1762));
        locations.add(new Location("5", "Aula 6", "Laboratorio Hidráulica", R.drawable.plano_fich_laboratorio_hidraulica_rot, 830, 254));
        locations.add(new Location("6", "Aula 7", "Laboratorio Hidráulica", R.drawable.plano_fich_laboratorio_hidraulica_rot, 1160, 1104));
        locations.add(new Location("7", "Aula 8", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 855, 1698));
        locations.add(new Location("8", "Aula 9", "Tercer Piso", R.drawable.plano_fich_tercer_piso_rot, 500, 1995));
        locations.add(new Location("9", "Aula 10", "Tercer Piso", R.drawable.plano_fich_tercer_piso_rot, 740, 2076));
        locations.add(new Location("10", "Aula Magna", "Planta Baja", R.drawable.plano_fich_planta_baja_rot, 1088, 1583));
        locations.add(new Location("11", "Aula de Dibujo", "Segundo Piso", R.drawable.plano_fich_segundo_piso_rot, 816, 1766));
        locations.add(new Location("12", "Laboratorio de Informática 1", "Primer Piso", R.drawable.plano_fich_primer_piso_rot, 1072, 1340));
        locations.add(new Location("13", "Laboratorio de Informática 2", "Primer Piso", R.drawable.plano_fich_primer_piso_rot, 1073, 1482));
        locations.add(new Location("14", "Laboratorio de Informática 3", "Primer Piso", R.drawable.plano_fich_primer_piso_rot, 896, 1135));
        locations.add(new Location("15", "Laboratorio de Informática 4", "Primer Piso", R.drawable.plano_fich_primer_piso_rot, 785, 1135));
        locations.add(new Location("16", "Laboratorio de Informática 5", "Laboratorio Hidráulica", R.drawable.plano_fich_laboratorio_hidraulica_rot, 938, 1676));
        locations.add(new Location("17", "Laboratorio de Electrónica", "Primer Piso", R.drawable.plano_fich_primer_piso_rot, 1076, 1646));
        locations.add(new Location("18", "Laboratorio de Química y Ambiente", "Segundo Piso", R.drawable.plano_fich_segundo_piso_rot, 544, 1748));

        for (Location location : locations) {
            LocationsRepository.saveLocation(location);
        }
    /*
        List<Notification> notifications = new ArrayList<>();

        notifications.add(new Notification("0", "Notification 1", new Date().toString(),"This is a test notification."));
        notifications.add(new Notification("1", "Notification 2", new Date().toString(),"This is a test notification."));
        notifications.add(new Notification("2", "Notification 3", new Date().toString(),"This is a test notification."));
        notifications.add(new Notification("3", "Notification 4", new Date().toString(),"This is a test notification."));
        notifications.add(new Notification("4", "Notification 5", new Date().toString(),"This is a test notification."));

        for (Notification notification : notifications) {
            NotificationsRepository.saveNotication(notification);
        }
    */
    }
}
