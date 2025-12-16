package excercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Question 2 (5 points) Write a method countAnimals that takes a String[] animals (array
 * of animal strings) and returns a new array containing the total count for each animal type. Each
 * string in the input array has the format "<number> <animal>" (e.g., "3 dogs", "5 cats").
 * The word is always the same (you don’t need to handle plural forms or typos). Sum up the
 * counts for each animal type and return an array of strings in the same format.
 * Example: If animals = ["3 dogs", "5 cats", "8 dogs", "1 chicken", "2 chicken"],
 * the method should return ["11 dogs", "5 cats", "3 chicken"] (3+8=11 dogs, 5 cats,
 * 1+2=3 chicken).
 * If animals = ["2 dogs", "3 dogs"], the method should return ["5 dogs"].
 * Note: The order of animals in the output should match the order they first appear in the input
 * array.
 */

public class ExamExercise
{
    private String[] animals;

    public ExamExercise(String[] animals)
    {
        setAnimals(animals);
    }

    /**
     * Starts the excercise logic
     */
    public void runExercise()
    {
        String[] result = countAnimals(getAnimals());

        IO.println("Result: " + Arrays.toString(result));

        IO.println(Arrays.toString(processArray(new String[] {"cat", "dog", "mtd", "rat"})));
        IO.println(Arrays.toString(processArray(new String[] {"cat", "dog", "rat"})));
    }

    private String[] countAnimals(String[] animals)
    {
        HashMap<String, Integer> animalCounts = new LinkedHashMap<>();

        for (String animal : animals)
        {
            int animal_count = Integer.parseInt(animal.split(" ")[0]);
            String animal_type = animal.split(" ")[1];

            animalCounts.merge(animal_type, animal_count, Integer::sum);
        }

        String[] result = new String[animalCounts.size()];
        int index = 0;

        for (String key : animalCounts.keySet())
        {
            String animalEntry = animalCounts.get(key).toString() + " " + key;
            result[index] = animalEntry;
            index++;
        }

        return result;
    }

    private String[] processArray(String[] array)
    {
        String[] modifiedArray = Arrays.copyOf(array, array.length);

        String key = "mtd";
        boolean containsKey = false;

        for (String element : modifiedArray)
        {
            if (element.equals(key))
            {
                containsKey = true;
                break;
            }
        }

        if (containsKey)
        {
            for (int i = 0; i < modifiedArray.length; i++)
            {
                modifiedArray[i] = reverseString(modifiedArray[i]);
            }

        }

        if (!containsKey)
        {
            for (int i = 0; i < modifiedArray.length; i++)
            {
                modifiedArray[i] = modifiedArray[i].toUpperCase();
            }
        }

        return modifiedArray;
    }

    private String reverseString(String str)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--)
        {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    /**
     * Displays the output of the excercise
     */
    public void displayOutput() {

    }

    public String[] getAnimals() {
        return animals;
    }

    public void setAnimals(String[] animals) {
        this.animals = animals;
    }
}
