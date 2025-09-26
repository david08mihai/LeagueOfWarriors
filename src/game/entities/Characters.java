package game.entities;

import game.spells.Earth;
import game.spells.Fire;
import game.spells.Ice;

import java.util.Random;

public abstract class Characters extends Entity {
    private String cname;
    private long experience;
    private int level;
    private  int strength;
    private int charisma;
    private int dexterity;

    public Characters(String cname, long experience, int level) {
        super();
        this.cname = cname;
        this.experience = experience;
        this.level = level;
        this.setCurrentHealth(100);
        this.setCurrentManna(50);
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public String toString() {
        return cname;
    }


    public void experienceToLevel() {
        if (experience > 30) {
            experience = 0;
            level++;
        }
    }

    public void abilitiesRegenerate() {
        if (!this.getSpells().isEmpty())
            this.getSpells().clear();
        Random rand = new Random();
        getSpells().add(new Fire());
        getSpells().add(new Ice());
        getSpells().add(new Earth());
        int min = 0;
        int max = 3;
        int numberOfAbilities = rand.nextInt(max - min) + min;
        for (int i = 0; i < numberOfAbilities; i++) {
            int randomAbility = rand.nextInt(3);
            switch (randomAbility) {
                case 0 -> getSpells().add(new Fire());
                case 1 -> getSpells().add(new Earth());
                case 2 -> getSpells().add(new Ice());
            }
        }

    }

}
