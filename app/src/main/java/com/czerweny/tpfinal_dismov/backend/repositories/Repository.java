package com.czerweny.tpfinal_dismov.backend.repositories;

import java.time.Instant;

public class Repository {

    static private final long FIVE_MINUTES_IN_SECONDS = 300;
    static private Instant eventsLastUpdated = null;

//    public static LiveData<Event> getEvent(String eventId) {
//        if (eventoEsViejo(eventId))
//            refrescarEvento(eventId);
//
//        MediatorLiveData<Event> data = new MediatorLiveData<>();
//
//        data.addSource(LocalDataBase.getEvento(eventId), eventRoom -> {
//            if (eventRoom != null)
//                data.setValue(new Event(eventRoom));
//        });
//
//        return data;
//    }
//
//    public static LiveData<List<Event>> getEvents() {
//        refreshEvents();
//
//        MediatorLiveData<List<Event>> data = new MediatorLiveData<>();
//
//        data.addSource(LocalDataBase.getEventsAsync(), eventsRoom -> {
//            List<Event> events = eventsRoomAEvents(eventsRoom);
//            data.setValue(events);
//        });
//
//        return data;
//    }
//
//    public static LiveData<List<Evento>> getEventosParaUsuarioCreador(String idUsuario) {
//        refrescarEventos();
//
//        MediatorLiveData<List<Evento>> data = new MediatorLiveData<>();
//
//        data.addSource(BaseDatosLocal.getEventosParaUsuarioCreador(idUsuario), eventosRoom -> {
//            List<Evento> eventos = eventosRoomAEventos(eventosRoom);
//            data.setValue(eventos);
//        });
//
//        return data;
//    }
//
//    public static LiveData<List<Evento>> getEventosParaUsuarioInteresado(String idUsuario) {
//        refrescarEventos();
//
//        MediatorLiveData<List<Evento>> data = new MediatorLiveData<>();
//
//        data.addSource(BaseDatosLocal.getEventosParaUsuarioInteresado(idUsuario), eventosRoom -> {
//            List<Evento> eventos = eventosRoomAEventos(eventosRoom);
//            data.setValue(eventos);
//        });
//
//        return data;
//    }
//
//    public static String crearIdDeEvento() {
//        return BaseDatosRemota.crearIdDeEvento();
//    }
//
//    public static Task<Void> guardarEvento(Evento evento) {
//        return BaseDatosRemota.guardarEvento(evento)
//                .addOnSuccessListener(aVoid -> BaseDatosLocal.guardarEvento(evento));
//    }
//
//    public static Task<Void> eliminarEvento(String idEvento) {
//        return BaseDatosRemota.eliminarEvento(idEvento)
//                .addOnSuccessListener(aVoid -> BaseDatosLocal.eliminarEvento(idEvento));
//    }
//
//    static private boolean eventoEsViejo(String idEvento) {
//        if (!BaseDatosLocal.existeEvento(idEvento))
//            return true;
//
//        Instant ultimaActualizacion = BaseDatosLocal.ultimaActualizacionEvento(idEvento);
//        Instant haceCincoMinutos = Instant.now().minusSeconds(CINCO_MINUTOS_EN_SEGUNDOS);
//
//        return ultimaActualizacion.isBefore(haceCincoMinutos);
//    }
//
//    static private void refrescarEvento(String idEvento) {
//        BaseDatosRemota.getEvento(idEvento)
//                .addOnSuccessListener(BaseDatosLocal::guardarEvento);
//    }
//
//    static private void refrescarEventos() {
//        Instant haceDiezMinutos = Instant.now().minusSeconds(CINCO_MINUTOS_EN_SEGUNDOS);
//
//        if (ultimaActualizacionEventos != null && ultimaActualizacionEventos.isAfter(haceDiezMinutos))
//            return;
//
//        BaseDatosRemota.getEventos()
//                .addOnSuccessListener(eventos -> {
//                    List<String> idEventosBorrados = obtenerIdsEventosBorrados(eventos);
//                    BaseDatosLocal.guardarYEliminarEventosEnMasa(eventos, idEventosBorrados);
//                    ultimaActualizacionEventos = Instant.now();
//                });
//    }
//
//    static private List<String> obtenerIdsEventosBorrados(List<Evento> eventosActualizados) {
//        List<String> idEventosViejos = new ArrayList<>();
//
//        for (EventoRoom eventoViejo : BaseDatosLocal.getEventosSync())
//            idEventosViejos.add(eventoViejo.id);
//
//        for (Evento eventoActualizado : eventosActualizados)
//            idEventosViejos.remove(eventoActualizado.getId());
//
//        return idEventosViejos;
//    }
//
//    public static LiveData<Usuario> getUsuario(String idUsuario) {
//        if (usuarioEsViejo(idUsuario))
//            refrescarUsuario(idUsuario);
//
//        MediatorLiveData<Usuario> data = new MediatorLiveData<>();
//
//        data.addSource(BaseDatosLocal.getUsuario(idUsuario), usuarioRoom -> {
//            if (usuarioRoom != null)
//                data.setValue(new Usuario(usuarioRoom));
//        });
//
//        return data;
//    }
//
//    private static boolean usuarioEsViejo(String idUsuario) {
//        if (!BaseDatosLocal.existeUsuario(idUsuario))
//            return true;
//
//        Instant ultimaActualizacion = BaseDatosLocal.ultimaActualizacionUsuario(idUsuario);
//        Instant haceDiezMinutos = Instant.now().minusSeconds(CINCO_MINUTOS_EN_SEGUNDOS);
//
//        return ultimaActualizacion.isBefore(haceDiezMinutos);
//    }
//
//    private static void refrescarUsuario(String idUsuario) {
//        BaseDatosRemota.getUsuario(idUsuario)
//                .addOnSuccessListener(BaseDatosLocal::guardarUsuario);
//    }
//
//    public static void guardarUsuario(Usuario usuario) {
//        BaseDatosRemota.guardarUsuario(usuario)
//                .addOnSuccessListener(aVoid -> BaseDatosLocal.guardarUsuario(usuario));
//    }
//
//    public static LiveData<List<Usuario>> getUsuariosAsistentesParaEvento(Evento evento) {
//        for (String idUsuario : evento.getIdUsuariosAsistentes())
//            if (usuarioEsViejo(idUsuario))
//                refrescarUsuario(idUsuario);
//
//        MediatorLiveData<List<Usuario>> data = new MediatorLiveData<>();
//
//        data.addSource(BaseDatosLocal.getUsuarios(evento.getIdUsuariosAsistentes()), usuariosRoom -> {
//            List<Usuario> usuarios = new ArrayList<>();
//
//            for (UsuarioRoom usuarioRoom : usuariosRoom)
//                usuarios.add(new Usuario(usuarioRoom));
//
//            data.setValue(usuarios);
//        });
//
//        return data;
//    }
//
//    private static List<Evento> eventosRoomAEventos(List<EventoRoom> eventosRoom) {
//        List<Evento> eventos = new ArrayList<>();
//
//        for (EventoRoom eventoRoom : eventosRoom)
//            eventos.add(new Evento(eventoRoom));
//
//        eventos.sort(Comparator
//                .comparing(Evento::getFechaInicio)
//                .thenComparing(Evento::getHoraInicio));
//
//        return eventos;
//    }
//
//    private static Task<Void> eliminarUsuario (String idUsuario) {
//        return BaseDatosRemota.eliminarUsuario(idUsuario)
//                .addOnSuccessListener(aVoid -> BaseDatosLocal.eliminarUsuario(idUsuario));
//    }
//
//    public static Task<Void> eliminarUsuarioYSusEventosCreados(String idUsuario) {
//        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
//
//        Utils.observarUnaSolaVez(getEventosParaUsuarioCreador(idUsuario), eventos -> {
//            List<Task<Void>> tasks = new ArrayList<>();
//
//            for (Evento evento : eventos) {
//                Evento eventoEliminado = evento.copy();
//                Task<Void> eliminarEventoTask = eliminarEvento(evento.getId())
//                        .addOnSuccessListener(aVoid -> Utils.notificarEliminacion(eventoEliminado));
//                tasks.add(eliminarEventoTask);
//            }
//
//            tasks.add(removerUsuarioDeSusEventosInteresado(idUsuario));
//
//            Tasks.whenAll(tasks)
//                    .continueWithTask(task -> {
//                        if (task.isSuccessful()) {
//                            return eliminarUsuario(idUsuario);
//                        } else {
//                            throw new Exception("Se falló en eliminar los eventos del usuario " + idUsuario);
//                        }
//                    })
//                    .addOnSuccessListener(aVoid -> taskCompletionSource.setResult(null))
//                    .addOnFailureListener(taskCompletionSource::setException);
//        });
//
//        return taskCompletionSource.getTask();
//    }
//
//    private static Task<Void> removerUsuarioDeSusEventosInteresado(String idUsuario) {
//        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
//
//        Utils.observarUnaSolaVez(getEventosParaUsuarioInteresado(idUsuario), eventos -> {
//            List<Task<Void>> tasks = new ArrayList<>();
//
//            for (Evento evento : eventos) {
//                evento.quitarUsuarioInteresado(idUsuario);
//                evento.quitarUsuarioAsistente(idUsuario);
//                tasks.add(guardarEvento(evento));
//            }
//
//            Tasks.whenAll(tasks)
//                    .addOnSuccessListener(aVoid -> taskCompletionSource.setResult(null))
//                    .addOnFailureListener(taskCompletionSource::setException);
//        });
//
//        return taskCompletionSource.getTask();
//    }
}
