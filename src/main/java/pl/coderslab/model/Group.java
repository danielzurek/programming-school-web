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
public class Group implements ActiveRecord<Group> {

    @Setter(AccessLevel.NONE)
    Integer id;
    String name;

    public static List<Group> findAll() throws SQLException {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT id, name FROM groups";
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Group group = new Group();
                    group.id = rs.getInt("id");
                    group.name = rs.getString("name");
                    groups.add(group);
                }
            }
        }
        return groups;
    }

    public static Optional<Group> findById(int id) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT name FROM groups WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Group group = new Group();
                    group.id = id;
                    group.name = rs.getString("name");
                    return Optional.of(group);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public Group save() throws SQLException {
        try (Connection connection = Database.getConnection()) {
            if (id == null) {
                String sql = "INSERT INTO groups(name) VALUES(?)";
                String[] generatedColumns = {"ID"};
                try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                    statement.setString(1, this.name);
                    if (statement.executeUpdate() > 0) {
                        ResultSet rs = statement.getGeneratedKeys();
                        if (rs.next()) {
                            this.id = rs.getInt(1);
                        }
                    }
                }
            } else {
                String sql = "UPDATE groups SET name = ? WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, this.name);
                    statement.setInt(2, this.id);
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
                String sql = "DELETE FROM groups WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    this.id = null;
                }
            }
        }
    }

    public List<User> getUsers() throws SQLException {
        return User.findByGroupId(this.id);
    }

}