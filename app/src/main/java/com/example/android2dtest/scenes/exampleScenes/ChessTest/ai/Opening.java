package com.example.android2dtest.scenes.exampleScenes.ChessTest.ai;

import java.util.ArrayList;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.board.GameBoard;
import com.example.android2dtest.scenes.exampleScenes.ChessTest.pieces.baseClasses.BasePiece;
import android.graphics.Point;
import java.util.Arrays;
import java.util.ArrayList;

public class Opening {

    String name;
    ArrayList<BasePiece[][]> positions = new ArrayList<>();

    public Opening(String name, String moves){
        this.name = name;

        String s = "";
        int count = 0;
        for (char c : moves.toCharArray()) {
             s += c;
             if(c==',')
                count++;
             if(count==64){
                 BasePiece[][] pos = new BasePiece[8][8];
                 GameBoard.setBoardByString(pos,s);
                 positions.add(pos);
                 count=0;
                 s = "";
             }

        }


    }

    public BasePiece[][] tryGetPositionIdx(BasePiece[][] board){
        int count = 1;
        for (BasePiece[][] position : positions) {
            //MyDebug.log("",""+count);
            if(Arrays.deepEquals(position, board))
                if(count > positions.size()-1)
                    return null;
                else
                    return GameBoard.cloneBoard(positions.get(count));
            count++;

        }
        return null;
    }
}
