/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void searchReturnsPlayerWhenPlayerExists() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }

    @Test
    public void searchReturnsNullWhenPlayerDoesNotExist() {
        assertNull(stats.search("Semenks"));
    }

    @Test
    public void allPlayersOfTeamAreReturnedAndNoExtras() {
        List<Player> team = stats.team("EDM");
        List<String> names = new ArrayList<>();
        for (Player player : team) {
            names.add(player.getName());
        }
        assertTrue(names.contains("Semenko"));
        assertTrue(names.contains("Kurri"));
        assertTrue(names.contains("Gretzky"));
        assertFalse(names.contains("Yzerman"));
    }

    @Test
    public void topScorersReturnedInOrder() {
        List<Player> topScorers = stats.topScorers(2);
        assertEquals(3, topScorers.size());
        assertEquals("Gretzky", topScorers.get(0).getName());
        assertEquals("Lemieux", topScorers.get(1).getName());
        assertEquals("Yzerman", topScorers.get(2).getName());
    }
}
