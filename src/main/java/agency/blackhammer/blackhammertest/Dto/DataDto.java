package agency.blackhammer.blackhammertest.Dto;

import org.springframework.stereotype.Component;

@Component
public class DataDto {

    private String letter;
    private int count;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
