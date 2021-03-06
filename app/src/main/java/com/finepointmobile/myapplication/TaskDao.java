package com.finepointmobile.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE task_id = :id")
    List<Task> getTaskById(int id);

    @Query("UPDATE Task SET short_text = :s_t, long_text = :l_t, check_text = :c_t , date_text = :d_t, expire_date = :e_d WHERE task_id = :id")
    void updateTask(int id, String s_t, String l_t, String c_t, String d_t, long e_d);

    @Query("UPDATE Task SET task_id = task_id - 1 WHERE task_id > :id")
    void reindexTasks(int id);

    @Query("DELETE FROM Task WHERE task_id = :id")
    void deleteTaskById(int id);

    @Query("SELECT * FROM Task ORDER BY expire_date")
    List<Task> getAllSorted();

    @Query("SELECT * FROM Task WHERE substr(date_text,1,6) = :date ORDER BY expire_date")
    List<Task> getTaskByDate(String date);

    @Query("SELECT MAX(id) FROM Task")
    int getMaxId();

    @Delete
    void deleteTask(Task task);

    @Insert
    void insertAll(Task... tasks);
}
