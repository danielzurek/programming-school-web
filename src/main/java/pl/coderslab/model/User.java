package pl.coderslab.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.database.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class User implements ActiveRecord<User> {

    @Setter(AccessLevel.NONE)
    Integer id;
    String email;
    @ToString.Exclude
    String password;
    String firstName;
    String lastName;
    Integer groupId;

    public static List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM users";
            try (Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    User user = new User();
                    user.id = rs.getInt("id");
                    user.email = rs.getString("email");
                    user.password = rs.getString("password");
                    user.firstName = rs.getString("first_name");
                    user.lastName = rs.getString("last_name");
                    user.groupId = rs.getInt("group_id");
                    users.add(user);
                }
            }
        }
        return users;
    }

    public static Optional<User> findById(int id) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    User user = new User();
                    user.id = id;
                    user.email = rs.getString("email");
                    user.password = rs.getString("password");
                    user.firstName = rs.getString("first_name");
                    user.lastName = rs.getString("last_name");
                    user.groupId = rs.getInt("group_id");
                    return Optional.of(user);
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    public static List<User> findByGroupId(int groupId) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM users WHERE group_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, groupId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.id = rs.getInt("id");
                    user.email = rs.getString("email");
                    user.password = rs.getString("password");
                    user.firstName = rs.getString("first_name");
                    user.lastName = rs.getString("last_name");
                    user.groupId = rs.getInt("group_id");
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public User save() throws SQLException {
        try (Connection connection = Database.getConnection()) {
            if (id == null) {
                String sql = "INSERT INTO users(email, password, first_name, last_name, group_id) VALUES(?, ?, ?, ?, ?)";
                String[] generatedColumns = {"ID"};
                try (PreparedStatement statement = connection.prepareStatement(sql, generatedColumns)) {
                    statement.setString(1, this.email);
                    statement.setString(2, this.password);
                    statement.setString(3, this.firstName);
                    statement.setString(4, this.lastName);
                    statement.setInt(5, this.groupId);
                    if (statement.executeUpdate() > 0) {
                        ResultSet rs = statement.getGeneratedKeys();
                        if (rs.next()) {
                            this.id = rs.getInt(1);
                        }
                    }
                }
            } else {
                String sql = "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ?, group_id = ? WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, this.email);
                    statement.setString(2, this.password);
                    statement.setString(3, this.firstName);
                    statement.setString(4, this.lastName);
                    statement.setInt(5, this.groupId);
                    statement.setInt(6, this.id);
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
                String sql = "DELETE FROM users WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    statement.executeUpdate();
                    this.id = null;
                }
            }
        }
    }

    public void setPassword(String plainText) {
        this.password = BCrypt.hashpw(plainText, BCrypt.gensalt());
    }
}