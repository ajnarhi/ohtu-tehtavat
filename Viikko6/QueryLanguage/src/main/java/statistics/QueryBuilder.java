/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author ajnarhi
 */
public class QueryBuilder {

    Matcher querybuilder;

    public QueryBuilder() {
        this.querybuilder = new All();
    }

    public Matcher build() {
        return querybuilder;
    }

    public QueryBuilder playsIn(String team) {
        this.querybuilder = new And(querybuilder, new PlaysIn(team));
        return this;

    }
    
    

    public QueryBuilder hasAtLeast(int points, String category) {
        this.querybuilder = new And(querybuilder,new HasAtLeast(points,category));
        return this;

    }
    
    public QueryBuilder hasFewerThan(int points, String category) {
        this.querybuilder =new And(querybuilder,new HasFewerThan(points,category));
        return this;

    }
    
    public QueryBuilder oneOf(Matcher m1, Matcher m2){
        this.querybuilder=new Or(m1, m2);
        return this;
    }

 

  
}
