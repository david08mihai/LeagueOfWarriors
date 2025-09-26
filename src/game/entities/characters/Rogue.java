package game.entities.characters;

import game.entities.Characters;
import game.entities.Entity;
import game.entities.Visitor;

public class Rogue extends Characters {

    public Rogue(String cname, long experience, int level) {
        super(cname, experience, level);
        this.setDexterity(8 + (int)(this.getLevel() * 1.2));  // Creșterea Dexterity-ului odată cu nivelul
        this.setStrength(2 + (int)(this.getLevel() * 0.5));   // Creșterea Strength-ului odată cu nivelul
        this.setCharisma( 1 + (int)(this.getLevel() * 0.75));  // Creșterea Charisma-ului odată cu nivelul
        this.setImmuneToEarth(true);
    }

    @Override
    public void recieveDamage(int damage) {
        double dexterityFactor = 0.15 * getStrength() + 0.1 * getCharisma();
        int reducedDamage = (int)(damage * (1 - dexterityFactor));

        System.out.println("You have lost " + reducedDamage + " damage!");

        this.setCurrentHealth(Math.max(this.getCurrentHealth() - reducedDamage, 0));
    }

    @Override
    public int getDamage() {
        int damage = 2 * getDexterity() * (getLevel() + 1);
        return Math.min(damage, 30);
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        visitor.visit(this);
    }
}
