package com.isthisteamisthis.lalalia.common.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Service
public class SaveWAVFileService {
    private final String perfectScoreDirectory = "C:\\september-ai\\db\\perfect-score";
    private final String voiceRangeDirectory = "C:\\september-ai\\db\\voice-range";
    private final String aiSongDirectory = "C:\\september-ai\\db\\ai-songs";
    private final String originalSongDirectory = "C:\\september-ai\\db\\original-songs";

    private final String coverImgDirectory = "C:\\september-ai\\db\\cover-img";

    public String savePerfectScoreFile(MultipartFile perfectScoreWav) throws IOException {
        File directory = new File(perfectScoreDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID().toString() + ".wav";

        Path filePath = Paths.get(perfectScoreDirectory, uniqueFileName);

        try (InputStream inputStream = perfectScoreWav.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return perfectScoreDirectory + "\\" + uniqueFileName;
    }

    public String saveVoiceRangeFile(MultipartFile voiceRangeWav) throws IOException {  //test 용
        File directory = new File(voiceRangeDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID().toString() + ".wav";

        Path filePath = Paths.get(voiceRangeDirectory, uniqueFileName);

        try (InputStream inputStream = voiceRangeWav.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return voiceRangeDirectory + "\\" + uniqueFileName;
    }

    public String saveAiOriginalFile(MultipartFile file) throws IOException {  //test 용
        File directory = new File(originalSongDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID().toString() + ".wav";
//        String uniqueFileName = aiSongWav.getOriginalFilename() + ".wav";

        Path filePath = Paths.get(originalSongDirectory, uniqueFileName);

        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return originalSongDirectory + "\\" + uniqueFileName;
    }

    public String saveAiSongFile(MultipartFile aiSongWav) throws IOException {  //test 용
        File directory = new File(aiSongDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID().toString() + ".wav";
//        String uniqueFileName = aiSongWav.getOriginalFilename() + ".wav";

        Path filePath = Paths.get(aiSongDirectory, uniqueFileName);

        try (InputStream inputStream = aiSongWav.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

        return aiSongDirectory + "\\" + uniqueFileName;
    }

    public String saveCoverImg(MultipartFile img) throws IOException {

        File directory = new File(coverImgDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String uniqueFileName = UUID.randomUUID().toString() + ".png";

        Path filePath = Paths.get(coverImgDirectory, uniqueFileName);


        return coverImgDirectory + "\\" + uniqueFileName;
    }
}
