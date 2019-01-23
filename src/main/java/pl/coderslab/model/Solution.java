package pl.coderslab.model;

import lombok.Data;
import pl.coderslab.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Data
public class Solution implements ActiveRecord<Solution> {

    Timestamp createdAt;
    Timestamp updatedAt;
    String content;
    Integer exerciseId;
    Integer userId;

    public static List<Solution> findAll() throws SQLException {
        return findAll(null);
    }

    public static List<Solution> findAll(Integer limit) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM solutions ORDER BY updated_at DESC";
            try (Statement statement = connection.createStatement()) {
                if (limit != null) {
                    statement.setFetchSize(limit);
                    statement.setMaxRows(limit);
                    statement.setFetchDirection(ResultSet.FETCH_FORWARD);
                }
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Solution solution = new Solution();
                    solution.createdAt = rs.getTimestamp("created_at");
                    solution.updatedAt = rs.getTimestamp("updated_at");
                    solution.content = rs.getString("content");
                    solution.exerciseId = rs.getInt("exercise_id");
                    solution.userId = rs.getInt("user_id");
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }

    public static List<Solution> findByUserId(int userId) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM solutions WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Solution solution = new Solution();
                    solution.createdAt = rs.getTimestamp("created_at");
                    solution.updatedAt = rs.getTimestamp("updated_at");
                    solution.content = rs.getString("content");
                    solution.exerciseId = rs.getInt("exercise_id");
                    solution.userId = rs.getInt("user_id");
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }

    public static List<Solution> findByExerciseId(int exerciseId) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM solutions WHERE exercise_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, exerciseId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Solution solution = new Solution();
                    solution.createdAt = rs.getTimestamp("created_at");
                    solution.updatedAt = rs.getTimestamp("updated_at");
                    solution.content = rs.getString("content");
                    solution.exerciseId = rs.getInt("exercise_id");
                    solution.userId = rs.getInt("user_id");
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }

    public static Optional<Solution> findByUserIdAndExerciseId(int userId, int exerciseId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM solutions WHERE user_id = ? AND exercise_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setInt(2, exerciseId);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Solution solution = new Solution();
                    solution.createdAt = rs.getTimestamp("created_at");
                    solution.updatedAt = rs.getTimestamp("updated_at");
                    solution.content = rs.getString("content");
                    solution.exerciseId = rs.getInt("exercise_id");
                    solution.userId = rs.getInt("user_id");
                    return Optional.of(solution);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Solution save() throws SQLException {
        try (Connection connection = Database.getConnection()) {
            if (userId != null && exerciseId != null) {
                String sql = "INSERT INTO solutions(created_at, updated_at, content, exercise_id, user_id) VALUES(NOW(), NOW(), ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, this.content);
                    statement.setInt(2, this.exerciseId);
                    statement.setInt(3, this.userId);
                    statement.executeUpdate();
                } catch (SQLIntegrityConstraintViolationException ex) {
                    sql = "UPDATE solutions SET updated_at = NOW(), content = ? WHERE exercise_id = ? AND user_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setString(1, this.content);
                        statement.setInt(2, this.exerciseId);
                        statement.setInt(3, this.userId);
                        statement.executeUpdate();
                    }
                }
            }
        }
        return this;
    }

    @Override
    public void delete() throws SQLException {
        if (this.userId != null && this.exerciseId != null) {
            try (Connection connection = Database.getConnection()) {
                String sql = "DELETE FROM solutions WHERE user_id = ? AND exercise_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    statement.setInt(2, exerciseId);
                    statement.executeUpdate();
                    this.userId = null;
                    this.exerciseId = null;
                }
            }
        }
    }

    public Exercise getExercise() throws SQLException {
        return Exercise.findById(exerciseId).orElse(null);
    }

    public User getAuthor() throws SQLException {
        return User.findById(userId).orElse(null);
    }
}