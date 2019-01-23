package pl.coderslab.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.coderslab.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Exercise implements ActiveRecord<Exercise> {

    @Setter(AccessLevel.NONE)
    Integer id;
    String title;
    String description;

    public static List<Exercise> findAll() throws SQLException {
        List<Exercise> exercises = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT id, title, description FROM exercises";
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Exercise exercise = new Exercise();
                    exercise.id = rs.getInt("id");
                    exercise.title = rs.getString("title");
                    exercise.description = rs.getString("description");
                    exercises.add(exercise);
                }
            }
        }
        return exercises;
    }

    public static Optional<Exercise> findById(int id) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT title, description FROM exercises WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Exercise exercise = new Exercise();
                    exercise.id = id;
                    exercise.title = rs.getString("title");
                    exercise.description = rs.getString("description");
                    return Optional.of(exercise);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public Exercise save() throws SQLException {
        try (Connection connection = Database.getConnection()) {
            if (id == null) {
                String sql = "INSERT INTO exercises(title, description) VALUES(?, ?)";
                String[] generatedColumns = {"ID"};
                try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                    statement.setString(1, this.title);
                    statement.setString(2, this.description);
                    if (statement.executeUpdate() > 0) {
                        ResultSet rs = statement.getGeneratedKeys();
                        if (rs.next()) {
                            this.id = rs.getInt(1);
                        }
                    }
                }
            } else {
                String sql = "UPDATE exercises SET title = ?, description = ? WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, this.title);
                    statement.setString(2, this.description);
                    statement.setInt(3, this.id);
                    statement.executeUpdate();
                }
            }
        }
        return this;
    }

    @Override
    public void delete() throws SQLException {
        if (this.id != null) {
            try (Connection connection = Database.getConnection()) {
                String sql = "DELETE FROM exercises WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    this.id = null;
                }
            }
        }
    }
}