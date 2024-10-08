package com.elearningwebappapi.fypapi;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.List;
import java.lang.Long;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping(path="/api/music")
public class MusicController {
    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private AwsAdapter awsAdapter;

    @GetMapping(value = {"", "/"})
    public ResponseEntity index() {
        return ResponseEntity.ok(musicRepository.findAll());
    }

    @PostMapping(value = "/upload")
    public ResponseEntity uploadMusic(@RequestPart("uid") String uidStr, @RequestPart("auth") String auth, @RequestPart("file") MultipartFile file) {
        
        long uidLong = Long.parseLong(uidStr);
        String filename = file.getOriginalFilename();
        String musicUrl = "";

        if (file.getContentType().matches("audio/(.*)")) {    // check file type
            try {
                // upload file to aws and database
                URL url = awsAdapter.storeObjectInS3(file, filename, file.getContentType());
                musicUrl = url.toString();
                return ResponseEntity.ok(musicRepository.save(new Music(uidLong, filename, auth, musicUrl)));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("upload failed by catching error: " + e);
            }
        } else {
            return ResponseEntity.badRequest().body("upload failed by type invaild, by receiving " + file.getContentType() + " type.");
        }
    }
}



