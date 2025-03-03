package game.entities;

import game.spells.Earth;
import game.spells.Fire;
import game.spells.Ice;
import game.spells.Spell;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity implements Battle, Element<Entity> {
    private ArrayList<Spell> spells = new ArrayList<>();
    private int currentHealth;
    private final int maximumHealth = 100;
    private int currentManna;
    private final int maximumManna = 50;
    private boolean immuneToFire = false;
    private boolean immuneToEarth = false;
    private boolean immuneToIce = false;


    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public int getCurrentManna() {
        return currentManna;
    }

    public void setCurrentManna(int currentManna) {
        this.currentManna = currentManna;
    }

    public int getMaximumManna() {
        return maximumManna;
    }

    public boolean isImmuneToFire() {
        return immuneToFire;
    }

    public void setImmuneToFire(boolean immuneToFire) {
        this.immuneToFire = immuneToFire;
    }

    public boolean isImmuneToEarth() {
        return immuneToEarth;
    }

    public void setImmuneToEarth(boolean immuneToEarth) {
        this.immuneToEarth = immuneToEarth;
    }

    public boolean isImmuneToIce() {
        return immuneToIce;
    }

    public void setImmuneToIce(boolean immuneToIce) {
        this.immuneToIce = immuneToIce;
    }

    public Entity() {
        Random rand = new Random();
        spells.add(new Fire());
        spells.add(new Ice());
        spells.add(new Earth());
        int numberOfAbilities = rand.nextInt(4);
        for (int i = 0; i < numberOfAbilities; i++) {
            int randomAbility = rand.nextInt(3);
            switch (randomAbility) {
                case 0 -> spells.add(new Fire());
                case 1 -> spells.add(new Earth());
                case 2 -> spells.add(new Ice());
            }
        }
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }



    public void manaRegenerate(float mana) {
        if (currentManna + mana >= maximumManna)
            currentManna = maximumManna;
        else
            currentManna += (int) mana;
    }

    public void healthRegenerate(float health) {
        if (currentHealth + health >= maximumHealth)
            currentHealth = maximumHealth;
        else
            currentHealth += (int) health;
    }
    //Attacker.accept(spell);

}
