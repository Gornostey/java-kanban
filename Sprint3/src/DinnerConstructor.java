import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class DinnerConstructor {
    HashMap <String, ArrayList<String>> typeToDishes = new HashMap<>();
    Random random = new Random();
    public void addDish(String dishType, String dishName){
        if (typeToDishes.containsKey(dishType)
                && !typeToDishes.containsValue(dishName)){
            typeToDishes.get(dishType).add(dishName);
            System.out.println("Такое блюдо уже существует");
        } else if (!typeToDishes.containsKey(dishType)
                && !typeToDishes.containsValue(dishName)) {
            typeToDishes.put(dishType, new ArrayList<>());
            typeToDishes.get(dishType).add(dishName);
            System.out.println("Добавлено " + dishType + " под названием: " + dishName);
        }

    }
    public ArrayList<ArrayList<String>> generateCombos(int numberOfCombos, ArrayList<String> comboStructure){
        ArrayList<ArrayList<String>> combos = new ArrayList<>();
        for(int i = 0; i < numberOfCombos; i++){
            ArrayList<String> currentCombos = new ArrayList<>();
            for (String dishType: comboStructure){
                ArrayList<String> avaiLableDishes = typeToDishes.get(dishType);
                int rand = random.nextInt(avaiLableDishes.size());
                String rand1 = avaiLableDishes.get(rand);
                currentCombos.add(rand1);
            }
            combos.add(currentCombos);
        }
        return combos;
    }
    public boolean checkType(String nextItem){
        return typeToDishes.containsKey(nextItem);
    }
}
