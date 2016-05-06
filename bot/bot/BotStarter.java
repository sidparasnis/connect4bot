// // Copyright 2015 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package bot;
import java.util.Random;
import bot.Field;

/**
 * BotStarter class
 *
 * Magic happens here. You should edit this file, or more specifically
 * the makeTurn() method to make your bot do more than random moves.
 *
 * @author Jim van Eeden <jim@starapple.nl>, Joost de Meij <joost@starapple.nl>
 */

public class BotStarter {
     Field field;
     int[] ratings;

     /**
      * Makes a turn. Edit this method to make your bot smarter.
      *
      * @return The column where the turn was made.
      */
     public int makeTurn(Field field) {
        // Field possibilities
        this.ratings = new int[7];
        for (int i=0; i<7; i++){
          this.ratings[i] = 0;
        }
        moveWithDepth(5, field);
        int move = new Random().nextInt(7);
        for (int i=0; i<7; i++){
          if(this.ratings[i] > this.ratings[move]){
            move = i;
          }
        }
        return move;
     }

     public void moveWithDepth(int d, Field field){
       Field[] p1;
       Field[] p2 = new Field[1];
       p2[0] = field;
       for(int i = 1; i<=d; i++){
         p1 = oneMoves(p2);
         p2 = twoMoves(p1);
       }
     }

     public Field[] oneMoves(Field[] field){
        int numField = field.length*7;
        Field[] moves = new Field[numField];
        for(int i = 0; i<field.length; i++){
          for(int j = 0; j < 7; j++){
            moves[i*j] = new Field(7,6);
            moves[i*j].parseFromString(field[i].toString());
            if(moves[i*j].addDisc(j, 1)==false){
              this.ratings[j] = -1000;
            }
            else{
              //check how many c4 abd update rating
            }
          }
        }

        return moves;
     }

     public Field[] twoMoves(Field[] field){
        int numField = field.length*7;
        Field[] moves = new Field[numField];
        for(int i = 0; i<field.length; i++){
          for(int j = 0; j < 7; j++){
            moves[i*j] = new Field(7,6);
            moves[i*j].parseFromString(field[i].toString());
            if(moves[i*j].addDisc(j, 2)==false){
              this.ratings[j] = -1000;
            }
            else{
              //check how many c4 abd update rating
            }
          }
        }

        return moves;
     }


    //  public int checkvert(){
    //     int result = 0;
    //     int[][] mBoard = this.field.getBoard();
    //     for(int i = 0; i<7; i++){
    //       for(int j = 0; j<6; j++){
    //         if(mBoard[i][j]==2){
    //           for(int c = 0; c<4; c++){
    //             if(mBoard[i+c ][j]==2){
    //               result++;
    //             }
    //           }
    //         }
    //       }
    //     }
    //     return result;
    //  }
     //
    //  public int checkhorz(){
    //     int result = 0;
    //     int[][] mBoard = this.field.getBoard();
    //     for(int i = 0; i<7-4; i++){
    //       for(int j = 0; j<6-4; j++){
    //         if(mBoard[i][j]==2){
    //           for(int c = 0; c<4; c++){
    //             if(mBoard[c][j+c]==2){
    //               result++;
    //             }
    //           }
    //         }
    //       }
    //     }
    //     return result;
    //  }
     //
    //  public int checkdiag

 	public static void main(String[] args) {
 		BotParser parser = new BotParser(new BotStarter());
 		parser.run();
 	}

 }
