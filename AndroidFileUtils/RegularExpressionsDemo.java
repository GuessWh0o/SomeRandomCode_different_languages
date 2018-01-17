import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegularExpressionsDemo {

    RegularExpressionsDemo() {
        patternMatchersDemo();
    }

    private void quantifiersDemo() {
        String demoString = "abbbbbbbcDeeeeeeffff35y99zaaaaaaaabcDeeeeeGhiiy99995532";
        System.out.println(demoString.replaceAll("^ab{7}", "XX"));  //Replacing ab and 7 b after with X
        System.out.println(demoString.replaceAll("^ab{3,7}", "XX"));  //Replacing ab and 3-7 b after with X

        System.out.println(demoString.replaceAll("^ab+", "XX"));  //Replacing ab and no matter how many b after with X

        System.out.println(demoString.replaceAll("^ab*", "XX"));  //Replacing ab and no matter what letter and its repetitions after with X

        System.out.println(demoString.replaceAll("D+e*f", "YY"));  //Replacing D followed by any number of e ending with f with X
    }

    private void patternMatchersDemo() {
        StringBuilder htmlText = new StringBuilder("<h1>My Heading</h1>");
        htmlText.append("<h2>Sub-heading</h2>");
        htmlText.append("<p>This is a paragraph about something.</p>");
        htmlText.append("<p>This is another paragraph about something else.</p>");
        htmlText.append("<h2>Summary</h2>");
        htmlText.append("<p>Here is the summary.</p>");

        //String patterString = ".*<h2>.*";       //Compare anything before and after <h2> tag
        String patterString = "<h2>";       //Finding occurrences of <h2> in text
        Pattern pattern = Pattern.compile(patterString);
        Matcher matcher = pattern.matcher(htmlText);
        System.out.println(matcher.matches());
        matcher.reset();

        int count = 0;
        while(matcher.find()) {
            count++;
            System.out.println("Occurrence " + count + " : " + matcher.start() + " to " + matcher.end());
        }

        String patternStringHtmlTextGroup = "(<h2>)";
        Pattern groupPattern = Pattern.compile(patternStringHtmlTextGroup);
        Matcher groupMatcher = groupPattern.matcher(htmlText);
        System.out.println(groupMatcher.matches());

        groupMatcher.reset();

        while(groupMatcher.find()) {
            System.out.println("Occurrence " + groupMatcher.group(1));
        }
    }

    private void replaceAllDemo() {
        String str1 = "-Hello, Word. -Hello";
        System.out.println(str1.replaceAll("Hello", "Bye"));  //Replacing each "Hello" with "Bye"

        String demoString = "abcDeef35y99zabcDeeGhiiy99z";
        System.out.println(demoString.matches("^abcDee"));  //FALSE//Returns true if matches whole string, not just a part of it
        System.out.println(demoString.matches("^abcDeef35abcDeeGhiiy99z"));  //TRUE//Returns true if matches whole string, not just a part of it

        System.out.println(demoString.replaceAll(".", "R"));  //Replacing each letter with "R"

        System.out.println(demoString.replaceAll("^abcDee", "YYY"));  //Replacing "abcDee" only on start with "Y"

        System.out.println(demoString.replaceAll("y99z$", "THE END"));  //Replacing "y99z" only on the end with "THE END"

        System.out.println(demoString.replaceAll("[aei]", "X"));  //Replacing all occurrences of a,e,i with "X"

        System.out.println(demoString.replaceAll("[aei][bf]", "Z"));  //Replacing all occurrences of a,e,i followed by either "b" or "f" with "Z"
    }

    private void boundaryMatchesDemo() {
        String guessWho = "guessWh0o";
        System.out.println(guessWho.replaceAll("[Gg]uessWh0o", "GuessWh0o"));  //Replacing l/u-case guessWh0o -> GuessWh0o

        String demoString = "abcDeef35y99zabcDeeGhiiy99z";

        System.out.println(demoString.replaceAll("[^DG]", "X"));  //Replacing all characters beside D and G with X

        System.out.println(demoString.replaceAll("[a-fA-F3-9]", "Z"));  //Replacing all characters between a-f, A-F and 3-9 with Z

        System.out.println(demoString.replaceAll("(?i)[a-f3-9]", "Z"));  //Replacing all characters between a-f and 3-9 with Z(no case sensitivity)

        System.out.println(demoString.replaceAll("[0-9]", "Y"));  //Replacing all numbers with Y
        System.out.println(demoString.replaceAll("\\d", "Y"));  //Replacing all numbers with Y

        System.out.println(demoString.replaceAll("\\D", "E"));  //Replacing all letters with E

        String strWithWhiteSpace = "String that has blanks\t a tab and a newLine\n";
        System.out.println(strWithWhiteSpace.replaceAll("\\s", ""));  //Removing all the whiteSpaces tabs and newLines

        System.out.println(strWithWhiteSpace.replaceAll("\\w", "X"));  //Replacing all the characters except tabs newLines spaces with "X"

        System.out.println(strWithWhiteSpace.replaceAll("\\b", "X"));  //Folds each word in the String with "X"
    }
}
