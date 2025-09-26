package game.spells;

import game.entities.Entity;

import java.util.Random;

public class Fire extends Spell{
    public Fire() {
        super(new Random().nextInt(15) + 20, new Random().nextInt(10) + 10);
    }

    public String toString() {
        return "Fire";
    }

    public void visit(Entity entity) {
        if (!entity.isImmuneToFire())
            entity.recieveDamage((int) this.getGivenDamage());
    }
}
