import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * Created by daniel on 15/03/17.
 */
public class Test {
    public static void main(String[] args) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/api/user/profile_picture?userId=2");

        File f = new File("/home/daniel/Imágenes/TLoU.jpg");
        FileBody uploadFilePart = new FileBody(f);
        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart("image", uploadFilePart);
        httpPost.setEntity(reqEntity);

        try {
            HttpResponse response = httpclient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


