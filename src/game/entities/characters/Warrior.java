package game.entities.characters;

import game.entities.Characters;
import game.entities.Entity;
import game.entities.Visitor;

public class Warrior extends Characters {

    public Warrior(String cname, long experience, int level) {
        super(cname, experience, level);
        this.setStrength(2 + (this.getLevel() * 2));
        this.setCharisma(1 + (this.getLevel()));
        this.setDexterity((int) ((this.getLevel() * 0.5)));
        this.setImmuneToFire(true);
    }

    @Override
    public void recieveDamage(int damage) {
        int reducedDamage;
        if (getCharisma() > 10 && getDexterity() > 5) {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
            reducedDamage = damage;
        }
        else {
            this.setCurrentHealth(this.getCurrentHealth () - (int) (1.3 * damage));
            reducedDamage = (int) (1.3 * damage);
        }
        System.out.println("You have lost " + reducedDamage + " damage!");
        this.setCurrentHealth(Math.max(this.getCurrentHealth(), 0));
    }

    @Override
    public int getDamage() {
        return Math.min(2 * getStrength() * (getLevel() + 1), 30);
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        visitor.visit(this);
    }

}
