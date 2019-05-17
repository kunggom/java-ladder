package ladder.model;

import ladder.model.Coin.Coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ladder {
    private final List<Level> levels;
    private final List<Integer> mappingTable;

    public Ladder(int width, int height, Coin possibility) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException();
        }
        final List<Level> levels = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            levels.add(new Level(width - 1, possibility));
        }
        this.levels = Collections.unmodifiableList(levels);
        mappingTable = processMappingTable(width);
    }

    public List<Player> apply(List<Player> players) {
        if (players.size() != getWidth()) {
            throw new IllegalArgumentException();
        }
        final List<String> rewards = players.stream().map(x -> x.getReward()).collect(Collectors.toList());
        final List<Player> result = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            result.add(new Player(players.get(i).getName(), rewards.get(mappingTable.indexOf(i))));
        }
        return Collections.unmodifiableList(result);
    }

    private List<Integer> processMappingTable(int width) {
        final List<Integer> table = IntStream.range(0, width).boxed().collect(Collectors.toList());
        levels.forEach(level -> level.getLines().forEach(line -> Collections.swap(table, line, line + 1)));
        return Collections.unmodifiableList(table);
    }

    public int getWidth() {
        return mappingTable.size();
    }

    public List<Level> getLevels() {
        return levels;
    }
}