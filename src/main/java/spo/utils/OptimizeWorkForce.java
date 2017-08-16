package spo.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import spo.domain.Junior;

public class OptimizeWorkForce {
	private static final int MINIMUN_ACCEPTABLE_DIFFERENCE = -1;
	private static final int MINUS_ONE_JUNIOR = -1;
	private static HashMap<String, Integer> optimizationResult = new HashMap<String, Integer>();
	private static final String SENIOR_KEY = "Senior";
	private static final String JUNIOR_KEY = "Junior";

	public static Map optimize(int capacity, int seniorWorkForce, int juniorWorkForce) {
		if (capacity > seniorWorkForce) {
			optimizationResult.put(SENIOR_KEY, capacity / seniorWorkForce);

			optimizationResult.put(JUNIOR_KEY, 0);
			int restToFill = capacity % seniorWorkForce;
			calculateRestToOptimize(restToFill, seniorWorkForce, juniorWorkForce, capacity);
		} else {
			optimizationResult.put(SENIOR_KEY, 1);

			optimizationResult.put(JUNIOR_KEY, 0);
		}
		return optimizationResult;
	}

	private static void calculateRestToOptimize(int restToFill, int seniorWorkForce, int juniorWorkForce,
			int totalOfCapacity) {
		int offSet = restToFill - juniorWorkForce;

		if (restToFill != 0) {
			if (restToFill == juniorWorkForce) {
				optimizationResult.put(JUNIOR_KEY, optimizationResult.get(JUNIOR_KEY) + 1);

			}
			if (restToFill > juniorWorkForce) {
				optimizationResult.put(JUNIOR_KEY, optimizationResult.get(JUNIOR_KEY) + (restToFill / juniorWorkForce));
				restToFill = restToFill % juniorWorkForce;
				calculateRestToOptimize(restToFill, seniorWorkForce, juniorWorkForce, totalOfCapacity);
			}

			if (offSet < MINIMUN_ACCEPTABLE_DIFFERENCE) {
				if (restToFill < juniorWorkForce) {
					if (optimizationResult.get(SENIOR_KEY) > 1) {
						optimizationResult.put(SENIOR_KEY, optimizationResult.get(SENIOR_KEY) - 1);
						calculateRestToOptimize(restToFill + seniorWorkForce, seniorWorkForce, juniorWorkForce,
								totalOfCapacity);
					} else {
						if (addSeniorAndRetrieveTotal(juniorWorkForce, seniorWorkForce,
								totalOfCapacity) < addJuniorAndRetrieveTotal(juniorWorkForce, seniorWorkForce,
										totalOfCapacity)) {
							optimizationResult.put(SENIOR_KEY, optimizationResult.get(SENIOR_KEY) + 1);
							if (optimizationResult.get(JUNIOR_KEY) > 0) {
								optimizationResult.put(JUNIOR_KEY, optimizationResult.get(JUNIOR_KEY) - 1);
							}
						} else {
							optimizationResult.put(JUNIOR_KEY, optimizationResult.get(JUNIOR_KEY) + 1);
						}
					}
				}
			} else if (offSet == MINIMUN_ACCEPTABLE_DIFFERENCE) {
				optimizationResult.put(JUNIOR_KEY, optimizationResult.get(JUNIOR_KEY) + 1);
			}
		}
	}

	private static int addSeniorAndRetrieveTotal(int juniorWorkForce, int seniorWorkForce, int totalOfCapacity) {
		int totalInMapWithSeniorAdded = sumInMap(optimizationResult, juniorWorkForce, seniorWorkForce, 1,
				optimizationResult.get(JUNIOR_KEY) == 0 ? 0 : MINUS_ONE_JUNIOR);
		return totalInMapWithSeniorAdded - totalOfCapacity;
	}

	private static int addJuniorAndRetrieveTotal(int juniorWorkForce, int seniorWorkForce, int totalOfCapacity) {
		int totalInMapWhitJuniorAdded = sumInMap(optimizationResult, juniorWorkForce, seniorWorkForce, 0, 1);
		return totalInMapWhitJuniorAdded - totalOfCapacity;
	}

	private static int sumInMap(Map<String, Integer> optimizationMap, int juniorWorkForce, int seniorWorkForce,
			int incrementToOptimizeSenior, int incrementToOptimizeJunior) {

		int sumSenior = ((optimizationMap.get(SENIOR_KEY) + incrementToOptimizeSenior) * seniorWorkForce);

		int sumJunior = ((optimizationMap.get(JUNIOR_KEY) + incrementToOptimizeJunior) * juniorWorkForce);

		return sumJunior + sumSenior;
	}

	public static void main(String args[]) {
		// int[] i = { 2 };
		int a = 10;
		int b = 6;
		System.out.println("Senior | Junior | Percent Overload");
		int diffToCero = 0;
		int diffToOne = 0;
		int diffToTwo = 0;
		int diffToThree = 0;
		int diffToFour = 0;
		int diffToFive = 0;
		int diffMoreThanSix = 0;
		for (int i = 1; i < 100; i++) {
			int currentCapacity = i;
			OptimizeWorkForce.optimize(currentCapacity, a, b);

			int sumSenior = optimizationResult.get(SENIOR_KEY) * a;
			int sumJunior = optimizationResult.get(JUNIOR_KEY) * b;
			BigDecimal dividendo = new BigDecimal((sumJunior + sumSenior) - currentCapacity);
			BigDecimal divisor = new BigDecimal(currentCapacity);

			int diff = (sumJunior + sumSenior) - currentCapacity;
			BigDecimal roundPercent = dividendo.divide(divisor, 2, BigDecimal.ROUND_HALF_EVEN)
					.multiply(new BigDecimal(100));

			System.out.println("--------------------------------");
			System.out.println(optimizationResult.get(SENIOR_KEY) + "      |    " + optimizationResult.get(JUNIOR_KEY)
					+ "    |    " + roundPercent + " | " + diff + " | " + (sumJunior + sumSenior) + " | "
					+ currentCapacity);
			System.out.println("--------------------------------");

			switch (diff) {
			case 0:
				diffToCero++;
				break;
			case 1:
				diffToOne++;
				break;
			case 2:
				diffToTwo++;
				break;
			case 3:
				diffToThree++;
				break;
			case 4:
				diffToFour++;
				break;
			case 5:
				diffToFive++;
				break;

			default:
				diffMoreThanSix++;
				break;
			}

			optimizationResult.clear();
		}
		System.out.println(diffToCero);
		System.out.println(diffToOne);
		System.out.println(diffToTwo);
		System.out.println(diffToThree);
		System.out.println(diffToFour);
		System.out.println(diffToFive);
		System.out.println(diffMoreThanSix);

	}
}
