package com.czerweny.tpfinal_dismov;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

public class Utils {
    public static LiveData<ArrayList<Object>> zipLiveData(LiveData<?>... liveItems){
        final ArrayList<Object> zippedObjects = new ArrayList<>();
        final MediatorLiveData<ArrayList<Object>> mediator = new MediatorLiveData<>();
        for(LiveData<?> item: liveItems){
            mediator.addSource(item, new Observer<Object>() {
                @Override
                public void onChanged(@Nullable Object o) {
                    if(!zippedObjects.contains(o)){
                        zippedObjects.add(o);
                    }
                    mediator.setValue(zippedObjects);
                }
            });
        }
        return mediator;
    }

    public static String getCurrentDate(String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static <T> void observeOneTime(LiveData<T> liveData, Observer<T> observer) {
        liveData.observeForever(new Observer<T>() {
            @Override
            public void onChanged(T t) {
                liveData.removeObserver(this);
                observer.onChanged(t);
            }
        });
    }
}