/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ajnarhi
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

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

    @Test
    public void searchWorks() {
        
        Player player=stats.search("Kurri");
       assertEquals(player.getName(),"Kurri");
       assertEquals(player.getTeam(), "EDM");
           assertEquals(player.getGoals(), 37);
           assertEquals(player.getAssists(), 53);

    }
    
    @Test
    public void searchReturnsNullIfPlayerNotOnTheList(){
        Player player=stats.search("Kuri");
        assertEquals(player, null);
    }
    
    @Test
    public void teamReturnsTeam(){
        List<Player> playersOfPit=stats.team("PIT");
        
        assertEquals(playersOfPit.get(0).getName(),"Lemieux");
        assertEquals(playersOfPit.get(0).getTeam(),"PIT");
        assertEquals(playersOfPit.get(0).getGoals(),45);
        assertEquals(playersOfPit.get(0).getAssists(),54);
    }
    
    @Test
    public void topScorersReturnsWHAT(){
        List<Player> topScorers=stats.topScorers(1);
        
        assertEquals(topScorers.get(0).getName(),"Gretzky");
        assertEquals(topScorers.get(0).getTeam(),"EDM");
        assertEquals(topScorers.get(0).getGoals(),35);
        assertEquals(topScorers.get(0).getAssists(),89);
        
        
    }
}
