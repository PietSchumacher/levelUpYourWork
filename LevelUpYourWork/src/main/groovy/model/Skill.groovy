package model

class Skill {
    String name
    int xp = 0

    void addXP(int amount) {
        xp += amount
    }
}
