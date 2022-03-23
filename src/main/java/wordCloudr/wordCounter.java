package wordCloudr;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import java.awt.*;
import java.util.*;
import java.util.List;

public class wordCounter {

    Map<String,Integer> wordMap = new HashMap<>();
    boolean caseSentive = false;
    String inputString;

    /**
     * method to set the input string.
     * @param s input String
     */
    public void setInput(String s){
        if(!caseSentive)
            s = s.toLowerCase();
        s=s.replaceAll("[^\\w\\s]", "");
        inputString=s;
    }

    /**
     * method used to filter out specified strings from the input string.
     * can be used to remove multiple words. Examples on how to specify words to be removed:
     *
     * removing single word: "-the"
     *  removes all instances of the word "the"
     * removing multiple words: "-the -is -java doc -a"
     *  removes all instances of the words "the", "is", "java doc", and "a"
     *
     * @param remove strings to remove
     */
    public void filterOut(String remove){
        //remove = remove.replaceAll("\\s","");
        if(!caseSentive)
            remove = remove.toLowerCase();
        System.out.println(remove);
        String[] words = remove.split("-+");
        for(String word:words){
            System.out.println(word);
            if(word.equalsIgnoreCase("#")){
                inputString = inputString.replaceAll("\\d","");
            }
            String regex = String.format("(%s)",word);
            inputString = inputString.replaceAll(regex, "");
        }
    }

    /**
     * method used for getting the input string.
     * @return input string
     */
    public String getInput(){
        return inputString;
    }

    public void countUserFrequency(){

    }


    /**
     *
     * @param s string whose words will be tallied.
     * @return a Map<String,Integer> the String is the counted word, and the integer is the tally of the word
     */
   public Map<String,Integer> countWords(String s){
        //System.out.println("Input:"+s);
        //System.out.println();
       if(caseSentive){
           s = s.toLowerCase();
       }
       inputString = s.replaceAll("[^\\w\\s]", "");
      /*  String[] words = s.split("\\s+");

        for (int i = 0; i < words.length; i++) {

            if(wordMap.containsKey(words[i])){
                wordMap.put(words[i],wordMap.get(words[i])+1);
            }
            else{
                wordMap.put(words[i],1);
            }
        }*/
        return wordMap;
    }

    /**
     * method used to set case sensitivity of tallying.
     * @param b case sensitivity true or false
     */
    public void isCaseSentive(boolean b){
       caseSentive = b;
    }


    public void createWordCloud(){
        List<WordFrequency> wordFrequencies = new LinkedList<>();

        final Dimension dimension = new Dimension(600, 600);
        final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.RECTANGLE);

        wordCounter word_counter = new wordCounter();
        Map<String,Integer> map = wordMap;
        //System.out.println("[Word]|[frequency]");
        for (String key:map.keySet()) {
            int value = map.get(key).intValue();
            //System.out.println(key.toString()+"|"+value);
            WordFrequency element = new WordFrequency(key, map.get(key).intValue());
            wordFrequencies.add(element);
        }



        //wordFrequencies = map;
        wordCloud.setPadding(0);
        wordCloud.setBackground(new RectangleBackground(dimension));
        wordCloud.setColorPalette(new ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE));
        wordCloud.setFontScalar(new LinearFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("wordcloud_rectangle.png");
    }









    public static void main(String[] args) {
      /*  wordCounter wc = new wordCounter();
        wc.countWords("This is a sample sentence. remove this sentence. testing 1 2 3. @Xyr. ");
        wc.filterOut("this, is, remove, [numbers]");
        wc.createWordCloud();*/

        wordCounter wc = new wordCounter();

        wc.setInput("This is a sample sentence. remove this sentence. testing 1 2 3. @Xyr. word testing counter");
        wc.filterOut("-word -Testing -#");
        //wc.filterOut("");
        System.out.println(wc.getInput());


    }


}
