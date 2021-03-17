package agency.blackhammer.blackhammertest.Controllers;

import agency.blackhammer.blackhammertest.Dto.DataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/wordanalyzer")
public class BlachHammerController {

    private final DataDto dataDto;

    @Autowired
    public BlachHammerController(DataDto dataDto) {
        this.dataDto = dataDto;
    }

    @GetMapping("/analyze{word}")
    public DataDto wordanalyzer(@RequestParam String word) {
        word = word.replaceAll("[^a-zA-Zа-яёА-ЯЁ]", "").toLowerCase(); //в задании учитываются
        // только буквы поэтому отделяю ненужные символы
        if (word.isBlank()){
            throw new RuntimeException("Параметр word не должен быть null или пустой строкой");
        }

            String[] userWordSplit = word.split("");
        Map<String, Integer> map = new LinkedHashMap<>(); //LinkedHashMap использую для гарантированного
        // обхода Map в правильном порядке через итератор
        for (int i = 0; i < userWordSplit.length; i++) {
            if (map.containsKey(userWordSplit[i])) {
                map.computeIfPresent(userWordSplit[i], (k, v) -> v + 1);
            } else {
                map.put(userWordSplit[i], 1);
            }
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (stringIntegerEntry.getValue() >= dataDto.getCount()) {
                dataDto.setCount(stringIntegerEntry.getValue());
                dataDto.setLetter(stringIntegerEntry.getKey());
            }
        }

        return dataDto;
    }


}

