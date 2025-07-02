package week3.day15;

import utils.InputParser;
import java.util.ArrayList;
import java.util.List;

public class ScienceForHungryPeople {
    private List<Ingredient> getIngredients(List<String> data) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (String s : data) {
            String[] tokens = s.split("\\s+");
            ingredients.add(new Ingredient(
                    Integer.parseInt(tokens[2].substring(0, tokens[2].length() - 1)),
                    Integer.parseInt(tokens[4].substring(0, tokens[4].length() - 1)),
                    Integer.parseInt(tokens[6].substring(0, tokens[6].length() - 1)),
                    Integer.parseInt(tokens[8].substring(0, tokens[8].length() - 1)),
                    Integer.parseInt(tokens[10])));
        }

        return ingredients;
    }

    private int calculateScore(int capacity, int durability, int flavor, int texture) {
        if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0) {
            return 0;
        }

        return capacity * durability * flavor * texture;
    }

    private int calculateCapacity(int frosting, int candy, int butterscotch, int sugar, List<Ingredient> info) {
        return info.get(0).capacity() * frosting + info.get(1).capacity() * candy + info.get(2).capacity() * butterscotch + info.get(3).capacity() * sugar;
    }

    private int calculateDurability(int frosting, int candy, int butterscotch, int sugar, List<Ingredient> info) {
        return info.get(0).durability() * frosting + info.get(1).durability() * candy + info.get(2).durability() * butterscotch + info.get(3).durability() * sugar;
    }

    private int calculateTexture(int frosting, int candy, int butterscotch, int sugar, List<Ingredient> info) {
        return info.get(0).texture() * frosting + info.get(1).texture() * candy + info.get(2).texture() * butterscotch + info.get(3).texture() * sugar;
    }

    private int calculateFlavor(int frosting, int candy, int butterscotch, int sugar, List<Ingredient> info) {
        return info.get(0).flavor() * frosting + info.get(1).flavor() * candy + info.get(2).flavor() * butterscotch + info.get(3).flavor() * sugar;
    }

    private int calculateCalories(int frosting, int candy, int butterscotch, int sugar, List<Ingredient> info) {
        return info.get(0).calories() * frosting + info.get(1).calories() * candy + info.get(2).calories() * butterscotch + info.get(3).calories() * sugar;
    }

    private int findBestScore(List<Ingredient> info) {
        int bestScore = 0;

        for (int frosting = 0; frosting < 101; frosting++) {
            for (int candy = 0; frosting + candy < 101; candy++) {
                for (int butterscotch = 0; frosting + candy + butterscotch < 101; butterscotch++) {
                    for (int sugar = 0; frosting + candy + butterscotch + sugar < 101; sugar++) {
                        int capacity = calculateCapacity(frosting, candy, butterscotch, sugar, info);
                        int durability = calculateDurability(frosting, candy, butterscotch, sugar, info);
                        int flavor = calculateFlavor(frosting, candy, butterscotch, sugar, info);
                        int texture = calculateTexture(frosting, candy, butterscotch, sugar, info);

                        bestScore = Math.max(bestScore, calculateScore(capacity, durability, flavor, texture));
                    }
                }
            }
        }

        return bestScore;
    }

    public int partOne(String filename) {
        List<Ingredient> ingredients = getIngredients(InputParser.parseInput(filename));
        return findBestScore(ingredients);
    }

    private int findBestScoreForCalories(List<Ingredient> info, int calories) {
        int bestScore = 0;

        for (int frosting = 0; frosting < 101; frosting++) {
            for (int candy = 0; frosting + candy < 101; candy++) {
                for (int butterscotch = 0; frosting + candy + butterscotch < 101; butterscotch++) {
                    for (int sugar = 0; frosting + candy + butterscotch + sugar < 101; sugar++) {
                        if (calculateCalories(frosting, candy, butterscotch, sugar, info) == calories) {
                            int capacity = calculateCapacity(frosting, candy, butterscotch, sugar, info);
                            int durability = calculateDurability(frosting, candy, butterscotch, sugar, info);
                            int flavor = calculateFlavor(frosting, candy, butterscotch, sugar, info);
                            int texture = calculateTexture(frosting, candy, butterscotch, sugar, info);

                            bestScore = Math.max(bestScore, calculateScore(capacity, durability, flavor, texture));
                        }
                    }
                }
            }
        }

        return bestScore;
    }

    public int partTwo(String filename) {
        List<Ingredient> ingredients = getIngredients(InputParser.parseInput(filename));
        return findBestScoreForCalories(ingredients, 500);
    }
}
