package algorithme;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class InputUtils {
    public static int[][] convertToArray(String input) {
        return new Gson().fromJson(input, new TypeToken<int[][]>() {
        }.getType());
    }
}
