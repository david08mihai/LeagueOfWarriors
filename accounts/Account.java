package game.accounts;

import java.util.ArrayList;
import java.util.SortedSet;

import game.entities.Characters;

public class Account {
    public Information information;
    public ArrayList<Characters> characters;
    int gamesNumber;

    public static class Information {
        private Credentials credentials;
        public SortedSet<String> favoriteGames;
        public String name;
        public String country;

        public Information(Account.InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.favoriteGames = builder.favoriteGames;
            this.name = builder.name;
            this.country = builder.country;
        }

        public Credentials getCredentials() {
            return credentials;
        }
        @Override
        public String toString() {
            return credentials + "," + favoriteGames + "," + name + "," + country;
        }
    }

    public static class InformationBuilder {
        private Credentials credentials;
        public SortedSet<String> favoriteGames;
        public String name;
        public String country;

        public InformationBuilder setCredentials(Credentials credentials) {
            this.credentials = credentials;
            return this;
        }

        public InformationBuilder setFavoriteGames(SortedSet<String> favoriteGames) {
            this.favoriteGames = favoriteGames;
            return this;
        }

        public InformationBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public InformationBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Information build() {
            return new Account.Information(this);
        }

    }

    public Account(ArrayList<Characters> charachers, int gamesNumber, Account.Information information) {
        this.characters = charachers;
        this.gamesNumber = gamesNumber;
        this.information = information;
    }



    @Override
    public String toString() {
        return "Account{" +
                ", info=" + information +
                ", charachers=" + characters +
                ", gamesNumber=" + gamesNumber +
                '}';
    }
}
