package game.spells;

import game.entities.Entity;

import java.util.Random;

public class Ice extends Spell {
    public Ice() {
        super(new Random().nextInt(20) + 10, new Random().nextInt(10) + 15);
    }

    @Override
    public String toString() {
        return "Ice";
    }

    @Override
    public void visit(Entity entity) {
        if (!entity.isImmuneToIce())
            entity.recieveDamage((int) this.getGivenDamage());
    }
}