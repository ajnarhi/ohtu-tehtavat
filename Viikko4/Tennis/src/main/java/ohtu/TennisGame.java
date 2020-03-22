package ohtu;

public class TennisGame {
    
    private int score1 = 0;
    private int score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            score1 += 1;
        else
            score2 += 1;
    }

    public String player1Winning(){
       String score="";
        switch (score1)
            {
                case 0:
                        score = "Love-All";
                    break;
                case 1:
                        score = "Fifteen-All";
                    break;
                case 2:
                        score = "Thirty-All";
                    break;
                case 3:
                        score = "Forty-All";
                    break;
                default:
                        score = "Deuce";
                    break;
                
        
    }
        return score;
    }
    public String advantageOrWinning(){
        String score = "";
          int minusResult = score1-score2;
            if (minusResult==1) score ="Advantage player1";
            else if (minusResult ==-1) score ="Advantage player2";
            else if (minusResult>=2) score = "Win for player1";
            else score ="Win for player2";
    
    return score;
    }
    
        public String otherPointSituations(){
        String score = "";
        int tempScore=0;
          for (int i=1; i<3; i++)
            {
                if (i==1) tempScore = score1;
                else { score+="-"; tempScore = score2;}
                switch(tempScore)
                {
                    case 0:
                        score+="Love";
                        break;
                    case 1:
                        score+="Fifteen";
                        break;
                    case 2:
                        score+="Thirty";
                        break;
                    case 3:
                        score+="Forty";
                        break;
                }
            }
    
    return score;
    }

    public String getScore() {
        String score = "";
        
        
        if (score1==score2)
        {
            score=player1Winning(); //en tunne tenniksen pistelaskua niin paljon että osaisin sanoa mitä tässä tapahtuu.Nimentä sen mukaan mitä luulen tapahtuvan...
            
            }
        
        else if (score1>=4 || score2>=4)
        {
            score=advantageOrWinning();
        }
        else
        {
            score=otherPointSituations();
            
        }
        return score;
    }
}