public class App {
    public static void main(String[] args) {
        int[] value = { 30, 14, 16, 9 };
        int[] weight = { 6, 3, 4, 2 };
        int w = 10;

        Mochila m = new Mochila(value, weight, w);

        int profit3 = m.bestProfitR(w, value.length - 1);
        int profit2 = m.bestProfitMemo(w, value.length - 1);
        int profit1 = m.bestProfitTab(w, value.length - 1);
        System.out.println(profit3);
        System.out.println(profit2);
        System.out.println(profit1);
    }
}

class Mochila {
    int[] value;
    int[] weigth;
    int w = 10;

    public Mochila(int[] value, int[] weight, int w) {
        this.value = value;
        this.weigth = weight;
        this.w = w;
    }

    public int bestProfitR(int maxWeigth, int itemNumber) {
        if (maxWeigth < 0 || itemNumber < 0)
            return 0;

        if (this.weigth[itemNumber] > maxWeigth)
            return bestProfitR(maxWeigth, itemNumber - 1);
        else
            return max(bestProfitR(maxWeigth, itemNumber - 1),
                    bestProfitR(maxWeigth - this.weigth[itemNumber], itemNumber - 1) + this.value[itemNumber]);
    }

    public int bestProfitMemo(int maxWeigth, int itemNumber) {
        int[][] memo = new int[maxWeigth][itemNumber + 1];
        if (maxWeigth < 0 || itemNumber < 0)
            return 0;

        if (memo[maxWeigth - 1][itemNumber] == 0) {
            if (this.weigth[itemNumber] > maxWeigth) {
                memo[maxWeigth - 1][itemNumber] = bestProfitMemo(maxWeigth, itemNumber - 1);
            } else {
                memo[maxWeigth - 1][itemNumber] = max(bestProfitMemo(maxWeigth, itemNumber - 1),
                        bestProfitMemo(maxWeigth - this.weigth[itemNumber], itemNumber - 1) + this.value[itemNumber]);
            }

        }

        return memo[maxWeigth - 1][itemNumber];
    }

    public int bestProfitTab(int maxWeigth, int itemNumber) {
        int[][] tab = new int[maxWeigth + 1][itemNumber + 2];

        for (int i = 0; i < maxWeigth + 1; i++)
            tab[i][0] = 0;

        for (int i = 0; i < itemNumber + 2; i++)
            tab[0][i] = 0;

        for (int w = 1; w < maxWeigth + 1; w++) {
            for (int j = 1; j < itemNumber + 2; j++) {  
                if (this.weigth[j - 1] > w)
                    tab[w][j] = tab[w][j - 1];
                else
                    tab[w][j] = max(tab[w][j - 1], tab[w - this.weigth[j - 1]][j-1] + this.value[j - 1]);
            }
        }
        return tab[maxWeigth][itemNumber + 1];
    }

    public int max(int value1, int value2) {
        if (value1 > value2)
            return value1;
        else
            return value2;
    }

}