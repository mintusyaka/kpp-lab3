package models;

import java.io.Serializable;

public class Dish implements Serializable {
    private String title;
    private String description;
    private boolean haveAllergens;

    public Dish() {}

    public Dish(String title, String description, boolean haveAllergens) {
        this.title = title;
        this.description = description;
        this.haveAllergens = haveAllergens;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHaveAllergens(boolean haveAllergens) {
        this.haveAllergens = haveAllergens;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean getHaveAllergens() {
        return haveAllergens;
    }

    @Override
    public String toString() {
        return "Title:" + title +
                "|Description:" + description +
                "|Have Allergens:" + haveAllergens + "\n";
    }
}