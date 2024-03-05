package org.example.projecttest;

interface Ability{
    void useAbility();
}
public class Weapon implements Ability{
    private String name;
    private int price;
    private int powerLevel;
    private boolean isMainHand;
    private Ability ability;

    public Weapon(String name, int price, int powerLevel, Ability ability) {
        this.name = name;
        this.price = price;
        this.powerLevel = powerLevel;
        this.isMainHand = false;
        this.ability = createAbility(); //no special ability by default, would like to add some to make weapons more interesting

    }

    //method to use special ability during battle defined when creating each ability
    //call it within weapon class to reduce confusion --> ability class is a part of the weapon but not an extension of weapon class
    public void useAbility(){
        this.ability.useAbility();
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public boolean CheckMainHand() {
        return isMainHand;
    }

    public void setMainHand(boolean mainHand) {
        isMainHand = mainHand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    //create 3 different subclasses of weapons. can add different features for different weapons later
    class Sword extends Weapon {
        public Sword(String WeaponName, int price, int powerLevel, Ability ability) {
            super(WeaponName, price, powerLevel, new Ability(ability.abilityName);
        }
    }

    class Axe extends Weapon {
        public Axe(String WeaponName, int price, int powerLevel, Ability ability) {
            super(WeaponName, price, powerLevel, new Ability(ability.abilityName));
        }
    }

    class Spear extends Weapon {
        public Spear(String WeaponName, int price, int powerLevel, Ability ability) {
            super(WeaponName, price, powerLevel, new Ability(ability.abilityName));
        }
    }


     /*class Ability {
        //dont want abilities to be guaranteed hit
        private String abilityName;

        public Ability(String abilityName) {
            this.abilityName = abilityName;
        }

        //ability will be different for each instance. override method when creating each subclass of ability
        public abstract void useAbility();

        public String getAbilityName() {
            return abilityName;
        }

        public void setAbilityName(String abilityName) {
            this.abilityName = abilityName;
        }
     }

        //define ability subclasses
        class NoAbility extends Ability {
            public NoAbility() {
                super("No Ability");
            }

            @Override
            public void useAbility
            {
                //does nothing
            }

        }

        class Looting extends Ability {
            public Looting() {
                super("Looting");
            }

            @Override
            public void useAbility
            {
                //define behavior for looting ability.
                // want this ability to take more money from loser upon winning battle
            }
        }

        class Freeze extends Ability {
            public Freeze() {
                super("Freeze");

            }

            @Override
            public void useAbility
            {
                //define behavior for freeze ability
                //want this ability to skip turn upon hitting regardless of battle outcome
            }
        }
*/

    //define ability interface


    class Weapons {
        //create 1 default weapon for each type
        Sword BasicSword = new Sword("Basic Sword", 10, 1, this.new NoAbility("No Ability"));
        Axe BasicAxe = new Axe("Basic Axe", 10, 1, this.new Ability.NoAbility("No Ability"));
        Spear BasicSpear = new Spear("Basic Spear", 10, 1, this.new Ability.NoAbility("No Ability"));
    }
}