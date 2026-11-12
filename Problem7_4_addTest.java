package algorithm.lecture07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 7_3
public final class Problem7_3 {


    public static int[] Solve(String[] enroll, String[] referral,
                              String[] seller, int[] amount) {
        Map<String, SalesPerson> personByName = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            SalesPerson person = new SalesPerson();
            personByName.put(enroll[i], person);

            if (!"-".equals(referral[i])) {
                SalesPerson parent = personByName.get(referral[i]);
                person.parent = parent;
                parent.children.add(person);
            }
        }

        for (int i = 0; i < seller.length; i++) {
            SalesPerson current = personByName.get(seller[i]);
            int money = amount[i] * 100;

            while (current != null && money > 0) {
                int toParent = money / 10; // 10% 부모로 올리기
                current.profit += money - toParent;
                current = current.parent;
                money = toParent;
            }
        }

        int[] result = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            result[i] = personByName.get(enroll[i]).profit;
        }
        return result;
    }

    private static final class SalesPerson {
        SalesPerson parent;
        List<SalesPerson> children = new ArrayList<>();
        int profit;
    }
}
