package com.faust.votingguide.models.forms;

import com.faust.votingguide.models.Candidate;
import com.faust.votingguide.models.Measure;

import java.util.List;

/**
 * Created by afaust on 8/16/17.
 */
public class ResourcesForm {

    private String article; //link

    private String video;

    private String audio;

    public ResourcesForm() {}

    public ResourcesForm(String article, String video, String audio) {
        this.article = article;
        this.video = video;
        this.audio = audio;
}

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
