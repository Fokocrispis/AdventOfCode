package day2;

import java.util.ArrayList;
import java.util.List;

public class Report {
	
	private final boolean VARIANT;

    private List<Integer> levels = new ArrayList<>();
    private List<Report> variants = new ArrayList<>();

    public Report() {
    	VARIANT = false;
    }

    public Report(List<Integer> levels) {
        this.levels = new ArrayList<>(levels);
        VARIANT=true;
    }

	public boolean isValidWithDampener() {

		if (variants != null) {
			for (Report variant : variants) {
				if (isValid(variant.getLevels())) {
					return true;
				}
			}
		}
		return false;
	}

    private void addVariants() {
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> variantLevels = new ArrayList<>();
            for (int j = 0; j < levels.size(); j++) {
                if (i != j) {
                    variantLevels.add(levels.get(j));
                }
            }
            variants.add(new Report(variantLevels));
        }
    }

    public boolean isValid(List<Integer> levels) {
        Boolean isIncreasing = null;
        int temp = 0;

        for (int i = 0; i < levels.size() - 1; i++) {
            int differential = levels.get(i + 1) - levels.get(i);

            if (differential == 0) {
                temp++;
            }

            if (Math.abs(differential) >= 4) {
                temp++;
            }

            if (isIncreasing == null) {
                isIncreasing = (differential > 0);
            } else if ((differential > 0) != isIncreasing) {
                temp++;
            }
        }
        return temp == 0;
    }

    public boolean isValid() {
        Boolean isIncreasing = null;
        int temp = 0;

        for (int i = 0; i < levels.size() - 1; i++) {
            int differential = levels.get(i + 1) - levels.get(i);

            if (differential == 0) {
                temp++;
            }

            if (Math.abs(differential) >= 4) {
                temp++;
            }

            if (isIncreasing == null) {
                isIncreasing = (differential > 0);
            } else if ((differential > 0) != isIncreasing) {
                temp++;
            }
        }
        
        if(temp!=0) {
        	addVariants();	
        }

        return temp == 0;
    }

    public void add(int level) {
        levels.add(level);
    }

    private int size() {
        return levels.size();
    }

    private List<Integer> getLevels() {
        return levels;
    }
    
    public boolean isVariant() {
    	return VARIANT;
    }

    public void print() {
        for (Integer i : levels) {
            System.out.print(i + " ");
        }
        System.out.print("Is valid: " + isValid() + "\n");
    }
}

