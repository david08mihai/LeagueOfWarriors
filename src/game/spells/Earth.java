package game.spells;

import game.entities.Entity;

import java.util.Random;

public class Earth extends Spell {
    public Earth() {
        super(new Random().nextInt(20) + 5, new Random().nextInt(10) + 6);
    }

    public String toString() {
        return "Earth";
    }

    public void visit(Entity entity) {
        if (!entity.isImmuneToEarth())
            entity.recieveDamage((int) this.getGivenDamage());
    }
}
