package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;
import views.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

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
    private Collection<Genre> genres;
    private Collection<GameMode> gameModes;
    private Collection<Keyword> keywords;


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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
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

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

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
    @ManyToMany //fetch = FetchType.EAGER
    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }

    @NotNull
    @ManyToMany
    public Collection<GameMode> getGameModes() {
        return gameModes;
    }

    public void setGameModes(Collection<GameMode> gameModes) {
        this.gameModes = gameModes;
    }

    @NotNull
    @ManyToMany
    @JsonIgnore
    public Collection<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<Keyword> keywords) {
        this.keywords = keywords;
    }

}
