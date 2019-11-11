package br.com.project.security;

import java.util.Map;

public class Principal {

    public static final String USER_KEY_ID = "sub";
    public static final String USER_KEY_NAME = "name";
    public static final String USER_KEY_LOGIN = "user_name";
    public static final String USER_KEY_EMAIL = "email";
    public static final String USER_FIRST_NAME = "given_name";
    public static final String USER_LAST_NAME = "family_name";

    private String id;
    private String name;
    private String email;
    private String login;
    private String firstName;
    private String lastName;

    public static Principal of(Map<String, ?> data) {
        Principal principal = new Principal();
        principal.id = (String) data.get(USER_KEY_ID);
        principal.name = (String) data.get(USER_KEY_NAME);
        principal.login = (String) data.get(USER_KEY_LOGIN);
        principal.email = (String) data.get(USER_KEY_EMAIL);
        principal.firstName = (String) data.get(USER_FIRST_NAME);
        principal.lastName = (String) data.get(USER_LAST_NAME);
        return principal;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
