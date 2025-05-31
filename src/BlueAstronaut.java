import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate, Comparable<Player> {

    private int numTasks;
    private int taskSpeed;
    private boolean firstTask;

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed, boolean firstTask) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
        this.firstTask = true;
    }

    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        this(name, susLevel, numTasks, taskSpeed, true);
    }

    public BlueAstronaut(String name) {
        this(name, 15, 6, 10, true);
    }

    public int getNumTasks() {
        return numTasks;
    }

    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }

    public int getTaskSpeed() {
        return taskSpeed;
    }

    public void setTaskSpeed(int taskSpeed) {
        this.taskSpeed = taskSpeed;
    }

    public void setFirstTask() {
        firstTask = false;
    }

    public boolean getFirstTask() {
        return firstTask;
    }

    @Override
    public void emergencyMeeting(){
        int length = getPlayers().length;
        Arrays.sort(getPlayers());
        if (!(this.isFrozen())){
            if (getPlayers()[length-1].getSusLevel() != getPlayers()[length-2].getSusLevel()){
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
    public void completeTask(){
        if (!(this.isFrozen())) {
            if (taskSpeed > 20) {
                numTasks -= 2;
            } else {
                numTasks -= 1;
            }
        }
        if (numTasks <= 0){
            numTasks = 0;
            if (firstTask) {
                this.setFirstTask();
                System.out.println("I have completed all my tasks");
                this.setSusLevel((int)(getSusLevel() * 0.5));
            }
        }
    }

    @Override
    public boolean equals(Object other){
        if (this == other) {
            return true;
            }
        if (other == null) {
            return false;
        }
        if (!this.equals(other)) {
            return false;
            }
        BlueAstronaut that = (BlueAstronaut) other;
        if (that.numTasks == this.numTasks && that.taskSpeed == this.taskSpeed){
            return true;
            }
        return false;
    }

    @Override
    public String toString(){
        String returnString = super.toString() + " I have " + numTasks + " left over.";
        if (getSusLevel() > 15){
            return returnString.toUpperCase();
        } else {
            return returnString;
        }
    }
}
