package project.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Meme> memes;

    public Type() {
        setMemes(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Meme> getMemes() {
        return memes;
    }

    public void setMemes(Set<Meme> memes) {
        this.memes = memes;
    }
}
