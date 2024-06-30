package org.example.queen.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/queens")
public class QueenController {



    @GetMapping("/solve")
    public List<List<Integer>> queens(@RequestParam int row, @RequestParam int col) {
        int n = 8;
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> board = new ArrayList<>(Collections.nCopies(n, -1));

        board.set(row, col);

        func(board, results, 0, row, col);

        return results;
    }

    private void func(List<Integer> board, List<List<Integer>> results, int currentRow, int fixedRow, int fixedCol) {
        int n = board.size();

        if (currentRow == n) {
            results.add(new ArrayList<>(board));
            return;
        }

        if (currentRow == fixedRow) {
            func(board, results, currentRow + 1, fixedRow, fixedCol);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValid(board, currentRow, col, fixedRow, fixedCol)) {
                board.set(currentRow, col);
                func(board, results, currentRow + 1, fixedRow, fixedCol);
                board.set(currentRow, -1);
            }
        }
    }

    private boolean isValid(List<Integer> board, int row, int col, int fixedRow, int fixedCol) {
        for (int i = 0; i < row; i++) {
            int queenCol = board.get(i);
            if (queenCol == col || Math.abs(queenCol - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return col != fixedCol && Math.abs(fixedCol - col) != Math.abs(fixedRow - row);
    }
}
