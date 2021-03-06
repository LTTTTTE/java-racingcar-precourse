package view;

import java.util.*;

public class Input {
    private static final int MAX_NAME_LENGTH = 5;

    private Scanner scanner = new Scanner(System.in);
    private String input;

    public Input() {
    }

    private void setInput() {
        input = scanner.nextLine();
    }

    public List<String> asCarNames() {
        Output.showNamesInputForm();
        setInput();
        if (isEndWithComma(input) || isDoubleComma(input)) {
            return new Input().asCarNames();
        }
        List<String> names = splitAsComma(input);
        if (isNamesLengthUnderMax(names) && isNoBlankNames(names)) {
            return names;
        }
        return new Input().asCarNames();
    }

    public int asRounds() {
        Output.showRoundsInputForm();
        setInput();
        if (isPositiveInteger(input)) {
            return Integer.parseInt(input);
        }
        return new Input().asRounds();
    }

    public List<String> splitAsComma(String inputs) {
        return Arrays.asList(inputs.split(","));
    }

    private boolean isNameLengthUnderMax(String name) {
        return name.length() <= MAX_NAME_LENGTH;
    }

    public boolean isNamesLengthUnderMax(List<String> names) {
        return names.stream().allMatch(this::isNameLengthUnderMax);
    }

    public boolean isNoBlankNames(List<String> names) {
        return names.stream().allMatch(x -> !x.equals(" ") && x.length() != 0)
                && names.size() != 0;
    }

    private boolean isEndWithComma(String input) {
        return input.endsWith(",");
    }

    private boolean isDoubleComma(String input) {
        return input.contains(",,");
    }

    public boolean isPositiveInteger(String times) {
        try {
            return Integer.parseInt(times) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
