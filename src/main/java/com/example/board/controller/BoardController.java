package com.example.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/B2/board")
public class BoardController {

    @PostMapping
    public ResponseEntity postBoard(@RequestParam("email") String email,
                            @RequestParam("name") String name,
                            @RequestParam("phone") String phone){

        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("name", name);
        map.put("phone", phone);

        return new ResponseEntity<>(map, HttpStatus.CREATED);


    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id")long boardId){
        System.out.println("# memberId: "+boardId);
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity getBoards(){
        System.out.println("# Get Boards");

        return new ResponseEntity(HttpStatus.OK);
    }
}
