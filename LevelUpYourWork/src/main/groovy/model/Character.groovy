package model

class Character {
    String name
    int level = 1
    int totalXP = 0
    List<Skill> skills = []

    void addXPToSkill(String skillName, int xp) {
        Skill skill = skills.find { it.name == skillName }
        if (skill) {
            skill.addXP(xp)
            totalXP += xp
            checkLevelUp()
        }
    }

    void checkLevelUp() {
        if (totalXP >= level * 100) { // Beispiel: Level-Up alle 100 XP
            level++
            println "Level-Up! Du bist jetzt Level $level"
        }
    }
}

