package br.com.project.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Optional.ofNullable;

public class GrantedAuthority {

    private List<SimpleGrantedAuthority> target = new LinkedList<>();

    private GrantedAuthority() {

    }

    @SuppressWarnings("unchecked")
    public static List<SimpleGrantedAuthority> of(Map<String, ?> map) {
        Map<String, Object> realmAccess = ofNullable((Map<String, Object>) map.get("realm_access")).orElse(emptyMap());
        List<String> roles = ofNullable((List<String>) realmAccess.get("roles")).orElse(emptyList());
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GrantedAuthority)) {
            return false;
        }
        GrantedAuthority other = (GrantedAuthority) obj;
        return Objects.equals(target, other.target);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GrantedAuthority [target=");
        builder.append(target);
        builder.append("]");
        return builder.toString();
    }

}
