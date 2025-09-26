package game.spells;

import game.entities.Entity;
import game.entities.Visitor;
import game.entities.characters.Mage;

public abstract class Spell implements Visitor<Entity> {
    private final float givenDamage;
    private final float mannaCost;


    public Spell (float givenDamage, float mannaCost) {
        this.givenDamage = givenDamage;
        this.mannaCost = mannaCost;
    }

    public float getGivenDamage() {
        return givenDamage;
    }

    public float getMannaCost() {
        return mannaCost;
    }


    public void display() {
        if (this instanceof Fire) {
            System.out.println("The Fire spell inflicts " + givenDamage + " damage and requires " + mannaCost + " manna to cast.");
        } else if (this instanceof Earth)
            System.out.println("The Earth spell inflicts " + givenDamage + " damage and requires " + mannaCost + " manna to cast.");
        else
            System.out.println("The Ice spell inflicts " + givenDamage + " damage and requires " + mannaCost + " manna to cast.");
    }

}
