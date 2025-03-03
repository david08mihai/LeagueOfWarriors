package game.entities.characters;

import game.entities.Characters;
import game.entities.Entity;
import game.entities.Visitor;

public class Mage extends Characters {

    public Mage(String cname, long experience, int level) {
        super(cname, experience, level);
        this.setCharisma(5 + (int) (this.getLevel() * 1.5));  // Creșterea Charisma-ului odată cu nivelul
        this.setStrength(5 + (int) (this.getLevel() * 0.5));    // Creșterea Strength-ului odată cu nivelul
        this.setDexterity(5 + (int) (this.getLevel() * 0.75));    // Creșterea Dexterity-ului odată cu nivelul
        this.setImmuneToFire(true);
    }

    @Override
    public void recieveDamage(int damage) {
        int reducedDamage;
        if (getDexterity() > 4 && getStrength() > 3) {
            this.setCurrentHealth(this.getCurrentHealth() - damage/2);
            reducedDamage = damage / 2;
        }
        else {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
            reducedDamage = damage;
        }
        this.setCurrentHealth(Math.max(this.getCurrentHealth(), 0));
        System.out.println("You have lost "+ reducedDamage + " damage!");
    }

    @Override
    public int getDamage() {
        int damage = 2 * getCharisma() * (getLevel() + 1);
        return Math.min(damage, 30);
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        visitor.visit(this);
    }
}