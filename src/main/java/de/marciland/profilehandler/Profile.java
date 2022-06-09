package de.marciland.profilehandler;

public class Profile {

    // profile information
    private String name = "";
    private String gender = "";
    private String goal = "";
    private int age = 0;
    private int height = 0;
    private int kcal = 0;
    private float weight = 0;

    /**
     * Every profile will be saved in a file when created.
     *
     * @param name   name of the user creating the profile.
     * @param gender gender of the user creating the profile.
     * @param age    age of the user creating the profile.
     * @param height height in cm of the user creating the profile.
     * @param weight weight in kg of the user creating the profile.
     * @param kcal   kcal/daily consumption limit of the user creating the profile.
     * @param goal   goal of the user creating the profile.
     */
    public Profile(String name, String gender, int age, int height, float weight, int kcal, String goal) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.kcal = kcal;
        this.goal = goal;
    }

    /**
     * Returns the name of this profile.
     *
     * @return name of this profile.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the gender of this profile.
     *
     * @return gender of this profile.
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Returns the goal of this profile.
     *
     * @return goal of this profile.
     */
    public String getGoal() {
        return this.goal;
    }

    /**
     * Returns the age of this profile.
     *
     * @return age of this profile.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Returns the height in cm of this profile.
     *
     * @return height of this profile.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Returns the kcal (daily consumption limit) of this profile.
     *
     * @return kcal of this profile.
     */
    public int getKcal() {
        return this.kcal;
    }

    /**
     * Returns the weight in kg of this profile.
     *
     * @return weight of this profile.
     */
    public float getWeight() {
        return this.weight;
    }
}
