package com.api.oson.model;


import com.api.oson.config.CustomOAuth2User;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String discriminator;

    @Column
    private String locale;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(CustomOAuth2User oAuthUser){
        this.username = oAuthUser.getName();
        this.email = oAuthUser.getEmail();
        this.discriminator = oAuthUser.getAttribute("discriminator");
        this.locale = oAuthUser.getAttribute("locale");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
