package game.entities.characters;

import game.entities.Characters;

public class CharacterFactory {
    public static Characters createCharacter(String type, String cname, long experience, int level) {
        switch (type) {
            case "Warrior" -> {
                return new Warrior(cname, experience, level);
            }
            case "Rogue" -> {
                return new Rogue(cname, experience, level);
            }
            case "Mage" -> {
                return new Mage(cname, experience, level);
            }
        }
        return null;
    }
}
