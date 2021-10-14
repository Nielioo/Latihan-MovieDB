package com.fei.moviedb.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Credits implements Parcelable {

    private String credit_type;
    private String department;
    private String job;
    private Media media;
    private String media_type;
    private String id;
    private Person person;

    public Credits (){}

    protected Credits(Parcel in) {
        credit_type = in.readString();
        department = in.readString();
        job = in.readString();
        media_type = in.readString();
        id = in.readString();
    }

    public static final Creator<Credits> CREATOR = new Creator<Credits>() {
        @Override
        public Credits createFromParcel(Parcel in) {
            return new Credits(in);
        }

        @Override
        public Credits[] newArray(int size) {
            return new Credits[size];
        }
    };

    public static Credits objectFromData(String str) {

        return new Gson().fromJson(str, Credits.class);
    }

    public String getCredit_type() {
        return credit_type;
    }

    public void setCredit_type(String credit_type) {
        this.credit_type = credit_type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(credit_type);
        parcel.writeString(department);
        parcel.writeString(job);
        parcel.writeString(media_type);
        parcel.writeString(id);
    }

    public static class Media {
        private int id;
        private String name;
        private String original_name;
        private String character;
        private List<?> episodes;
        private List<Seasons> seasons;

        public static Media objectFromData(String str) {

            return new Gson().fromJson(str, Media.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public List<?> getEpisodes() {
            return episodes;
        }

        public void setEpisodes(List<?> episodes) {
            this.episodes = episodes;
        }

        public List<Seasons> getSeasons() {
            return seasons;
        }

        public void setSeasons(List<Seasons> seasons) {
            this.seasons = seasons;
        }

        public static class Seasons {
            private String air_date;
            private String poster_path;
            private int season_number;

            public static Seasons objectFromData(String str) {

                return new Gson().fromJson(str, Seasons.class);
            }

            public String getAir_date() {
                return air_date;
            }

            public void setAir_date(String air_date) {
                this.air_date = air_date;
            }

            public String getPoster_path() {
                return poster_path;
            }

            public void setPoster_path(String poster_path) {
                this.poster_path = poster_path;
            }

            public int getSeason_number() {
                return season_number;
            }

            public void setSeason_number(int season_number) {
                this.season_number = season_number;
            }
        }
    }

    public static class Person {
        private String name;
        private int id;

        public static Person objectFromData(String str) {

            return new Gson().fromJson(str, Person.class);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
