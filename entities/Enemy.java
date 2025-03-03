package game.entities;

import java.util.Random;

public class Enemy extends Entity {

    public Enemy() {
        Random rand = new Random();
        this.setCurrentHealth(rand.nextInt(this.getMaximumHealth() - 60) + 60);
        this.setCurrentManna(rand.nextInt(getMaximumManna() - 20) + 20);
        this.setImmuneToIce(rand.nextDouble() < 0.1);
        this.setImmuneToFire(rand.nextDouble() < 0.1);
        this.setImmuneToIce(rand.nextDouble() < 0.1);
    }


    public void recieveDamage(int damage) {
        Random random = new Random();
        if (random.nextBoolean()) {
            System.out.println("The enemy didn't get the damage!");
        } else {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
            System.out.println("The enemy got " + damage + " damage!");
        }

        this.setCurrentHealth(Math.max(this.getCurrentHealth(), 0));
    }

    public int getDamage() {
        Random random = new Random();
        int damage = 8;

        if (random.nextBoolean()) {
            damage *= 2;
            return damage;
        } else {
            return damage;
        }
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        visitor.visit(this);
    }


}
