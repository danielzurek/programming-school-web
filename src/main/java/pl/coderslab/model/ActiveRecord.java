package pl.coderslab.model;

import java.sql.SQLException;

public interface ActiveRecord<T> {

    T save() throws SQLException;

    void delete() throws SQLException;
}