package de.marciland.profilehandler;

public class Profile {

    /*
     * profile information
     */
    private String name = "";
    private String gender = "";
    private String goal = "";

    private int age = 0;
    private int height = 0;
    private int kcal = 0;

    private float weight = 0;

    public Profile(String name, String gender, int age, int height, float weight, int kcal, String goal) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.kcal = kcal;
        this.goal = goal;
    }

    /*
     * getters
     */
    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public String getGoal() {
        return this.goal;
    }

    public int getAge() {
        return this.age;
    }

    public int getHeight() {
        return this.height;
    }

    public int getKcal() {
        return this.kcal;
    }

    public float getWeight() {
        return this.weight;
    }
}
