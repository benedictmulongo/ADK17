/* Labb 2 i DD1352 Algoritmer, datastrukturer och komplexitet    */
/* Se labbanvisning under kurswebben https://www.kth.se/social/course/DD1352 */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
package com.company;
import java.util.LinkedList;
import java.util.List;

public class ClosestWords
{
  Array C = new Array(50);
  int [][] D = C.get_matrix();
  LinkedList<String> closestWords = null;

  int closestDistance = 1000;
  String previous = " ";
  String current = " ";
  int nb_of_simularity;

  int partDist(String w1, String w2, int w1len, int w2len)
  {
    // end_of_search is the min length between the current word and the previuos word
    // in ordlista in that point of iteration
    int end_of_search = Math.min(previous.length(), w2len);
    int compute_from_index = 0;
    for(int j = 0; j < end_of_search; j++)
    {
      //count how many word do the current word and previous word
      // have in commun
      if(previous.charAt(j) == w2.charAt(j))
      {
        compute_from_index++;
      }
      else
        break;
    }
    int cost;
    for(int i = 1; i <= w1.length(); i++)
    {
      for(int k = compute_from_index + 1 ; k <= w2.length(); k++)
      {
        cost = w1.charAt(i-1) == w2.charAt(k-1)? 0 : 1;
        D[i][k] = Math.min(D[i-1][k] + 1,Math.min(D[i][k-1] + 1, D[i-1][k-1] + cost));
      }

    }

    return D[w1len][w2len];
  }

  int Distance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }

  public ClosestWords(String w, List<String> wordList)
  {

/*    for (String s : wordList)
    {
      int dist = Distance(w, s);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      previous = s;

      if (dist < closestDistance || closestDistance == 1000) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }*/

    for (String s : wordList)
    {
      if (Math.abs(w.length() - s.length()) <= closestDistance)
      {
        int dist = Distance(w, s);
        previous = s;
        // System.out.println("d(" + w + "," + s + ")=" + dist);
        if (dist < closestDistance || closestDistance == 1000)
        {
          closestDistance = dist;
          closestWords = new LinkedList<String>();
          closestWords.add(s);
        }
        else if (dist == closestDistance)
          closestWords.add(s);
      }
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
