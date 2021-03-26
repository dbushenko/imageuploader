package aws.imgupload.imgupload.api;

import aws.imgupload.imgupload.application.ImageApp;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImagesController {
    private final ImageApp imageApp;

    public ImagesController(final ImageApp imageApp) {
        this.imageApp = imageApp;
    }

    @GetMapping(value="/{name}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] getImage(@PathVariable String name) throws IOException {
        return imageApp.findImageByName(name);
    }

    @GetMapping(value="/random", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody
    byte[] random() throws IOException {
        return imageApp.findRandomImage();
    }

    @PostMapping("/")
    public void postImage(@RequestParam MultipartFile file) throws IOException {
        imageApp.uploadImage(file.getOriginalFilename(), file.getBytes());
    }

}
