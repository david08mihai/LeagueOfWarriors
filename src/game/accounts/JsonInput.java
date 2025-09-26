package game.accounts;

import game.entities.Characters;
import game.entities.characters.CharacterFactory;
import org.json.simple.JSONObject;
import org.json.simple.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class JsonInput {
    public static ArrayList<Account> deserializeAccounts() throws IOException {
        String accountPath = "src\\game\\accounts\\accounts.json";
        String content = new String((Files.readAllBytes(Paths.get(accountPath))));
        Object object = JSONValue.parse(content);
        JSONObject obj = (JSONObject) object;
        JSONArray accountsArray = (JSONArray) obj.get("accounts");

        ArrayList<Account> accounts = new ArrayList<>();
        for (int i = 0; i < accountsArray.size(); i++) {
            JSONObject accountJson = (JSONObject) accountsArray.get(i);
            // name, country, games_number
            String name = (String) accountJson.get("name");
            String country = (String) accountJson.get("country");
            int gamesNumber = Integer.parseInt((String) accountJson.get("maps_completed"));

            // Credentials
            Credentials credentials = null;

            JSONObject credentialsJson = (JSONObject) accountJson.get("credentials");
            String email = (String) credentialsJson.get("email");
            String password = (String) credentialsJson.get("password");

            credentials = new Credentials(email, password);

            // Favorite games
            SortedSet<String> favoriteGames = new TreeSet<>();

            JSONArray games = (JSONArray) accountJson.get("favorite_games");
            for (int j = 0; j < games.size(); j++) {
                favoriteGames.add((String) games.get(j));
            }

            // Characters
            ArrayList<Characters> characters = new ArrayList<>();
            JSONArray charactersListJson = (JSONArray) accountJson.get("characters");
            for (int j = 0; j < charactersListJson.size(); j++) {
                JSONObject charJson = (JSONObject) charactersListJson.get(j);
                String cname = (String) charJson.get("name");
                String profession = (String) charJson.get("profession");
                String level = (String) charJson.get("level");
                int lvl = Integer.parseInt(level);
                long experience = (long) charJson.get("experience");

                Characters newCharacter = null;
                newCharacter = CharacterFactory.createCharacter(profession, cname, experience, lvl);
                characters.add(newCharacter);
            }

            //Builder
            Account.Information information = new Account.InformationBuilder().setCredentials(credentials)
                    .setName(name).setCountry(country).setFavoriteGames(favoriteGames).build();
            Account account = new Account(characters, gamesNumber, information);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public String toString() {
        return "JsonInput";
    }
}
