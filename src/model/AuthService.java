package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthService {
    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        // Usuarios fijos
        users.put("mariam", new User("mariam", "mariam123"));
        users.put("karen", new User("karen", "karen123"));
        users.put("alejandro", new User("alejandro", "alejandro123"));
        users.put("miguel", new User("miguel", "miguel123"));
        users.put("eder", new User("eder", "eder123"));

    }

    public Optional<User> login(String username, String password) {
        User u = users.get(username);
        if (u != null && u.matches(username, password)) {
            return Optional.of(u);
        }
        return Optional.empty();
    }
}
