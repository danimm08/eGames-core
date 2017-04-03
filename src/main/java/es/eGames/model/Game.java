package es.eGames.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import es.eGames.views.View;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by daniel on 4/02/17.
 */

@Entity
@Access(AccessType.PROPERTY)
public class Game extends BaseEntity {

    private String title;
    private String coverUrl;
    private Date firstReleaseDate;
    private String publisher;
    private String storyLine;
    private String summary;
    private Integer timeToBeat;

    private Platform platform;
    private Set<Genre> genres;
    private Set<GameMode> gameModes;
    private Set<Keyword> keywords;


    public Game() {
        super();
    }

    @NotBlank
    @JsonView(View.ListPersonalGame.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonView(View.ListPersonalGame.class)
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(Date firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(length = 100000)
    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    @Column(length = 100000)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getTimeToBeat() {
        return timeToBeat;
    }

    public void setTimeToBeat(Integer timeToBeat) {
        this.timeToBeat = timeToBeat;
    }

    @NotNull
    @ManyToOne(optional = false)
    @JsonView(View.ListPersonalGame.class)
    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @NotNull
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    public Set<GameMode> getGameModes() {
        return gameModes;
    }

    public void setGameModes(Set<GameMode> gameModes) {
        this.gameModes = gameModes;
    }

    @NotNull
    @ManyToMany
    @JsonIgnore
    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

}
