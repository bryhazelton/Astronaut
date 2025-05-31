import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor, Comparable<Player> {
    private String skill;

    public RedAstronaut(String name, int susLevel, String skill ) {
        super(name, susLevel);
        this.skill = skill;
    }

    public RedAstronaut(String name) {
        this(name, 15, "experienced");
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }


    @Override
    public void emergencyMeeting() {
        int length = getPlayers().length;
        Arrays.sort(getPlayers());
        if (!this.isFrozen() && !this.equals(getPlayers()[length - 1])) {
            if (getPlayers()[length -1].getSusLevel() != getPlayers()[length -2].getSusLevel()){
                for (int i = length -1; i >= 0; i--) {
                    if (!getPlayers()[i].isFrozen()) {
                        getPlayers()[i].setFrozen(true);
                        break;
                    }
                }
            }
        }
        gameOver();
    }
    @Override
    public void freeze(Player p){
        if (!(p instanceof Impostor) && !(p.isFrozen()) && !(this.isFrozen())) {
            if (this.getSusLevel() < p.getSusLevel()) {
                p.setFrozen(true);
            } else {
                this.setSusLevel(getSusLevel() * 2);
                }
        }
        gameOver();
    }

    @Override
    public void sabotage(Player p) {
        if (!(p instanceof Impostor) && !(p.isFrozen()) && !(this.isFrozen())) {
            if (this.getSusLevel() < 20) {
                p.setSusLevel((int)(p.getSusLevel() * 1.5));
            } else {
                p.setSusLevel((int)(p.getSusLevel() * 1.25));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!super.equals(o)) return false;
        RedAstronaut that = (RedAstronaut) o;
        return skill.equals(that.skill);
    }

    @Override
    public String toString() {
        String returnString = super.toString() + " I am an " + skill + " player!";
        if (this.getSusLevel() > 15) {
            return returnString.toUpperCase();
        } else {
            return returnString;
        }
    }
}
