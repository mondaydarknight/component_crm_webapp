package com.smartbee.crm.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String username;

    @NotNull
    @Pattern(regexp = "^[_'.@A-Za-z0-9-]*$")
    @Size(min = 1, max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String loginId;

    @NotNull
    @JsonIgnore
    @Size(min = 60, max = 60)
    private String password;

    @NotNull
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId.toLowerCase(Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User{\n");
        stringBuilder.append("    id: ").append(toIndentedString(id)).append("\n");
        stringBuilder.append("    loginId: ").append(toIndentedString(loginId)).append("\n");
        stringBuilder.append("    authority: ").append(toIndentedString(authority)).append("\n");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String toIndentedString(Object object) {
        return (object == null) ? "null" : object.toString().replace("\n", "\n    ");
    }
}
