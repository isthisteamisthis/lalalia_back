package com.isthisteamisthis.lalalia.common.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class SaveWAVFileService {

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    @Value("${app.storage-link}")
    private String storageLink;

    public String savePerfectScoreFile(MultipartFile perfectScoreWav) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(perfectScoreWav.getBytes());
        String name = UUID.randomUUID() + ".wav";
        Blob blob = bucket.create(name, content, perfectScoreWav.getContentType());

        return storageLink + name + "?alt=media";
    }

    public String saveAiOriginalFile(MultipartFile file) throws IOException {  //test 용
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(file.getBytes());
        String name = UUID.randomUUID() + ".wav";
        Blob blob = bucket.create(name, content, file.getContentType());

        return storageLink + name + "?alt=media";
    }

    public String saveAiSongFile(MultipartFile aiSongWav) throws IOException {  //test 용
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(aiSongWav.getBytes());
        String name = UUID.randomUUID() + ".wav";
        Blob blob = bucket.create(name, content, aiSongWav.getContentType());

        return storageLink + name + "?alt=media";
    }

    public String saveCoverImg(MultipartFile img) throws IOException, FirebaseAuthException {
        
        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);
        InputStream content = new ByteArrayInputStream(img.getBytes());
        String name = UUID.randomUUID().toString();
        Blob blob = bucket.create(name, content, img.getContentType());
        
        return storageLink + name + "?alt=media";

    }
}
