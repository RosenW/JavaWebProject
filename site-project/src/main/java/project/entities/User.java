package project.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    private Long id;

    private String email;

    private String password;

    private Set<Role> roles;

    private Set<Meme> likedMemes;

    private Set<Meme> dislikedMemes;

    public User(String username, String password) {
        this.email = username;
        this.password = password;

        this.roles = new HashSet<>();
    }

    public User() {
        this.roles = new HashSet<>();
        likedMemes = new HashSet<>();
        dislikedMemes = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    public Set<Role> getRoles() {
        return roles;
    }

    @ManyToMany
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "meme_id", referencedColumnName = "id"))
    public Set<Meme> getLikedMemes() {
        return likedMemes;
    }

    @ManyToMany
    @JoinTable(name = "dislikes",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "meme_id", referencedColumnName = "id"))
    public Set<Meme> getDislikedMemes() {
        return dislikedMemes;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Transient
    public boolean isAdmin() {
        return this.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Transient
    @Override
    public String getUsername() {
        return this.email;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setLikedMemes(Set<Meme> likedMemes) {
        this.likedMemes = likedMemes;
    }

    public void setDislikedMemes(Set<Meme> dislikedMemes) {
        this.dislikedMemes = dislikedMemes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addToLiked(Meme meme) {
        this.dislikedMemes.remove(meme);
        this.likedMemes.add(meme);
    }

    public void addToDisliked(Meme meme) {
        this.likedMemes.remove(meme);
        this.dislikedMemes.add(meme);
    }
}